package testApplication;

import java.util.Scanner;

public class InputOutputManager {
    private Scanner scanner = new Scanner(System.in);

    public String getInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}