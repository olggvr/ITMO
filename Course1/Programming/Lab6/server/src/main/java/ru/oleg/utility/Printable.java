package ru.oleg.utility;

/**
 * Интерфейс объединяющий способы вывода
 */
public interface Printable {

    void println(String a);

    void print(String a);

    void printError(String a);

    default void println(String a, OutputColors consoleColors) {
        println(a);
    }

    default void print(String a, OutputColors consoleColors) {
        print(a);
    }
}
