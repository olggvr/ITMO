package ru.oleg.commands;


import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;

/**
 * Команда 'ping'
 * пингануть сервак
 */
public class Ping extends Command {
    public Ping() {
        super("ping", ": пингануть сервер");
    }

    /**
     * Исполнить команду
     *
     * @param request запрос клиента
     * @throws ru.oleg.exceptions.IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        return new Response(ResponseStatus.OK, "pong");
    }
}
