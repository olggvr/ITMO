package skills;
import ru.ifmo.se.pokemon.*;
public class Waterfall extends PhysicalMove {
    public Waterfall(double pow, double acc){
        super(Type.WATER, pow, acc);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        super.applyOppEffects(p);
        if (Math.random() <= 0.2) Effect.flinch(p);
    }
    @Override
    protected String describe(){
        String[] piece = this.getClass().toString().split("\\.");
        return "делает " + piece[piece.length - 1];
    }
}
