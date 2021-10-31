package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IntersectSortedArrays {
  public static List<Integer> bSearchBruteForce(List<Integer> A,
                                         List<Integer> B){
    List<Integer> intersection = new ArrayList<>();
    for(int i = 0; i < A.size(); i++){
      if((i == 0 || !A.get(i).equals(A.get(i-1))) && Collections.binarySearch(B, A.get(i)) >= 0)
        intersection.add(A.get(i));
    }
    return intersection;
  }

  public static List<Integer> optimal(List<Integer> A,
                                                List<Integer> B){
    List<Integer> intersection = new ArrayList<>();
    int i = 0, j = 0;
    while(i < A.size() && j < B.size()){
      if( A.get(i).equals(B.get(j)) && (i == 0 || !A.get(i).equals(A.get(i-1)))) {
        intersection.add(A.get(i));
        i++; j++;
      }
      else if(A.get(i) < B.get(j))
        i++;
      else
        j++;
    }
    return intersection;
  }

  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    return optimal(A,B);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
