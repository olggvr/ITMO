package skills;
import ru.ifmo.se.pokemon.*;
public class Facade extends PhysicalMove {
    public Facade(double pow, double acc){
        super(Type.NORMAL, pow, acc);
    }
    @Override
    protected void applyOppDamage(Pokemon def, double damage){
        if (def.getCondition() == Status.BURN ||
            def.getCondition() == Status.POISON || def.getCondition() == Status.PARALYZE){
            def.setMod(Stat.HP, (int) Math.round(damage * 2));
        }
    }
    @Override
    protected String describe(){
        String[] piece = this.getClass().toString().split("\\.");
        return "делает " + piece[piece.length - 1];
    }
}
