package console;

/**
 * Класс для возможных цветов текста
 */
public enum OutputColors {
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    PURPLE("\u001B[35m"),
    BLUE("\u001B[34m"),
    CYAN("\u001B[36m"),
    RESET("\u001B[0m"),
    WHITE("\u001B[37m"),


    ;

    private final String title;

    OutputColors(String title) {
        this.title = title;
    }

    /**
     * Основной метод раскрашивания текста
     *
     * @param s     строка которую нужно покрасить
     * @param color значение цвета
     * @return цветная строка для вывода в консоль
     */
    public static String toColor(String s, OutputColors color) {
        return color + s + OutputColors.RESET;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
