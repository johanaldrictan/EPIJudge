package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    ListNode<Integer> temp = new ListNode<>(0, L);
    ListNode<Integer> forward = temp.next;
    ListNode<Integer> back = temp;
    while(k-- > 0 && forward != null){ //move forward iterator to k steps ahead
      forward = forward.next;
    }

    while (forward != null){ //move together till forward hits null, forward is guaranteed to hit null before back
      forward = forward.next;
      back = back.next;
    }

    //back should point to right before the kth elem
    back.next = back.next.next;

    return temp.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
