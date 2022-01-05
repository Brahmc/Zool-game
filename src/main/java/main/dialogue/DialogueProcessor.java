package main.dialogue;

import main.Item;
import main.Parser;
import main.characters.Person;
import main.characters.Player;

import java.util.Locale;
import java.util.Scanner;

public class DialogueProcessor {
    private final static int PRINT_COLOR = 33;

    public static void processDialogue(Person person, Player player) {
        boolean finished = false;
        while(!finished) {
            printText(person, player);
            if(!person.getDialogue().hasOptions()) return; // don't to continue if dialogue is text only
            switch (person.getDialogue().getType()) {
                case DEFAULT -> getResponse(person);
                case GIVE -> giveItem(player, person);
                case END -> {   // loads next dialogue but doesn't ask any new questions and ends the loop.
                    person.nextDialogue("");
                    finished = true;
                }
            }
        }

        System.out.println();
        System.out.println(player.getInfo());
    }

    private static void getResponse(Person person) {
        Parser pars = new Parser();
        boolean answered = false;
        while (!answered) {
            System.out.println(person.getCurrentOptions());
            String answer = pars.getInput().toLowerCase(Locale.ROOT); // hold till answer
            answered = person.nextDialogue(answer);
        }
    }

    private static void giveItem(Player player, Person person) {
        Item item = person.getItemOnOffer();
        Parser pars = new Parser();
        boolean answered = false;
        while (!answered) {
            System.out.println(person.getCurrentOptions());
            String answer = pars.getFirstOnly(); // hold till answer
            answered = person.nextDialogue(answer);

            if(answer.equals("take")) {
                person.giveCharacter(player, item);
                System.out.println("You received: " + item);
            }
        }
    }

    private static void printText(Person person, Player player) {
        String text = person.getDialogue().getText();
        Scanner converter = new Scanner(text).useDelimiter("__");

        StringBuilder endString = new StringBuilder(person.getDisplayName() + ": “");
        while (converter.hasNext()) {
            String part = converter.next();
            switch (part) {
                case "PLAYER_NAME" -> endString.append(player.getDisplayName());
                case "CHARACTER_NAME" -> endString.append(person.getDisplayName());
                case "ITEM_NAME" -> endString.append(person.getItemOnOffer().getName());
                default -> {
                    String COLOR_START = "\u001B[" + PRINT_COLOR + "m";
                    String COLOR_END = "\u001B[0m";
                    endString.append(COLOR_START).append(part).append(COLOR_END);
                }
            }
        }
        endString.append("”");
        System.out.println(endString);
    }
}
