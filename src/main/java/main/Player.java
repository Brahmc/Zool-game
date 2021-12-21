package main;

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
}
