package main.dialogue;


import java.util.ArrayList;

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

    abstract public ArrayList<String> getOptions();

    abstract public boolean hasFollowUp();

    abstract protected Dialogue getFollowUp(String option);

    public Dialogue getFollowUp(int num) {
        ArrayList<String> options = getOptions();
        if(options == null || options.size() < num || num < 0) return null;
        return getFollowUp(options.get(num));
    }
}
