package main.dialogue;

import main.items.Item;
import main.parser.Parser;
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

            if(nonPlayer.getDialogue().getType().equals(Dialogue.Type.END)) {
                nonPlayer.nextDialogue(1); // loads next dialogue but doesn't ask any new questions and ends the loop.
                finished = true;
            } else {
                getResponse(player, nonPlayer);
            }
        }

        System.out.println();
        System.out.println(player.getInfo());
    }

    private static void getResponse(Player player, NonPlayer nonPlayer) {
        Parser pars = new Parser();
        boolean answered = false;
        while (!answered) {
            System.out.println(nonPlayer.getCurrentOptions());
            int answer = getInt(pars.getFirstOnly()); // hold till answer

            if(answerAction(player, nonPlayer, answer)) {
                answered = nonPlayer.nextDialogue(answer);
            } else answered = true;
        }
    }

    /**
     * @return true if nextDialogue should be triggered
     */
    private static boolean answerAction(Player player, NonPlayer nonPlayer, int answer) {
        if(answer == -1) return true; // no valid answer
        return switch (nonPlayer.getDialogue().getType()) {
            case GIVE -> give(player, nonPlayer, answer);
            case RECEIVE -> receive(player, nonPlayer, answer);
            default -> true;
        };
    }

    private static boolean give(Player player, NonPlayer nonPlayer, int answer) {
        Item item = nonPlayer.getItemOnOffer();
        if(answer == 1) {
            String response = nonPlayer.giveCharacter(player, item);
            System.out.println(response);
        }
        return true;
    }

    private static boolean receive(Player player, NonPlayer nonPlayer, int answer){
        Item item = nonPlayer.getItemOnOffer();
        if(player.getInventory().contains(item)) { // check for item
            if(answer == 1) player.giveCharacter(nonPlayer, item);
            return true;
        }
        nonPlayer.nextDialogue(2); // NoItem response
        return false;
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

        StringBuilder endString = new StringBuilder(nonPlayer.getDisplayName() + ": ???");
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
        endString.append("???");
        System.out.println(endString);
    }
}
