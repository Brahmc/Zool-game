package main.dialogue;

import main.Exceptoins.NoDefaultDialogeException;
import main.Item;
import main.Parser;
import main.Person;
import main.Player;

import java.util.Scanner;

public interface DialogueProcessor {

    static void proccesDialogue(Person person, Player player) {
        Dialogue dialogue = person.getDialogue();
        printText(dialogue.getText(), player);
        System.out.println();
        if(!person.getDialogue().hasOptions()) return; // no need to continue if dialogue is text only

        if(dialogue instanceof DialogueGive give) {
            giveItem(give, player, person);
        }   else if(dialogue instanceof DialogueDefault){
            getResponse(person);
        }
         proccesDialogue(person, player); //run method again for next dialogue
    }

    private static void printText(String text, Player player) {
        Scanner converter = new Scanner(text).useDelimiter("__");
        while (converter.hasNext()) {
            String part = converter.next();
            if(part.equals("PLAYER_NAME")) {
                System.out.print(player.getName());
                continue;
            }
            System.out.print(part);
        }
    }

    private static void getResponse(Person person) {
        Parser p = new Parser();
        boolean answered = false;
        while (!answered) {
            System.out.println(person.getCurrentOptions());
            String answer = p.getFirstOnly(); // hold till answer

            answered = person.nextDialogue(answer);
        }
    }

    private static void giveItem(DialogueGive dialogueGive, Player player, Person person) {
        Item item = dialogueGive.getItem();
        boolean answered = false;

        Parser p = new Parser();
        while (!answered) {
            System.out.println(person.getName() + "Offered you: " + item + ".");
            System.out.println(dialogueGive.getOptions());

            String answer = p.getFirstOnly(); // hold till answer
            answered = person.nextDialogue(answer);
            if(answer.equals("take")) {
                person.givePlayer(player, item);
                System.out.println("You received: " + item);
                answered = true;
            }
        }
    }
}
