package commands;


import console.ConsoleOutput;
import console.OutputColors;
import exceptions.IllegalArgumentsException;
import managers.CollectionManager;

/**
 * Команда 'info'
 * Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
 */
public class Info extends Command {
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public Info(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("info", ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
        this.consoleOutput = consoleOutput;
    }

    /**
     * Исполнить команду
     *
     * @param args аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public void execute(String args) throws IllegalArgumentsException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        String lastInitTime = (collectionManager.getLastInitTime() == null)
                ? "В сессии коллекция не инициализирована"
                : collectionManager.getLastInitTime().toString();
        String lastSaveTime = (collectionManager.getLastSaveTime() == null)
                ? "В сессии коллекция не инициализирована "
                : collectionManager.getLastSaveTime().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Сведения о коллекции: \n")
                .append(OutputColors.toColor("Тип: ", OutputColors.GREEN) + collectionManager.collectionType() + "\n")
                .append(OutputColors.toColor("Количество элементов: ", OutputColors.GREEN) + collectionManager.collectionSize() + "\n")
                .append(OutputColors.toColor("Дата последней инициализации: ", OutputColors.GREEN) + lastInitTime + "\n")
                .append(OutputColors.toColor("Дата последнего изменения: ", OutputColors.GREEN) + lastSaveTime + "\n");
        consoleOutput.print(stringBuilder.toString());
    }
}
