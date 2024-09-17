package ru.oleg.utility;


import ru.oleg.exceptions.*;
import ru.oleg.managers.CommandManager;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;

public class RequestHandler {

    private final CommandManager commandManager;

    public RequestHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response bufferError(){

        return new Response(ResponseStatus.ERROR,
                "Данные не влезают в буфер на сервере");
    }

    public Response handle(Request request) {
        try {
            commandManager.addToHistory(request.getCommandName());
            return commandManager.execute(request);
        } catch (IllegalArgumentsException e) {
            return new Response(ResponseStatus.WRONG_ARGUMENTS,
                    "Неверное использование аргументов команды");
        } catch (CommandRuntimeException e) {
            return new Response(ResponseStatus.ERROR,
                    "Ошибка при исполнении программы");
        } catch (NoCommandException e) {
            return new Response(ResponseStatus.ERROR, "Такой команды нет в списке");
        } catch (ExitException e) {
            return new Response(ResponseStatus.EXIT);
        } catch (InvalidFormException e) {
            throw new RuntimeException(e);
        }
    }
}
