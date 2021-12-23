package main;


abstract public class Dialogue {

    private final String text;

    public Dialogue(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    abstract public String getOptions();

    abstract public boolean hasOptions();
}
