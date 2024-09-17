package pokemons;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import skills.WaterGun;
import skills.WaterPulse;
import skills.Waterfall;

public class Psyduck extends Pokemon {

    public Psyduck(String name, int level){
        super(name, level);

        super.setType(Type.WATER);
        super.setStats(50, 52, 48, 65, 50, 55);

        // add moves
        WaterGun wg = new WaterGun(40, 100);
        WaterPulse wp = new WaterPulse(60, 100);
        Waterfall wf = new Waterfall(80, 100);
        super.setMove(wg, wp, wf);
    }

}
