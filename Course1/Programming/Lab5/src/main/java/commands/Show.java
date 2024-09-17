package commands;


import console.ConsoleOutput;
import exceptions.IllegalArgumentsException;
import managers.CollectionManager;
import collections.SpaceMarine;

import java.util.Collection;

/**
 * Команда 'show'
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class Show extends Command {
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public Show(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("show", ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
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
        Collection<SpaceMarine> collection = collectionManager.getCollection();
        if (collection == null || collection.isEmpty()) {
            consoleOutput.printError("Коллекция еще не инициализирована");
            return;
        }
        consoleOutput.println(collection.toString());
    }
}
