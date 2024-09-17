package commands;

import console.ConsoleOutput;
import console.OutputColors;
import console.ExecuteManager;
import exceptions.CommandRuntimeException;
import exceptions.ExitException;
import exceptions.IllegalArgumentsException;
import exceptions.NoCommandException;
import managers.CommandManager;
import managers.FileManager;

import java.io.*;
import java.util.NoSuchElementException;

/**
 * Команда 'execute_script'
 * Считать и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public class ExecuteScript extends Command {
    private final FileManager fileManager;
    private final ConsoleOutput consoleOutput;
    private final CommandManager commandManager;

    public ExecuteScript(ConsoleOutput consoleOutput, FileManager fileManager, CommandManager commandManager) {
        super("execute_script", " file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.fileManager = fileManager;
        this.consoleOutput = consoleOutput;
        this.commandManager = commandManager;
    }

    /**
     * Исполнить команду
     *
     * @param args аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     * @throws CommandRuntimeException   команда вызвала ошибку при исполнении
     * @throws ExitException             требуется выход из программы
     */
    @Override
    public void execute(String args) throws CommandRuntimeException, ExitException, IllegalArgumentsException {
        if (args == null || args.isEmpty()) {
            consoleOutput.printError("Путь не распознан");
            return;
        } else consoleOutput.println(OutputColors.toColor("Путь получен успешно", OutputColors.CYAN));

        try {
            ConsoleOutput.setFileMode(true);
            ExecuteManager.pushFile(args);
            for (String line = ExecuteManager.readLine(); line != null; line = ExecuteManager.readLine()) {
                try {
                    commandManager.addToHistory(line);
                    String[] cmd = (line + " ").split(" ", 3);
                    if (cmd[0].isBlank()) return;
                    if (cmd[0].equals("execute_script")) {
                        if (ExecuteManager.fileRepeat(cmd[1])) {
                            consoleOutput.printError("Найдена рекурсия по пути " + new File(cmd[1]).getAbsolutePath());
                            continue;
                        }
                    }
                    consoleOutput.println(OutputColors.toColor("Выполнение команды " + cmd[0], OutputColors.YELLOW));
                    commandManager.execute(cmd[0], cmd[1]);
                    if (cmd[0].equals("execute_script")) {
                        ExecuteManager.popFile();
                    }
                } catch (NoSuchElementException exception) {
                    consoleOutput.printError("Пользовательский ввод не обнаружен!");
                } catch (NoCommandException noCommandException) {
                    consoleOutput.printError("Такой команды нет в списке");
                } catch (IllegalArgumentsException e) {
                    consoleOutput.printError("Введены неправильные аргументы команды");
                } catch (CommandRuntimeException e) {
                    consoleOutput.printError("Ошибка при исполнении команды");
                }
            }
            ExecuteManager.popFile();
        } catch (NoCommandException noCommandException) {
            consoleOutput.printError("Такой команды не существует");
        } catch (FileNotFoundException fileNotFoundException) {
            consoleOutput.printError("Такого файла не существует");
        } catch (IOException e) {
            consoleOutput.printError("Ошибка ввода вывода");
        }
        ConsoleOutput.setFileMode(false);
    }
}
