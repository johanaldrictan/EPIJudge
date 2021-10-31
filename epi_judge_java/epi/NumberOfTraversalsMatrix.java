package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class NumberOfTraversalsMatrix {
  @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")

  public static int numberOfWays(int n, int m) {
    int[][] table = new int[n][m];
    for (int i = 0; i < n; i++){
      for(int j = 0; j < m; j++){
        if(i == 0)
          table[i][j] = 1;
        else if(j == 0)
          table[i][j] = 1;
        else{
          table[i][j] = table[i][j-1] + table[i-1][j];
        }
      }
    }
    return table[n-1][m-1];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
