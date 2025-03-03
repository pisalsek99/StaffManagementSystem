import java.util.Scanner;

public class ValidateInput {
    public static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(Main.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print (Main.RED + "Invalid input. Please enter a valid integer: " + Main.RESET);
            }
        }
    }

    public static double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(Main.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print( Main.RED + "Invalid input. Please enter a valid double: " + Main.RESET);
            }
        }
    }

    public static String getStringInput(Scanner sc, String errorMessage) {
        String input = sc.nextLine();

        // Check if input is empty
        while (input.trim().isEmpty()) {
            System.out.print(errorMessage);
            input = sc.nextLine();
        }
        while (!input.matches("[a-zA-Z ]+")) {
            System.out.print(Main.RED + "Invalid input. Only letters and spaces are allowed." + Main.RESET);
            input = sc.nextLine();
        }
        return input;
    }
}