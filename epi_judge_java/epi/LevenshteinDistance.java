package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class LevenshteinDistance {
  private static int getMin(int a, int b, int c){
    return Math.min(Integer.MAX_VALUE, Math.min(a, Math.min(b, c)));
  }

  @EpiTest(testDataFile = "levenshtein_distance.tsv")

  public static int levenshteinDistance(String A, String B) {
    int[][] table = new int[A.length() + 1][B.length() + 1];
    for(int i = 0; i <= A.length(); i++){
      for(int j = 0; j <= B.length(); j++){
        if(i == 0)
          table[i][j] = j;
        else if(j == 0)
          table[i][j] = i;
        else{
          table[i][j] = getMin(table[i-1][j-1] + (A.charAt(i-1) ==  B.charAt(j-1) ? 0 : 1), table[i-1][j] + 1, table[i][j-1] + 1);
        }
      }
    }
    return table[A.length()][B.length()];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
