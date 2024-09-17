package managers;

import console.ConsoleOutput;
import console.OutputColors;
import console.Printable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ExitException;
import exceptions.InvalidFormException;
import collections.SpaceMarine;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * Класс реализующий работу с файлами
 *
 */
public class FileManager {
    private String text;
    private final Printable console;
    private final CollectionManager collectionManager;
    private final Gson gson;
    private final String file_path;

    /**
     * В конструкторе инициализируем Gson и другие переменные
     *
     * @param consoleOutput     Пользовательский ввод-вывод
     * @param collectionManager Работа с коллекцией
     */

    public FileManager(ConsoleOutput consoleOutput, CollectionManager collectionManager, String file_path) {
        this.console = consoleOutput;
        this.collectionManager = collectionManager;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting()
                .create();
        this.file_path = file_path;
    }


    /**
     * Обращение к переменным среды и чтение файла в поле по указанному пути
     *
     * @throws ExitException если путь - null или отсутствует, то программа заканчивает выполнение
     */
    public void findFile() throws ExitException {
        if (file_path == null || file_path.isEmpty()) {
            console.printError("Путь к файлу должен быть передан в качестве аргумента командной строки");
            throw new ExitException();
        } else {
            console.println(OutputColors.toColor("Путь получен успешно", OutputColors.CYAN));
        }

        File file = new File(file_path);

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String line = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                stringBuilder.append(line);
            }
            this.text = stringBuilder.toString();
            if (this.text.isEmpty()) {
                console.printError("Файл пустой");
                this.text = gson.toJson(collectionManager); // Сохраняем текущее состояние коллекции
            }
        } catch (FileNotFoundException fnfe) {
            console.printError("Такого файла не найдено");
            throw new ExitException();
        } catch (IOException ioe) {
            console.printError("Ошибка ввода/вывода" + ioe);
            throw new ExitException();
        }

    }

    /**
     * Создание объектов в консольном менеджере из JSON
     *
     * @throws ExitException Если объекты в файле невалидны выходим из программы
     */
    public void createObjects() throws ExitException {
        try {
            CollectionManager collectionManagerWithObjects = gson.fromJson(this.text, CollectionManager.class);

            for (SpaceMarine s : collectionManagerWithObjects.getCollection()) {
                if (this.collectionManager.checkExist(s.getId())) {
                    console.printError("В файле повторяются айди!");
                    throw new ExitException();
                }
            }
            this.collectionManager.addElements(collectionManagerWithObjects.getCollection());
        } catch (InvalidFormException e) {
            console.printError("Объекты в файле не валидны");
            throw new ExitException();
        }
        SpaceMarine.updateId(collectionManager.getCollection());
    }

    /**
     * Сохраняем коллекцию из менеджера в файл в формате JSON
     */
    public void saveObjects() {

        if (file_path == null || file_path.isEmpty()) {
            console.printError("Путь должен быть в аргументе командной строки в переменной 'file_path'");
        } else {
            console.println(OutputColors.toColor("Путь получен успешно", OutputColors.CYAN));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_path))) {
            gson.toJson(collectionManager, writer);
        } catch (FileNotFoundException e) {
            console.printError("Файл не существует");
        } catch (IOException e) {
            console.printError("Ошибка ввода вывода");
        }

    }
}
