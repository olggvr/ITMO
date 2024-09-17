package ru.oleg.commands;


import ru.oleg.asks.AskSpaceMarine;
import ru.oleg.commandLine.Printable;
import ru.oleg.exceptions.FIleFieldException;
import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.exceptions.InvalidFormException;
import ru.oleg.models.SpaceMarine;

public class Add extends Command {
    private final Printable console;


    public Add(Printable console) {
        super("add", "добавление элемента");
        this.console = console;
    }


    public SpaceMarine execute(String args) throws InvalidFormException, IllegalArgumentsException {
        try {
            if (!args.isBlank()) throw new IllegalArgumentsException();

            SpaceMarine spaceMarine = new AskSpaceMarine(console).build();
            if (!spaceMarine.validate()) throw new InvalidFormException();
            return spaceMarine;
        }catch (FIleFieldException e){
            console.printError("Поля объекта не валидны!");
            return null;

        }
    }


}
