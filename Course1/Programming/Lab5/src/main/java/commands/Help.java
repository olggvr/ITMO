package commands;


import console.ConsoleOutput;
import console.OutputColors;
import exceptions.IllegalArgumentsException;
import managers.CommandManager;

/**
 * Команда 'help'
 * Вывести справку по доступным командам
 */
public class Help extends Command {
    private final CommandManager commandManager;
    private final ConsoleOutput consoleOutput;

    public Help(ConsoleOutput consoleOutput, CommandManager commandManager) {
        super("help", ": вывести справку по доступным командам");
        this.commandManager = commandManager;
        this.consoleOutput = consoleOutput;
    }

    /**
     * Исполнить команду
     *
     * @param args аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public void execute(String args) throws IllegalArgumentsException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        commandManager.getCommands()
                .forEach(command -> consoleOutput.println(OutputColors.toColor(command.getName(), OutputColors.CYAN) + command.getDescription()));
    }
}