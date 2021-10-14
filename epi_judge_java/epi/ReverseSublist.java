package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    ListNode<Integer> temp = new ListNode<>(0, L);
    ListNode<Integer> subList = temp;
    int counter = 1;
    while(counter++ < start){
      subList = subList.next;
    }
    ListNode<Integer> iterator = subList.next;
    while(start++ < finish){
      ListNode<Integer> swap = iterator.next;
      iterator.next = swap.next;
      swap.next = subList.next;
      subList.next = swap;
    }
    return temp.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
