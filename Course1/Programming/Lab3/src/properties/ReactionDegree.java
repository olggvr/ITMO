package properties;

public enum ReactionDegree {
    STRONG("страшно"),
    LITTLE("слабо");

    private final String audState;

    ReactionDegree(String audState){
        this.audState = audState;
    }

    @Override
    public String toString() {
        return audState;
    }
}