package managers;


import commands.Command;
import exceptions.CommandRuntimeException;
import exceptions.ExitException;
import exceptions.IllegalArgumentsException;
import exceptions.NoCommandException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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

    public void addCommand(Command command) {
        this.commands.put(command.getName(), command);
    }

    public void addCommand(Collection<Command> commands) {
        this.commands.putAll(commands.stream().collect(Collectors.toMap(Command::getName, s -> s)));

    }

    public Collection<Command> getCommands() {
        return commands.values();
    }

    public void addToHistory(String line) {
        if (line.isBlank()) return;
        this.commandHistory.add(line);
    }

    public List<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Выполняет команду
     *
     * @param name название команды
     * @param args аргументы команды
     * @throws NoCommandException  такая команда отсутствует
     * @throws IllegalArgumentsException    неверные аргументы команды
     * @throws CommandRuntimeException команда выдала ошибку при исполнении
     * @throws ExitException       команда вызвала выход из программы
     */

    public void execute(String name, String args) throws NoCommandException, CommandRuntimeException, IllegalArgumentsException, ExitException {
        Command command = commands.get(name);
        if (command == null) throw new NoCommandException();
        command.execute(args);
    }


}
