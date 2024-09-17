package ru.oleg.commands;


import ru.oleg.managers.CollectionManager;
import ru.oleg.models.SpaceMarine;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;
import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.utility.DatabaseHandler;

import java.util.List;

/**
 * Команда 'clear'
 * Очищает коллекцию
 */
public class Clear extends Command implements CollectionEditor {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", ": очистить коллекцию");
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
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        List<Integer> deletedIds = collectionManager.getCollection().stream()
                .filter(spaceMarine -> spaceMarine.getUserLogin().equals(request.getUser().name()))
                .map(SpaceMarine::getId).mapToInt(Long::intValue).boxed() // преобразование в int
                .toList();
        if (DatabaseHandler.getDatabaseManager().deleteAllObjects(request.getUser(), deletedIds)) {
            collectionManager.removeElements(deletedIds);
            return new Response(ResponseStatus.OK, "Ваши элементы удалены");
        }
        return new Response(ResponseStatus.ERROR, "Элементы коллекции удалить не удалось");

    }
}
