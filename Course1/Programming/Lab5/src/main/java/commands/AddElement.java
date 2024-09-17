package commands;

import exceptions.FIleFieldException;
import exceptions.IllegalArgumentsException;
import managers.CollectionManager;
import console.ConsoleOutput;
import console.OutputColors;
import exceptions.InvalidFormException;
import collections.asks.AskSpaceMarine;


/**
 * Команда 'add'
 * Добавляет новый элемент в коллекцию
 */
public class AddElement extends Command {
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public AddElement(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("add", " {element}: добавить новый элемент в коллекцию");
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
            consoleOutput.println(OutputColors.toColor("Создание объекта SpaceMarine", OutputColors.CYAN));
            collectionManager.addElement(new AskSpaceMarine(consoleOutput).build());
            consoleOutput.println(OutputColors.toColor("Создание объекта SpaceMarine окончено успешно!", OutputColors.CYAN));
        } catch (InvalidFormException invalidForm) {
            consoleOutput.printError("Поля объекта не валидны! Объект не создан!");
        } catch (FIleFieldException e) {
            consoleOutput.printError("Поля в файле не валидны! Объект не создан");
        }
    }
}
