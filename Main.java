import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader();
        boolean isRunning = true;
        int choice;
        String filePath;

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
                    System.out.print("Enter location of map: ");
                    filePath = scanner.nextLine();
                    ArrayList<Map> map = reader.readMap(filePath);

                    /* DEBUG CHECKER IF DISTANCES ARE BEING READ
                    for (int i = 0; i < map.size(); i++) {
                        System.out.println("DEBUG: " + map.get(i).getDistance());
                    }*/

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
