public class Item {
    private String name;
    private String description;
    private double weight;
    boolean isCollectable;

    public Item(String name, String description, double weight, boolean isCollectable) {
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.isCollectable = isCollectable;
    }

    public Item(String name, String description, double weight) {
        this(name, description, weight, true);
    }

    public String getName() {
        return name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
