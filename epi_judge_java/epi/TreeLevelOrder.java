package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> depthOrder = new ArrayList<>();
    if(tree == null)
      return depthOrder;
    List<BinaryTreeNode<Integer>> currentDepth = List.of(tree);
    while (!currentDepth.isEmpty()){
      depthOrder.add(currentDepth.stream().map(curr -> curr.data).collect(Collectors.toList()));
      currentDepth = currentDepth.stream().map(curr -> Arrays.asList(curr.left, curr.right))
                                                             .flatMap(List::stream)
                                                             .filter(next -> next != null)
                                                             .collect(Collectors.toList());
    }

    return depthOrder;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
