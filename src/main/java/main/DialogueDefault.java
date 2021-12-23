package main;

import java.util.HashMap;
import java.util.Set;

public class DialogueDefault extends Dialogue{

    private final HashMap<String, Dialogue> followUps;

    public DialogueDefault(String text) {
        super(text);
        followUps = new HashMap<>();
    }

    public void addOption(String option, Dialogue followUp) {
        followUps.put(option, followUp);
    }

    public Dialogue getFollowUp(String option) {
        return followUps.get(option);
    }

    public HashMap<String, Dialogue> getFollowUps() {
        return followUps;
    }

    public boolean hasOption(String option) {
        return (followUps.containsKey(option));
    }

    @Override
    public String getOptions() {
        return String.join(", ", followUps.keySet());
    }

    @Override
    public boolean hasOptions() {
        return (followUps.size() != 0);
    }
}
