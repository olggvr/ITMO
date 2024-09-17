import pokemons.*;
import ru.ifmo.se.pokemon.*;
public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        // create new pokemons
        Psyduck pd = new Psyduck("Псайдак", 1);
        Cresselia c = new Cresselia("Креселия", 1);
        Seedot s = new Seedot("Сидот", 1);
        Golduck gd = new Golduck("Голдак", 1);
        Nuzleaf nf = new Nuzleaf("Нузлив", 1);
        Shiftry sf = new Shiftry("Шивти", 1);
        // add pokemons to commands
        b.addAlly(pd);
        b.addAlly(c);
        b.addAlly(s);
        b.addFoe(gd);
        b.addFoe(nf);
        b.addFoe(sf);
        // go battle
        b.go();
    }
}