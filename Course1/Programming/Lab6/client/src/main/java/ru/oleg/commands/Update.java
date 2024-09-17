package ru.oleg.commands;


import ru.oleg.asks.AskSpaceMarine;
import ru.oleg.commandLine.Printable;
import ru.oleg.exceptions.FIleFieldException;
import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.models.SpaceMarine;

public class Update extends Command{
    private Printable console;

    public Update(Printable console){
        super("update", "обновление по id");
        this.console = console;
    }



    public SpaceMarine execute(String args) throws IllegalArgumentsException {
        if(args.isBlank()) throw new IllegalArgumentsException();
        SpaceMarine spaceMarine = new AskSpaceMarine(console).build();
        if (!spaceMarine.validate()) throw new FIleFieldException();
        return spaceMarine;

    }
}
