package ru.oleg.commands;


import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.managers.DatabaseManager;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;

import java.sql.SQLException;

/**
 * Команда 'register'
 * Регистрирует пользователя
 */
public class Register extends Command {
    DatabaseManager databaseManager;

    public Register(DatabaseManager databaseManager) {
        super("register", ": Зарегестрировать пользователя");
        this.databaseManager = databaseManager;
    }

    /**
     * Исполнить команду
     *
     * @param request запрос клиента
     * @throws ru.oleg.exceptions.IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        this.commandLogger.debug("получен юзер: " + request.getUser());
        try {
            databaseManager.addUser(request.getUser());
        } catch (SQLException e) {
            commandLogger.fatal("Невозможно добавить пользователя");
            return new Response(ResponseStatus.LOGIN_FAILED, "Введен невалидный пароль!");
        }
        return new Response(ResponseStatus.OK, "Вы успешно зарегистрированы!");
    }
}
