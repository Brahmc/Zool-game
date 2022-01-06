package main.dialogue;

import main.items.Item;

import java.util.ArrayList;

public class DialogueGive extends Dialogue{

    private final Item item;
    private Dialogue takeResponse;
    private Dialogue refuseResponse;

    public DialogueGive(String text, Item item) {
        super(text, Type.GIVE);
        this.item = item;
    }

    public void setTakeResponse(Dialogue takeResponse) {
        this.takeResponse = takeResponse;
    }

    public void setRefuseResponse(Dialogue refuseResponse) {
        this.refuseResponse = refuseResponse;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("take");
        options.add("refuse");
        return options;
    }

    @Override
    public boolean hasFollowUp() {
        return true;
    }

    @Override
    protected Dialogue getFollowUp(String option) {
        return switch (option) {
            case "take" -> takeResponse;
            case "refuse" -> refuseResponse;
            default -> null;
        };
    }
}
