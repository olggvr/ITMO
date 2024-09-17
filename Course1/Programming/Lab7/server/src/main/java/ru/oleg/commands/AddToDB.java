package ru.oleg.commands;

import ru.oleg.managers.CollectionManager;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;
import ru.oleg.utility.DatabaseHandler;

public class AddToDB {

    public static Response Add(Request request, CollectionManager collectionManager){
        int new_id = DatabaseHandler.getDatabaseManager().addObject(request.getObject(), request.getUser());
        if (new_id == -1) return new Response(ResponseStatus.ERROR, "Объект добавить не удалось");
        request.getObject().setId((long) new_id);
        request.getObject().setUserLogin(request.getUser().name());
        collectionManager.addElement(request.getObject());
        return new Response(ResponseStatus.OK, "Объект успешно добавлен");
    }
}
