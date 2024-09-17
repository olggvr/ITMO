package commands;

import console.ConsoleOutput;
import managers.CollectionManager;
import collections.SpaceMarine;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Команда 'print_asceding'
 * выводит элементы коллекции в порядке возрастания
 */

public class PrintAsceding extends Command {
    private ConsoleOutput consoleOutput;
    private CollectionManager collectionManager;

    public PrintAsceding(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("print_asceding", " : вывести элементы коллекции в порядке возрастания");
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) {
        if (collectionManager.getCollection() == null || collectionManager.getCollection().isEmpty()) {
            consoleOutput.printError("Тут пуста, выводить нечего, братиш");
            return;
        }
//        collectionManager.getCollection().stream()
//               .filter(Objects::nonNull).sorted(SpaceMarine::compareTo).
//               collect(Collectors.toList());
        System.out.println(collectionManager.getCollection().stream()
                .filter(Objects::nonNull)
                .sorted(SpaceMarine::compareTo).collect(Collectors.toList()));


    }
}
