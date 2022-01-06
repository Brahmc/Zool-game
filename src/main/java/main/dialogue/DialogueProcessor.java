package main.dialogue;

import main.items.Item;
import main.Parser;
import main.characters.NonPlayer;
import main.characters.Player;

import java.util.Scanner;

public class DialogueProcessor {
    private final static int PRINT_COLOR = 33;

    public static void processDialogue(NonPlayer nonPlayer, Player player) {
        boolean finished = false;
        while(!finished) {
            printText(nonPlayer, player);
            if(!nonPlayer.getDialogue().hasFollowUp()) return; // don't to continue if dialogue is text only
            switch (nonPlayer.getDialogue().getType()) {
                case DEFAULT -> getResponse(nonPlayer);
                case GIVE -> giveItem(player, nonPlayer);
                case END -> {   // loads next dialogue but doesn't ask any new questions and ends the loop.
                    nonPlayer.nextDialogue(1);
                    finished = true;
                }
            }
        }

        System.out.println();
        System.out.println(player.getInfo());
    }

    private static void getResponse(NonPlayer nonPlayer) {
        Parser pars = new Parser();
        boolean answered = false;
        while (!answered) {
            System.out.println(nonPlayer.getCurrentOptions());
            int answer = getInt(pars.getFirstOnly()); // hold till answer
            answered = nonPlayer.nextDialogue(answer);
        }
    }

    private static void giveItem(Player player, NonPlayer nonPlayer) {
        Item item = nonPlayer.getItemOnOffer();
        Parser pars = new Parser();
        boolean answered = false;
        while (!answered) {
            System.out.println(nonPlayer.getCurrentOptions());
            int answer = getInt(pars.getFirstOnly()); // hold till answer
            answered = nonPlayer.nextDialogue(answer);

            if(answer == 1) {
                String response = nonPlayer.giveCharacter(player, item);
                System.out.println(response);
            }
        }
    }

    private static int getInt(String string) {
        try{
            return Integer.parseInt(string);
        } catch (Exception e) {
            return -1;
        }
    }

    private static void printText(NonPlayer nonPlayer, Player player) {
        String text = nonPlayer.getDialogue().getText();
        Scanner converter = new Scanner(text).useDelimiter("__");

        StringBuilder endString = new StringBuilder(nonPlayer.getDisplayName() + ": “");
        while (converter.hasNext()) {
            String part = converter.next();
            switch (part) {
                case "PLAYER_NAME" -> endString.append(player.getDisplayName());
                case "CHARACTER_NAME" -> endString.append(nonPlayer.getDisplayName());
                case "ITEM_NAME" -> endString.append(nonPlayer.getItemOnOffer().getName());
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
