package main.dialogue;

import main.items.Item;

import java.util.ArrayList;

public class DialogueReceive extends Dialogue{
    private final Item item;
    private Dialogue hasItemResponse;
    private Dialogue noItemResponse;

    public DialogueReceive(String text, Item item) {
        super(text, Type.RECEIVE);
        this.item = item;
    }

    public void setHasItemResponse(Dialogue hasItemResponse) {
        this.hasItemResponse = hasItemResponse;
    }

    public void setNoItemResponse(DialogueEnd noItemResponse) throws IllegalArgumentException{
        if(noItemResponse.hasFollowUp()) throw new IllegalArgumentException("NoItemResponse cannot have any followUps");
        noItemResponse.setNewDialogue(this); // if player doesn't have the item this dialogue will repeat next time
        this.noItemResponse = noItemResponse;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("yes");
        options.add("no");
        return options;
    }

    @Override
    public boolean hasFollowUp() {
        return true;
    }

    @Override
    protected Dialogue getFollowUp(String option) {
        return switch (option) {
            case "yes" -> hasItemResponse;
            case "no" -> noItemResponse;
            default -> null;
        };
    }
}
