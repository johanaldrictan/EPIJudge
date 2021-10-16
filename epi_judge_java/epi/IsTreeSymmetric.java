package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  public static boolean checkSymmetry (BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right){
    if(left == null && right == null)
      return true;
    else if(left != null && right != null){
      return left.data == right.data && checkSymmetry(left.left, right.right) && checkSymmetry(right.left, left.right);
    }
    return false;
  }


  @EpiTest(testDataFile = "is_tree_symmetric.tsv")
  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    return tree == null || checkSymmetry(tree.left, tree.right);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
