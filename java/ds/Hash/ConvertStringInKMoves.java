package ds.Hash;

/**
 * Given two strings s and t, your goal is to convert s into t in k moves or less.
 *
 * During the ith (1 <= i <= k) move you can:
 *
 * - Choose any index j (1-indexed) from s, such that 1 <= j <= s.length and j has not been chosen in any previous move,
 *   and shift the character at that index i times.
 * - Do nothing.
 *
 * Shifting a character means replacing it by the next letter in the alphabet (wrapping around so that 'z' becomes 'a').
 * Shifting a character by i means applying the shift operations i times.
 *
 * Remember that any index j can be picked at most once.
 *
 * Return true if it's possible to convert s into t in no more than k moves, otherwise return false.
 */
public class ConvertStringInKMoves {
    /*
     * From the condition of moving, we can move i-times on i-th move.
        - that means if t[j]-s[j] = x, then the value of movement i=x
            It means we need to shift x times to change s[j] to t[j]
        - Then then, the next move can't be equal or less than the last move,
            - next move will be > last move
        So movement is idealy number of shifts we need to do.
        An important note, shifts are in circular, means 'a' can be
        shifted 26 times to the next 'a', 27 times to 'b'
        So if the difference between t_j-s_j <0 that means we need to wrap shifts
        or t_j-s_j < last move we need to wrap
        for example: s="abcdf" t="bbdeh"
        j=0, (a,b): shift = 1, move=1,
        j=1, (b,b): shift = 0 + 0*26 = 0
        j=2, (c,d): shift = 1 + 1*26 = 27, move = 27
        j=3, (d,e): shift = 1 + 2*26  = 53, move = 53
        j=4, (f,h): shift = 2 + 3*26  = 80, move = 80
     */
    public static boolean canConvertString(String s, String t, int k) {
        if (s.length()!=t.length()){
            return false;
        }
        int[] multiplier = new int[26];

        for(int i = 0; i < s.length(); i++) {
            int sc = (int) s.charAt(i);
            int tc = (int) t.charAt(i);
            int shift = tc - sc + (tc < sc ? 26:0);

            // multiplier[shift] = how many times a character in t has be converted to s by shift shifts.
            // like s="abcd" t="bddg"
            // (a,b), shift = 1, multiplier[1] = 1
            // (b,d), shift = 2, multiplier[2] = 1
            // (c,d), shift = 1, multiplier[1] = (1+1) = 2
            // (d,g), shift = 3, multiplier[3] = 1

            if (shift != 0 && shift + multiplier[shift] * 26 > k) {
                return false;
            }
            ++multiplier[shift];
        }
        return true;
    }
    public static void main(String args[]) {
        System.out.println("Input: s=input, t=ouput, k=9 \nOutput: "+ConvertStringInKMoves.canConvertString("input", "ouput", 9));

        System.out.println("\nInput: s=abc, t=bcd, k=10 \nOutput: "+ConvertStringInKMoves.canConvertString("abc", "bcd", 10));

        System.out.println("\nInput: s=aab, t=bbb, k=27 \nOutput: "+ConvertStringInKMoves.canConvertString("aab", "bbb", 27));

        System.out.println("\nInput: s=iqssxdlb, t=dyuqrwyr, k=40 \nOutput: "+ConvertStringInKMoves.canConvertString("iqssxdlb", "dyuqrwyr", 40));
        System.out.println("\nInput: s=atmtxzjkz, t=tvbtjhvjd, k=35 \nOutput: "+ConvertStringInKMoves.canConvertString("atmtxzjkz", "tvbtjhvjd", 35));
    }
}
