package main;

import Commands.Command;
import Commands.CommandFactory;

import java.util.*;

public class Parser {
    private final Scanner reader;
    private final CommandFactory commandFactory;
    private final HashSet<CommandFactory.CommandType> commands;

    public Parser(Type type) {
        commandFactory = new CommandFactory();
        commands = CommandList.getCommands(type);
        reader = new Scanner(System.in);
    }

    public enum Type {
        WALK, FIGHT, NULL
    }


    protected String[] getInput() {
        String inputLine;
        System.out.print(">");

        inputLine = reader.nextLine();
        return inputLine.toLowerCase(Locale.ROOT).split("\s+");
    }

    public Command getCommand() {
        String[] lineArray = getInput();
        List<String> args = toArgs(lineArray);
        CommandFactory.CommandType commandWord = commandFactory.getCommand(lineArray[0]);

        return toCommand(commandWord, args);
    }

    private List<String> toArgs(String[] lineArray) {
        List<String> args = new LinkedList<>(Arrays.asList(lineArray));  //convert input array to linkedList
        args.remove(0); //remove command word from arguments
        return args;
    }

    protected Command toCommand(CommandFactory.CommandType commandWord, List<String> args) {
        if(commands.contains(commandWord)) {
            return commandFactory.getCommand(commandWord, args, commands);
        } else {
            return commandFactory.getCommand(CommandFactory.CommandType.UNKNOWN, args, commands);
        }
    }
}
