package models.human;

import interfaces.ISatisfied;
import interfaces.IShortest;
import properties.HumanState;

public class Owner extends AbstractEssence implements ISatisfied {
    public Owner(String name) {
        super(name);
    }

    private HumanState humanState;

    @Override
    public void satisfied(boolean isFunny, boolean isAttention, IShortest shortest){
        if(isFunny && isAttention) System.out.println(getName() + " очень доволен, " + shortest.getName() + " - хороший работник");
    }
}
