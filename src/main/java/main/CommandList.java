package main;

import Commands.CommandFactory;

import java.util.Collections;
import java.util.HashSet;

public class CommandList{


    public CommandList() {

    }

    private static HashSet<CommandFactory.CommandType> getDefault() { //commands you can ALWAYS do
        HashSet<CommandFactory.CommandType> commands = new HashSet<>();
        Collections.addAll(commands,
                CommandFactory.CommandType.HELP);
        return commands;
    }

    private static HashSet<CommandFactory.CommandType> getWALK() {
        HashSet<CommandFactory.CommandType> commands = getDefault();
        Collections.addAll(commands,
                CommandFactory.CommandType.LOOK,
                CommandFactory.CommandType.UNKNOWN);
        return commands;
    }

    private static HashSet<CommandFactory.CommandType> getFIGHT() {
        HashSet<CommandFactory.CommandType> commands = getDefault();

        return commands;
    }

    public static HashSet<CommandFactory.CommandType> getCommands(Parser.Type type) {
        return switch (type) {
            case WALK -> getWALK();
            case FIGHT -> getFIGHT();
        };
    }
}
