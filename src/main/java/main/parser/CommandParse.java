package main.parser;

import Commands.CommandFactory;
import main.CommandList;

import java.util.*;

public class CommandParse extends Parser{
    private final CommandFactory commandFactory;
    private final HashSet<CommandFactory.Command> commands;

    public CommandParse(CommandParse.Type type) {
        commandFactory = new CommandFactory();
        commands = CommandList.getCommands(type);
    }

    public enum Type {
        WALK, FIGHT
    }
    public Commands.Command getCommand() {
        String[] lineArray = getInput().toLowerCase(Locale.ROOT).split("\s+");
        List<String> args = toArgs(lineArray);
        CommandFactory.Command commandWord = commandFactory.getCommand(lineArray[0]);

        return toCommand(commandWord, args);
    }

    private List<String> toArgs(String[] lineArray) {
        List<String> args = new LinkedList<>(Arrays.asList(lineArray));  //convert input array to linkedList
        args.remove(0); //remove command word from arguments
        return args;
    }

    private Commands.Command toCommand(CommandFactory.Command commandWord, List<String> args) {
        if(commands.contains(commandWord)) {
            return commandFactory.getCommand(commandWord, args, commands);
        } else {
            return commandFactory.getCommand(CommandFactory.Command.UNKNOWN, args, commands);
        }
    }
}
