package properties;

public enum MoveDegree {

    FASTER("побыстрее"),
    SLOWLER("помедленней");

    private final String name;

    MoveDegree(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
