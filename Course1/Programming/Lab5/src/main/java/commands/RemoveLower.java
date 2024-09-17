package commands;

import console.ConsoleOutput;
import console.OutputColors;
import exceptions.FIleFieldException;
import exceptions.IllegalArgumentsException;
import managers.CollectionManager;
import collections.SpaceMarine;
import collections.asks.AskSpaceMarine;

import java.util.Collection;
import java.util.Objects;

/**
 * Команда 'remove_lower'
 * Удаляет из коллекции все элементы, меньшие, чем заданный
 */
public class RemoveLower extends Command {
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public RemoveLower(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("remove_lower", "{element} : удалить из коллекции все элементы, меньшие, чем заданный");
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;
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
        class NoElements extends RuntimeException {

        }
        try {
            consoleOutput.println(OutputColors.toColor("Создание объекта StudyGroup", OutputColors.CYAN));
            SpaceMarine newElement = new AskSpaceMarine(consoleOutput).build();
            consoleOutput.println(OutputColors.toColor("Создание объекта StudyGroup окончено успешно!", OutputColors.CYAN));
            Collection<SpaceMarine> toRemove = collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .filter(spaceMarine -> spaceMarine.compareTo(newElement) <= -1)
                    .toList();
            collectionManager.removeElements(toRemove);
            consoleOutput.println(OutputColors.toColor("Удалены элементы, меньшие, чем заданный", OutputColors.GREEN));
        } catch (NoElements e) {
            consoleOutput.printError("В коллекции нет элементов");
        } catch (FIleFieldException e) {
            consoleOutput.printError("Поля в файле не валидны! Объект не создан");
        }
    }

}
