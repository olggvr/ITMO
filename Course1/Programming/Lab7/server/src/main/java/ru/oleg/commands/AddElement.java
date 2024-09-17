package ru.oleg.commands;


import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.exceptions.InvalidFormException;
import ru.oleg.managers.CollectionManager;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;


/**
 * Команда 'add'
 * Добавляет новый элемент в коллекцию
 */
public class AddElement extends Command implements CollectionEditor {
    private final CollectionManager collectionManager;


    public AddElement(CollectionManager collectionManager) {
        super("add", " {element} : добавить новый элемент в коллекцию");
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
        if (request.getObject() == null) {
            return new Response(ResponseStatus.ERROR, "Не могу добавить обжект");
        }
//        int new_id = DatabaseHandler.getDatabaseManager().addObject(request.getObject(), request.getUser());
//        if (new_id == -1) return new Response(ResponseStatus.ERROR, "Объект добавить не удалось");
//        request.getObject().setId((long) new_id);
//        request.getObject().setUserLogin(request.getUser().name());
//        collectionManager.addElement(request.getObject());
//        return new Response(ResponseStatus.OK, "Объект успешно добавлен");
        return AddToDB.Add(request, collectionManager);

    }
}
