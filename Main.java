import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        int choice;

        while (isRunning) {
            System.out.println("+-----------------------------+");
            System.out.println("|   Welcome to Mailman Sims   |");
            System.out.println("+-----------------------------+");

            System.out.println("Please select an option:");
            System.out.println("1. Start Simulation");
            System.out.println("2. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Starting the simulation...");
                    // Add simulation logic here
                    break;
                case 2:
                    isRunning = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
