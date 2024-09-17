package commands;

import console.ConsoleOutput;
import console.OutputColors;
import exceptions.FIleFieldException;
import exceptions.IllegalArgumentsException;
import exceptions.InvalidFormException;
import managers.CollectionManager;
import collections.SpaceMarine;
import collections.asks.AskSpaceMarine;

/**
 * Команда 'update'
 * Обновляет значение элемента коллекции, id которого равен заданному
 */
public class UpdateId extends Command {
    private final CollectionManager collectionManager;
    private final ConsoleOutput consoleOutput;

    public UpdateId(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("update", " id {element}: обновить значение элемента коллекции, id которого равен заданному");
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
        if (args.isBlank()) throw new IllegalArgumentsException();
        class NoSuchId extends RuntimeException {

        }
        try {
            int id = Integer.parseInt(args.trim());
            if (!collectionManager.checkExist((long)id )) throw new NoSuchId();
            consoleOutput.println(OutputColors.toColor("Создание нового объекта StudyGroup", OutputColors.CYAN));
            SpaceMarine newSpaceMarine = new AskSpaceMarine(consoleOutput).build();
            collectionManager.editById((long) id, newSpaceMarine);
            consoleOutput.println(OutputColors.toColor("Создание нового объекта StudyGroup окончено успешно!", OutputColors.CYAN));
        } catch (NoSuchId err) {
            consoleOutput.printError("В коллекции нет элемента с таким id");
        } catch (InvalidFormException invalidForm) {
            consoleOutput.printError("Поля объекта не валидны! Объект не создан!");
        } catch (NumberFormatException exception) {
            consoleOutput.printError("id должно быть числом типа int");
        } catch (FIleFieldException e) {
            consoleOutput.printError("Поля в файле не валидны! Объект не создан");
        }
    }
}