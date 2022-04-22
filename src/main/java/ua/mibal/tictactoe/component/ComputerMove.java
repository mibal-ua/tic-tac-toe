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

package main.java.ua.mibal.tictactoe.component;

import main.java.ua.mibal.tictactoe.model.Cell;
import main.java.ua.mibal.tictactoe.model.GameTable;

import java.util.Random;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class ComputerMove {

    public void make(final GameTable gameTable) {
        final Random random = new Random();
        while (true) {
            final int row = random.nextInt(3);
            final int col = random.nextInt(3);
            Cell cell = new Cell(row, col);
            if (gameTable.isEmpty(cell)) {
                gameTable.setSign(cell, 'O');
                return;
            }
        }
    }
}