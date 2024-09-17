package interfaces;

import models.human.AbstractEssence;
import models.item.Toys;

public interface IThrowTo {

    void throwTo(AbstractEssence fromWhere, AbstractEssence target, Toys toy);

}
