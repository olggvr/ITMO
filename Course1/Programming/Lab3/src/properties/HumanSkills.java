package properties;

public enum HumanSkills {

    GOOD_ACTOR("хорошим актером"),
    DEFTLY ("ловко");

    private final String humanSkill;

    HumanSkills(String humanSkill) {this.humanSkill = humanSkill;}

    @Override
    public String toString() {
        return humanSkill;
    }
}