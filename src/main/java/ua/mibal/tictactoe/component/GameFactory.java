package ua.mibal.tictactoe.component;

import ua.mibal.tictactoe.component.keypad.DesktopNumericKeypadCellNumberConverter;
import ua.mibal.tictactoe.model.Player;
import ua.mibal.tictactoe.model.PlayerType;

import static ua.mibal.tictactoe.model.PlayerType.COMPUTER;
import static ua.mibal.tictactoe.model.PlayerType.USER;
import static ua.mibal.tictactoe.model.Sign.O;
import static ua.mibal.tictactoe.model.Sign.X;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class GameFactory {

    private final PlayerType player1Type;

    private final PlayerType player2Type;

    public GameFactory(final String[] args) {
        PlayerType player1Type = null;
        PlayerType player2Type = null;

        for (final String arg : args) {
            if (USER.name().equalsIgnoreCase(arg) || COMPUTER.name().equalsIgnoreCase(arg)) {
                if (player1Type == null) {
                    player1Type = PlayerType.valueOf(arg.toUpperCase());
                } else if (player2Type == null) {
                    player2Type = PlayerType.valueOf(arg.toUpperCase());
                } else {
                    System.err.println("Unsupported command line argument: '" + arg + "'");
                }
            } else {
                System.err.println("Unsupported command line argument: '" + arg + "'");
            }
        }

        if (player1Type == null) {
            this.player1Type = USER;
            this.player2Type = COMPUTER;
        } else if (player2Type == null) {
            this.player1Type = USER;
            this.player2Type = player1Type;
        } else {
            this.player1Type = player1Type;
            this.player2Type = player2Type;
        }
    }

    public Game create() {
        final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();
        final Player player1;
        final Player player2;
        if (player1Type == USER) {
            player1 = new Player(X, new UserMove(cellNumberConverter));
        } else {
            player1 = new Player(X, new ComputerMove());
        }
        if (player2Type == USER) {
            player2 = new Player(O, new UserMove(cellNumberConverter));
        } else {
            player2 = new Player(O, new ComputerMove());
        }
        return new Game(
                new DataPrinter(cellNumberConverter),
                player1,
                player2,
                new WinnerVerifier(),
                new CellVerifier(),
                player1Type != player2Type
        );
    }
}