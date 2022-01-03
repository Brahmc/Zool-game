package main.dialogue;

import java.util.HashMap;
import java.util.HashSet;

public class DialogueDefault extends Dialogue{

    private final HashMap<String, Dialogue> followUps;

    public DialogueDefault(String text) {
        super(text, Type.DEFAULT);
        followUps = new HashMap<>();
    }

    public void addOption(String option, Dialogue followUp) {
        followUps.put(option, followUp);
    }

    public Dialogue getFollowUp(String option) {
        return followUps.get(option);
    }

    public boolean hasOption(String option) {
        return (followUps.containsKey(option));
    }

    @Override
    public HashSet<String> getOptions() {
        return new HashSet<>(followUps.keySet());
    }

    @Override
    public boolean hasOptions() {
        return (followUps.size() != 0);
    }
}
