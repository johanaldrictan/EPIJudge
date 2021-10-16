package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  private static class Subtree{
    public boolean isBalanced;
    public int height;

    public Subtree(boolean balanced, int h){
      isBalanced = balanced;
      height = h;
    }
  }

  public static Subtree checkBalance(BinaryTreeNode<Integer> tree){
    if(tree == null)
      return new Subtree(true, -1);
    Subtree left = checkBalance(tree.left);
    Subtree right = checkBalance(tree.right);
    if(!left.isBalanced)
      return left;
    if(!right.isBalanced)
      return right;

    boolean balanced = Math.abs(left.height - right.height) <= 1;
    int height = Math.max(left.height, right.height) + 1;
    return new Subtree(balanced, height);
  }

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return checkBalance(tree).isBalanced;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
