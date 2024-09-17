package ru.oleg.managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.oleg.commands.Command;
import ru.oleg.exceptions.*;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Рекорд для хранения владельца команды в истории
 */
record HistoryCommand(String username, String command) {
}

/**
 * Командный менеджер.
 * Реализует паттерн программирования Command
 */
public class CommandManager {
    /**
     * Поле для хранения комманд в виде Имя-Комманда
     */
    private final HashMap<String, Command> commands = new HashMap<>();
    /**
     * Поле для истории команд
     */
    private final List<HistoryCommand> commandHistory = new ArrayList<>();

    private static final Logger commandManagerLogger = LogManager.getLogger(CommandManager.class);

    public CommandManager(DatabaseManager databaseManager) {
    }

    public void addCommand(Command command) {
        this.commands.put(command.getName(), command);
    }

    public void addCommand(Collection<Command> commands) {
        this.commands.putAll(commands.stream()
                .collect(Collectors.toMap(Command::getName, s -> s)));
    }

    public Collection<Command> getCommands() {
        return commands.values();
    }

    public void addToHistory(User user, String line) {
        if (line.isBlank()) return;
        this.commandHistory.add(new HistoryCommand(user.name(), line));
    }

    public List<String> getCommandHistory(User user) {
        return commandHistory.stream()
                .filter(historyCommand -> historyCommand.username().equals(user.name()))
                .map(HistoryCommand::command)
                .toList();
    }

    /**
     * Выполняет команду
     *
     * @param request - запрос клиента
     * @throws ru.oleg.exceptions.NoCommandException        такая команда отсутствует
     * @throws ru.oleg.exceptions.IllegalArgumentsException неверные аргументы команды
     * @throws ru.oleg.exceptions.CommandRuntimeException   команда выдала ошибку при исполнении
     * @throws ru.oleg.exceptions.ExitException             команда вызвала выход из программы
     */
    public Response execute(Request request) throws NoCommandException, IllegalArgumentsException, CommandRuntimeException, ExitException, InvalidFormException {
        Command command = commands.get(request.getCommandName());
        if (command == null) {
            commandManagerLogger.fatal("Нет такой команды " + request.getCommandName());
            throw new NoCommandException();
        }
        Response response = command.execute(request);
        commandManagerLogger.info("Выполнение команды ", response);
        return response;
    }
}
