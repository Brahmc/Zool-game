package main.characters;

import main.event.Event;
import main.exceptoins.IllegalItemException;
import main.items.Item;
import main.Room;
import main.exceptoins.NoItemException;
import main.exceptoins.NotCollectableException;

import java.util.ArrayList;


public class Player extends Character{
    private Room currentRoom;
    private final ArrayList<Room> ROOM_HISTORY;

    public Player(String name) {
        super(name);
        ROOM_HISTORY = new ArrayList<>();
        currentRoom = null;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * takes item from current room
     * @param name String matching Item name
     */
    public void takeFromRoom(String name) throws NoItemException, NotCollectableException {
        Item item = currentRoom.getItemByName(name);
        if(item == null) {
            throw new NoItemException("There is no such item in the room!");
        }
        if(!item.isCollectable()) {
            throw new NotCollectableException("You can't take that item!");
        }
        giveItem(item);
        currentRoom.removeItem(item);
    }

    /**
     * drops item in current room
     * @param name String matching Item name
     * @return true if successful
     */
    public boolean drop(String name) {
       Item item = getItemByName(name);
       if(item != null) {
           getInventory().remove(item);
           currentRoom.addItem(item);
           return true;
       }
       return false;
    }

    /**
     * @param name String matching Item name
     * @return item response string
     */
    public String useItemByName(String name) throws NoItemException, IllegalItemException {
        Item item = getItemByName(name);
        if(item == null) {
            throw new NoItemException("There is no such item in your inventory!");
        }
        return item.use(this);
    }

    /**
     * @param name name of NonPlayer
     * @return NonPlayer in current room matching name
     */
    public NonPlayer getNearbyCharacterByName(String name) {
        return currentRoom.getNonPlayerByName(name);
    }

    /**
     * @return String with list of items and player info
     */
    public String seeInventory() {
        ArrayList<String> itemDesc = new ArrayList<>();
        for(Item item : getInventory()) {
            if(!item.equals(getWeapon()) && !item.equals(getArmor())) { // equipped items don't have to be displayed in inventory
                itemDesc.add(item.toString());
            }
        }
        String BOLD = "\033[0;1m"; // Start bold (ANSI)
        String END = "\u001B[0m"; // White text
        String part1 = "Health: " + getDisplayHealth() + "\n" + getStats() +
                        BOLD+"\n\nItems:\n"+END; // in bold

        if(itemDesc.isEmpty()) return part1 + "You don't have any items!";
        else return part1 + String.join("\n", itemDesc);
    }

    public String getInfo() {
        return "You are currently " + currentRoom.getLongDescription();
    }

    /**
     *Changes current room to direction matching exit if the exit is valid
     *@return true if successful
     */
    public boolean goRoom(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if(nextRoom == null) return false;
        ROOM_HISTORY.add(currentRoom);
        setCurrentRoom(nextRoom);
        return true;
    }

    /**
     * Sets currentRoom to last room.
     * @return true if successful
     */
    public boolean goBackRoom() {
        int savedRooms = ROOM_HISTORY.size();
        if(savedRooms < 1) return false;
        currentRoom = ROOM_HISTORY.get(savedRooms - 1);
        ROOM_HISTORY.remove(savedRooms -1); // remove last room
        return true;
    }

    /**
     * Triggers event if room has one.
     * @return true if event ends game
     */
    public boolean triggerRoomEvent() { //
        Event roomEvent = currentRoom.getEvent();
        if(roomEvent == null) return false;
        return roomEvent.execute(this);
    }
}
