package main;

public class DialogueGive extends Dialogue{

    private final Item item;
    private Dialogue takeResponse;
    private Dialogue refuseResponse;

    public DialogueGive(String text, Item item) {
        super(text);
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
    public String getOptions() {
        return "Options: take, refuse";
    }

    @Override
    public boolean hasOptions() {
        return true;
    }
}
