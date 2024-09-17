package commands;

import console.ConsoleOutput;
import console.OutputColors;
import exceptions.FIleFieldException;
import exceptions.IllegalArgumentsException;
import exceptions.InvalidFormException;
import managers.CollectionManager;
import collections.SpaceMarine;
import collections.asks.AskSpaceMarine;

import java.util.Objects;

/**
 * Команда 'add_if_min'
 * Добавляет элемент, если его значение меньше, чем у наименьшего элемента
 */
public class AddIfMin extends Command {

    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public AddIfMin(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("add_if_min", " {element}: добавить элемент в коллекцию если его значение меньше," +
                " чем у наименьшего элемента");
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
        try {
            consoleOutput.println(OutputColors.toColor("Создание объекта StudyGroup", OutputColors.CYAN));
            SpaceMarine newElement = new AskSpaceMarine(consoleOutput).build();
            consoleOutput.println(OutputColors.toColor("Создание объекта StudyGroup окончено успешно!", OutputColors.CYAN));
            if (newElement.compareTo(Objects.requireNonNull(collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .min(SpaceMarine::compareTo)
                    .orElse(null))) <= -1) {
                collectionManager.addElement(newElement);
                consoleOutput.println(OutputColors.toColor("Объект успешно добавлен", OutputColors.GREEN));

            } else {
                consoleOutput.println(OutputColors.toColor("Элемент больше минимального", OutputColors.RED));
            }
        } catch (InvalidFormException invalidForm) {
            consoleOutput.printError("Поля объекта не валидны! Объект не создан!");
        } catch (FIleFieldException e) {
            consoleOutput.printError("Поля в файле не валидны! Объект не создан");
        }
    }

}
