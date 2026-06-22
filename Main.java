import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader();
        boolean isRunning = true;
        int choice, choice2, choice3;
        ArrayList<Map> map;
        ArrayList<Map> routes;
        ArrayList<String> postOffices;
        ArrayList<String> cityNames;
        HashMap<String, String> cityToPost = new HashMap<String, String>();
        ArrayDeque<String> cities = new ArrayDeque<String>();
        String filePath;
        String prevCity;
        String destination;
        String city;
        String currOffice;
        int cityIdx;
        int mailCount;
        int i, j;

        while(isRunning) {
            System.out.println("+-------------------------------------+");
            System.out.println("|   Welcome to Arrow Mail Simulator   |");
            System.out.println("+-------------------------------------+");

            try {
                System.out.println("Please select an option:");
                System.out.println("1. Start Simulation");
                System.out.println("2. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice) {
                    case 1:
                        System.out.println("Starting the simulation...");

                        do {
                            map = new ArrayList<Map>();
                            routes = new ArrayList<Map>();
                            cities.clear();
                            boolean isRead = false;

                            while(!isRead) {
                                try {
                                    System.out.print("Enter location of map: ");
                                    filePath = scanner.nextLine();
                                    map = reader.readFile(filePath);
                                    isRead = true;
                                } catch (FileNotFoundException e) {
                                    System.out.println("Could not locate file. Please try again.");
                                } catch (NumberFormatException e) {
                                    System.out.println("Could not read number format. Please try again.");
                                } catch (IOException e) {
                                    System.out.println("Could not read file. Please try again.");
                                }
                            }

                            System.out.println("List of cities:");
                            prevCity = "";
                            cityIdx = 1;
                            postOffices = new ArrayList<String>();
                            cityNames = new ArrayList<String>();

                            for (i = 0; i < map.size(); i++) {
                                String currCity = map.get(i).getCity();
                                String postOffice = map.get(i).getOffice();

                                if (!(currCity.equals(prevCity))) {
                                    System.out.println(cityIdx + " - " + currCity);
                                    postOffices.add(postOffice);
                                    cityNames.add(currCity);
                                    cityIdx++;
                                    cityToPost.put(currCity, postOffice);
                                }
                                prevCity = currCity;
                            }

                            boolean isValidNum = false;
                            choice2 = 0;

                            do {
                                try {
                                    System.out.print("Select the number of the city to start: ");
                                    choice2 = scanner.nextInt();
                                    scanner.nextLine();

                                    if (choice2 <= 0 || choice2 >= cityIdx)
                                        System.out.println("Invalid option. Please try again later.");
                                    else isValidNum = true;
                                } catch(InputMismatchException e) {
                                    System.out.println("Invalid input. Please try again.");
                                    scanner.nextLine();
                                }
                            } while(!isValidNum);

                            cities.add(cityNames.get(choice2 - 1));
                            int instance = 1;
                            while (!cities.isEmpty()) {
                                boolean isValidCount = false;
                                mailCount = 0;
                                city = cities.peek();
                                currOffice = cityToPost.get(city);
                                System.out.println("We are going to " + currOffice + " to get the mails for delivery.");

                                do {
                                    try {
                                        System.out.print("Enter the amount of mails: ");
                                        mailCount = scanner.nextInt();
                                        scanner.nextLine();

                                        if(mailCount <= 0 && instance == 1)
                                            System.out.println("Invalid input. Please try again.");
                                        else{
                                            isValidCount = true;
                                            instance++;
                                        } 
                                            
                                    } catch(InputMismatchException e) {
                                        System.out.println("Invalid input. Please try again.");
                                        scanner.nextLine();
                                    }
                                } while(!isValidCount);

                                for (i = 0; i < mailCount; i++) {
                                    boolean isFound;

                                    do {
                                        isFound = false;
                                        System.out.print("Destination of Mail " + (i + 1) + ": ");
                                        destination = scanner.nextLine();

                                        for (j = 0; j < map.size(); j++) {
                                            if (destination.equals(map.get(j).getUniversity()) && !isFound) {
                                                routes.add(map.get(j));
                                                isFound = true;

                                                if (!cities.contains(map.get(j).getCity()) && !map.get(j).getCity().equals(city)) {
                                                    cities.add(map.get(j).getCity());
                                                }
                                            }
                                        }

                                        if(!isFound)
                                            System.out.println("Invalid input. Please try again.");
                                    } while(!isFound);
                                }

                                city = cities.poll();
                                Sorter.cocktailSort(routes);
                                Display.clearScreen();
                                Display.showRoute(routes, city);
                                System.out.println("Delivering mails to " + city + "...");

                                for (i = 0; i < routes.size(); i++) {
                                    if (routes.get(i).getCity().equals(city)) {
                                        Display.showStorage(routes);
                                        System.out.println();
                                        System.out.println("Delivering mail to " + routes.get(i).getUniversity() + " in " + routes.get(i).getCity() + " with distance " + routes.get(i).getDistance() + " km");
                                        // SIMULATION DISPLAY SEQUENCE
                                        Display.animateDelivery(routes.get(i).getUniversity());
                                        routes.remove(i);
                                        i--;
                                    }
                                }
                                Display.showStorage(routes);
                                System.out.println("\nAll mails for " + city + " have been delivered!");

                                if (!cities.isEmpty())
                                    System.out.println("Let us go to the next post office.\n");
                                else System.out.println("We are officially done for today!\n");
                            }

                            boolean isValidChoice = false;
                            choice3 = 0;

                            do {
                                try {
                                    System.out.print("Would you like to simulate again? (1 for Yes, 2 for No): ");
                                    choice3 = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (choice3) {
                                        case 1:
                                            System.out.println("Restarting the simulation...");
                                            isValidChoice = true;
                                            break;
                                        case 2:
                                            System.out.println("Going Back To The Main Menu...");
                                            isValidChoice = true;
                                            break;
                                        default:
                                            System.out.println("Invalid option. Please try again.");
                                    }
                                } catch(InputMismatchException e) {
                                    System.out.println("Invalid input. Please try again.");
                                    scanner.nextLine();
                                }
                            } while(!isValidChoice);
                        } while(choice3 == 1);
                        break;
                    case 2:
                        isRunning = false;
                        Display.exitMessage();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid option. Please try again.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}
