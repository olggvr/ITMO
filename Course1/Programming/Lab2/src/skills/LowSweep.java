package skills;
import ru.ifmo.se.pokemon.*;
public class LowSweep extends PhysicalMove {
    public LowSweep(double pow, double acc){
        super(Type.FIGHTING, pow, acc);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        super.applyOppEffects(p);
        Effect e = new Effect().stat(Stat.SPEED, -1);
        p.addEffect(e);
    }
    @Override
    protected String describe(){
        String[] piece = this.getClass().toString().split("\\.");
        return "делает " + piece[piece.length - 1];
    }
}
