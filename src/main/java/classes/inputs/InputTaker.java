package classes.inputs;

import classes.enums.Option;

import java.util.Locale;
import java.util.Scanner;

public class InputTaker {

    Scanner scanner = new Scanner(System.in);

    public int getIntInput() {
        return scanner.nextInt();
    }

    public String getStringInput() {
        return scanner.nextLine();
    }

    public int getIntinputWithMessage(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }

    public double getDoubleInputWithMessage(String message) {
        System.out.println(message);
        Scanner doubleScanner = new Scanner(System.in).useLocale(Locale.US);
        return doubleScanner.nextDouble();
    }

    public String getStringInputWithMessage(String message) {
        System.out.println(message);
        return scanner.next();
    }

    public Option getOptionInt() throws Exception {
        int input = scanner.nextInt();
        Option option;
        switch (input) {
            case 1 -> option = Option.ONE;
            case 2 -> option = Option.TWO;
            case 3 -> option = Option.THREE;
            case 4 -> option = Option.FOUR;
            case 5 -> option = Option.FIVE;
            case 6 -> option = Option.SIX;
            case 7 -> option = Option.SEVEN;
            case 8 -> option = Option.EIGHT;
            case 9 -> option = Option.NINE;
            case 0 -> option = Option.ZERO;
            default -> throw new Exception("Something went wrong.");
        }
        return option;
    }

    public Option getOptionIntWithMessage(String message) throws Exception {
        System.out.println(message);
        int input = scanner.nextInt();
        Option option;
        switch (input) {
            case 1 -> option = Option.ONE;
            case 2 -> option = Option.TWO;
            case 3 -> option = Option.THREE;
            case 4 -> option = Option.FOUR;
            case 5 -> option = Option.FIVE;
            case 6 -> option = Option.SIX;
            case 7 -> option = Option.SEVEN;
            case 8 -> option = Option.EIGHT;
            case 9 -> option = Option.NINE;
            case 0 -> option = Option.ZERO;
            default -> throw new Exception("Something went wrong.");
        }
        return option;
    }
}
