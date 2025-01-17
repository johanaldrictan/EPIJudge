package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class ReplaceAndRemove {

  public static int replaceAndRemove(int size, char[] s) {
    int write = 0;
    int numA = 0;
    //remove b's and count a's
    for(int i = 0; i < size; i++){
      if(s[i] != 'b'){
        s[write++] = s[i];
      }
      if(s[i] == 'a')
        numA++;
    }

    //go backwards and add the d's
    int current = write - 1;
    write += (numA -1);
    int finalSize = write + 1;
    while(current >= 0){
      if(s[current] == 'a'){
        s[write--] = 'd';
        s[write--] = 'd';
      }
      else{
        s[write--] = s[current];
      }
      current--;
    }
    return finalSize;
  }
  @EpiTest(testDataFile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
      throws Exception {
    char[] sCopy = new char[s.size()];
    for (int i = 0; i < size; ++i) {
      if (!s.get(i).isEmpty()) {
        sCopy[i] = s.get(i).charAt(0);
      }
    }

    Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

    List<String> result = new ArrayList<>();
    for (int i = 0; i < resSize; ++i) {
      result.add(Character.toString(sCopy[i]));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
