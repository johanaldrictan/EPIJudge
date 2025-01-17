package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    List<Integer> sorted = new ArrayList<>();
    for (int i = 0; i < k && sequence.hasNext(); i++){
      minHeap.add(sequence.next());
    }
    while(sequence.hasNext()){
      minHeap.add(sequence.next());
      sorted.add(minHeap.remove());
    }
    sorted.addAll(Stream.generate(minHeap::remove).limit(minHeap.size()).collect(Collectors.toList()));
    return sorted;
  }
  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
