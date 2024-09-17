package commands;

import console.ConsoleOutput;
import console.OutputColors;
import exceptions.IllegalArgumentsException;
import managers.CollectionManager;
import collections.SpaceMarine;
import collections.Weapon;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * Команда 'remove_all_weapon_type'
 * удаляет все элементы, значения поля weaponType которых эквивалентны заданному
 */
public class RemoveAllByWeaponType extends Command {
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public RemoveAllByWeaponType(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("remove_all_by_weapon_type", " weaponType: удалить все элементы" +
                ", значения поля weaponType которых эквивалентны заданному");
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;

    }

    /**
     * Исполнить команду
     *
     * @param args аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public void execute(String args) throws IllegalArgumentsException {
        if (args.isBlank()) throw new IllegalArgumentsException();
        try {
            Weapon weaponType = Weapon.valueOf(args.trim().toUpperCase());
            Collection<SpaceMarine> toRemove = collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .filter(spaceMarine -> spaceMarine.getWeaponType() == weaponType)
                    .toList();
            collectionManager.removeElements(toRemove);
            consoleOutput.println(OutputColors.toColor("Удалены элементы с таким weapon_type", OutputColors.GREEN));
        } catch (IllegalArgumentException exception) {
            consoleOutput.printError("weapon_type должен быть одним из: " + Arrays.toString(Weapon.values()));
        }
    }


}
