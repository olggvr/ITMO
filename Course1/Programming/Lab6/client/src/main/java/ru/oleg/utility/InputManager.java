package ru.oleg.utility;


import ru.oleg.commandLine.ConsoleOutput;
import ru.oleg.commandLine.Printable;
import ru.oleg.commands.CommandManager;
import ru.oleg.exceptions.ExitException;
import ru.oleg.exceptions.IllegalArgumentsException;
import ru.oleg.exceptions.InvalidFormException;
import ru.oleg.exceptions.NoCommandException;
import ru.oleg.models.SpaceMarine;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;


/**
 * Класс обработки пользовательского ввода
 */
public class InputManager {
    private final Printable console;
    private final Scanner userScanner;
    private final Client client;
    private final CommandManager commandManager;


    public InputManager(Printable console, Scanner userScanner, Client client, CommandManager commandManager) {
        this.console = console;
        this.userScanner = userScanner;
        this.client = client;
        this.commandManager = commandManager;
    }


    /**
     * Перманентная работа с пользователем и выполнение команд
     */
    public void interactiveMode() {
        while (true) {
            try {
                if (!userScanner.hasNext()) throw new ExitException();
                String[] commandParts = (userScanner.nextLine().trim() + " ").split(" ", 2); // прибавляем пробел, чтобы split выдал два элемента в массиве
                if (commandParts.length > 1) {
                    commandParts[0] = commandParts[0].trim();
                    commandParts[1] = commandParts[1].trim();
                }
                if (commandManager.containsCommand(commandParts[0])) {
                    SpaceMarine spaceMarine = commandManager.execute(commandManager.getCommand(commandParts[0]), commandParts[1]);
                    Response response = client.sendAndAskResponse(new Request(commandParts[0], commandParts[1], spaceMarine));
                    if (response.getStatus() != ResponseStatus.OK) {
                        console.printError(response.getResponse());
                    } else {
                        this.printResponse(response);
                    }
                } else {
                    Response response = client.sendAndAskResponse(new Request(commandParts[0].trim(), commandParts[1].trim()));
                    this.printResponse(response);
                    switch (response.getStatus()) {
                        case EXIT -> throw new ExitException();
                        case EXECUTE_SCRIPT -> {
                            ConsoleOutput.setFileMode(true);
                            this.fileExecution(response.getResponse());
                            ConsoleOutput.setFileMode(false);
                        }
                        default -> {
                        }
                    }

                }
            } catch (NoSuchElementException exception) {
                console.printError("Пользовательский ввод не обнаружен!");
            } catch (ExitException exitException) {
                console.println(OutputColors.toColor("До свидания!", OutputColors.YELLOW));
                return;
            } catch (NoCommandException err) {
                console.printError("Команды не найдено");
            } catch (InvalidFormException err) {
                console.printError("Поля не валидны!");
            } catch (IllegalArgumentsException e) {
                console.printError("Неверные аргументы команды!");

            }


//                for (String command : commandList) {
//                    if (Objects.equals(userCommand[0], command)) {
//                        SpaceMarine spaceMarine = new AskSpaceMarine(console).build();
//                        if (!spaceMarine.validate()) throw new InvalidFormException();
//                        Response newResponse = client.sendAndAskResponse(
//                                new Request(
//                                        userCommand[0].trim(),
//                                        userCommand[1].trim(),
//                                        spaceMarine));
//                        if (newResponse.getStatus() != ResponseStatus.OK) {
//                            console.printError(newResponse.getResponse());
//                        } else {
//                            this.printResponse(newResponse);
//                        }
//                        break;
//                    }
//                    else{
//                        Response response = client.sendAndAskResponse(new Request(userCommand[0].trim(), userCommand[1].trim()));
//                        this.printResponse(response);
//                        switch (response.getStatus()) {
//                            case ASK_OBJECT -> {
//                                SpaceMarine spaceMarine = new AskSpaceMarine(console).build();
//                                if (!spaceMarine.validate()) throw new InvalidFormException();
//                                Response newResponse = client.sendAndAskResponse(
//                                        new Request(
//                                                userCommand[0].trim(),
//                                                userCommand[1].trim(),
//                                                spaceMarine));
//                                if (newResponse.getStatus() != ResponseStatus.OK) {
//                                    console.printError(newResponse.getResponse());
//                                } else {
//                                    this.printResponse(newResponse);
//
//                                }
//
//                            }
//
//                            case EXIT -> throw new ExitException();
//                            case EXECUTE_SCRIPT -> {
//                                ConsoleOutput.setFileMode(true);
//                                this.fileExecution(response.getResponse());
//                                ConsoleOutput.setFileMode(false);
//                            }
//                            default -> {
//                            }
//                        }
//
//
//                        break;
//
//                    }
//                }


//            } catch (InvalidFormException err) {
//                console.printError("Поля не валидны! Объект не создан");
//            } catch (NoSuchElementException exception) {
//                console.printError("Пользовательский ввод не обнаружен!");
//            } catch (ExitException exitObliged) {
//                console.println(OutputColors.toColor("До свидания!", OutputColors.YELLOW));
//                return;
//            }
        }
    }

    private void printResponse(Response response) {
        switch (response.getStatus()) {
            case OK -> {
                if ((Objects.isNull(response.getCollection()))) {
                    console.println(response.getResponse());
                } else {
                    console.println(response.getResponse() + "\n" + response.getCollection().toString());
                }
            }
            case ERROR -> console.printError(response.getResponse());
            case WRONG_ARGUMENTS -> console.printError("Неверное использование команды!");
            default -> {
            }
        }
    }

    private void fileExecution(String args) throws ExitException {
        if (args == null || args.isEmpty()) {
            console.printError("Путь не распознан");
            return;
        } else console.println(OutputColors.toColor("Путь получен успешно", OutputColors.PURPLE));
        args = args.trim();
        try {
            ExecuteManager.pushFile(args);
            for (String line = ExecuteManager.readLine(); line != null; line = ExecuteManager.readLine()) {
                String[] userCommand = (line + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                if (userCommand[0].isBlank()) return;
                if (userCommand[0].equals("execute_script")) {
                    if (ExecuteManager.fileRepeat(userCommand[1])) {
                        console.printError("Найдена рекурсия по пути " + new File(userCommand[1]).getAbsolutePath());

                    }
                } else if (commandManager.containsCommand(userCommand[0])) {
                    SpaceMarine spaceMarine = commandManager.execute(commandManager.getCommand(userCommand[0]), userCommand[1]);
                    Response response = client.sendAndAskResponse(new Request(userCommand[0], userCommand[1], spaceMarine));
                    if (response.getStatus() != ResponseStatus.OK) {
                        console.printError(response.getResponse());
                    } else {
                        this.printResponse(response);
                    }
                } else {
                    console.println(OutputColors.toColor("Выполнение команды " + userCommand[0], OutputColors.YELLOW));
                    Response response = client.sendAndAskResponse(new Request(userCommand[0].trim(), userCommand[1].trim()));
                    this.printResponse(response);
                    switch (response.getStatus()) {
                        case EXIT -> throw new ExitException();
                        case EXECUTE_SCRIPT -> {
                            this.fileExecution(response.getResponse());
                            ExecuteManager.popRecursion();
                        }
                        default -> {
                        }
                    }
                }

            }
            ExecuteManager.popFile();
        } catch (FileNotFoundException fileNotFoundException) {
            console.printError("Такого файла не существует");
        } catch (IOException e) {
            console.printError("Ошибка ввода вывода");
        }
    }
}

