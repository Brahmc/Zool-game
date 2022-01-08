package main;

import java.util.Collections;
import java.util.HashSet;
import Commands.CommandFactory.Command;

public class CommandList {


    public CommandList() {

    }

    private static HashSet<Command> getDefault() { //commands you can ALWAYS do
        HashSet<Command> commands = new HashSet<>();
        Collections.addAll(commands,
                Command.HELP);
        return commands;
    }

    private static HashSet<Command> getWALK() {
        HashSet<Command> commands = getDefault();
        Collections.addAll(commands,
                Command.LOOK,
                Command.UNKNOWN,
                Command.TAKE,
                Command.DROP,
                Command.GO,
                Command.QUIT,
                Command.TEST,
                Command.TALK,
                Command.INV,
                Command.BACK,
                Command.USE,
                Command.FIGHT);
        return commands;
    }

    private static HashSet<Command> getFIGHT() {
        HashSet<Command> commands = getDefault();
        Collections.addAll(commands,
                Command.ATTACK,
                Command.INV,
                Command.USE);
        return commands;
    }

    public static HashSet<Command> getCommands(CommandParse.Type type) {
        return switch (type) {
            case WALK -> getWALK();
            case FIGHT -> getFIGHT();
        };
    }
}
