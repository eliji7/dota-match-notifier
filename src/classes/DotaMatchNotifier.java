package classes;

public class DotaMatchNotifier {
	
	 public static void main(String[] args) throws Exception {
 
          while (true) {
        	  
               String currentTitle = ForegroundWinTool.getActiveWindowTitle();
               if (currentTitle.equals("Dota 2")) {
                    System.out.println("Match Found!");
                    DiscordPoster.postMessage();
                    break;
               }
               try {
                    Thread.sleep(500); // checks every 0.5 seconds
               }
               catch (InterruptedException ex) {
                    // ignore
               }
          }
     }
}
