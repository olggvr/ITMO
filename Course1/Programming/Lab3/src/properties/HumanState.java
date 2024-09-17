package properties;

public enum HumanState {

    NEW("новый"),
    UNHAPPY("несчасный"),
    HAPPY("рад"),
    CHAGRIN("огорчение"),
    SCARY("испуганно");


    private final String humanState;

    HumanState(String humanState){this.humanState = humanState;}

    @Override
    public String toString() {
        return humanState;
    }
}