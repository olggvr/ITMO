package collections;

import console.OutputColors;

import java.util.Objects;

/**
 * Класс главы
 */
public class Chapter implements FieldValidator {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Integer marinesCount; //Поле может быть null, Значение поля
    // должно быть больше 0, Максимальное значение поля: 1000

    public Chapter(String name, Integer marinesCount) {
        this.name = name;
        this.marinesCount = marinesCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarinesCount() {
        return marinesCount;
    }

    public void setMarinesCount(Integer marinesCount) {
        this.marinesCount = marinesCount;
    }

    @Override
    public boolean validate() {
        if (this.name == null || this.name.isEmpty()) return false;
        return !(this.marinesCount < 0 || this.marinesCount > 1000);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Chapter chapter = (Chapter) obj;
        return Objects.equals(name, chapter.name) && Objects.equals(marinesCount, chapter.marinesCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, marinesCount);
    }

    @Override
    public String toString() {
        return "Chapter{" +
                OutputColors.toColor("name: ", OutputColors.BLUE) + name + '\n' +
                OutputColors.toColor("marinesCount: ", OutputColors.BLUE) + marinesCount + '\n' +
                '}';
    }
}
