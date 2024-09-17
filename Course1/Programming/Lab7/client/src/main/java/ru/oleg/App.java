package ru.oleg;


import ru.oleg.commandLine.ConsoleOutput;
import ru.oleg.commandLine.Printable;
import ru.oleg.commands.*;
import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.utility.Client;
import ru.oleg.utility.InputManager;

import java.util.Scanner;

public class App {
    private static String host;
    private static int port;
    private static final Printable console = new ConsoleOutput();

    private static final CommandManager commandManager = new CommandManager();


    public static boolean parseHostPort(String[] args) {
        try {
            if (args.length != 2) throw new IllegalArgumentsException("Передайте хост и порт в аргументы " +
                    "командной строки в формате <host> <port>");
            host = args[0];
            port = Integer.parseInt(args[1]);
            if (port < 0) throw new IllegalArgumentsException("Порт должен быть натуральным числом");
            return true;
        } catch (IllegalArgumentsException e) {
            console.printError(e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        if (!parseHostPort(args)) return;

        Client client = new Client(host, port, console);
        commandManager.addCommand(new Add(console));
        commandManager.addCommand(new Update(console));
        commandManager.addCommand(new AddIfMin(console));
        commandManager.addCommand(new RemoveLower(console));
        new InputManager(console, new Scanner(System.in), client, commandManager).interactiveMode();

    }
}
