package ru.oleg.commands;


import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.managers.CommandManager;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;

import java.util.List;


/**
 * Команда 'history'
 * Выводит последние 5 команд (без их аргументов)
 */
public class History extends Command {
    private final CommandManager commandManager;

    public History(CommandManager commandManager) {
        super("history", " вывести последние 5 команд (без их аргументов)");
        this.commandManager = commandManager;
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
        List<String> history = commandManager.getCommandHistory(request.getUser());
        return new Response(ResponseStatus.OK,
                String.join("\n",
                        history.subList(Math.max(history.size() - 5, 0), history.size())));
    }
}
