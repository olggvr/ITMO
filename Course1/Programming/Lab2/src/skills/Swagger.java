package skills;
import ru.ifmo.se.pokemon.*;
public class Swagger extends StatusMove {
    public Swagger(double pow, double acc){
        super(Type.NORMAL, pow, acc);
    }
    @Override
    protected String describe(){
        String[] piece = this.getClass().toString().split("\\.");
        return "делает " + piece[piece.length - 1];
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        super.applyOppEffects(p);
        Effect.confuse(p);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        super.applySelfEffects(p);
        Effect e = new Effect().stat(Stat.ATTACK, 2);
        p.addEffect(e);
    }
}
