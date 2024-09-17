package models.human;

import interfaces.*;
import interfaces.Runnable;
import models.item.Items;
import properties.Attitudes;
import properties.BodyPartState;
import properties.HumanState;
import properties.MoveDegree;

public class Goat extends AbstractEssence implements IEssence, Holdable, Runnable, Opportunity {
    public Goat(String name) {
        super(name);
    }
    public static int molesCount = 10;
    static class Hands extends BodyParts implements BodyPart {
        public Hands(String name) {
            super(name);
        }
        public int getHandMoles(){return molesCount - 3;}
    }
    public Head head = new Head("голова");
    Hands hand = new Hands("рука");

    @Override
    public void hold(BodyPartState eyeState){
        System.out.println(getName() + " схватился " + hand.getPartName() + " за " +
                eyeState + " " + head.eyes.getName());
        head.eyes.isEyeDamaged = true;
    }

    @Override
    public void runAway(MoveDegree moveDegree, Items target) {
        //...
        //...
    }


    @Override
    public void seeOpportunityToEarn(Attitudes attitude, HumanState humanState, HumanState goatState, IEssence person) {
        class Money{
            public int count = 100;
            private final String name;

            Money(String name){this.name = name;}

        }
        Money money = new Money("денег");
        System.out.println(getName() + " " + attitude + " " + humanState + " " + person.getName());
        System.out.println("но " + getName() + " " + goatState + ", что может заработать " + money.name);
    }
}
