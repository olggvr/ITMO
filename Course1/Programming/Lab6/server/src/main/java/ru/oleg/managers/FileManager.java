package ru.oleg.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.oleg.exceptions.ExitException;
import ru.oleg.exceptions.InvalidFormException;
import ru.oleg.models.SpaceMarine;
import ru.oleg.utility.LocalDateTimeAdapter;
import ru.oleg.utility.OutputColors;
import ru.oleg.utility.Printable;

import java.io.*;
import java.time.LocalDateTime;

/**
 * Класс реализующий работу с файлами
 */
public class FileManager {
    private final String file_path;
    private String text;
    private final Printable console;
    private final Gson gson;
    private final CollectionManager collectionManager;
    static final Logger fileManagerLogger = LogManager.getLogger(FileManager.class);


    /**
     * В конструкторе задаются алиасы для библиотеки Gson
     *
     * @param console           Пользовательский ввод-вывод
     * @param collectionManager Работа с коллекцией
     */
    public FileManager(Printable console, CollectionManager collectionManager, String file_path) {
        this.console = console;
        this.collectionManager = collectionManager;
        this.file_path = file_path;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting()
                .create();
        fileManagerLogger.info("Создание алиасы");
    }

    /**
     * Обращение к переменным среды и чтение файла в поле по указанному пути
     *
     * @throws ExitException если путь - null или отсутствует программа заканчивает выполнение
     */
    public void findFile() throws ExitException {

        if (file_path == null || file_path.isEmpty()) {
            console.printError("Путь должен быть в переменных окружения в переменной 'file_path'");
            fileManagerLogger.fatal("Нет пути в переменных окружения");
            throw new ExitException();
        } else {
            console.println(OutputColors.toColor("Путь получен успешно", OutputColors.PURPLE));
            fileManagerLogger.info("Путь получен успешно");
        }

        File file = new File(file_path);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            fileManagerLogger.info("Файл прочитан");
            if (stringBuilder.isEmpty()) {
                console.printError("Файл пустой");
                fileManagerLogger.info("Файл пустой");
                this.text = "[]";
                return;
            }
            this.text = stringBuilder.toString();
        } catch (FileNotFoundException fnfe) {
            console.printError("Такого файла не найдено");
            fileManagerLogger.fatal("Такого файла не найдено");
            throw new ExitException();
        } catch (IOException ioe) {
            console.printError("Ошибка ввода/вывода" + ioe);
            fileManagerLogger.fatal("Ошибка ввода/вывода" + ioe);
            throw new ExitException();
        }
    }

    /**
     * Создание объектов в консольном менеджере
     *
     * @throws ExitException Если объекты в файле невалидны выходим из программы
     */

    public void createObjects() throws ExitException {
        try {
            CollectionManager collectionManagerWithObjects = gson.fromJson(this.text, CollectionManager.class);

            for (SpaceMarine s : collectionManagerWithObjects.getCollection()) {
                if (this.collectionManager.checkExist(s.getId())) {
                    console.printError("В файле повторяются айди!");
                    fileManagerLogger.fatal("В файле повторяются айди!");
                    throw new ExitException();
                }
            }
            this.collectionManager.addElements(collectionManagerWithObjects.getCollection());
        } catch (InvalidFormException e) {
            console.printError("Объекты в файле не валидны");
            throw new ExitException();
        }
        console.println("Получены объекты:\n" + collectionManager.getCollection().toString());
        SpaceMarine.updateId(collectionManager.getCollection());
    }


    /**
     * Сохраняем коллекцию из менеджера в файл
     */
    public void saveObjects() {

        if (file_path == null || file_path.isEmpty()) {
            console.printError("Путь должен быть в переменных окружения в переменной 'file_path'");
            fileManagerLogger.fatal("Отсутствует путь в переменных окружения");
            return;
        } else {
            console.println(OutputColors.toColor("Путь получен успешно", OutputColors.PURPLE));
            fileManagerLogger.info(OutputColors.toColor("Путь получен успешно", OutputColors.PURPLE));
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_path))) {
//        try (Writer writer = new FileWriter(file_path)) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
            gson.toJson(collectionManager, writer);
            fileManagerLogger.info("Файл записан");
        } catch (IOException e) {
            console.printError("Ошибка ввода вывода");
            fileManagerLogger.error("Ошибка ввода вывода");
        }
    }
}