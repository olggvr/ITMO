package models.human;

import interfaces.IInterest;

public class Rand extends AbstractEssence implements IInterest {

    public boolean isAttention = false;
    public Rand(String name) {
        super(name);
    }

    @Override
    public void attention(boolean isFunny) {
        if(isFunny) {
            System.out.println("шоу привлекало " + getName());
            isAttention = true;
        }
    }
}
