import Commands.CommandFactory;

import java.util.HashSet;

public class WalkModule extends CommandModule{

    HashSet<CommandFactory.CommandType> commands;

    public WalkModule() {
        commands = new HashSet<>();
        fillValidCommands();
    }

    private void fillValidCommands() {
        commands.add(CommandFactory.CommandType.LOOK);
        commands.add(CommandFactory.CommandType.UNKNOWN);
    }

    @Override
    protected HashSet<CommandFactory.CommandType> getCommands() {
        return commands;
    }
}
