package pattern.Palindrome;

/**
 * You are given a string num consisting of digits only.
 *
 * Return the largest palindromic integer (in the form of a string)
 * that can be formed using digits taken from num. It should not contain leading zeroes.
 *
 * Notes:
 * You do not need to use all the digits of num, but you must use at least one digit.
 * The digits can be reordered.
 *
 * Example:
 * Input: num = "444947137"
 * Output: "7449447"
 * Explanation:
 * Use the digits "4449477" from "444947137" to form the palindromic integer "7449447".
 * It can be shown that "7449447" is the largest palindromic integer that can be formed.
 *
 */
public class LargestPalindromicNumber {
    public static String largestPalindromic(String num) {
        int[] digitFrequency =new int[10];
        for(int i=0; i<num.length(); i++) {
            int digit = (int) num.charAt(i) - 48;
            digitFrequency[digit]++;
        }

        StringBuffer ans = new StringBuffer();
        int single = -1;
        for(int i=9;i>=0;i--){
            if(ans.length() == 0 && i == 0) {
                continue;
            }

            while(digitFrequency[i]>1){
                ans.append((char)(i+48));
                digitFrequency[i] -= 2; // taking a pair, 1 for on the left end 1 for the right end
            }

            if(digitFrequency[i] == 1 && single == -1){
                single = i;
            }
        }

        if(ans.length()==0 && single==-1) {
            return "0";
        }

        int i = ans.length()-1;

        if(single != -1) {
            ans.append((char)(single+48));
        }
        for(;i>=0;i--){
            ans.append(ans.charAt(i));
        }
        return ans.toString();
    }
    public static void main(String args[]) {
        System.out.println(LargestPalindromicNumber.largestPalindromic("444947137"));
    }
}
