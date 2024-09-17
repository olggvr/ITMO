package interfaces;

import models.item.Items;
import properties.MoveDegree;

public interface Runnable {

    void runAway(MoveDegree moveDegree, Items target);

}
