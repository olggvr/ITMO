package commands;

import console.ConsoleOutput;
import console.OutputColors;
import exceptions.IllegalArgumentsException;
import managers.CollectionManager;

/**
 * Команда 'remove_head'
 * Выводит первый элемент коллекции и удаляет его
 */

public class RemoveHead extends Command {
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public RemoveHead(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("remove_head", " вывести первый элемент коллекции и удалить его");
        this.collectionManager = collectionManager;
        this.consoleOutput = consoleOutput;
    }

    @Override
    public void execute(String args) throws IllegalArgumentsException {
        if (collectionManager.getCollection() == null || collectionManager.getCollection().isEmpty()) {
            consoleOutput.printError("Нечего удалять, братиш! Пуста");
            return;
        }
        System.out.println(collectionManager.getCollection().peek());
        collectionManager.removeElement(collectionManager.getCollection().poll());

        consoleOutput.println(OutputColors.toColor("Объект удален успешно", OutputColors.GREEN));


    }
}
