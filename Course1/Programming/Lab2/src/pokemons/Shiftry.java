package pokemons;

import ru.ifmo.se.pokemon.Type;
import skills.SwordsDance;

public class Shiftry extends Nuzleaf {

    public Shiftry(String name, int level){
        super(name, level);

        super.setType(Type.GRASS, Type.DARK);
        super.setStats(90, 100, 60, 90, 60, 80);

        // add moves
        SwordsDance sd = new SwordsDance(0, 0);
        super.addMove(sd);
    }

}
