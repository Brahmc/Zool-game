package main.characters;

import main.event.Event;
import main.exceptoins.IllegalItemException;
import main.items.Armor;
import main.items.Item;
import main.Room;
import main.exceptoins.NoItemException;
import main.exceptoins.NotCollectableException;
import main.items.Weapon;

import java.util.ArrayList;


public class Player extends Character{
    private Room currentRoom;
    private final ArrayList<Room> ROOM_HISTORY;
    private double maxWeight;

    public Player(String name) {
        super(name);
        ROOM_HISTORY = new ArrayList<>();
        maxWeight = 25;
        currentRoom = null;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void takeFromRoom(String name) throws NoItemException, NotCollectableException {
        Item item = currentRoom.getItemByName(name);
        if(item == null) {
            throw new NoItemException("There is no such item in the room!");
        }
        if(!item.isCollectable()) {
            throw new NotCollectableException("You can't take that item!");
        }
        getInventory().add(item);
        currentRoom.removeItem(item);
    }

    public boolean drop(String name) {
       Item item = getItemByName(name);
       if(item != null) {
           getInventory().remove(item);
           currentRoom.addItem(item);
           return true;
       }
       return false;
    }

    public void equipItem(Item item) throws IllegalItemException, NoItemException {
        if(item == null) {
            throw new NoItemException("There is no such item in your inventory!");
        }
        if(item instanceof Weapon weapon) setWeapon(weapon);
        else if(item instanceof Armor armor)

        throw new IllegalItemException("That item can't be equipped!");
    }

    public void useItemByName(String name) throws NoItemException, IllegalItemException {
        Item item = getItemByName(name);
        if(item == null) {
            throw new NoItemException("There is no such item in your inventory!");
        }
        item.use(this);
    }

    private Item getItemByName(String name) {
        for(Item item : getInventory()) {
            if(item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * @return String with list of items.
     */
    public String seeInventory() {
        ArrayList<String> itemDesc = new ArrayList<>();
        for(Item item : getInventory()) {
            itemDesc.add(item.toString());
        }
        String invString = "Equipped: \nWeapon: " + getWeapon() + "\nArmor: ";
        if(getArmor() == null) invString += "No armor equipped";
        else invString += getArmor();
        invString += "\n\n";

        if(itemDesc.isEmpty()) invString += "You don't have any items!";
        else invString +=String.join("\n", itemDesc);

        return invString;
    }

    public String getInfo() {
        return "You are currently " + currentRoom.getLongDescription();
    }

    /**
     *changes currentRoom to direction matching exit if the exit is valid
     * @return true if successful
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
