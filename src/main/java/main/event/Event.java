package main.event;

abstract public class Event {

    String message;

    public Event(String message) {
        this.message = message;
    }

    /**
     *
     * @return true when player quits
     */
    abstract public boolean execute();
}
