package main;

import main.dialogue.Dialogue;
import main.dialogue.DialogueDefault;
import main.dialogue.DialogueGive;

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
        return currentDialogue.getOptions();
    }

    public boolean nextDialogue(String option) {
        if(currentDialogue instanceof  DialogueDefault d) {
            if(d.hasOption(option)) {
                currentDialogue = d.getFollowUp(option);
                return true;
            }
            return false;
        }
        if(currentDialogue instanceof  DialogueGive g) {
            if(option.equals("take")) {
                currentDialogue = g.getTakeResponse();

                return true;
            }
            if(option.equals("refuse")) {
                currentDialogue = g.getRefuseResponse();
                return true;
            }
        }
            return false;
    }

    public Item getItemOnOffer() {
        if(currentDialogue instanceof DialogueGive g) {
            return g.getItem();
        }
        return null;
    }

    public void givePlayer(Player player, Item item) {
        player.getInventory().add(item);
        getInventory().remove(item);
    }
}
