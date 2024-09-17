package ru.oleg.commands;


import ru.oleg.asks.AskSpaceMarine;
import ru.oleg.commandLine.Printable;
import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.models.SpaceMarine;

public class Add extends Command {
    private final Printable console;


    public Add(Printable console) {
        super("add", "добавление элемента");
        this.console = console;
    }


    public SpaceMarine execute(String args) throws IllegalArgumentsException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        return new AskSpaceMarine(console).build();
    }


}
