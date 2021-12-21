package Commands;

import java.util.List;

public class LOOKcommand extends Command {

    public LOOKcommand(List<String> args) {
        super(args);
    }

    public boolean execute() {
        System.out.println("test");
        return false;
    }


}
