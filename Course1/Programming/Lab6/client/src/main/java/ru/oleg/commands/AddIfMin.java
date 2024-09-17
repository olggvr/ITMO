package ru.oleg.commands;

import ru.oleg.asks.AskSpaceMarine;
import ru.oleg.commandLine.Printable;
import ru.oleg.exceptions.FIleFieldException;
import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.models.SpaceMarine;

public class AddIfMin extends Command{
    private final Printable console;

    public AddIfMin(Printable console) {
        super("add_if_min", "добавление элемента, если он наименьший");
        this.console = console;
    }

    public SpaceMarine execute(String args) throws IllegalArgumentsException{
        if(args.isBlank()) throw new IllegalArgumentsException();
        SpaceMarine spaceMarine = new AskSpaceMarine(console).build();
        if (!spaceMarine.validate()) throw new FIleFieldException();
        return spaceMarine;

    }
}
