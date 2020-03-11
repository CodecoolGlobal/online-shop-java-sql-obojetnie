package classes.menus;

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

    public String getStringInputWithMessage(String message) {
        System.out.println(message);
        return scanner.next();
    }
}
