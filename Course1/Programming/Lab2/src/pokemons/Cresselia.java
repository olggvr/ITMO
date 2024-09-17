package pokemons;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import skills.DreamEater;
import skills.ShadowBall;
import skills.Slash;
import skills.Swagger;

public class Cresselia extends Pokemon {

    public Cresselia(String name, int level){
        super(name, level);

        super.setType(Type.PSYCHIC);
        super.setStats(120, 70, 110, 75, 120, 85);

        // add moves
        Slash slash = new Slash(70, 100);
        DreamEater de = new DreamEater(100, 100);
        Swagger swag = new Swagger(0, 85);
        ShadowBall sb = new ShadowBall(80, 100);
        super.setMove(slash, de, swag, sb);
    }

}
