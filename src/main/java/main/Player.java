package main;

import main.Exceptoins.NoItemException;
import main.Exceptoins.NotCollectableException;

import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private double maxWeight;

    public Player(String name) {
        setName(name);
        inventory = new ArrayList<>();
        health = 0;
        maxWeight = 25;
        health = 100;
        currentRoom = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void take(String name) throws NoItemException, NotCollectableException {
        Item item = currentRoom.getItemByName(name);
        if(item == null) {
            throw new NoItemException("There is no such item in the room!");
        }
        if(!item.isCollectable()) {
            throw new NotCollectableException("You can't take that item!");
        }
        inventory.add(item);
        currentRoom.removeItem(item);
    }

    public String getInfo() {
        return "You are currently " + getCurrentRoom().getLongDescription();
    }

    public boolean drop(String name) {
        for(Item item : inventory) {
            if(item.getName().equals(name)) {
                inventory.remove(item);
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
