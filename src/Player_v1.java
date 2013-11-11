import java.util.Scanner;

/**
 * Seconde heuristique : va vers le plus proche adversaire.
 * 
 * Ne semble pas vraiment mieux fonctionner.
 */
class Player_v1 {
   static int[][]   tab      = new int[32][22];
   static {
      for (int i = 0; i < 32; i++) {
         tab[i][0] = 9;
         tab[i][21] = 9;
      }
      for (int i = 0; i < 22; i++) {
         tab[0][i] = 9;
         tab[31][i] = 9;
      }
      for (int i = 1; i < 31; i++)
         for (int j = 1; j < 21; j++)
            tab[i][j] = -1;
   }
   static int       me;
   static int       mx;                        // My x
   static int       my;                        // My y
   static int[][]   ppos     = new int[4][2];  // Player position
   static boolean[] pdead    = new boolean[4]; // True is player i is dead
   static int       nbplayer = -1;

   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      while (true) {
         String res = step(in);
         System.out.println(res);

      }
   }

   protected static String step(Scanner in) {
      int nb = in.nextInt();
      if (nbplayer == -1) nbplayer = nb;
      me = in.nextInt();

      // Compute logic here
      for (int i = 0; i < nb; i++) {
         // Ignore
         in.nextInt();
         in.nextInt();

         int x = in.nextInt();
         int y = in.nextInt();

         if (x == -1 && !pdead[i]) {
            killPlayer(i);
         }
         else {
            // Update wall and my position
            tab[x + 1][y + 1] = i;
            ppos[i][0] = x + 1;
            ppos[i][1] = y + 1;
         }
         mx = ppos[me][0];
         my = ppos[me][1];
      }

      // chercher closest player
      int closest = -1;
      int dist = Integer.MAX_VALUE;
      for (int i = 0; i < nb; i++) {
         if (i == me) continue;
         int dx = ppos[i][0] - mx;
         if (dx < 0) dx = -dx;
         int dy = ppos[i][1] - my;
         if (dy < 0) dy = -dy;
         if (dx + dy < dist) {
            dist = dx + dy;
            closest = i;
         }
      }

      // Go toward the closest.
      int dx = ppos[closest][0] - mx;
      if (dx < 0) dx = -dx;
      int dy = ppos[closest][1] - my;
      if (dy < 0) dy = -dy;

      if (dx > dy) {
         if (ppos[closest][0] - mx > 0 && tab[mx + 1][my] < 0) return "RIGHT";
         else if (ppos[closest][0] - mx < 0 && tab[mx - 1][my] < 0) return "LEFT";
      }
      else {
         if (ppos[closest][1] - my > 0 && tab[mx][my + 1] < 0) return "DOWN";
         else if (ppos[closest][1] - my < 0 && tab[mx][my - 1] < 0) return "UP";
      }
      String res = "UP";
      // Write action to standard output
      if (tab[mx + 1][my] < 0) {
         res = "RIGHT";
      }
      else if (tab[mx][my + 1] < 0) {
         res = "DOWN";
      }
      else if (tab[mx - 1][my] < 0) {
         res = "LEFT";
      }
      return res;
   }

   private static void killPlayer(int p) {
      pdead[p] = true;
      for (int i = 1; i < 31; i++)
         for (int j = 1; j < 21; j++)
            if (tab[i][j] == p) tab[i][j] = -1;

   }

}
