package ds.Array;

import java.util.Arrays;
import java.util.List;

public class ValidSubsequence {
    /**
     * O(n)
     * O(1)
     * @param array
     * @param sequence
     * @return
     */
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        int n = sequence.size(), m = array.size();
        int seqIndex = 0;
        for(int i=0;i<m;i++) {

            if (array.get(i).equals(sequence.get(seqIndex))) {
                seqIndex++;
            }
            // subsequence match found
            if (seqIndex == n) {
                break;
            }
        }
        if (seqIndex == n) {
            return true;
        }
        return false;
    }
    public static void main(String args[]) {
        List<Integer> array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
        List<Integer> sequence = Arrays.asList(1, 6, -1, 10);

        System.out.println(ValidSubsequence.isValidSubsequence(array, sequence));
    }
}
