package console;

public class ConsoleOutput implements Printable {

    private static boolean fileMode = false;

    public static boolean isFileMode() {
        return fileMode;
    }

    public static void setFileMode(boolean fileMode) {
        ConsoleOutput.fileMode = fileMode;
    }

    @Override
    public void println(String a) {
        System.out.println(a);
    }

    @Override
    public void print(String a) {
        System.out.print(a);
    }

    @Override
    public void printError(String a) {
        System.out.println(OutputColors.RED + a + OutputColors.RESET);
    }
}
