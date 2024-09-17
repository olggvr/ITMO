package interfaces;

import models.item.Toys;
import properties.HumanSkills;

public interface IDodge {

    void dodge(Toys from, HumanSkills humanSkill, boolean isFly);

}
