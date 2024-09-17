package commands;

import console.ConsoleOutput;
import exceptions.IllegalArgumentsException;
import managers.CollectionManager;

/**
 * Команда 'clear'
 * Очищает коллекцию
 */
public class Clear extends Command {
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public Clear(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("clear", ": очистить коллекцию");
        this.collectionManager = collectionManager;
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
        collectionManager.clear();
        consoleOutput.println("Элементы удалены");
    }
}
