package collections.asks;

import console.*;
import exceptions.FIleFieldException;
import collections.Chapter;
import collections.Coordinates;
import collections.SpaceMarine;
import collections.Weapon;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Форма Космического десантника
 */

public class AskSpaceMarine extends AskForm<SpaceMarine> {
    private final Printable console;
    private final ConsoleInput scanner;

    public AskSpaceMarine(Printable console) {
        this.console = (ConsoleOutput.isFileMode())
                ? new PrintConsole()
                : console;
        this.scanner = (ConsoleOutput.isFileMode())
                ? new ExecuteManager()
                : new ConsoleInput();
    }

    /**
     * Сконструировать новый элемент класса {@link SpaceMarine}
     *
     * @return объект класса {@link SpaceMarine}
     */
    @Override
    public SpaceMarine build() {
        return new SpaceMarine(askName(),
                askCoordinates(),
                LocalDateTime.now(),
                askHealth(),
                askAchievements(),
                askHeight(),
                askWeaponType(),
                askChapter());
    }


    private String askName() {
        String name;
        while (true) {
            console.println(OutputColors.toColor("Введите имя десантника", OutputColors.BLUE));
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                console.printError("Имя не может быть пустым");
                if (ConsoleOutput.isFileMode()) throw new FIleFieldException();
            } else {
                return name;
            }
        }
    }

    private Coordinates askCoordinates() {
        return new AskCoordinates(console).build();
    }


    private Integer askHealth() {
        while (true) {
            console.println(OutputColors.toColor("Введите количество HP", OutputColors.BLUE));
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                console.printError("HP должно быть числом типа int");
                if (ConsoleOutput.isFileMode()) throw new FIleFieldException();
            }
        }
    }

    private String askAchievements() {
        while (true) {
            String achievements;
            console.println(OutputColors.toColor("Введите ачивки", OutputColors.BLUE));
            achievements = scanner.nextLine().trim();
            if (achievements.isEmpty()) {
                console.printError("Ачивки не могу быть пустыми");
                if (ConsoleOutput.isFileMode()) throw new FIleFieldException();
            } else {
                return achievements;
            }
        }
    }


    private Long askHeight() {
        while (true) {
            console.println(OutputColors.toColor("Введите рост", OutputColors.BLUE));
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException exception) {
                console.printError("Рост должен быть числом типа long");
                if (ConsoleOutput.isFileMode()) throw new FIleFieldException();
            }
        }
    }


    private Weapon askWeaponType() {
        return new AskWeaponType(console).build();
    }

    private Chapter askChapter() {
        return new AskChapter(console).build();
    }


}
