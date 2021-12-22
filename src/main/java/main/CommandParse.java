package main;

import Commands.Command;
import Commands.CommandFactory;

import java.util.*;

public class CommandParse extends Parser{
    private final CommandFactory commandFactory;
    private final HashSet<CommandFactory.CommandType> commands;

    public CommandParse(CommandParse.Type type) {
        commandFactory = new CommandFactory();
        commands = CommandList.getCommands(type);
    }

    public enum Type {
        WALK, FIGHT
    }
    public Command getCommand() {
        String[] lineArray = getInput().toLowerCase(Locale.ROOT).split("\s+");
        List<String> args = toArgs(lineArray);
        CommandFactory.CommandType commandWord = commandFactory.getCommand(lineArray[0]);

        return toCommand(commandWord, args);
    }

    protected List<String> toArgs(String[] lineArray) {
        List<String> args = new LinkedList<>(Arrays.asList(lineArray));  //convert input array to linkedList
        args.remove(0); //remove command word from arguments
        return args;
    }

    private Command toCommand(CommandFactory.CommandType commandWord, List<String> args) {
        if(commands.contains(commandWord)) {
            return commandFactory.getCommand(commandWord, args, commands);
        } else {
            return commandFactory.getCommand(CommandFactory.CommandType.UNKNOWN, args, commands);
        }
    }
}
