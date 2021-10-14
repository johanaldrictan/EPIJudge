package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    ListNode<Integer> list = new ListNode<>(0,null);
    ListNode<Integer> temp = list;
    ListNode<Integer> tempL1 = L1;
    ListNode<Integer> tempL2 = L2;

    while(tempL1 != null && tempL2 != null){
      if(tempL1.data <= tempL2.data) {
        temp.next = tempL1;
        tempL1 = tempL1.next;
      }
      else{
        temp.next = tempL2;
        tempL2 = tempL2.next;
      }
      temp = temp.next;
    }
    temp.next = tempL1 == null ? tempL2 : tempL1;
    return list.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
