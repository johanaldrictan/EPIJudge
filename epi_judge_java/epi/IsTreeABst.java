package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  private static boolean dataInRange(BinaryTreeNode<Integer> tree, Integer lower, Integer higher){
    if(tree == null)
      return true;
    else if(Integer.compare(lower, tree.data) > 0 || Integer.compare(higher, tree.data) < 0)
      return false;

    return dataInRange(tree.left, lower, tree.data) && dataInRange(tree.right, tree.data, higher);
  }

  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return dataInRange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
