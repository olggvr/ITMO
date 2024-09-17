package skills;
import ru.ifmo.se.pokemon.*;
public class WaterPulse extends SpecialMove {
    public WaterPulse(double pow, double acc){
        super(Type.WATER, pow, acc);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        super.applyOppEffects(p);

        if (Math.random() <= 0.2) Effect.confuse(p);
    }
    @Override
    protected String describe(){
        String[] piece = this.getClass().toString().split("\\.");
        return "делает " + piece[piece.length - 1];
    }
}
