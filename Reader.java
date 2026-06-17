import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    public ArrayList<Route> readRoutes(String filePath) {

        ArrayList<Route> routes = new ArrayList<Route>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String header = reader.readLine();
            String line;

            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 4) {
                    String city = data[0].trim();
                    String office = data[1].trim();
                    String university = data[2].trim();
                    double distance = Double.parseDouble(data[3].trim());

                    Route route = new Route(city, office, university, distance);
                    routes.add(route);
                }
            }
        }

        catch(FileNotFoundException e) {
            System.out.println("Could not locate file.");
        }

        catch (NumberFormatException e) {
            System.out.println("Could not read number format.");
        }

        catch(IOException e) {
            System.out.println("Something went wrong.");
        }

        return routes;
    }
}
