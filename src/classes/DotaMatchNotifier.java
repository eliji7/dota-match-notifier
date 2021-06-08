package classes;

public class DotaMatchNotifier {
	
	 public static void main(String[] args) throws Exception {
 
          while (true) {
        	  
               String currentTitle = ForegroundWinTool.getActiveWindowTitle();
               
               if (currentTitle.equals("Dota 2")) {
                    System.out.println("Match Found!");
                    //DO
                    break;
               }
               try {
                    Thread.sleep(1000); // checks every 1 seconds
               }
               catch (InterruptedException ex) {
                    // ignore
               }
          }
     }
}
