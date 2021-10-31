package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.Queue;

public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {
    int aBack = m - 1, bBack = n - 1, writeIdx = m + n - 1;
    while(aBack >= 0 && bBack >= 0){
      A.set(writeIdx--, A.get(aBack) > B.get(bBack) ? A.get(aBack--) : B.get(bBack--));
    }
    while(bBack >= 0){
      A.set(writeIdx--, B.get(bBack--));
    }
    return;
  }
  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
