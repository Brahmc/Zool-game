package main.characters;

import main.items.Item;
import main.dialogue.*;

import java.util.ArrayList;

public class NonPlayer extends Character {
    private Dialogue currentDialogue;
    private final String profession;

    public NonPlayer(String name, String profession) {
        super(name);
        this.profession = profession;
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
            offer += getDisplayName() + " offered you: " + g.getItem() + ".\n";
        }

        ArrayList<String> formatted = new ArrayList<>();
        int num = 1;
        for(String option : currentDialogue.getOptions()) {
            formatted.add(num + " (" + option + ")");
            num++;
        }
        return offer + "Options: " + String.join(", ", formatted);
    }

    /**
     * @return true if option is valid
     */
    public boolean nextDialogue(int option) {
        Dialogue followup = currentDialogue.getFollowUp(option - 1);
        if(followup == null) return false; // option not valid
        currentDialogue = followup;
        return true;
    }

    public Item getItemOnOffer() {
        if(currentDialogue instanceof DialogueGive g) {
            return g.getItem();
        }
        return null;
    }

    public String giveCharacter(Character character, Item item) {
        if(!getInventory().contains(item)) return "I don't have that item!";

        character.giveItem(item);
        getInventory().remove(item);
        return getDisplayName() + " gave you: " + item;
    }

    @Override
    public String toString() {
        return getDisplayName() + " (" + profession + ")";
    }
}
