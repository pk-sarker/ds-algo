package ds.Array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class QueryKthSmallestTrimmedNumber {
    public static int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int[] answer = new int[queries.length];
        int nl = nums[0].length();
        int k = 0;

        for(int i=0;i<queries.length;i++) {
            String[][] b = new String[nums.length][2];
            for (int j=0;j<nums.length;j++) {
                String num = nums[j];
                String trimmed = num.substring(num.length()-queries[i][1], num.length());
                b[j][0] = trimmed;
                b[j][1] = Integer.toString(j);
            }
            Arrays.sort(b,(x, y) -> x[0].compareTo(y[0])); // Sort the array by its value
            answer[k++] = Integer.parseInt(b[queries[i][0]-1][1]); // store the kth smallest element index in ans
        }
        return answer;
    }

    public static void main(String args[]) {
        QueryKthSmallestTrimmedNumber obj = new QueryKthSmallestTrimmedNumber();
        System.out.println("Input: nums = [\"102\",\"473\",\"251\",\"814\"], queries = [[1,1],[2,3],[4,2],[1,2]]" +
                "\nOutput: " + Arrays.toString(QueryKthSmallestTrimmedNumber.smallestTrimmedNumbers(new String[]{"102","473","251","814"}, new int[][]{{1,1},{2,3},{4,2},{1,2}})));
    }
}
