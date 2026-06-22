import java.util.ArrayList;

public class Display {
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

  public static void showStorage(ArrayList<Map> routes) {
    System.out.println();
    System.out.println("========== MAIL STORAGE ==========");

     for(int i = routes.size() - 1; i >= 0; i--) {
        System.out.println("+----------------------+");
        System.out.printf("| %-20s |\n", shorten(routes.get(i).getUniversity()));
      }
        
        if(routes.isEmpty())
			      System.out.println("[ Mail Storage Empty ]");	
        else 
            System.out.println("+----------------------+");
    
        System.out.println();
    }
    
    public static void showRoute(ArrayList<Map> routes, String currCity) {
		System.out.println();
		System.out.println("========== DELIVERY ROUTE ==========");

		System.out.print("Post Office");

		for(int i = 0; i < routes.size(); i++) {
			if(routes.get(i).getCity().equals(currCity))
				System.out.print(" ---> " + shorten(routes.get(i).getUniversity()));
		}

		System.out.println();
		System.out.println();
	}
	
	public static void animateDelivery(String destination) {

		int length = 20;

		for(int i = 0; i <= length; i++) {
			StringBuilder line = new StringBuilder();

			for(int j = 0; j < i; j++) {
				line.append("-");
			}

			line.append("[]");

			for(int j = i; j < length; j++) {
				line.append("-");
			}

			line.append("> ");
			line.append(shorten(destination));
			System.out.print("\r" + line);

			try {
				Thread.sleep(200);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("     Delivered");
	}
    
    private static String shorten(String text) {
		int maxLength = 20;
		if(text.length() <= maxLength)
			return text;

		return text.substring(0, maxLength - 3) + "...";
	}	

	public static void exitMessage() {
		System.out.println();
		System.out.println("+----------------------------------------+");
		System.out.println("| Work hard, Mailman! See you next time! |");
		System.out.println("+----------------------------------------+");
	}
}
