package ru.oleg.commandLine;


import ru.oleg.utility.UserScanner;

import java.util.Scanner;

/**
 * Класс для стандартного ввода через консоль
 */
public class ConsoleInput implements UserInput {

    private static final Scanner userScanner = UserScanner.getUserScanner();


    public String nextLine() {
        return userScanner.nextLine();
    }

}
