package collections.asks;

import console.*;
import exceptions.FIleFieldException;
import collections.Coordinates;

/**
 * Форма для координат
 */
public class AskCoordinates extends AskForm<Coordinates> {
    private final Printable console;
    private final ConsoleInput scanner;

    public AskCoordinates(Printable console) {
        this.console = (ConsoleOutput.isFileMode())
                ? new PrintConsole()
                : console;
        this.scanner = (ConsoleOutput.isFileMode())
                ? new ExecuteManager()
                : new ConsoleInput();
    }

    /**
     * Сконструировать новый элемент класса {@link Coordinates}
     *
     * @return объект класса {@link Coordinates}
     */


    @Override
    public Coordinates build() {
        return new Coordinates(askX(), askY());
    }

    public Double askX() {
        while (true) {
            console.println(OutputColors.toColor("Введите координату X", OutputColors.PURPLE));
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                console.printError("Координата X должна быть числом типа double");
                if (ConsoleOutput.isFileMode()) throw new FIleFieldException();
            }
        }
    }

    public Double askY() {
        while (true) {
            console.println(OutputColors.toColor("Введите координату Y", OutputColors.PURPLE));
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                console.printError("Координата Y должна быть числом типа double");
                if (ConsoleOutput.isFileMode()) throw new FIleFieldException();
            }
        }
    }


}
