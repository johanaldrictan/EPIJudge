package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;

public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> bruteForce(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    HashSet<BinaryTree<Integer>> visited = new HashSet<>();
    BinaryTree<Integer> temp0 = node0;
    while(temp0 != null){
      visited.add(temp0);
      temp0 = temp0.parent;
    }
    BinaryTree<Integer> temp1 = node1;
    while(temp1 != null){
      if(!visited.add(temp1))
        return temp1;
      temp1 = temp1.parent;
    }
    return null;
  }

  private static int getDepth(BinaryTree<Integer> tree){
    int depth = 0;
    BinaryTree<Integer> temp0 = tree;
    while(temp0 != null){
      depth++;
      temp0 = temp0.parent;
    }
    return depth;
  }

  private static BinaryTree<Integer> goUpKTimes(int k, BinaryTree<Integer> tree){
    BinaryTree<Integer> temp0 = tree;
    while(k-- > 0 && temp0 != null){
      temp0 = temp0.parent;
    }
    return temp0;
  }

  public static BinaryTree<Integer> optimal(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    int depth0 = getDepth(node0);
    int depth1 = getDepth(node1);

    if(depth0 > depth1){
      node0 = goUpKTimes(depth0 - depth1, node0);
    }
    else if(depth1 > depth0){
      node1 = goUpKTimes(depth1 - depth0, node1);
    }
    while(node0 != null && node1 != null){
      if(node0 == node1)
        return node0;
      node0 = node0.parent;
      node1 = node1.parent;
    }
    return null;
  }

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    return optimal(node0, node1);
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
