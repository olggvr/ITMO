package models.audience;

import except.WrongAngleException;
import interfaces.IEssence;
import interfaces.IShortest;
import models.human.Shortest;
import properties.ReactionDegree;
import interfaces.ILaugh;

public class Audience extends AbstractAudience implements ILaugh, IEssence {

    public boolean not_like=false;
    public boolean isShortFunny = false;

    public Audience(String name) {
        super(name);
    }

    private ReactionDegree audState;

    @Override
    public void laugh(ReactionDegree degree) {
        System.out.println(getName() + " " + degree + " смешилась");
        isShortFunny = true;
    }

    public void dont_like(boolean type, IShortest shortest) throws WrongAngleException {
        this.not_like=type;
        if (not_like){
            shortest.move();
        }
    }
}
