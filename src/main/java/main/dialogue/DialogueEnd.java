package main.dialogue;

import java.util.HashSet;

public class DialogueEnd extends Dialogue{
    private final Dialogue newDialogue;

    public DialogueEnd(String text, Dialogue newDialogue) {
        super(text, Type.END);
        this.newDialogue = newDialogue;
    }

    @Override
    public HashSet<String> getOptions() {
        return null;
    }

    @Override
    public boolean hasOptions() {
        return true;
    }

    public Dialogue getNewDialogue() {
        return newDialogue;
    }
}
