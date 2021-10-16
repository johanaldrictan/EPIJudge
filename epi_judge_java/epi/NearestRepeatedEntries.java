package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {
    Map<String, Integer> wordsToIndex = new HashMap<>();
    int nearest = Integer.MAX_VALUE;
    for (int i = 0; i < paragraph.size(); i++) {
     if(wordsToIndex.containsKey(paragraph.get(i)))
       nearest = Math.min(nearest, i - wordsToIndex.get(paragraph.get(i)));
     wordsToIndex.put(paragraph.get(i), i);
    }
    return nearest != Integer.MAX_VALUE ? nearest : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
