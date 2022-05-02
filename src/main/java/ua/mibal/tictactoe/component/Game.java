/*
 * Copyright 2022 http://t.me/mibal_ua
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ua.mibal.tictactoe.component;

import ua.mibal.tictactoe.model.GameTable;

import java.util.Random;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class Game {

    private final DataPrinter dataPrinter;

    private final ComputerMove computerMove;

    private final ua.mibal.tictactoe.component.UserMove userMove;

    private final ua.mibal.tictactoe.component.WinnerVerifier winnerVerifier;

    private final CellVerifier drawVerifier;

    public Game(final DataPrinter dataPrinter,
                final ComputerMove computerMove,
                final ua.mibal.tictactoe.component.UserMove userMove,
                final ua.mibal.tictactoe.component.WinnerVerifier winnerVerifier,
                final CellVerifier drawVerifier) {
        this.dataPrinter = dataPrinter;
        this.computerMove = computerMove;
        this.userMove = userMove;
        this.winnerVerifier = winnerVerifier;
        this.drawVerifier = drawVerifier;
    }

    public void play() {
        System.out.println("Use the following mapping table to specify a cell using numbers from 1 to 9:");
        dataPrinter.printMappingTable();
        final GameTable gameTable = new GameTable();
        if (new Random().nextBoolean()) {
            computerMove.make(gameTable);
            dataPrinter.printGameTable(gameTable);
        }
        final Move[] moves = {userMove, computerMove};
        while (true) {
            for (final Move move : moves) {
                move.make(gameTable);
                dataPrinter.printGameTable(gameTable);
                if (move instanceof UserMove) {
                    if (winnerVerifier.isUserWin(gameTable)) {
                        System.out.println("YOU WIN!");
                        printGameOver();
                        return;
                    }
                } else {
                    if (winnerVerifier.isComputerWin(gameTable)) {
                        System.out.println("COMPUTER WIN!");
                        printGameOver();
                        return;
                    }
                }
                if (drawVerifier.allCellsFilled(gameTable)) {
                    System.out.println("DRAW!");
                    printGameOver();
                    return;
                }
            }
        }
    }

    private void printGameOver() {
        System.out.println("GAME OVER!");
    }
}
