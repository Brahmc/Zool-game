package main.parser;

import java.util.*;

public class Parser {
    private final Scanner reader;
    private final String INPUT_CHAR;

    public Parser() {
        this(">");
    }
    public Parser(String inputChar) {
        reader = new Scanner(System.in);
        INPUT_CHAR = inputChar;
    }

    public String getInput() {
        String inputLine;
        System.out.print(INPUT_CHAR);

        inputLine = reader.nextLine();
        return inputLine.trim();
    }

    public String getFirstOnly() {
        return getInput().split("\s+")[0];
    }
}
