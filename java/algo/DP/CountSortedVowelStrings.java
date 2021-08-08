package algo.DP;

/**
 * Given an integer n, return the number of strings of length n that consist only of
 * vowels (a, e, i, o, u) and are lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or
 * comes before s[i+1] in the alphabet.
 *
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 *
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 *
 * Input: n = 33
 * Output: 66045
 *
 * Vowels: a, e, i, o, u
 * n = 1, => [a, e, i, o, u]
 *              5                      4           3           2      1
 * n = 2  => [aa, ae, ai, ao, au, ee, ei, eo, eu, ii, io, iu, oo, ou, uu]
 *
 * n = 3 => [aaa, aae, aai, aao, aau, aei, aeo, aeu, aee, aii, aio, aiu, aoo, aou, auu]
 */
public class CountSortedVowelStrings {

    // Brute force, O(n^5)
    public int countVowelStrings(int n) {
        return countVowelStringsHelperBF(n, 1);
    }
    // n=1, char at pos 1 will have only one choice, for 5 vowel total is 5
    // n=2, there are two positions, __, first one can be picked any one of the five
    //      but the 2nd one has to be in order.
    //      for vowel at pos 1 will have, n-1=5-1=4 choices to 2nd position
    //      Similarly, vowel at position 2 will have n-2=3 choices for 2nd position
    // n=3, there are 3 positions, ___, first one can be picked any one of the five
    //      for vowel 1, for 2nd position it will have n-1 choices and for
    //      3rd position it will have n-2 choice.
    //
    // n = 2
    // a - {a, e, i, o, u}, e - {e, i, o, u}, i - {i, o, u}, o - {o, u}, u - {u}


    private int countVowelStringsHelperBF(int n, int startVowel) {
        if (n==0) { // made all the choice
            return 1;
        }
        int count = 0;
        for(int i=startVowel; i<=5; i++) {
            // n determines the recursion depth
            count += countVowelStringsHelperBF(n-1, i);
        }
        return count;
    }


    /**
     * Optimized
     * @param args
     */

    /*
    v=1,n=1
    only one string: {a}
    v=1,n=2
    only one string: {aa}

    v=2,n=1 => {a, e}
    v=3,n=1 => {a, e, i} = 3
    v=2,n=2 => {a(a), a(e), e(e)} => {aa, ae, ee} = 3
    v=2,n=3 =  {a(a)(a), a(a)(e), a(e)(e), e(e)(e)} => {aaa, aae, aee, eee} = 4
    v=3,n=2 => {a(a), a(e), a(i), e(e), e(i), i(i)}
            => {aa, ae, ai, ee, ei, ii} = 6
    v=3,n=3 => {a(a)(a), a(a)(e), a(a)(i),
                a(e)(e), a(e)(i)
                a(i)(i),
                e(e)(e), e(e)(i)
                e(i)(i),
                i(i)(i)}
                => 10

    count (v,n) = count(v-1,n)  + count(v,n-1)
    count(3,3) = count(3-1,3) + count(3, 2)
               = count(2,3) + count(3,2) = 4 + 6 = 10

    * number of vowels can be considered as number or pods which starts with a common vowel
    * n= is number of vowel in each string,
    *
    -> If we keep n fixed and increase number of vowels then we will be adding a extra pod
    *  and one more choice for previous pods. For example, if we had v=3 and n=2 then for
    each pod which may start with any of the vowel,{a,e,i}, let say a, then we had one more
    position to fill to make a string with length 2. To fil that we have 3 choices {a, e,i}, if
    we take those then substrings will be {aa, ae, ai}.
    Now if we increase the vowel then the choice increases, {a, e, i, o}, with one more choice
    it will generate one more string, {ao}. So with one increase of vowel will add a new pod,
    for the new one, and one more choice for previous pods

    */
    int[][] memo;
    public int countVowelStringsOpt(int n) {
        this.memo = new int[n+1][6];  // will start from 1
        return helper(n, 5);
    }

    public int helper(int n, int vowel) {
        if (n==1) {
            return vowel; // v=5, n=1 => 5, one for each vowel
        }

        if (vowel == 1) {
            return 1; // there is no choice, only one string could be formed
        }

        // if already computed for current n and vowel
        if (this.memo[n][vowel] != 0) {
            return this.memo[n][vowel];
        }

        int count = helper(n, vowel-1) + helper(n-1, vowel);
        this.memo[n][vowel] = count;

        return count;
    }


    public static void main(String args[]) {
        CountSortedVowelStrings obj = new CountSortedVowelStrings();
        System.out.println("n=1 Count: "+obj.countVowelStrings(1));
        System.out.println("n=2 Count: "+obj.countVowelStrings(2));
        // 3,3 =
        System.out.println("n=3 Count: "+obj.countVowelStrings(3));
        System.out.println("n=3 Count: "+obj.countVowelStringsOpt(3));
    }
}
