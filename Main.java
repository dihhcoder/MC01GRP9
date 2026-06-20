import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {

        // Order is still temporary
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader();
        boolean isRunning = true;
        int choice, choice2;
        String filePath;
        ArrayList<Map> map;
        String prevCity;
        int cityIdx;
        ArrayList<String> postOffices;
        int i, j, restartChoice;
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
                                try {
                                    System.out.print("Select a city to start: ");
                                    choice2 = scanner.nextInt();
                                    scanner.nextLine();

                                    if(choice2 <= 0 || choice2 >= cityIdx)
                                        System.out.println("Invalid Option. Please try again.");
                                }

                                catch(InputMismatchException e) {
                                    System.out.println("Invalid Option. Please enter a valid number.");
                                    scanner.nextLine();
                                    choice2 = -1;
                                }
                            } while(choice2 <= 0 || choice2 >= cityIdx);

                            System.out.println("We are going to " + postOffices.get(choice2 - 1) + " to deliver the mails.\n");
                            // (OPTIONAL) Add a display sequence here
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
                                        if (destination.equals(map.get(j).getUniversity()) && !isFound) {
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

                            // Simulation Display Sequence

                            do {
                                System.out.println("Would you like to simulate again? (1 for Yes, 2 for No)");
                                restartChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch(restartChoice) {
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
                            } while(restartChoice != 1 && restartChoice != 2);
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

    public void cocktailSort(ArrayList<Map> routes) {
        boolean swapped = true;
        int start = 0;
        int end = routes.size() - 1;
        int i;

        while(swapped) {
            swapped = false;

            for(i = start; i < end; i++) {
                if (routes.get(i).getDistance() > routes.get(i + 1).getDistance()) {
                    Map temp = routes.get(i);
                    routes.set(i, routes.get(i + 1));
                    routes.set(i + 1, temp);
                    swapped = true;
                }
            }

            if(swapped) {
                swapped = false;
                end--;

                for(i = end - 1; i >= start; i--) {
                    if(routes.get(i).getDistance() > routes.get(i + 1).getDistance()) {
                        Map temp = routes.get(i);
                        routes.set(i, routes.get(i + 1));
                        routes.set(i + 1, temp);
                        swapped = true;
                    }
                }
                start++;
            }
        }
    }
}

