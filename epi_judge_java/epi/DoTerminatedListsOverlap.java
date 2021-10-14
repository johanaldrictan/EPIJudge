package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;

public class DoTerminatedListsOverlap {

  public static ListNode<Integer> bruteForce(ListNode<Integer> l0, ListNode<Integer> l1) {
    HashSet<ListNode<Integer>> visited = new HashSet<>();
    ListNode<Integer> tempL0 = l0;
    ListNode<Integer> tempL1 = l1;

    while(tempL0 != null){
      visited.add(tempL0);
      tempL0 = tempL0.next;
    }
    while(tempL1 != null){
      if(!visited.add(tempL1)){
        return tempL1;
      }
      tempL1 = tempL1.next;
    }
    return null;
  }
  public static ListNode<Integer> optimal(ListNode<Integer> l0, ListNode<Integer> l1) {
    ListNode<Integer> tempL0 = l0;
    ListNode<Integer> tempL1 = l1;

    int lengthL0 = length(l0), lengthL1 = length(l1);
    if(lengthL0 > lengthL1){
      int diff = lengthL0 - lengthL1;
      while(diff-- != 0){
        tempL0 = tempL0.next;
      }
    }
    else{
      int diff = lengthL1 - lengthL0;
      while(diff-- != 0){
        tempL1 = tempL1.next;
      }
    }
    while(tempL0 != null && tempL1 != null){
      if(tempL0 == tempL1)
        return tempL0;
      tempL0 = tempL0.next;
      tempL1 = tempL1.next;
    }
    return null;
  }

  private static int length(ListNode<Integer> list){
    ListNode<Integer> temp = list;
    int counter = 0;
    while(temp != null){
      counter++;
      temp = temp.next;
    }
    return counter;
  }
  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    return optimal(l0,l1);
  }
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
