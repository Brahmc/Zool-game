package main.characters;

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

    public void equipWeaponByName(String name) throws IllegalItemException, NoItemException {
        Item item = getItemByName(name);
        if(item == null) {
            throw new NoItemException("There is no such item in your inventory!");
        }
        if(!(item instanceof Weapon weapon)) throw new IllegalItemException("That item is not a weapon!");
        setWeapon(weapon);
    }

    public void equipArmorByName(String name) throws IllegalItemException, NoItemException {
        Item item = getItemByName(name);
        if(item == null) {
            throw new NoItemException("There is no such item in your inventory!");
        }
        if(!(item instanceof Armor Armor)) throw new IllegalItemException("That item is not armor!");

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
        if(itemDesc.isEmpty()) return "You don't have any items!";
        return getDisplayName() + "'s inventory:\n" + String.join("\n", itemDesc);
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

    public boolean triggerRoomEvent() {
        return currentRoom.executeEvent();
    }
}
