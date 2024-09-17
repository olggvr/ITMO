package properties;

public enum BodyPartState {

    DAMAGED("подбитый"),
    HEALTHY("здоровый"),
    ILL("заболевший");

    private final String eyeState;

    BodyPartState(String eyeState){
        this.eyeState = eyeState;
    }

    @Override
    public String toString() {
        return this.eyeState;
    }
}
