package ds.Array;

/**
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 *
 * countAndSay(1) = "1"
 * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is
 * then converted into a different digit string.
 * To determine how you "say" a digit string, split it into the minimal number of groups so
 * that each group is a contiguous section all of the same character. Then for each group, say
 * the number of characters, then say the character. To convert the saying into a digit string,
 * replace the counts with a number and concatenate every saying.
 *
 * For example, the saying and conversion for digit string "3322251":
 *
 * Given a positive integer n, return the nth term of the count-and-say sequence.
 *
 * Input: n = 1
 * Output: "1"
 * Explanation: This is the base case.
 *
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 *
 *
 */
public class CountAndSay {

    public static String countAndSay(int n) {

        StringBuilder current = new StringBuilder();
        StringBuilder prev = new StringBuilder();
        current.append("1");
        int count;
        char say;

        for(int i=1; i<n;i++) {
            prev = current;
            say = prev.charAt(0);
            current = new StringBuilder();
            count = 1;
            for(int j=1;j<prev.length();j++) {
                if (prev.charAt(j) != say) {
                    current.append(count).append(say);
                    count = 1;
                    say = prev.charAt(j);
                } else {
                    count++;
                }
            }
            // 2233444 -> 444
            current.append(count).append(say);
        }

        return current.toString();
    }

    public static void main(String args[]) {
        System.out.println(CountAndSay.countAndSay(4));
    }
}
