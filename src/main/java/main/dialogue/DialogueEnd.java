package main.dialogue;

import java.util.ArrayList;

public class DialogueEnd extends Dialogue{
    private Dialogue newDialogue;

    public DialogueEnd(String text, Dialogue newDialogue) {
        super(text, Type.END);
        this.newDialogue = newDialogue;
    }

    public void setNewDialogue(Dialogue newDialogue) {
        this.newDialogue = newDialogue;
    }

    @Override
    public ArrayList<String> getOptions() {
        return new ArrayList<>();
    }

    @Override
    public boolean hasFollowUp() {
        return (newDialogue != null);
    }

    @Override
    protected Dialogue getFollowUp(String option) {
        return newDialogue;
    }
    public Dialogue getFollowUp(int num) {
        return newDialogue;
    }

}
