package models.human;

import dialog.Dialog;
import except.UncheckedException;
import except.WrongAngleException;
import interfaces.*;
import models.item.Items;
import models.item.Toys;
import properties.HumanSkills;
import properties.HumanState;

public class Shortest extends AbstractEssence implements IPush, ITOTB, IDodge, IShortest, IFall, IJerk, Jumpable{
    private Head head = new Head("голова");
    private Legs legs = new Legs("ноги");
    public int angle = 90;
    public boolean isPushedHead = false;
    public int health = 100;
    public int normalHealth = 100;

    public Shortest(String name) {
        super(name);
    }

    @Override
    public void pushHead(Items item) throws UncheckedException {
        isPushedHead = true;
        System.out.println(getName() + " просунул " + head.getPartName() + " в " + item.getThingType());
    }

    @Override
    public void TOTB(HumanState humanState, HumanSkills humanSkill) {
        System.out.println(humanState + " коротышка оказался " + humanSkill);
    }

    @Override
    public void dodge(Toys from, HumanSkills humanSkill, boolean isFly) {
        if (isFly) System.out.println("коротышка " + humanSkill + " увертывался от " + from.getThingType());
    }

    @Override
    public void move() throws WrongAngleException {
        try{
            throw new WrongAngleException("svdffd");
        }catch(WrongAngleException ex){
            ex.getStackTrace();
        };
        this.angle -= 45;
        if(this.angle != 45) throw new WrongAngleException("Рука повернута неверно");
        System.out.println(getName()+ " наклонил голову и нарочно подставил лоб под удар");
    }

    @Override
    public void fall(Items target) {
        System.out.println(getName() + " падал на " + target.getThingType());
    }

    @Override
    public void jerk() {
        System.out.println(getName() + " дрыгал " + legs.getPartName());
    }

    @Override
    public void jump(Items target) {
        System.out.println(getName() + " вскочил на " + target.getThingType());
    }
}
