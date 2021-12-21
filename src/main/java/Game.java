import Commands.Command;

public class Game {
    private final WalkModule walkModule;

    public Game() {
        walkModule = new WalkModule();
    }

    public void play() {
        boolean finished = false;

        while(!finished) {
            Command c = walkModule.getCommand();
            finished = c.execute();
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
