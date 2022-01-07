package main.items;

public class Armor extends Item{
    private final int PROTECTION;

    public Armor(String name, String description, double weight, int protection) {
        super(name, description, weight);
        PROTECTION = protection;
    }

    public int getPROTECTION() {
        return PROTECTION;
    }
}
