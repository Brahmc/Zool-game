import java.awt.*;

public class Test {

    public static void main(String[] args) {
        int PRINTCOLOR = 145;


        String COLOR_START = "\u001B[" + PRINTCOLOR + "m";
        String COLOR_END = "\u001B[0m";
        System.out.print(COLOR_START + " Hello world! " + COLOR_END);

        System.out.println();
    }
}
