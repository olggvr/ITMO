package commands;


import exceptions.CommandRuntimeException;
import exceptions.ExitException;
import exceptions.IllegalArgumentsException;

import java.util.Objects;

/**
 * Абстрактный класс для всех команд
 */
public abstract class Command {
    private final String name;
    private final String description;


    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public void execute(String args) throws CommandRuntimeException, ExitException, IllegalArgumentsException {};

    public String getName() {
        return name;
    }



    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(name, command.name) &&
                Objects.equals(description, command.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

}
