package main;

import Commands.Command;

public class Game {
    private final CommandParse parser;
    private final Player player;

    public Game() {
        parser = new CommandParse(CommandParse.Type.WALK);
        player = new Player(askName());
        createRooms();
        printWelcome();
    }

    private String askName() {
        boolean nameRight = false;
        String name = "";

        while(!nameRight) {
            System.out.println("What do you want to be called?");
            name = parser.getInput();
            if(name.contains(" ")) {
                System.out.println("Your name cannot contain spaces!");
                continue;
            }
            nameRight = OptionParse.twoChoice(parser, "Are you sure you want to be called " + name + "?"
                                        , "yes", "no");
        }
        return "\u001B[32m" + name + "\u001B[0m"; // name will always be printed in color
    }

    private void createRooms() {
        Room villageCenter, townHall, backAlley, adventurersGuild,
                gate, field, forrest, farmLand, demonForrest, demonCastle;

        // create rooms
        villageCenter = new Room("in the center of the village");
        townHall = new Room("in the town hall");
        backAlley = new Room("in a shady back alley in the back of the village");
        adventurersGuild = new Room("in he adventurers guild");
        gate = new Room("at the gate that leads to the outside");
        field = new Room("in the field near the village");
        forrest = new Room("in the forrest");
        farmLand = new Room("at a wide stretch of farm land");
        demonForrest = new Room("in a cursed forrest near the demon lords castle");
        demonCastle = new Room("in what looks like the castle of the demon lord");

        villageCenter.addExit("north", backAlley);
        villageCenter.addExit("east", townHall);
        villageCenter.addExit("south", gate);
        villageCenter.addExit("west", adventurersGuild);

        townHall.addExit("west", villageCenter);
        backAlley.addExit("south", villageCenter);
        adventurersGuild.addExit("east", villageCenter);

        gate.addExit("north", villageCenter);
        gate.addExit("south", field);

        field.addExit("north", gate);
        field.addExit("east", farmLand);
        field.addExit("south", forrest);

        forrest.addExit("north", field);

        farmLand.addExit("west", field);
        farmLand.addExit("north", demonForrest);

        demonCastle.addExit("south", farmLand);

        // spawn items
        townHall.addItem(new Item("sword", "stone sword", 1.2, 3));
        townHall.addItem(new Item("bookshelf", "wooden bookshelf", 100, 100));

        player.setCurrentRoom(villageCenter);
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul, " + player.getName() + "!");
        System.out.println("For hundreds of years an evil demon lord has been terrorising the people.");
        System.out.println("But don't worry, someone by the name of " + player.getName() + " can stop him. Or so the legend goes...");
        System.out.println("There is only one problem, you have no clue how to defeat a demon lord.");
        System.out.println("But im sure you will figure it out soon enough!, you might find some leads in the town.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    private void printLocationInfo() {
        System.out.println(player.getInfo());
        System.out.println();
    }



    public void play() {
        boolean finished = false;

        while(!finished) {
            Command c = parser.getCommand();
            finished = c.execute(player);
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
