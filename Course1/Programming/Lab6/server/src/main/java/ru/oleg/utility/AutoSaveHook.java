package ru.oleg.utility;

import ru.oleg.managers.FileManager;

public class AutoSaveHook implements Runnable {


    private final FileManager fileManager;

    public AutoSaveHook(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void run() {
        fileManager.saveObjects();
    }
}
