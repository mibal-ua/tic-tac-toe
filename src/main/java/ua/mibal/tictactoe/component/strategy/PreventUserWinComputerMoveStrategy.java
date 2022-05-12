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

package ua.mibal.tictactoe.component.strategy;

import ua.mibal.tictactoe.component.ComputerMoveStrategy;
import ua.mibal.tictactoe.model.game.Cell;
import ua.mibal.tictactoe.model.game.GameTable;
import ua.mibal.tictactoe.model.game.Sign;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class PreventUserWinComputerMoveStrategy implements ComputerMoveStrategy {

    @Override
    public boolean tryToMakeMove(final GameTable gameTable, Sign sign) {
        return tryToPreventByRows(gameTable, sign) ||
               tryToPreventByCols(gameTable, sign) ||
               tryToPreventByMainDiagonal(gameTable, sign) ||
               tryToPreventBySecondaryDiagonal(gameTable, sign);
    }

    @SuppressWarnings("Convert2MethodRef")
    private boolean tryToPreventByRows(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            if (tryToMakeMoveUsingLambdaConversion(gameTable, sign, i, (k, j) -> new Cell(k, j))) {
                return true;
            }
        }
        return false;
    }

    private boolean tryToPreventByCols(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            if (tryToMakeMoveUsingLambdaConversion(gameTable, sign, i, (k, j) -> new Cell(j, k))) {
                return true;
            }
        }
        return false;
    }

    private boolean tryToPreventByMainDiagonal(final GameTable gameTable, final Sign sign) {
        return tryToMakeMoveUsingLambdaConversion(gameTable, sign, -1, (k, j) -> new Cell(j, j));
    }

    private boolean tryToPreventBySecondaryDiagonal(final GameTable gameTable, final Sign sign) {
        return tryToMakeMoveUsingLambdaConversion(gameTable, sign, -1, (k, j) -> new Cell(j, 2 - j));
    }

    /**
     * @author Michael Balakhon
     * @link http://t.me/mibal_ua
     */
    @FunctionalInterface
    private interface Lambda {

        Cell conversion(int k, int j);
    }

    private boolean tryToMakeMoveUsingLambdaConversion(GameTable gameTable,
                                                       Sign sign,
                                                       final int i,
                                                       final Lambda lambda) {
        int countEmptyCells = 0;
        int countSignCells = 0;
        Cell lastEmptyCell = null;
        for (int j = 0; j < 3; j++) {
            final Cell cell = lambda.conversion(i, j);
            if (gameTable.isEmpty(cell)) {
                lastEmptyCell = cell;
                countEmptyCells++;
            } else if (gameTable.getSign(cell) == sign.oppositeSign()) {
                countSignCells++;
            } else {
                break;
            }
        }
        if (countEmptyCells == 1 && countSignCells == 2) {
            gameTable.setSign(lastEmptyCell, sign);
            return true;
        }
        return false;
    }
}
