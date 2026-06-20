import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {

        // ORDER IS STILL TEMPORARY
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader();
        boolean isRunning = true;
        int choice, choice2, choice3;
        String filePath;
        ArrayList<Map> map;
        String prevCity;
        int cityIdx;
        ArrayList<String> postOffices;
        int i, j;
        int mailCount;
        String destination;
        ArrayList<Map> routes;

        while(isRunning) {
            System.out.println("+-----------------------------+");
            System.out.println("|   Welcome to Mailman Sims   |");
            System.out.println("+-----------------------------+");

            do {
                System.out.println("Please select an option:");
                System.out.println("1. Start Simulation");
                System.out.println("2. Exit");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Starting the simulation...");

                        do {
                            System.out.print("Enter location of map: ");
                            filePath = scanner.nextLine();
                            map = reader.readMap(filePath);

                            /* DEBUG MAP READER(for distance)
                            for (int i = 0; i < map.size(); i++) {
                                System.out.println("DEBUG: " + map.get(i).getDistance());
                            }*/

                            System.out.println("List of cities:");
                            prevCity = "";
                            cityIdx = 1;
                            postOffices = new ArrayList<String>();

                            for (i = 0; i < map.size(); i++) {
                                String currCity = map.get(i).getCity();
                                String postOffice = map.get(i).getOffice();

                                if(!(currCity.equals(prevCity))) {
                                    System.out.println(cityIdx + " - " + currCity);
                                    postOffices.add(postOffice);
                                    cityIdx++;
                                }

                                prevCity = currCity;
                            }

                            do {
                                System.out.print("Select a city to start: ");
                                choice2 = scanner.nextInt();
                                scanner.nextLine();

                                if(choice2 <= 0 || choice2 >= cityIdx)
                                    System.out.println("Invalid option. Please try again.");
                            } while(choice2 <= 0 || choice2 >= cityIdx);

                            System.out.println("We are going to " + postOffices.get(choice2 - 1) + " to deliver the mails.\n");
                            // (OPTIONAL) ADD A DISPLAY SEQUENCE HERE
                            System.out.print("Enter the amount of mails: ");
                            mailCount = scanner.nextInt();
                            scanner.nextLine();
                            routes = new ArrayList<Map>();

                            for(i = 0; i < mailCount; i++) {
                                boolean isFound;

                                do {
                                    isFound = false;
                                    System.out.print("Destination of Mail " + (i + 1) + ": ");
                                    destination = scanner.nextLine();

                                    for(j = 0; j < map.size(); j++) {
                                        if(destination.equals(map.get(j).getUniversity()) && !isFound) {
                                            routes.add(map.get(j));
                                            isFound = true;
                                        }
                                    }

                                    if(!isFound)
                                        System.out.println("Invalid input. Please try again.");
                                } while(!isFound);
                            }

                            /* DEBUG ROUTES READER
                            for(i = 0; i < routes.size(); i++) {
                                System.out.println("(DEBUG) Route " + (i + 1) + ": " + routes.get(i).getUniversity());
                            }*/

                            // SIMULATION DISPLAY SEQUENCE

                            do {
                                System.out.println("Would you like to simulate again? (1 for Yes, 2 for No)");
                                choice3 = scanner.nextInt();
                                scanner.nextLine();

                                switch(choice3) {
                                    case 1:
                                        System.out.println("Restarting the simulation...");
                                        break;
                                    case 2:
                                        isRunning = false;
                                        System.out.println("Exiting the program. Goodbye!");
                                        break;
                                    default:
                                        System.out.println("Invalid option. Please try again.");
                                }
                            } while(choice3 != 1 && choice3 != 2);
                        } while(isRunning);
                        break;
                    case 2:
                        isRunning = false;
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } while(choice != 1 && choice != 2);
        }
        scanner.close();
    }
}
