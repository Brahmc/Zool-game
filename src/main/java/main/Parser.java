package main;

import java.util.*;

public class Parser {
    private final Scanner reader;

    public Parser() {
        reader = new Scanner(System.in);
    }

    public String getInput() {
        String inputLine;
        System.out.print(">");

        inputLine = reader.nextLine();
        return inputLine.trim();
    }
}
