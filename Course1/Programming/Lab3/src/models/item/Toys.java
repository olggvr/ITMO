package models.item;

import interfaces.IBounce;
import interfaces.IThrowTo;
import models.human.AbstractEssence;
import models.human.Shortest;

public class Toys extends Items implements IThrowTo, IBounce {
    public Toys(String type) {
        super(type);
    }

    public boolean isFly = false;

    @Override
    public void throwTo(AbstractEssence fromWhere, AbstractEssence target, Toys toy) {
        if (target instanceof Shortest short1 && short1.isPushedHead) {
            System.out.println(toy.getThingType() + " полетели в " + target.getName());
            isFly = true;
        }
    }

    @Override
    public void bounce(int health, int normalHealth, Items target) {
        if(health == normalHealth) System.out.println(getThingType() + " отскакивали не причинив вреда здоровью и падали на " + target.getThingType());
    }
}
