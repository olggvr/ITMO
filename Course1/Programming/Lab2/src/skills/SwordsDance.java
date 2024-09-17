package skills;
import ru.ifmo.se.pokemon.*;
public class SwordsDance extends StatusMove {
    public SwordsDance(double pow, double acc){
        super(Type.NORMAL, pow, acc);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        super.applySelfEffects(p);
        Effect e = new Effect().stat(Stat.ATTACK, 2);
        p.addEffect(e);
    }
    @Override
    protected String describe(){
        String[] piece = this.getClass().toString().split("\\.");
        return "делает " + piece[piece.length - 1];
    }
}
