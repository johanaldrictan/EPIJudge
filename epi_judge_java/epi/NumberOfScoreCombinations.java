package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class NumberOfScoreCombinations {
  @EpiTest(testDataFile = "number_of_score_combinations.tsv")

  public static int
  numCombinationsForFinalScore(int finalScore,
                               List<Integer> individualPlayScores) {
    //sort individual play scores, start with smallest
    Collections.sort(individualPlayScores);
    int[] numWays = new int[finalScore+1];
    numWays[0] = 1;
    for(int i = 0; i < individualPlayScores.size(); i++){
      for(int j = individualPlayScores.get(i); j <= finalScore; j++){
        numWays[j] += numWays[j-individualPlayScores.get(i)];
      }
    }
    return numWays[finalScore];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
