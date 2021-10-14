package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  public static void bruteForce(int pivotIndex, List<Color> A){
    ArrayList<Color> less = new ArrayList<Color>();
    ArrayList<Color> equal = new ArrayList<Color>();
    ArrayList<Color> greater = new ArrayList<Color>();
    for(int i = 0; i < A.size(); i++){
      if(A.get(i).ordinal() < A.get(pivotIndex).ordinal()){
        less.add(A.get(i));
      }
      else if(A.get(i).ordinal() > A.get(pivotIndex).ordinal()){
        greater.add(A.get(i));
      }
      else{
        equal.add(A.get(i));
      }
    }
    A.clear();
    A.addAll(less);
    A.addAll(equal);
    A.addAll(greater);
  }
  public static void inPlace(int pivotIndex, List<Color> A){
    for(int i = 0; i < A.size(); i++) {
      for (int j = i + 1; j < A.size(); j++) {
        if (A.get(j).ordinal() < A.get(pivotIndex).ordinal()) {
          Collections.swap(A, i, j);
        }
      }
    }
    for(int i = A.size() - 1; i >= 0; i--) {
      for (int j = i - 1; j >= 0; j--) {
        if (A.get(j).ordinal() > A.get(pivotIndex).ordinal()) {
          Collections.swap(A, i, j);
        }
      }
    }
  }

  public static void inPlace2(int pivotIndex, List<Color> A){
    Color pivot = A.get(pivotIndex);
    int smaller = 0;
    for(int i = 0; i < A.size(); i++) {
      if (A.get(i).ordinal() < pivot.ordinal()) {
        Collections.swap(A, smaller++, i);
      }
    }
    int larger = A.size() - 1;
    for(int i = A.size() - 1; i >= 0; i--) {
      if (A.get(i).ordinal() > pivot.ordinal()) {
        Collections.swap(A, larger--, i);
      }
    }
  }

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    inPlace2(pivotIndex, A);
    return;
  }
  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
