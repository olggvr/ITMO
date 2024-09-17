package skills;
import ru.ifmo.se.pokemon.*;
public class Slash extends PhysicalMove {
    public Slash(double pow, double acc){
        super(Type.NORMAL, pow, acc);

    }
    protected double calcCriticalHit(Pokemon att, Pokemon def){
        return 1d / 8d;
    }
    @Override
    protected String describe(){
        String[] piece = this.getClass().toString().split("\\.");
        return "делает " + piece[piece.length - 1];
    }
}
