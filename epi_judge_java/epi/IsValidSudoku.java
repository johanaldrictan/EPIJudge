package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    //go through rows
    for(int row = 0; row < partialAssignment.size(); row++){
      if(checkDup(partialAssignment, row, row + 1, 0, partialAssignment.size()))
        return false;
    }

    //go through columns
    for(int col = 0; col < partialAssignment.size(); col++){
      if(checkDup(partialAssignment, 0, partialAssignment.size(), col, col + 1))
        return false;
    }
    //go through subarrays
    int subArraySize = (int)Math.sqrt(partialAssignment.size());
    for (int row = 0; row < subArraySize; ++row) {
      for (int col = 0; col < subArraySize; ++col) {
        if (checkDup(partialAssignment, subArraySize * row,
                subArraySize * (row + 1), subArraySize * col,
                subArraySize * (col + 1))) {
          return false;
        }
      }
    }
    return true;
  }

  public static boolean checkDup(List<List<Integer>> list, int rStart, int rEnd, int cStart, int cEnd){
    List<Boolean> encountered = new ArrayList<Boolean>(Collections.nCopies(list.size() + 1, false));
    for(int row = rStart; row < rEnd; row++){
      for(int col = cStart; col < cEnd; col++){
        if(list.get(row).get(col) != 0 && encountered.get(list.get(row).get(col)))
          return true;
        encountered.set(list.get(row).get(col), true);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
