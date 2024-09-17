package ru.oleg.commands;


import ru.oleg.managers.CollectionManager;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;

/**
 * Команда 'remove_head'
 * Выводит первый элемент коллекции и удаляет его
 */

public class RemoveHead extends Command {
    private final CollectionManager collectionManager;

    public RemoveHead(CollectionManager collectionManager) {
        super("remove_head", " вывести первый элемент коллекции и удалить его");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if (collectionManager.getCollection() == null || collectionManager.getCollection().isEmpty()) {
            return new Response(ResponseStatus.ERROR, "коллекш из емпти");

        }
        return new Response(ResponseStatus.OK, "данный элемент удалён: " + collectionManager.getCollection().poll());
    }


}
