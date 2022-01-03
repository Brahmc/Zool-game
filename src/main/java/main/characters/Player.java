package main.characters;

import main.Item;
import main.Room;
import main.exceptoins.NoItemException;
import main.exceptoins.NotCollectableException;


public class Player extends Character{
    private Room currentRoom;
    private double maxWeight;

    public Player(String name) {
        super(name);
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

    public String getInfo() {
        return "You are currently " + currentRoom.getLongDescription();
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

    public boolean goRoom(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if(nextRoom == null) return false;
        currentRoom = nextRoom;
        return true;
    }
}
