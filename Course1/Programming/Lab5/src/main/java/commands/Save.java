package commands;


import console.ConsoleOutput;
import console.OutputColors;
import exceptions.IllegalArgumentsException;
import managers.FileManager;

/**
 * Команда 'save'
 * Сохраняет коллекцию в файл
 */
public class Save extends Command {
    private FileManager fileManager;
    private ConsoleOutput consoleOutput;

    public Save(ConsoleOutput consoleOutput, FileManager fileManager) {
        super("save", ": сохранить коллекцию в файл");
        this.fileManager = fileManager;
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
        fileManager.saveObjects();
        consoleOutput.println(OutputColors.toColor("Объекты сохранены успешно", OutputColors.GREEN));
    }
}

