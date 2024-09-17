package ru.oleg.commands;


import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.managers.CollectionManager;
import ru.oleg.models.SpaceMarine;
import ru.oleg.models.Weapon;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;

import java.util.Collection;
import java.util.Objects;

/**
 * Команда 'remove_all_weapon_type'
 * удаляет все элементы, значения поля weaponType которых эквивалентны заданному
 */
public class RemoveAllByWeaponType extends Command implements CollectionEditor {
    private final CollectionManager collectionManager;

    public RemoveAllByWeaponType(CollectionManager collectionManager) {
        super("remove_all_by_weapon_type", " weaponType: удалить все элементы" +
                ", значения поля weaponType которых эквивалентны заданному");
        this.collectionManager = collectionManager;

    }

    /**
     * Исполнить команду
     *
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (request.getArgs().isBlank()) throw new IllegalArgumentsException();
        try {
            Weapon weaponType = Weapon.valueOf(request.getArgs().trim().toUpperCase());
            Collection<SpaceMarine> toRemove = collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .filter(spaceMarine -> spaceMarine.getWeaponType() == weaponType)
                    .toList();
            collectionManager.removeElements(toRemove);
            return new Response(ResponseStatus.OK, "Удалены элементы с таким weapon_type");
        } catch (NumberFormatException exception) {
            return new Response(ResponseStatus.ERROR, "weapon_type должно быть числом типа long");
        }
    }


}
