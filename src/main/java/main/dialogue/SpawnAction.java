package main.dialogue;

import main.Room;
import main.characters.NonPlayer;

public class SpawnAction {
    private final Room room;
    private final NonPlayer nonPlayer;

    public SpawnAction(Room room, NonPlayer nonPlayer) {
        this.room = room;
        this.nonPlayer = nonPlayer;
    }

    /**
     * spawns nonPlayer
     */
    public void spawn() {
        room.addCharacter(nonPlayer);
    }
}
