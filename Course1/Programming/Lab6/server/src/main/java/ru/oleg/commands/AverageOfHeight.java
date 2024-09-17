package ru.oleg.commands;


import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.managers.CollectionManager;
import ru.oleg.models.SpaceMarine;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;

/**
 * Команда 'average_of_height'
 * Выводит среднее значение поля height всех элементов
 */
public class AverageOfHeight extends Command {
    private final CollectionManager collectionManager;

    public AverageOfHeight(CollectionManager collectionManager) {
        super("average_of_height", " : вывести среднее значение поля height всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();


        return new Response(ResponseStatus.OK, "Среднее значение роста во всех элементах " + collectionManager.getCollection().stream()
                .mapToDouble(SpaceMarine::getHeight)
                .average()
                .orElse(0.0));


    }


}
