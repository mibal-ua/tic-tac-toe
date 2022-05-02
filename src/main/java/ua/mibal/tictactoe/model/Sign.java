package ua.mibal.tictactoe.model;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public enum Sign {

    X,

    O,

    EMPTY;

    @Override
    public String toString() {
        if (this == EMPTY) {
            return " ";
        } else {
            return name();
        }
    }
}
