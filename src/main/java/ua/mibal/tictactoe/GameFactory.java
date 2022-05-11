/*
 * Copyright (c) 2022. http://t.me/mibal_ua
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ua.mibal.tictactoe;

import ua.mibal.tictactoe.component.*;
import ua.mibal.tictactoe.component.config.CommandLineArgumentParser;
import ua.mibal.tictactoe.component.console.CellNumberConverter;
import ua.mibal.tictactoe.component.console.ConsoleDataPrinter;
import ua.mibal.tictactoe.component.console.ConsoleGameOverHandler;
import ua.mibal.tictactoe.component.console.ConsoleUserInputReader;
import ua.mibal.tictactoe.component.console.keypad.DesktopNumericKeypadCellNumberConverter;
import ua.mibal.tictactoe.component.strategy.RandomComputerMoveStrategy;
import ua.mibal.tictactoe.component.swing.GameWindow;
import ua.mibal.tictactoe.model.game.Player;
import ua.mibal.tictactoe.model.config.PlayerType;
import ua.mibal.tictactoe.model.config.UserInterface;

import static ua.mibal.tictactoe.model.config.PlayerType.USER;
import static ua.mibal.tictactoe.model.game.Sign.O;
import static ua.mibal.tictactoe.model.game.Sign.X;
import static ua.mibal.tictactoe.model.config.UserInterface.GUI;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class GameFactory {

    private final PlayerType player1Type;

    private final PlayerType player2Type;

    private final UserInterface userInterface;

    public GameFactory(final String[] args) {

        final CommandLineArgumentParser.CommandLineArguments commandLineArguments =
                new CommandLineArgumentParser(args).parse();
        player1Type = commandLineArguments.getPlayer1Type();
        player2Type = commandLineArguments.getPlayer2Type();
        userInterface = commandLineArguments.getUserInterface();
    }

    public Game create() {
        final ComputerMoveStrategy[] strategies = {
                new RandomComputerMoveStrategy()
        };
        final DataPrinter dataPrinter;
        final UserInputReader userInputReader;
        final GameOverHandler gameOverHandler;

        if (userInterface == GUI) {
            final GameWindow gameWindow = new GameWindow();
            dataPrinter = gameWindow;
            userInputReader = gameWindow;
            gameOverHandler = gameWindow;
        } else {
            final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();
            dataPrinter = new ConsoleDataPrinter(cellNumberConverter);
            userInputReader = new ConsoleUserInputReader(cellNumberConverter, dataPrinter);
            gameOverHandler = new ConsoleGameOverHandler(dataPrinter);
        }
        final Player player1;
        if (player1Type == USER) {
            player1 = new Player(X, new UserMove(userInputReader, dataPrinter));
        } else {
            player1 = new Player(X, new ComputerMove(strategies));
        }
        final Player player2;
        if (player2Type == USER) {
            player2 = new Player(O, new UserMove(userInputReader, dataPrinter));
        } else {
            player2 = new Player(O, new ComputerMove(strategies));
        }
        final boolean canSecondPlayerMakeFirstMove = player1Type != player2Type;
        return new Game(
                dataPrinter,
                player1,
                player2,
                new WinnerVerifier(),
                new CellVerifier(),
                canSecondPlayerMakeFirstMove,
                gameOverHandler);
    }
}