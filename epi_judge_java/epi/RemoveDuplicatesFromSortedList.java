package epi;
import datastructs.linkedlist.SingleNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    ListNode<Integer> temp = L;
    while(temp != null){
      ListNode<Integer> nextUnique = temp.next;
      while (nextUnique != null && nextUnique.data == temp.data) {
        nextUnique = nextUnique.next;
      }
      temp.next = nextUnique;
      temp = nextUnique;
    }
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
