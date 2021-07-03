package ds.Array;


public class ValidSubsequence2 {

    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        if (n > m) {
            return false;
        }
        int sIdx = 0;
        for(int i=0;i<m;i++){
            if (sIdx == n) {
                break;
            }
            if (s.charAt(sIdx) == t.charAt(i)) {
                sIdx++;
            }
        }
        if (sIdx == n) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {

    }
}
