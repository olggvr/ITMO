package ru.oleg.utility;


import ru.oleg.asks.UserForm;
import ru.oleg.commandLine.ConsoleOutput;
import ru.oleg.commandLine.Printable;
import ru.oleg.commands.CommandManager;
import ru.oleg.exceptions.*;
import ru.oleg.models.SpaceMarine;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;
import ru.oleg.network.User;

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
    private User user = null;


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
                if (Objects.isNull(user)) {
                    Response response = null;
                    boolean isLogin = true;
                    do {
                        if (!Objects.isNull(response)) {
                            console.println((isLogin)
                                    ? "Такой связки логин-пароль нет, попробуйте снова!"
                                    : "Этот логин уже занят, попробуйте снова!");
                        }
                        UserForm userForm = new UserForm(console);
                        isLogin = userForm.askIfLogin();
                        user = new UserForm(console).build();
                        if (isLogin) {
                            response = client.sendAndAskResponse(new Request("ping", "", user));
                        } else {
                            response = client.sendAndAskResponse(new Request("register", "", user));
                        }
                    } while (response.getStatus() != ResponseStatus.OK);
                    console.println("Вы успешно зашли в аккаунт");
                }

                if (!userScanner.hasNext()) throw new ExitException();
                String[] commandParts = (userScanner.nextLine().trim() + " ").split(" ", 2); // прибавляем пробел, чтобы split выдал два элемента в массиве
                if (commandParts.length > 1) {
                    commandParts[0] = commandParts[0].trim();
                    commandParts[1] = commandParts[1].trim();
                }
                if (commandManager.containsCommand(commandParts[0])) {
                    SpaceMarine spaceMarine = commandManager.execute(commandManager.getCommand(commandParts[0]), commandParts[1]);
                    Response response = client.sendAndAskResponse(new Request(commandParts[0].trim(), commandParts[1].trim(), user, spaceMarine));
                    if (response.getStatus() != ResponseStatus.OK) {
                        console.printError(response.getResponse());
                    } else {
                        this.printResponse(response);
                    }
                } else {
                    Response response = client.sendAndAskResponse(new Request(commandParts[0].trim(), commandParts[1].trim(), user));
                    this.printResponse(response);
                    switch (response.getStatus()) {
                        case EXIT -> throw new ExitException();
                        case EXECUTE_SCRIPT -> {
                            ConsoleOutput.setFileMode(true);
                            this.fileExecution(response.getResponse());
                            ConsoleOutput.setFileMode(false);
                        }
                        case LOGIN_FAILED -> {
                            console.printError("Ошибка с вашим аккаунтом. Зайдите в него снова");
                            this.user = null;
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
                    console.println(OutputColors.toColor("Выполнение команды " + userCommand[0], OutputColors.YELLOW));
                    SpaceMarine spaceMarine = commandManager.execute(commandManager.getCommand(userCommand[0]), userCommand[1]);
                    Response response = client.sendAndAskResponse(new Request(userCommand[0].trim(), userCommand[1].trim(), user, spaceMarine));
                    this.printResponse(response);
                } else {
                    console.println(OutputColors.toColor("Выполнение команды " + userCommand[0], OutputColors.YELLOW));
                    Response response = client.sendAndAskResponse(new Request(userCommand[0].trim(), userCommand[1].trim(), user));
                    this.printResponse(response);
                    switch (response.getStatus()) {
                        case EXIT -> throw new ExitException();
                        case EXECUTE_SCRIPT -> {
                            this.fileExecution(response.getResponse());
                            ExecuteManager.popRecursion();
                        }
                        case LOGIN_FAILED -> {
                            console.printError("Ошибка с вашим аккаунтом. Зайдите в него снова");
                            this.user = null;

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

