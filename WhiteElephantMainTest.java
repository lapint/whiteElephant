import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashSet;

public class WhiteElephantMainTest extends TestCase
{

   WhiteElephantMain test;
   final String[] participants = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "ab", "ac", "ad", "af", "ag", "ah", "ai", "aj", "ak", "al", "am", "an", "ap", "aq", "ar", "as", "at", "au", "av", "aw", "ax", "ay", "az"};

   @Test
   public void testGenerateAssignments()
   {
      // Do 10000 iterations to validate randomness in results and hit more edge cases;
      for (int i = 0; i < 100000; i++)
      {
         final String[] assignments = test.generateAssignments(participants);

         validateAssignments(assignments, participants);
      }
   }

   @Test
   public void testGenerateAssignments2()
   {
      // Do 10000 iterations to validate randomness in results and hit more edge cases;
      for (int i = 0; i < 100000; i++)
      {
         final String[] assignments = test.generateAssignments2(participants);

         validateAssignments(assignments, participants);
      }
   }

   public void validateAssignments(String[] assignments, String[] participants)
   {
      assertEquals(assignments.length, participants.length);

      HashSet<String> matches = new HashSet<>();

      for (int j = 0; j < assignments.length; j++)
      {
         // Check if there are duplicates which would indicate a failure.
         assertTrue(matches.add(assignments[j]));

         // Ensure no one is responsible for their own gift
         assertFalse(assignments[j].equals(participants[j]));
      }
   }
}
