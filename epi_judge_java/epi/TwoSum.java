package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
  @EpiTest(testDataFile = "two_sum.tsv")

  public static boolean hasTwoSum(List<Integer> A, int t) {
    int i = 0, j = A.size() - 1;
    while(i <= j){
      if(A.get(i) + A.get(j) == t)
        return true;
      else if(A.get(i) + A.get(j) < t)
        i++;
      else
        j--;
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
