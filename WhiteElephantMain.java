import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by lpint on 11/24/14.
 *
 * For both implementations, duplicates are not supported/handled.
 *
 */
public class WhiteElephantMain
{
   public static void main(String[] args)
   {
      final String[] participants = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "ab", "ac", "ad", "af", "ag", "ah", "ai", "aj", "ak", "al", "am", "an", "ap", "aq", "ar", "as", "at", "au", "av", "aw", "ax", "ay", "az"};

      final String[] assignments = generateAssignments(participants);
      System.out.println("Participants: \t" + Arrays.toString(participants));
      System.out.println("Assignments: \t" + Arrays.toString(assignments));

      final String[] assignments2 = generateAssignments2(participants);
      System.out.println("Participants: \t" + Arrays.toString(participants));
      System.out.println("Assignments: \t" + Arrays.toString(assignments2));

   }

   // This is the most efficient and is O(N)
   public static final String[] generateAssignments(String[] participants)
   {

      // White elephants with < 3 people would be silly
      if (participants.length <= 2)
      {
         System.out.println("Less then 3 people for a white elephant exchange is strange..");
      }

      // Make a copy instead of manipulating the original array passed in.
      String[] assignments = new String[participants.length];
      System.arraycopy( participants, 0, assignments, 0, participants.length );

      Random rnd = new Random();

      final int len = participants.length;
      int i = len-1 ;
      while (i > 0)
      {
         int next = rnd.nextInt(i);

         String temp = assignments[next];
         assignments[next] = assignments[i];
         assignments[i] = temp;

         i--;
      }

      return assignments;
   }

   // This is the far less elegant way I chose to do for fun to see if I could do a completely different version
   // It performs much slower but still works.
   public static final String[] generateAssignments2(String[] participants)
   {
      final int len = participants.length;

      // White elephants with less then 3 people would be silly
      if (len < 3)
      {
         System.out.println("Less then 3 people for a white elephant exchange is strange..");
         if (len <= 1)
            return participants.clone();
      }

      String[] assignments = new String[participants.length];

      // Record matches as they happen
      HashSet<String> matches = new HashSet<>();

      Random rnd = new Random();

      int i = len-1 ;
      while ( i >= 0)
      {
         int next = rnd.nextInt(len);

         // Skip if the new index would be in the same location or if the item has already been added indicating a miss
         if (next != i && !matches.contains(participants[next]))
         {
            matches.add(participants[next]);
            assignments[i] = participants[next];
            i--;
         }
         // Edge Case - everything has been added except for participant[0] into assignment[0] which fails rule #1 of the project
         else if (next == 1 && i == 0)
         {
            while (next <= 1)
            {
               next = rnd.nextInt(len);
            }
            int old=-1;
            for (int j = 0; j < len; j++)
            {
               if (assignments[j] != null && assignments[j].equals(participants[0]))
               {
                  old = j;
                  break;
               }
            }
            // participant 0 has not been inserted in yet.
            if (old == -1)
            {
               // Swap out the random value we want
               String temp = participants[next];
               for (int k = 0; k < len; k++)
               {
                  if (assignments[k] != null && assignments[k].equals(temp))
                  {
                     assignments[0] = temp;
                     assignments[k] = participants[0];
                     i--;
                  }
               }
            }
            else
            {
               // check for the 1 item that hasn't been added and put it in location 0
               for (int k = 0; k < len; k++){
                  if (matches.add(participants[k])){
                     assignments[0] = participants[k];
                     i--;
                  }
               }
            }
         }
      }
      return assignments;
   }
}
