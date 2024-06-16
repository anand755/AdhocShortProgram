import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Write a function:
class Solution { public int solution (int[] A); }
that, given an array A of length N representing a record of prices over the last N days, returns the maximum income you
could make. As the result may be large, return its last nine digits without leading zeros
(return the result modulo 1,000,000,000).
Examples:
1. Given A = [4, 1, 2, 3], the function should return 6. You could sell the product on the first day (for 4),
   buy it on the second (for 1) and sell it again on the last day (for 3). The income would be equal 4-1+3 = 6.
2. Given A = [1, 2, 3, 3, 2, 1, 5], the function should return 7. You could sell the product when its value was 3,
   buy it when it changed to 1, and sell it again when it was worth 5.
3. Given A = [1000000000, 1, 2, 2, 1000000000, 1, 1000000000], the function should return 999999998. The maximum possible
   income is 2999999998, whose last 9 digits are 999999998.
Write an efficient algorithm for the following assumptions:
N is an integer within the range [1..200,000];
each element of array A is an integer within the range [0..1,000,000,000]. */
public class StockSellBuyDP {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());
        while (testCaseCount-- > 0) {
            int[] arrInput = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int maxProfit = getMaxProfit(arrInput);
            writer.write(maxProfit + "\n");
            writer.flush();
        }
        //writer.flush();
    }

    private static int getMaxProfit(int[] arrInput) {
        int length = arrInput.length;
        long[] dpSell = new long[length];
        long[] dpBuy = new long[length];
        long nab = 0, nas = 0;
        dpSell[0] = arrInput[0];
        dpBuy[0] = 0;
        int m = (int) 1E9;

        for (int i = 1; i < length; i++) {
            dpSell[i] = Math.max((dpBuy[i - 1] + arrInput[i]), (nab + arrInput[i]));
            dpBuy[i] = Math.max(dpSell[i - 1] - arrInput[i], nas - arrInput[i]);
            nas = Math.max(nas, dpSell[i - 1]);
            nab = Math.max(nab, dpBuy[i - 1]);
        }
        return (int) ((Math.max(dpSell[length - 1], nas)) % m);
    }
}
