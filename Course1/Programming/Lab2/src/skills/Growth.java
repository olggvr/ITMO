package skills;
import ru.ifmo.se.pokemon.*;
public class Growth extends StatusMove {
    public Growth(double pow, double acc){
        super(Type.NORMAL, pow, acc);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        super.applySelfEffects(p);
        Effect e = new Effect().stat(Stat.ATTACK, 1);
        p.addEffect(e);
        Effect e1 = new Effect().stat(Stat.SPECIAL_ATTACK, 1);
        p.addEffect(e1);
    }
    @Override
    protected String describe(){
        String[] piece = this.getClass().toString().split("\\.");
        return "делает " + piece[piece.length - 1];
    }
}
