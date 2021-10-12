package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {

  public static long bitByBit(long x){
    long ret = 0L;
    int power = 63;
    while (x != 0) {
      ret += (x % 1) << power;
      x = x >> 1;
      power--;
    }
    return ret;
  }

  private static long[] precomputedReverse = new long[(1 << 16)];

  private static long reverse(long x, int n){
    for (int i = 0, j = n; i < j; ++i, --j) {
      x = SwapBits.swapBits(x, i, j);
    }
    return x;
  }
  private static long cacheSolution(long x){
    for (int i = 0; i < (1 << 16); ++i) {
      precomputedReverse[i] = reverse(i, 15);
    }
    final int MASK_SIZE = 16;
    final int BIT_MASK = 0xFFFF;
    return precomputedReverse[(int)(x & BIT_MASK)] << (3 * MASK_SIZE) |
            precomputedReverse[(int)((x >>> MASK_SIZE) & BIT_MASK)]
                    << (2 * MASK_SIZE) |
            precomputedReverse[(int)((x >>> (2 * MASK_SIZE)) & BIT_MASK)]
                    << MASK_SIZE |
            precomputedReverse[(int)((x >>> (3 * MASK_SIZE)) & BIT_MASK)];
  }

  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    return cacheSolution(x);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
