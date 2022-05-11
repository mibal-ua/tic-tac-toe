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
public class FirstMoveToTheCenterComputerMoveStrategy implements ComputerMoveStrategy {


    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Sign sign) {
        Cell cell = new Cell(1, 1);
        if (gameTable.isEmpty(cell)) {
            gameTable.setSign(cell, sign);
            return true;
        } else {
            return false;
        }
    }
}
