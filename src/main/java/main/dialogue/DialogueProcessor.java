package main.dialogue;

import main.Item;
import main.Parser;
import main.Person;
import main.Player;

import java.util.Scanner;

public interface DialogueProcessor {
    int PRINTCOLOR = 32;

    static void processDialogue(Person person, Player player) {
        printText(person, player);
        System.out.println();
        if(!person.getDialogue().hasOptions()) return; // don't to continue if dialogue is text only

        switch (person.getDialogue().getType()) {
            case DEFAULT -> getResponse(person);
            case GIVE -> giveItem(player, person);
        }
         processDialogue(person, player); //run method again for next dialogue
    }

    private static void printText(Person person, Player player) {
        String text = person.getDialogue().getText();
        Scanner converter = new Scanner(text).useDelimiter("__");

        System.out.print(person.getName() + ": “");
        while (converter.hasNext()) {
            String part = converter.next();
            switch (part) {
                case "PLAYER_NAME" -> System.out.print(player.getDisplayName());
                case "CHARACTER_NAME" -> System.out.print(person.getDisplayName());
                default -> {
                    String COLOR_START = "\u001B[" + PRINTCOLOR + "m";
                    String COLOR_END = "\u001B[0m";
                    System.out.print(COLOR_START + part + COLOR_END);
                }
            }
        }
        System.out.print("”");
    }

    private static void getResponse(Person person) {
        Parser pars = new Parser();
        boolean answered = false;
        while (!answered) {
            System.out.println(person.getCurrentOptions());
            String answer = pars.getFirstOnly(); // hold till answer

            answered = person.nextDialogue(answer);
        }
    }

    private static void giveItem(Player player, Person person) {
        boolean answered = false;
        Item item = person.getItemOnOffer();

        Parser pars = new Parser();
        while (!answered) {
            System.out.println(person.getDisplayName() + " offered you: " + item + ".");
            System.out.println(person.getCurrentOptions());

            String answer = pars.getFirstOnly(); // hold till answer
            answered = person.nextDialogue(answer);
            if(answer.equals("take")) {
                person.givePlayer(player, item);
                System.out.println("You received: " + item);
                answered = true;
            }
        }
    }
}
