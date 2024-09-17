package models.human;

import interfaces.IEssence;

import java.util.Objects;

public abstract class AbstractEssence implements IEssence {

    private String name;

    public AbstractEssence(String name) {
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEssence that = (AbstractEssence) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
