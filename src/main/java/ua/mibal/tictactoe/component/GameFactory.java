package ua.mibal.tictactoe.component;

import ua.mibal.tictactoe.component.keypad.DesktopNumericKeypadCellNumberConverter;
import ua.mibal.tictactoe.model.Player;
import ua.mibal.tictactoe.model.PlayerType;

import static ua.mibal.tictactoe.model.Sign.O;
import static ua.mibal.tictactoe.model.Sign.X;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class GameFactory {

    private final PlayerType player1Type = PlayerType.USER;

    private final PlayerType player2Type = PlayerType.COMPUTER;

    public GameFactory(final String[] args){
        // TODO
    }

    public Game create(){
        final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();
        return new Game(
                new DataPrinter(cellNumberConverter),
                // FIXME
                new Player(X, new UserMove(cellNumberConverter)),
                new Player(O, new UserMove(cellNumberConverter)),
                /*new Player(O, new ComputerMove()),
                new Player(X, new ComputerMove()),*/
                new WinnerVerifier(),
                new CellVerifier(),
                false);
    }
}
