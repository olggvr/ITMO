package ru.oleg.managers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.oleg.exceptions.InvalidFormException;
import ru.oleg.models.SpaceMarine;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.PriorityQueue;

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

    static final Logger collectionManagerLogger = LogManager.getLogger(CollectionManager.class);

    public CollectionManager() {
        this.lastInitTime = LocalDateTime.now();
        this.lastSaveTime = null;
    }

    public PriorityQueue<SpaceMarine> getCollection() {
        return collection;
    }
//
//    public static void updateId(Collection<SpaceMarine> collection) {
//        nextId = collection.stream()
//                .filter(Objects::nonNull)
//                .map(SpaceMarine::getId)
//                .max(Long::compareTo)
//                .orElse(0L);
//        collectionManagerLogger.info("Обновлен айди на " + nextId);
//    }


    public String getLastInitTime() {
        if (lastInitTime != null) {
            return lastInitTime.toString();
        }
        return null;
    }

    public String getLastSaveTime() {
        if (lastSaveTime != null) {
            return lastSaveTime.toString();
        }
        return null;
    }

    /**
     * @return Имя типа коллекции.
     */
    public String collectionType() {
        return collection.getClass().getName();
    }

    public int collectionSize() {
        return collection.size();
    }

    public void clear() {
        this.collection.clear();
        lastInitTime = LocalDateTime.now();
    }

    public SpaceMarine getFirst() {
        return collection.peek();
    }

    /**
     * @param id ID элемента.
     * @return Элемент по его ID или null, если не найдено.
     */
    public SpaceMarine getById(Long id) {
        for (SpaceMarine element : collection) {
            if (element.getId().equals(id)) return element;
        }
        return null;
    }

    /**
     * Изменить элемент коллекции с таким id
     *
     * @param id         id
     * @param newElement новый элемент
     * @throws InvalidFormException Нет элемента с таким id
     */
    public void editById(Long id, SpaceMarine newElement) throws InvalidFormException {
        SpaceMarine pastElement = this.getById(id);
        if (pastElement == null) {
            throw new InvalidFormException();
        }
        this.collection.remove(pastElement);
        newElement.setId(id);
        this.addElement(newElement);
        SpaceMarine.updateId(this.getCollection());
    }

    /**
     * @param id ID элемента.
     * @return Проверяет, существует ли элемент с таким ID.
     */
    public boolean checkExist(Long id) {
        return collection.stream()
                .anyMatch((x) -> x.getId().equals(id));
    }

    public void addElement(SpaceMarine spaceMarine) throws InvalidFormException {
        this.lastSaveTime = LocalDateTime.now();
        if (spaceMarine != null){
        if (!spaceMarine.validate()) throw new InvalidFormException();
        spaceMarine.setId(getLastId() + 1);
        collection.add(spaceMarine);}
    }

    public void addElements(Collection<SpaceMarine> collection) throws InvalidFormException {
        if (collection == null) return;
        for (SpaceMarine spaceMarine : collection) {
            this.addElement(spaceMarine);
        }
    }

    public void removeElement(SpaceMarine spaceMarine) {
        collection.remove(spaceMarine);
    }


    public void removeElements(Collection<SpaceMarine> collection) {
        this.collection.removeAll(collection);
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

