package main;

import Commands.Command;
import main.characters.Player;
import main.parser.CommandParse;

public class Game {
    private final CommandParse parser;
    private final Player player;

    public Game() {
        parser = new CommandParse(CommandParse.Type.WALK);
        player = new Player(askName());
        player.setColor(36);
        Map.generateMap(player);
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
            nameRight = MultipleChoice.twoChoice(parser, "Are you sure you want to be called " + name + "?"
                                        , "yes", "no");
        }
        return name; // name will always be printed in color
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zool, " + player.getDisplayName() + "!");
        System.out.println();
        System.out.println("For hundreds of years an evil demon lord has been terrorising the people.");
        System.out.println("But don't worry, someone by the name of " + player.getDisplayName() + " can stop him. Or so the legend goes...");
        System.out.println("There is only one problem, you have no clue how to defeat a demon lord.");
        System.out.println("But im sure you will figure it out soon enough!, you might find some leads in the town.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    public void play() {
        boolean finished = false;

        while(!finished) {
            Command c = parser.getCommand();
            finished = c.execute(player);
        }

        if(player.isDead()) { // game stopped because player died
            playerDied();
        }
    }

    private void printLocationInfo() {
        System.out.println(player.getInfo());
        System.out.println();
    }

    private void playerDied() {
        System.out.println("You died!!");
        if(MultipleChoice.twoChoice(parser, "Do you want to restart?", "restart", "quit")) {
            Game g = new Game();
            g.play(); // run game again
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
