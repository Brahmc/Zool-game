package main.event;

import main.characters.Player;

abstract public class Event {

    private final String MESSAGE;

    public Event(String message) {
        this.MESSAGE = message;
    }

    protected String getMessage() {
        return MESSAGE;
    }

    /**
     * Executes custom event.
     * @return true if event ends game
     */
    abstract public boolean execute(Player player);
}
