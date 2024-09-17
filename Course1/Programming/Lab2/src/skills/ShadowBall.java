package skills;
import ru.ifmo.se.pokemon.*;
public class ShadowBall extends SpecialMove {
    public ShadowBall(double pow, double acc){
        super(Type.GHOST, pow, acc);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        super.applyOppEffects(p);
        if(Math.random() <= 0.2) {
            Effect e = new Effect().stat(Stat.SPECIAL_DEFENSE, -1);
            p.addEffect(e);
        }
    }
    @Override
    protected String describe(){
        String[] piece = this.getClass().toString().split("\\.");
        return "делает " + piece[piece.length - 1];
    }
}
