package main.dialogue;

import main.Item;

import java.util.HashSet;

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

    public Dialogue getTakeResponse() {
        return takeResponse;
    }

    public void setRefuseResponse(Dialogue refuseResponse) {
        this.refuseResponse = refuseResponse;
    }

    public Dialogue getRefuseResponse() {
        return refuseResponse;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public HashSet<String> getOptions() {
        HashSet<String> options = new HashSet<>();
        options.add("take");
        options.add("refuse");
        return options;
    }

    @Override
    public boolean hasOptions() {
        return true;
    }

    @Override
    public Dialogue getFollowUp(String option) {
        return switch (option) {
            case "take" -> takeResponse;
            case "refuse" -> refuseResponse;
            default -> null;
        };
    }
}
