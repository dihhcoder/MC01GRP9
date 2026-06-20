import java.util.ArrayList;

public class Sorter {
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
