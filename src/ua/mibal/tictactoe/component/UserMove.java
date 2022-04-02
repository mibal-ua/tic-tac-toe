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

import java.util.Scanner;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class UserMove {
    public void make(final GameTable gameTable) {
        while (true) {
            System.out.println("Please type number between 1 and 9:");
            int user = new Scanner(System.in).nextInt();
            if (requestIsCorrect(user)) {
                Cell cell = createCellByNumber(user);
                if (gameTable.isEmpty(cell)) {
                    gameTable.setSign(cell, 'X');
                    return;
                }
                System.out.print(user + " cell is not empty. ");
                continue;
            }
            System.out.print(user + " is not correct number. ️");
        }
        /*
        7 - 0 0
        8 - 0 1
        9 - 0 2

        int row = 2 - (user - 1)/3;
        int col = (user - 1)%3;

        4 - 1 0
        5 - 1 1
        6 - 1 2

        1 - 2 0
        2 - 2 1
        3 - 2 2
         */
    }

    private Cell createCellByNumber(final int user) {
        int row = 2 - (user - 1) / 3;
        int col = (user - 1) % 3;
        return new Cell(row, col);
    }

    private boolean requestIsCorrect(final int user) {
        return 1 <= user && user <= 9;
    }
}
