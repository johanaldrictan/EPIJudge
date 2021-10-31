package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
public class NQueens {

  private static void solver(int n, int row, List<Integer> col, List<List<Integer>> result){
    if(row == n)
      result.add(new ArrayList<>(col));
    else{
      for(int c = 0; c < n; ++c){
        col.add(c);
        if(checker(col))
          solver(n, row + 1, col, result);
        col.remove(col.size() -1);
      }
    }
  }

  private static boolean checker(List<Integer> col){
    int row = col.size()-1;
    for(int r = 0; r < row; r++){
      int diff = Math.abs(col.get(r) - col.get(row));
      if(diff == 0 || diff == row - r)
        return false;
    }
    return true;
  }

  @EpiTest(testDataFile = "n_queens.tsv")

  public static List<List<Integer>> nQueens(int n) {
    List<List<Integer>> res = new ArrayList<>();
    solver(n, 0, new ArrayList<Integer>(), res);
    return res;
  }
  @EpiTestComparator
  public static boolean comp(List<List<Integer>> expected,
                             List<List<Integer>> result) {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NQueens.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
