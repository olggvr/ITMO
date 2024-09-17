package ru.oleg.managers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.oleg.App;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Класс хранящий коллекцию fixedThreadPool для их проверки c помощью Future
 */
public class PoolManager {
    private static final Collection<Future<ConnectionManagerPool>> fixedThreadPoolFutures = new ArrayList<>();
    private static final Logger pullManagerLogger = LogManager.getLogger(PoolManager.class);


    public static void addNewFixedThreadPoolFuture(Future<ConnectionManagerPool> future) {
        fixedThreadPoolFutures.add(future);
    }


    public static void checkAllFutures() {
        if (!fixedThreadPoolFutures.isEmpty()) {
            App.rootLogger.debug("------------------------СПИСОК ПОТОКОВ---------------------------");
            fixedThreadPoolFutures.forEach(s -> pullManagerLogger.debug(s.toString()));
            App.rootLogger.debug("-------------------------------ЗЭ ЭНД----------------------------------");
        }
        fixedThreadPoolFutures.stream()
                .filter(Future::isDone)
                .forEach(f -> {
                    try {
                        ConnectionManager.submitNewResponse(f.get());

                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });
        fixedThreadPoolFutures.removeIf(Future::isDone);
    }
}
