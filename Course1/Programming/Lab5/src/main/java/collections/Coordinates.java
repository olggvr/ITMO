package collections;

import java.util.Objects;

/**
 * Класс координат
 */

public class Coordinates implements FieldValidator {
    private Double x; //Поле не может быть null
    private double y; //Значение поля должно быть больше -273

    public Coordinates(Double x, Double y) {
        this.x = x;
        this.y = y;

    }


    public Double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    /**
     * Валидирует правильность полей.
     *
     * @return true, если все верно, иначе false
     */
    @Override
    public boolean validate() {
        if (this.x == null) return false;
        return !(this.y < -273);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinates that = (Coordinates) obj;
        return Double.compare(y, that.y) == 0 && Objects.equals(x, that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
