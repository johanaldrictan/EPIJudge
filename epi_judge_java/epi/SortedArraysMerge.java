package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {

  private static class Pair<T1, T2>{
    public T1 key;
    public T2 value;
    public Pair(T1 i1, T2 i2){
      key = i1;
      value = i2;
    }
  }

  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Iterator<Integer>> iterators = new ArrayList<>(sortedArrays.size());
    for (List<Integer> arr : sortedArrays) {
      iterators.add(arr.iterator());
    }
    PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>(sortedArrays.size(), (n1, n2) -> Integer.compare(n1.value,n2.value));
    for(int i = 0; i < iterators.size(); i++){
      if(iterators.get(i).hasNext()){
        minHeap.add(new Pair<>(i, iterators.get(i).next()));
      }
    }
    List<Integer> merged = new ArrayList<>();
    while(!minHeap.isEmpty()){
      Pair<Integer, Integer> min = minHeap.poll();
      merged.add(min.value);
      if(iterators.get(min.key).hasNext()){
        minHeap.add(new Pair<>(min.key, iterators.get(min.key).next()));
      }
    }

    return merged;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
