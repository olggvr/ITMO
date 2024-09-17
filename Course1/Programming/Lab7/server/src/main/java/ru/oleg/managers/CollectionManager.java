package ru.oleg.managers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.oleg.exceptions.InvalidFormException;
import ru.oleg.models.SpaceMarine;
import ru.oleg.utility.DatabaseHandler;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс, организующий работу с коллекцией
 */
public class CollectionManager {
    private final PriorityQueue<SpaceMarine> collection = new PriorityQueue<>();

    /**
     * Дата создания коллекции
     */
    private LocalDateTime lastInitTime;
    /**
     * Дата последнего изменения коллекции
     */
    private LocalDateTime lastSaveTime;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock writeLock = lock.writeLock();
    Lock readLock = lock.readLock();

    private static final Logger collectionManagerLogger = LogManager.getLogger(CollectionManager.class);

    public CollectionManager() {
        this.lastInitTime = LocalDateTime.now();
        this.lastSaveTime = null;
        collection.addAll(DatabaseHandler.getDatabaseManager().loadCollection());
    }

    public PriorityQueue<SpaceMarine> getCollection() {
        try {
            readLock.lock();
            return collection;
        } finally {
            readLock.unlock();
        }
    }


    public String getLastInitTime() {
        try {
            readLock.lock();
            return lastInitTime.toString();
        } finally {
            readLock.unlock();
        }
    }

    public String getLastSaveTime() {
        try {
            readLock.lock();
            return lastInitTime.toString();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * @return Имя типа коллекции.
     */
    public String collectionType() {
        try {
            readLock.lock();
            return collection.getClass().getName();
        } finally {
            readLock.unlock();
        }
    }

    public int collectionSize() {
        try {
            readLock.lock();
            return collection.size();
        } finally {
            readLock.unlock();
        }
    }

    public void clear() {
        try {
            writeLock.lock();
            this.collection.clear();
            lastInitTime = LocalDateTime.now();
            collectionManagerLogger.info("Коллекция очищена");
        } finally {
            writeLock.unlock();
        }
    }

    public SpaceMarine getFirst() {
        try {
            readLock.lock();
            return collection.peek();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * @param id ID элемента.
     * @return Элемент по его ID или null, если не найдено.
     */
    public SpaceMarine getById(Long id) {
        try {
            readLock.lock();
            for (SpaceMarine element : collection) {
                if (Objects.equals(element.getId(), id)) return element;
            }
            return null;
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Изменить элемент коллекции с таким id
     *
     * @param id         id
     * @param newElement новый элемент
     * @throws InvalidFormException Нет элемента с таким id
     */
    public void editById(Long id, SpaceMarine newElement) throws InvalidFormException {
        try {
            writeLock.lock();
            SpaceMarine pastElement = this.getById(id);
            this.removeElement(pastElement);
            newElement.setId(id);
            this.addElement(newElement);
            collectionManagerLogger.info("Объект с айди " + id + " изменен", newElement);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * @param id ID элемента.
     * @return Проверяет, существует ли элемент с таким ID.
     */
    public boolean checkExist(Long id) {
        try {
            readLock.lock();
            return collection.stream()
                    .anyMatch((x) -> Objects.equals(x.getId(), id));
        } finally {
            readLock.unlock();
        }
    }

    public void addElement(SpaceMarine spaceMarine) {
        try {
            writeLock.lock();
            this.lastSaveTime = LocalDateTime.now();
            collection.add(spaceMarine);
            collectionManagerLogger.info("Добавлен объект в коллекцию", spaceMarine);
        } finally {
            writeLock.unlock();
        }
    }

    public void addElements(Collection<SpaceMarine> collection) {
        if (collection == null) return;
        for (SpaceMarine spaceMarine : collection) {
            this.addElement(spaceMarine);
        }
    }

    public void removeElement(SpaceMarine spaceMarine) {
        try {
            writeLock.lock();
            collection.remove(spaceMarine);
        } finally {
            writeLock.unlock();
        }
    }


    public void removeElements(Collection<SpaceMarine> collection) {
        try {
            writeLock.lock();
            this.collection.removeAll(collection);
        } finally {
            writeLock.unlock();
        }
    }

    public void removeElements(List<Integer> deletedIds) {
        try {
            writeLock.lock();
            deletedIds
                    .forEach((id) -> this.collection.remove(this.getById((long) id)));
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекшн из емпти, бразе!";
        var last = getFirst();

        StringBuilder info = new StringBuilder();
        for (SpaceMarine spaceMarine : collection) {
            info.append(spaceMarine);
            if (spaceMarine != last) info.append("\n\n");
        }
        return info.toString();
    }

    public Long getLastId() {
        return getCollection().stream()
                .mapToLong(SpaceMarine::getId)
                .max()
                .orElse(0L);
    }
}

