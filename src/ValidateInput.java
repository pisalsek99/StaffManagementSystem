import java.util.Scanner;

public class ValidateInput {
    public static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(StaffManagementSystem.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }

    public static double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(StaffManagementSystem.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid double: ");
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

        // Regular expression to allow only alphabets (a-z, A-Z) and spaces
        while (!input.matches("[a-zA-Z ]+")) {
            System.out.print("Invalid input. Only letters and spaces are allowed.");
            input = sc.nextLine();
        }
        return input;
    }
}