public record Example(String name, int age) {

    public Example(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Hello from " + this.name + ", " + this.age);
    }
}
