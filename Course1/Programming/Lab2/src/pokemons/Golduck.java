package pokemons;

import ru.ifmo.se.pokemon.Type;
import skills.LowSweep;

public class Golduck extends Psyduck {

    public Golduck(String name, int level){
        super(name, level);

        super.setType(Type.WATER);
        super.setStats(80, 82, 78, 95, 80, 85);

        // add moves
        LowSweep lw = new LowSweep(65, 100);
        super.addMove(lw);
    }

}
