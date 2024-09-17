package commands;

import console.ConsoleOutput;
import console.OutputColors;
import exceptions.IllegalArgumentsException;
import managers.CollectionManager;
import collections.SpaceMarine;

import java.util.Objects;

/**
 * Команда 'average_of_height'
 * Выводит среднее значение поля height всех элементов
 */
public class AverageOfHeight extends Command {
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public AverageOfHeight(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("average_of_height", " : вывести среднее значение поля height всех элементов коллекции");
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) throws IllegalArgumentsException {
        double averageHeight = collectionManager.getCollection().stream()
                .filter(Objects::nonNull)
                .mapToDouble(SpaceMarine::getHeight)
                .average()
                .getAsDouble();

        consoleOutput.print(OutputColors.toColor("Среднее значение поля height: ", OutputColors.GREEN));
        consoleOutput.println(String.format("%.2f", averageHeight));
    }


}
