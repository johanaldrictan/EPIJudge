package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

  public static boolean isPalindrome(String s) {
    int front = 0;
    int back = s.length() - 1;
    while(front < back){
      while(front < back && !Character.isLetterOrDigit(s.charAt(front)))
        front++;
      while(front < back && !Character.isLetterOrDigit(s.charAt(back)))
        back--;
      if(Character.toLowerCase(s.charAt(front++)) != Character.toLowerCase(s.charAt(back--)))
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
