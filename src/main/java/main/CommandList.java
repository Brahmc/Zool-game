package main;

import Commands.CommandFactory;

import java.util.Collections;
import java.util.HashSet;
import Commands.CommandFactory.CommandType;

public class CommandList implements CmdList{


    public CommandList() {

    }

    private static HashSet<CommandType> getDefault() { //commands you can ALWAYS do
        HashSet<CommandType> commands = new HashSet<>();
        Collections.addAll(commands,
                CommandType.HELP);
        return commands;
    }

    private static HashSet<CommandType> getWALK() {
        HashSet<CommandType> commands = getDefault();
        Collections.addAll(commands,
                CommandType.LOOK,
                CommandType.UNKNOWN,
                CommandType.TAKE,
                CommandType.DROP,
                CommandType.GO,
                CommandType.QUIT,
                CommandType.TEST,
                CommandType.TALK);
        return commands;
    }

    private static HashSet<CommandType> getFIGHT() {
        HashSet<CommandType> commands = getDefault();

        return commands;
    }

    public static HashSet<CommandFactory.CommandType> getCommands(CommandParse.Type type) {
        return switch (type) {
            case WALK -> getWALK();
            case FIGHT -> getFIGHT();
        };
    }
}
