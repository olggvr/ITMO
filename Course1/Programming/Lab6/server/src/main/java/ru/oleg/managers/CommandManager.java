package ru.oleg.managers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.oleg.commands.CollectionEditor;
import ru.oleg.commands.Command;
import ru.oleg.exceptions.*;
import ru.oleg.network.Request;
import ru.oleg.network.Response;

import java.util.*;
import java.util.stream.Collectors;


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
    private final List<String> commandHistory = new ArrayList<>();

    private final FileManager fileManager;

    static final Logger commandManagerLogger = LogManager.getLogger(CommandManager.class);

    public CommandManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }


    public void addCommand(Command command) {
        this.commands.put(command.getName(), command);
        commandManagerLogger.info("Добавлена команда", command);
    }

    public void addCommand(Collection<Command> commands) {
        this.commands.putAll(commands.stream().collect(Collectors.toMap(Command::getName, s -> s)));
        commandManagerLogger.info("Добавлены комманды", commands);

    }


    public Collection<Command> getCommands() {
        return commands.values();
    }

    public Set<String> getCommandsNames() {
        return commands.keySet();

    }

    public void addToHistory(String line) {
        if (line.isBlank()) return;
        this.commandHistory.add(line);
        commandManagerLogger.info("Добавлена команда в историю: " + line, line);
    }

    public List<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Выполняет команду
     *
     * @throws NoCommandException        такая команда отсутствует
     * @throws IllegalArgumentsException неверные аргументы команды
     * @throws CommandRuntimeException   команда выдала ошибку при исполнении
     * @throws ExitException             команда вызвала выход из программы
     */

    public Response execute(Request request) throws NoCommandException, IllegalArgumentsException, ExitException, CommandRuntimeException, InvalidFormException {
        Command command = commands.get(request.getCommandName());
        if (command == null) {
            commandManagerLogger.fatal("Нет такой команды " + request.getCommandName());
            throw new NoCommandException();
        }
        Response response = command.execute(request);
        commandManagerLogger.info("Выполнение команды ", response);
        if (command instanceof CollectionEditor) {
            commandManagerLogger.info("Файл обновлен");
            fileManager.saveObjects();
        }
        return response;
    }


}
