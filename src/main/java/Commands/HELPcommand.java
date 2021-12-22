package Commands;

import main.Parser;
import main.Player;

import java.lang.reflect.Array;
import java.util.*;

public class HELPcommand extends Command{
    private final HashSet<String> helpWords;

    public HELPcommand(List<String> args, HashSet<CommandFactory.CommandType>
            commands, HashMap<String, CommandFactory.CommandType> map) {
        super(args);
        helpWords = getHelpWords(commands, map);
    }

    @Override
    public boolean execute(Player player) {
        System.out.println("Your command words are: " + String.join(", ", helpWords));
        return false;
    }

    private HashSet<String> getHelpWords(HashSet<CommandFactory.CommandType>
                                                 commands, HashMap<String, CommandFactory.CommandType> map) {
        map.remove("unknown");
        for(String word : map.keySet()) { // remove all commands not in set
            if(!commands.contains(map.get(word))) map.remove(word);
        }
        return new HashSet<>(map.keySet()); // return keys of remaining commands
    }
}
