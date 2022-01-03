package main.characters;

import main.Item;
import main.dialogue.*;

public class Person extends Character {

    private Dialogue currentDialogue;

    public Person(String name) {
        super(name);
        currentDialogue = null;
    }

    public void setCurrentDialogue(Dialogue dialogue) {
        this.currentDialogue = dialogue;
    }

    public Dialogue getDialogue() {
        return currentDialogue;
    }

    public String getCurrentOptions() {
        String offer = "";
        if(currentDialogue instanceof DialogueGive g) {
            offer += getDisplayName() + " offered you: " + g.getItem().getName() + ".\n";
        }
        return offer + "Options: " + String.join(", ", currentDialogue.getOptions());
    }

    public boolean nextDialogue(String option) {
        if(currentDialogue.hasOptions()) {
            currentDialogue = currentDialogue.getFollowUp(option);
            return true;
        }
        return false;
    }

    public Item getItemOnOffer() {
        if(currentDialogue instanceof DialogueGive g) {
            return g.getItem();
        }
        return null;
    }

    public void giveCharacter(Character character, Item item) {
        character.getInventory().add(item);
        getInventory().remove(item);
    }
}
