package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;
public class Knapsack {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class Item {
    public Integer weight;
    public Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }

  @EpiTest(testDataFile = "knapsack.tsv")

  public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
    int[][] table = new int[items.size() + 1][capacity+1];
    for(int i = 0; i <= items.size(); i++){
      for(int j = 0; j <= capacity; j++){
        if(i == 0 || j == 0)
          table[i][j] = 0;
        else if(items.get(i - 1).weight <= j)
          table[i][j] = Math.max(items.get(i-1).value + table[i - 1][j - items.get(i-1).weight], table[i - 1][j]);
        else
          table[i][j] = table[i - 1][j];
      }
    }
    return table[items.size()][capacity];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Knapsack.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
