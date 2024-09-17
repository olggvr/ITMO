import dialog.Dialog;
import except.UncheckedException;
import except.WrongAngleException;
import models.audience.Audience;
import models.human.Goat;
import models.human.Owner;
import models.human.Rand;
import models.human.Shortest;
import models.item.Items;
import models.item.Toys;
import properties.*;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws WrongAngleException {

        Shortest shortest = new Shortest("коротышка");
        Items item = new Items("отверстие в занавеске");
        Toys toy = new Toys("мячи");
        Audience aud = new Audience("публика");
        Items floor = new Items("пол");
        Rand rand = new Rand("новых зрителей");
        Owner owner = new Owner("хозяин");
        Goat goat = new Goat("козлик"){
            @Override
            public void runAway(MoveDegree moveDegree, Items target){
                boolean isPartDamaged = head.eyes.isEyeDamaged;
                if(isPartDamaged) {
                    System.out.println(getName() + " " + moveDegree + " побежал " + target.getThingType());
                }
            }
        };
        Items home = new Items("домой");
        Items platform = new Items("помост");
        Dialog dialog = new Dialog();

        int r = (int)Math.round(Math.random()*10);
        if(r == 5 || r == 1 || r == 8)throw new UncheckedException("Wrong number detected");

        goat.hold(BodyPartState.DAMAGED);
        goat.runAway(MoveDegree.FASTER, home);
        goat.seeOpportunityToEarn(Attitudes.PITY, HumanState.UNHAPPY, HumanState.HAPPY, shortest);
        shortest.jump(platform);
        dialog.SayPhraze(shortest, aud, "А теперь я буду! Давайте в меня бросайте.", null);

        shortest.pushHead(item);
        toy.throwTo(null, shortest, toy);

        shortest.TOTB(HumanState.NEW, HumanSkills.GOOD_ACTOR);

        shortest.dodge(toy, HumanSkills.DEFTLY, toy.isFly);

        aud.dont_like(true, shortest);
        toy.bounce(shortest.health, shortest.normalHealth, floor);

        shortest.fall(floor);
        shortest.jerk();

        aud.laugh(ReactionDegree.STRONG);
        rand.attention(aud.isShortFunny);
        owner.satisfied(aud.isShortFunny, rand.isAttention, shortest);

        dialog.SayPhraze(goat, shortest, "Теперь нам с тобой придется лечь спать без ужина", HumanState.CHAGRIN);
        dialog.SayPhraze(shortest, goat, "А разве у тебя не осталось больше монеток?", null);
        dialog.SayPhraze(goat, shortest, "Осталось всего двадцать сантиков, но эти деньги понадобятся нам," +
                "чтобы заплатить за ночлег.", null);
        dialog.SayPhraze(shortest, goat, "А может быть, лучше эти деньги проесть и переночевать просто на улице?", null);
        dialog.SayPhraze(goat, shortest, "Что ты! Что ты! " +
                "Или забыл, что я тебе про Дурацкий остров рассказывал? Лучше без еды потерпеть, " +
                "чем попасть в полицейские руки.", HumanState.SCARY);
    }
}