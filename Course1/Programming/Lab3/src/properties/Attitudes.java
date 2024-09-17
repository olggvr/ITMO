package properties;

public enum Attitudes {

    PITY("жалко"),
    PROUD("горд");

    private final String attitude;

    Attitudes(String attitude){
        this.attitude = attitude;
    }

    @Override
    public String toString() {
        return this.attitude;
    }

}
