import java.util.Scanner;
import java.util.ArrayList;
import java.util.ArrayDeque;

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
        ArrayList<String> cityNames;
        int i, j, restartChoice;
        int mailCount;
        String destination;
        ArrayList<Map> routes;
        ArrayDeque<String> cities = new ArrayDeque<String>();
        String city;

        while(isRunning) {
            System.out.println("+-----------------------------+");
            System.out.println("|   Welcome to Mailman Sims   |");
            System.out.println("+-----------------------------+");
            System.out.println("Please select an option:");
            System.out.println("1. Start Simulation");
            System.out.println("2. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    System.out.println("Starting the simulation...");

                    do {
                        routes = new ArrayList<Map>();
                        cities.clear();
                        System.out.print("Enter location of map: ");
                        filePath = scanner.nextLine();
                        map = reader.readMap(filePath);

                        System.out.println("List of cities:");
                        prevCity = "";
                        cityIdx = 1;
                        postOffices = new ArrayList<String>();
                        cityNames = new ArrayList<String>();

                        for(i = 0; i < map.size(); i++) {
                            String currCity = map.get(i).getCity();
                            String postOffice = map.get(i).getOffice();

                            if(!(currCity.equals(prevCity))) {
                                System.out.println(cityIdx + " - " + currCity);
                                postOffices.add(postOffice);
                                cityNames.add(currCity);
                                cityIdx++;
                            }
                            prevCity = currCity;
                        }

                        do {
                            System.out.print("Select a city to start: ");
                            choice2 = scanner.nextInt();
                            scanner.nextLine();

                            if(choice2 <= 0 || choice2 >= cityIdx)
                                System.out.println("Invalid option. Please try again later.");
                        } while(choice2 <= 0 || choice2 >= cityIdx);

                        cities.add(cityNames.get(choice2 - 1));

                        while(!cities.isEmpty()) {

                            city = cities.peek();
                            // LINE BELOW NEEDS FIXING, SHOULD DISPLAY POST OFFICE NAME NOT CITY NAME
                            System.out.println("We are going to " + cities.peekFirst() + " to deliver the mails.");
                            // (OPTIONAL) ADD A DISPLAY HERE
                            System.out.print("Enter the amount of mails: ");
                            mailCount = scanner.nextInt();
                            scanner.nextLine();

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

                                            if(!cities.contains(map.get(j).getCity()) && !map.get(j).getCity().equals(city)) {
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
                            System.out.println("Delivering mails to " + city);

                            for(i = 0; i < routes.size(); i++) {   
                                if(routes.get(i).getCity().equals(city)) {
                                    System.out.println("Delivering mail to " + routes.get(i).getUniversity() + " in " + routes.get(i).getCity() + " with distance " + routes.get(i).getDistance());                             
                                    routes.remove(i);
                                    // SIMULATION DISPLAY SEQUENCE
                                    i--;
                                }
                            }

                            System.out.println("All mails for " + city + " have been delivered!");

                            if(!cities.isEmpty())
                                System.out.println("Let us go to the next post office.");
                            else System.out.println("We are officially done for today!");
                        }

                        do {
                            System.out.print("Would you like to simulate again? (1 for Yes, 2 for No): ");
                            restartChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch(restartChoice) {
                                case 1:
                                    System.out.println("Restarting the simulation...");
                                    break;
                                case 2:
                                    System.out.println("Going Back To The Main Menu...");
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                        } while(restartChoice != 1 && restartChoice != 2);
                    } while(restartChoice == 1);
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