package ru.oleg.commands;


import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.exceptions.InvalidFormException;
import ru.oleg.managers.CollectionManager;
import ru.oleg.models.SpaceMarine;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;

import java.util.Objects;

/**
 * Команда 'add_if_min'
 * Добавляет элемент, если его значение меньше, чем у наименьшего элемента
 */
public class AddIfMin extends Command implements CollectionEditor {

    private final CollectionManager collectionManager;

    public AddIfMin(CollectionManager collectionManager) {
        super("add_if_min", " {element}: добавить элемент в коллекцию если его значение меньше," +
                " чем у наименьшего элемента");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнить команду
     *
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException, InvalidFormException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        if (request.getObject().compareTo(Objects.requireNonNull(collectionManager.getCollection().stream()
                .filter(Objects::nonNull)
                .min(SpaceMarine::compareTo)
                .orElse(null))) <= -1) {
            return AddToDB.Add(request, collectionManager);
//            int new_id = DatabaseHandler.getDatabaseManager().addObject(request.getObject(), request.getUser());
//            if (new_id == -1) return new Response(ResponseStatus.ERROR, "Объект добавить не удалось");
//            request.getObject().setId((long) new_id);
//            request.getObject().setUserLogin(request.getUser().name());
//            collectionManager.addElement(request.getObject());
//            return new Response(ResponseStatus.OK, "Объект успешно добавлен");
        }
        return new Response(ResponseStatus.ERROR, "Элемент больше минимального");
    }

}
