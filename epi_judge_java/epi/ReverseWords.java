package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {

  public static void reverseWords(char[] input) {
    //reverse everything
    reverse(input, 0, input.length - 1);
    int wordStart = 0;
    int wordEnd = 0;
    //reverse each word
    while(wordStart < input.length){
      while(wordStart < wordEnd || wordStart < input.length && input[wordStart] == ' ')
        wordStart++;
      while(wordEnd < wordStart || wordEnd < input.length && input[wordEnd] != ' ')
        wordEnd++;
      reverse(input, wordStart, wordEnd - 1);
    }
    return;
  }
  public static void reverse(char[] arr, int start, int end){
    while(start < end){
      char tmp = arr[start];
      arr[start++] = arr[end];
      arr[end--] = tmp;
    }
  }
  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
