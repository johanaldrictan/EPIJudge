package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    StringBuilder s = new StringBuilder();
    boolean neg = x < 0;
    do{
      int value = Math.abs(x % 10);
      //convert value and add
      s.append((char) ('0' + value));
      //shift
      x = x / 10;
    }while(x != 0);
    return s.append(neg ? '-' : "").reverse().toString();
  }
  public static int stringToInt(String s) {
    long x = 0;
    int end = 0;
    int power = 0;
    if(s.charAt(0) == '-' || s.charAt(0) == '+')
      end=1;
    for(int i = s.length() - 1; i >= end; i--){
      x += Math.pow(10, power++) * (s.charAt(i) - '0');
    }
    if(end == 1 && s.charAt(0) == '-')
      x *= -1;
    return (int)x;
  }

  public static int wtf(String s) {

    return (s.charAt(0) == '-' ? -1 : 1) *
            s.substring((s.charAt(0) == '-' || s.charAt(0) == '+') ? 1 : 0)
                    .chars()
                    .reduce(0, (runningSum, c) -> runningSum * 10 + c - '0');
  }


  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
