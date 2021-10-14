package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    boolean neg = numAsString.startsWith("-");
    int num = numAsString.substring(neg ? 1 : 0)
                         .chars()
                         .reduce(0,
                                 (runningSum, c) -> runningSum * b1 + (Character.isDigit(c) ? c - '0' : c - 'A' + 10));
    if(num == 0)
      return (neg ? "-": "") + "0";
    return (neg ? "-": "") + constructFromBase(num, b2);
  }
  private static String constructFromBase(int numAsInt, int base) {
    return numAsInt == 0
            ? ""
            : constructFromBase(numAsInt / base, base) +
            (char)(numAsInt % base >= 10 ? 'A' + numAsInt % base - 10
                    : '0' + numAsInt % base);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
