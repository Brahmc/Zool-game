package main.dialogue;


abstract public class Dialogue {
    private final String text;
    private final Type type;

    public Dialogue(String text, Type type) {
        this.text = text;
        this.type = type;
    }

    enum Type {
        DEFAULT, GIVE
    }

    public String getText() {
        return text;
    }

    public Type getType() {
        return type;
    }

    abstract public String getOptions();

    abstract public boolean hasOptions();
}
