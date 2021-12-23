package main;

import main.Exceptoins.NoDefaultDialogeException;

import java.util.Scanner;

public class DialogueProcessor {

    public static void proccesDialogue(Person person, Player player) {
        Dialogue dialogue = person.getDialogue();
        printText(dialogue.getText(), player);
        System.out.println();
        if(!person.getDialogue().hasOptions()) return;

        if(dialogue instanceof DialogueGive give) {
            giveItem(give, player, person);
        }   else if(dialogue instanceof DialogueDefault dDefault){
            getResponse(dDefault, person);
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

    private static void getResponse(DialogueDefault dDefault, Person person) {
        Parser p = new Parser();
        while (true) {
            System.out.println(dDefault.getOptions());
            String answer = p.getFirstOnly(); // hold till answer
            if(dDefault.hasOption(answer)) {
                try {
                    person.nextDialogue(answer);
                    return;
                } catch (NoDefaultDialogeException e) { // should in theory never happen
                    e.printStackTrace();
                    return;
                }
            }
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
            if(answer.equals("take")) {
                player.getInventory().add(item);
                person.getInventory().remove(item);
                person.setCurrentDialogue(dialogueGive.getTakeResponse());
                System.out.println("You received: " + item);
                answered = true;
            }
            if(answer.equals("refuse")) {
                person.setCurrentDialogue(dialogueGive.getRefuseResponse());
                answered = true;
            }
        }
    }
}
