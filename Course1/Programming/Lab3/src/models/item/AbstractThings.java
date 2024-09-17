package models.item;

import java.util.Objects;

public abstract class AbstractThings {

    private String type;

    public AbstractThings(String type){this.type = type;}

    public String getThingType(){return this.type;}

    public void setThingType(String type){this.type = type;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractThings that = (AbstractThings) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
