package console;

import java.io.*;
import java.util.ArrayDeque;

/**
 * Класс для хранения файл менеджера для команды execute
 */
public class ExecuteManager extends ConsoleInput {
    private static final ArrayDeque<String> pathQueue = new ArrayDeque<>();
    private static final ArrayDeque<BufferedReader> fileReaders = new ArrayDeque<>();

    public static void pushFile(String path) throws FileNotFoundException {
        pathQueue.push(new File(path).getAbsolutePath());
        fileReaders.push(new BufferedReader(new InputStreamReader(new FileInputStream(path))));
    }

    public static File getFile() {
        return new File(pathQueue.getLast());
    }

    public static String readLine() throws IOException {
        return fileReaders.getFirst().readLine();
    }

    public static void popFile() throws IOException {
        fileReaders.getFirst().close();
        fileReaders.pop();
        pathQueue.pop();
    }

    public static boolean fileRepeat(String path) {
        return pathQueue.contains(new File(path).getAbsolutePath());
    }

}
