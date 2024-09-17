package pokemons;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import skills.DoubleTeam;
import skills.Facade;

public class Seedot extends Pokemon {

    public Seedot(String name, int level){
        super(name, level);

        super.setType(Type.GRASS);
        super.setStats(40, 40, 50, 30, 30, 30);

        // add moves
        DoubleTeam dt = new DoubleTeam(0, 0);
        Facade facade = new Facade(70, 100);
        super.setMove(dt, facade);
    }

}
