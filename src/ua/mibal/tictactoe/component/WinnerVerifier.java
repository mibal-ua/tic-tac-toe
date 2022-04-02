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

import ua.mibal.tictactoe.model.Cell;
import ua.mibal.tictactoe.model.GameTable;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class WinnerVerifier {

    public boolean isUserWin(final GameTable gameTable) {
        return isWinner(gameTable, 'X');
    }

    public boolean isComputerWin(final GameTable gameTable) {
        return isWinner(gameTable, 'O');
    }

    private boolean isWinner(GameTable gameTable, char sign) {
        return isWinnerByRows(gameTable, sign) ||
               isWinnerByCols(gameTable, sign) ||
               isWinnerByMainDiagonal(gameTable, sign) ||
               isWinnerBySecondDiagonal(gameTable, sign);
    }

    private boolean isWinnerByRows(final GameTable gameTable, final char sign) {
        //horizontal
        for (int i = 0; i < 3; i++) {
            if (gameTable.getSign(new Cell(i, 0)) == gameTable.getSign(new Cell(i, 1)) &&
                gameTable.getSign(new Cell(i, 1)) == gameTable.getSign(new Cell(i, 2)) &&
                gameTable.getSign(new Cell(i, 2)) == sign) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinnerByCols(final GameTable gameTable, final char sign) {
        //vertical
        for (int i = 0; i < 3; i++) {
            if (gameTable.getSign(new Cell(0, i)) == gameTable.getSign(new Cell(1, i)) &&
                gameTable.getSign(new Cell(1, i)) == gameTable.getSign(new Cell(2, i)) &&
                gameTable.getSign(new Cell(2, i)) == sign) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinnerByMainDiagonal(final GameTable gameTable, final char sign) {
        //diagonal
        return gameTable.getSign(new Cell(0, 0)) == gameTable.getSign(new Cell(1, 1)) &&
               gameTable.getSign(new Cell(1, 1)) == gameTable.getSign(new Cell(2, 2)) &&
               gameTable.getSign(new Cell(2, 2)) == sign;
    }

    private boolean isWinnerBySecondDiagonal(final GameTable gameTable, final char sign) {
        return gameTable.getSign(new Cell(2, 0)) == gameTable.getSign(new Cell(1, 1)) &&
               gameTable.getSign(new Cell(1, 1)) == gameTable.getSign(new Cell(0, 2)) &&
               gameTable.getSign(new Cell(0, 2)) == 'O';
    }

}
