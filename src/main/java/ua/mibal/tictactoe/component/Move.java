package ua.mibal.tictactoe.component;

import ua.mibal.tictactoe.model.GameTable;
import ua.mibal.tictactoe.model.Sign;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public interface Move {
    void make(GameTable gameTable, Sign sign);
}
