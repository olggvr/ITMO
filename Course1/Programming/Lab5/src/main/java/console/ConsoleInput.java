package console;

import managers.UserScanner;

import java.util.Scanner;

/**
 * Класс для стандартного ввода через консоль
 */
public class ConsoleInput {

    private static final Scanner userScanner = UserScanner.getUserScanner();


    public String nextLine() {
        return userScanner.nextLine();
    }

}
