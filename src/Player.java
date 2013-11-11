import java.util.Scanner;

class Player {
   static int[][] tab = new int[30][20];
   static {
      for (int i = 0; i < 30; i++)
         for (int j = 0; j < 20; j++)
            tab[i][j] = -1;
   }
   static int     mx;                   // My x
   static int     my;                   // My y

   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      while (true) {
         String res = step(in);
         System.out.println(res);

      }
   }

   protected static String step(Scanner in) {
      int nb = in.nextInt();
      int p = in.nextInt();

      // Compute logic here
      for (int i = 0; i < nb; i++) {
         // Ignore
         in.nextInt();
         in.nextInt();

         int x = in.nextInt();
         int y = in.nextInt();

         // Update wall and my position
         if (x != -1 && y != -1) tab[x][y] = i;
         if (i == p) {
            mx = x;
            my = y;
         }
      }

      String res = "UP";
      // Write action to standard output
      if (mx < 29 && tab[mx + 1][my] == -1) {
         res = "RIGHT";
      }
      else if (my < 19 && tab[mx][my + 1] == -1) {
         res = "DOWN";
      }
      else if (mx > 0 && tab[mx - 1][my] == -1) {
         res = "LEFT";
      }
      return res;
   }
}
