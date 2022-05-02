package ua.mibal.tictactoe.model;

import ua.mibal.tictactoe.component.Move;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class Player {

    private final Sign sign;

    private final Move move;

    public Player(final Sign sign,
                  final Move move) {
        this.sign = sign;
        this.move = move;
    }

    public Sign getSign() {
        return sign;
    }

    public Move getMove() {
        return move;
    }

    public void makeMove(final GameTable gameTable){
        move.make(gameTable, sign);
    }

    @Override
    public String toString() {
        return "'" + sign + "'";
    }
}
