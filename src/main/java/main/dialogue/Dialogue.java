package main.dialogue;


import main.items.Item;

import java.util.ArrayList;

abstract public class Dialogue {
    private final String text;
    private final Type type;
    private SpawnAction spawnAction;

    public Dialogue(String text, Type type) {
        this.text = text;
        this.type = type;
    }

    enum Type {
        DEFAULT, GIVE, END, RECEIVE
    }

    public String getText() {
        return text.trim();
    }

    public Type getType() {
        return type;
    }

    public Item getItem() {
        return null;
    }

    /**
     * Will spawn character when getFollowUp gets called
     */
    public void setSpawnAction(SpawnAction spawnAction) {
        this.spawnAction = spawnAction;
    }

    abstract public ArrayList<String> getOptions();

    abstract public boolean hasFollowUp();

    abstract protected Dialogue getFollowUp(String option);

    public Dialogue getFollowUp(int num) {
        if(spawnAction != null) spawnAction.spawn(); // spawns character

        ArrayList<String> options = getOptions();
        if(options == null || options.size() < num || num < 0) return null;
        return getFollowUp(options.get(num));
    }
}
