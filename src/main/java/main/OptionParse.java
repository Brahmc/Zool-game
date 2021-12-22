package main;

import java.util.Arrays;
import java.util.Locale;

public class OptionParse {

    public OptionParse() {

    }

    public static String multipleChoice(Parser parser, String desc, String... options) {

        String choices = desc + " \n Options " + String.join(", ", options);
        System.out.println(choices);

        while(true) {
            String input = parser.getInput().toLowerCase(Locale.ROOT);
            if(Arrays.stream(options).toList().contains(input)) {
                return input;
            }
            System.out.println(choices);
        }
    }

    /**
     * @return true if option1, false if option2
     */
    public static boolean twoChoice(Parser parser, String desc, String op1, String op2) {
        String choices = desc + " \n Options: " + String.join(", ", op1, op2);
        System.out.println(choices);

        while(true) {
            String input = parser.getInput().toLowerCase(Locale.ROOT);
            if(input.equals(op1.toLowerCase(Locale.ROOT))) {
                return true;
            }
            if(input.equals(op2.toLowerCase(Locale.ROOT))) {
                return false;
            }
            System.out.println(choices);
        }
    }
}
