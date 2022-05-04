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

package ua.mibal.tictactoe.component.console;

import ua.mibal.tictactoe.component.CellNumberConverter;
import ua.mibal.tictactoe.component.DataPrinter;
import ua.mibal.tictactoe.component.UserInputReader;
import ua.mibal.tictactoe.model.Cell;

import java.util.Scanner;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class ConsoleUserInputReader implements UserInputReader {

    private final CellNumberConverter cellNumberConverter;

    private final DataPrinter dataPrinter;

    public ConsoleUserInputReader(final CellNumberConverter cellNumberConverter,
                                  final DataPrinter dataPrinter) {
        this.cellNumberConverter = cellNumberConverter;
        this.dataPrinter = dataPrinter;
    }

    @Override
    public Cell getUserInput() {
        while (true) {
            //System.out.println("Please type number between 1 and 9:");
            dataPrinter.printInfoMessage("Please type number between 1 and 9:");
            final String userInput = new Scanner(System.in).nextLine();
            if (userInput.length() == 1) {
                final char ch = userInput.charAt(0);
                if ('1' <= ch && ch <= '9') {
                    return cellNumberConverter.toCell(ch);
                }
            }
        }
    }
}
