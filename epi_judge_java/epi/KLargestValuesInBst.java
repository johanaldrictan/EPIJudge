package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class KLargestValuesInBst {

  public static void helper(BstNode<Integer> tree, int k, List<Integer> kGreatest){
    if(tree != null && kGreatest.size() < k){
      helper(tree.right, k, kGreatest);
      if(kGreatest.size() < k){
        kGreatest.add(tree.data);
        helper(tree.left, k, kGreatest);
      }
    }
  }
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    List<Integer> kGreatest = new ArrayList<>();
    helper(tree, k, kGreatest);
    return kGreatest;
  }
  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
