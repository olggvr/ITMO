package skills;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;
public class WaterGun extends SpecialMove {
    public WaterGun(double pow, double acc){
        super(Type.WATER, pow, acc);
    }
    @Override
    protected String describe(){
        String[] piece = this.getClass().toString().split("\\.");
        return "делает " + piece[piece.length - 1];
    }
}