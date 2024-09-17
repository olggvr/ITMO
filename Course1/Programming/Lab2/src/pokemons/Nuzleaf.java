package pokemons;

import ru.ifmo.se.pokemon.Type;
import skills.Growth;

public class Nuzleaf extends Seedot {

    public Nuzleaf(String name, int level){
        super(name, level);

        super.setType(Type.GRASS, Type.DARK);
        super.setStats(70, 70, 40, 60, 40, 60);

        // add moves
        Growth growth = new Growth(0, 0);
        super.addMove(growth);
    }

}
