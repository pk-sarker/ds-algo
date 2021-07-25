package pattern.Palindrome;

/**
 * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 */
public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <=1) {
            return true;
        }

        for(int i=0, j=s.length()-1; i<j; i++, j--) {
            while(i<j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while(i<j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
        }
        return true;
    }
    public static void main(String args[]) {
        System.out.println(ValidPalindrome.isPalindrome("A man, a plan, a canal: Panama"));
    }
}
