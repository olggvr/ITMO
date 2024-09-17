package collections.asks;

import console.*;
import exceptions.FIleFieldException;
import collections.Weapon;

import java.util.Locale;


/**
 * Форма для выбора оружия
 */
public class AskWeaponType extends AskForm<Weapon> {
    private final Printable console;
    private final ConsoleInput scanner;


    public AskWeaponType(Printable console) {
        this.console = (ConsoleOutput.isFileMode())
                ? new PrintConsole()
                : console;
        this.scanner = (ConsoleOutput.isFileMode())
                ? new ExecuteManager()
                : new ConsoleInput();
    }

    /**
     * Сконструировать новый элемент класса {@link Weapon}
     *
     * @return объект класса {@link Weapon}
     */

    @Override
    public Weapon build() {
        console.println("Возможное оружие: ");
        console.println(Weapon.names());
        while (true) {
            console.println(OutputColors.toColor("Введите тип оружия: ", OutputColors.YELLOW));
            String input = scanner.nextLine().trim();
            try {
                return Weapon.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception) {
                console.println("Такого оружия нет в списке");
                if (ConsoleOutput.isFileMode()) throw new FIleFieldException();
            }
        }
    }


}
