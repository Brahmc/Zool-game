package main.characters;

import main.items.Item;
import main.Room;
import main.exceptoins.NoItemException;
import main.exceptoins.NotCollectableException;

import java.util.ArrayList;


public class Player extends Character{
    private Room currentRoom;
    private ArrayList<Room> roomHistory;
    private double maxWeight;

    public Player(String name) {
        super(name);
        roomHistory = new ArrayList<>();
        maxWeight = 25;
        currentRoom = null;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
        roomHistory.add(currentRoom);
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
        for(Item item : getInventory()) {
            if(item.getName().equals(name)) {
                getInventory().remove(item);
                currentRoom.addItem(item);
                return true;
            }
        }
        return false;
    }

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

    public boolean goRoom(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if(nextRoom == null) return false;
        setCurrentRoom(nextRoom);
        return true;
    }

    public boolean goBackRoom() {
        int savedRooms = roomHistory.size();
        if(savedRooms <= 0) return false;
        currentRoom = roomHistory.get(savedRooms - 2);
        roomHistory.remove(savedRooms -1); // remove last room
        return true;
    }

    public boolean triggerRoomEvent() {
        return currentRoom.executeEvent();
    }
}
