package ru.oleg.commands;


import ru.oleg.managers.CollectionManager;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;
import ru.oleg.exceptions.IllegalArgumentsException;

/**
 * Команда 'clear'
 * Очищает коллекцию
 */
public class Clear extends Command {
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
        collectionManager.clear();

        return new Response(ResponseStatus.OK, "Элементы удалены");

    }
}
