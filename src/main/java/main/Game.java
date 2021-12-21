package main;

import Commands.Command;

public class Game {
    private final Parser parser;

    public Game() {
        parser = new Parser(Parser.Type.WALK);
    }

    public void play() {
        boolean finished = false;

        while(!finished) {
            Command c = parser.getCommand();
            finished = c.execute();
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
