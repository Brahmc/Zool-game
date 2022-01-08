package Commands;

import main.dialogue.DialogueProcessor;
import main.characters.NonPlayer;
import main.characters.Player;

import java.util.List;

public class TALKcommand extends Command{

    public TALKcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
        return "Talk to Characters in the current room.";
    }

    @Override
    public boolean execute(Player player) {
        if(getArgs().size() == 0) {
            System.out.println("Talk to who?");
            return false;
        }

        String characterName = getArgs().get(0);
        NonPlayer character = player.getNearbyCharacterByName(characterName);

        if(character == null) {
            System.out.println("There is no such character in the room!");
            return false;
        }
        if(!character.hasDialogue()) {
            System.out.println("You can't talk to that character!");
            return false;
        }
        DialogueProcessor.processDialogue(character, player);
        return false;
    }
}
