package main.dialogue;

import java.util.ArrayList;
import java.util.HashMap;

public class DialogueDefault extends Dialogue{

    private final HashMap<String, Dialogue> followUps;

    public DialogueDefault(String text) {
        super(text, Type.DEFAULT);
        followUps = new HashMap<>();
    }

    public void addOption(String option, Dialogue followUp) {
        followUps.put(option, followUp);
    }

    @Override
    protected Dialogue getFollowUp(String option) {
        return followUps.get(option);
    }

    @Override
    public ArrayList<String> getOptions() {
        return new ArrayList<>(followUps.keySet());
    }

    @Override
    public boolean hasFollowUp() {
        return (followUps.size() > 0);
    }
}
