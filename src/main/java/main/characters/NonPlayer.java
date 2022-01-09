package main.characters;

import main.items.Item;
import main.dialogue.*;

import java.util.ArrayList;

public class NonPlayer extends Character {
    private Dialogue currentDialogue;
    private final String profession;
    private final boolean canFight;

    public NonPlayer(String name, String profession) {
        this(name, profession, false);
    }

    public NonPlayer(String name, String profession, boolean canFight) {
        this(name, profession, canFight, 100);
    }

    public NonPlayer(String name, String profession, boolean canFight, int maxHealth) {
        super(name, maxHealth);
        this.profession = profession;
        currentDialogue = null;
        this.canFight = canFight;
    }

    public void setCurrentDialogue(Dialogue dialogue) {
        this.currentDialogue = dialogue;
    }

    public Dialogue getDialogue() {
        return currentDialogue;
    }

    public boolean hasDialogue() {
        return !(currentDialogue == null);
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

    public boolean canFight() {
        return canFight;
    }

    /**
     * Change currentDialogue to the next dialogue depending on the option given.
     * @param option int belonging to one of the currentDialogue options
     * @return true if option is valid
     */
    public boolean nextDialogue(int option) {
        Dialogue followup = currentDialogue.getFollowUp(option - 1);
        if(followup == null) return false; // option not valid
        currentDialogue = followup;
        return true;
    }

    /**
     * @return item currently being offered by currentDialogue
     */
    public Item getItemOnOffer() {
        return currentDialogue.getItem();
    }

    @Override
    public String toString() {
        return getDisplayName() + " (" + profession + ")";
    }
}
