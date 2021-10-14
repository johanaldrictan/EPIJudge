package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> bruteForce(List<Integer> A){
    int carryOver = 0;
    ArrayList<Integer> integer = new ArrayList<>();
    A.set(A.size() - 1, A.get(A.size() - 1) + 1);
    for(int i = A.size() - 1; i >= 0; i--){
      int result = A.get(i) + carryOver;
      if(carryOver >= 1)
        carryOver = 0; //carryOver was used
      integer.add(0, result % 10);
      carryOver += result / 10;
    }
    if(carryOver >= 1)
      integer.add(0, carryOver);
    return integer;
  }


  public static List<Integer> plusOne(List<Integer> A) {
    return bruteForce(A);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
