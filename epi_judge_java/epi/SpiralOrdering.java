package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    final int[][] directionOffset = {{0,1}, {1,0}, {0,-1}, {-1,0}}; //moving to the right hence 0,1 first
    int direction = 0;
    int row = 0;
    int col = 0;
    List<Integer> ordering = new ArrayList<>();
    double numElems = Math.pow(squareMatrix.size(), 2);
    for(int i = 0; i < numElems; i++){
      ordering.add(squareMatrix.get(row).get(col));
      squareMatrix.get(row).set(col,0); //encountered so set to 0 to aid wrap around step
      int nextX = directionOffset[direction][0] + row;
      int nextY = directionOffset[direction][1] + col;
      if(nextX < 0 || nextX >= squareMatrix.size() ||
        nextY < 0 || nextY >= squareMatrix.size() ||
        squareMatrix.get(nextX).get(nextY) == 0){
        direction = (direction + 1) % 4;
        //recalc nexts
        nextX = directionOffset[direction][0] + row;
        nextY = directionOffset[direction][1] + col;
      }
      row = nextX;
      col = nextY;
    }
    return ordering;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
