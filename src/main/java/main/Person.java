package main;


import main.Exceptoins.NoDefaultDialogeException;

public class Person extends Character{

    private Dialogue currentDialogue;

    public Person(String name) {
        super(name);
        currentDialogue = null;
    }

    public boolean setCurrentDialogue(Dialogue dialogue) {
        this.currentDialogue = dialogue;
        return !(currentDialogue == null);
    }


    public Dialogue getDialogue() {
        return currentDialogue;
    }

    public boolean nextDialogue(String option) throws NoDefaultDialogeException {
        if(currentDialogue instanceof  DialogueDefault d) {
            currentDialogue = d.getFollowUp(option);
        } else {
            throw new NoDefaultDialogeException("currentRoom is no instance of DialogueDefault");
        }
        return !(currentDialogue == null);
    }

    public boolean hasDialogue() {
        return (currentDialogue != null);
    }


}
