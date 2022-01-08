package Commands;

import main.characters.Player;

import java.util.*;

public class HELPcommand extends Command {
    private final HashSet<String> helpWords;
    private final CommandFactory factory;

    public HELPcommand(List<String> args, HashSet<CommandFactory.Command> commands) {
        super(args);
        helpWords = getHelpWords(commands);
        factory = new CommandFactory();
    }

    @Override
    public String getDescription() {
        return "Get a list of current available commands.";
    }

    @Override
    public boolean execute(Player player) {
        List<String> args = getArgs();
        if(args.size() <= 0) {
            System.out.println("Your command words are: " + String.join(", ", helpWords));
            System.out.println("Use 'help [command word]' for more detail.");
            return false;
        }
        CommandFactory.Command commandType = factory.getCommand(args.get(0));
        Command command = factory.getCommand(commandType, null, null); // since command is never executed last 2 parameters are not needed

        System.out.println(command.getDescription());
        return false;
    }

    private HashSet<String> getHelpWords(HashSet<CommandFactory.Command> commands) {
        HashMap<String, CommandFactory.Command> map = CommandFactory.getCommandMap();
        map.remove("unknown");
        HashSet<String> currentlyValid = new HashSet<>();
        for(String word : map.keySet()) { // remove all commands not in set
            if(commands.contains(map.get(word))) currentlyValid.add(word);
        }
        return currentlyValid; // return keys of remaining commands
    }
}
