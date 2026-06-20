import java.util.ArrayList;

public class Sorter {
    public void cocktailSort(ArrayList<Map> m) {
        boolean swapped = true;
        int start = 0;
        int end = m.size() - 1;
        int i;

        while(swapped) {
            swapped = false;

            for(i = start; i < end; i++) {
                if (m.get(i).getDistance() > m.get(i + 1).getDistance()) {
                    Map temp = m.get(i);
                    m.set(i, m.get(i + 1));
                    m.set(i + 1, temp);
                    swapped = true;
                }
            }

            if(swapped) {
                swapped = false;
                end--;

                for(i = end - 1; i >= start; i--) {
                    if(m.get(i).getDistance() > m.get(i + 1).getDistance()) {
                        Map temp = m.get(i);
                        m.set(i, m.get(i + 1));
                        m.set(i + 1, temp);
                        swapped = true;
                    }
                }
                start++;
            }
        }
    }
}
