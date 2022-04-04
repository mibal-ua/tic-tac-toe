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

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class CellNumberConverter {

    public static Cell convert(final int num) {
        int row = 2 - (num - 1) / 3;
        int col = (num - 1) % 3;
        return new Cell(row, col);
    }
}