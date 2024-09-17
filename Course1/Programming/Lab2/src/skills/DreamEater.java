package skills;

import ru.ifmo.se.pokemon.*;

public class DreamEater extends SpecialMove {

    public DreamEater(double pow, double acc){
        super(Type.PSYCHIC, pow, acc);
    }

    @Override
    protected String describe(){
        String[] piece = this.getClass().toString().split("\\.");
        return "делает " + piece[piece.length - 1];
    }

    @Override
    protected void applyOppDamage(Pokemon def, double damage){
        if(def.getCondition() != Status.SLEEP) def.setMod(Stat.HP, 0);;
    }

    @Override
    protected void applySelfDamage(Pokemon att, double damage){
        att.setStats(Math.round(att.getHP() * 1.5), att.getStat(Stat.ATTACK),
                att.getStat(Stat.DEFENSE), att.getStat(Stat.SPECIAL_ATTACK),
                att.getStat(Stat.SPECIAL_DEFENSE), att.getStat(Stat.SPEED));
    }

}
