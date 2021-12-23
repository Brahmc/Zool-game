package Commands;

import main.Character;
import main.DialogueProcessor;
import main.Person;
import main.Player;

import java.util.List;

public class TALKcommand extends Command{

    public TALKcommand(List<String> args) {
        super(args);
    }

    @Override
    public boolean execute(Player player) {
        if(getArgs().size() == 0) {
            System.out.println("Talk to who?");
            return false;
        }

        String characterName = getArgs().get(0);
        Character character = player.getCurrentRoom()
                .getCharacterByName(characterName);
        if(character == null) {
            System.out.println("There is no such person in the room!");
            return false;
        }
        if(!(character instanceof Person person)) {
            System.out.println("You can't talk to that character!");
            return false;
        }
        DialogueProcessor.proccesDialogue(person, player);
        return false;
    }
}
