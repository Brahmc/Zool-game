package main.dialogue;


import java.util.HashSet;

abstract public class Dialogue {
    private final String text;
    private final Type type;

    public Dialogue(String text, Type type) {
        this.text = text;
        this.type = type;
    }

    enum Type {
        DEFAULT, GIVE, END
    }

    public String getText() {
        return text.trim();
    }

    public Type getType() {
        return type;
    }

    abstract public HashSet<String> getOptions();

    abstract public boolean hasOptions();
}
