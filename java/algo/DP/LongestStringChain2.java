package algo.DP;

import java.util.*;

public class LongestStringChain2 {
    public static int longestStrChain(String[] words) {
        // sorting the array in ascending order of words length, smaller words at the beginning
        Arrays.sort(words, (a, b) -> a.length() - b.length()); // O(n log n)

        System.out.println(Arrays.toString(words));

        // <word, max chain length>
        Map<String, Integer> memo = new HashMap<>();
        Map<String, List<String>> wordsInPath = new HashMap<>();

        int longestChain = 1;
        int minLen = -1;
        List<String> maxPath = new ArrayList<>();
        for(int i=0;i<words.length;i++) {
            String w = words[i];
            int curLen = 1;
            int wLen = w.length();
            if (!wordsInPath.containsKey(w)) {
                wordsInPath.put(w, new ArrayList<>());
                wordsInPath.get(w).add(w);
            }

            if (minLen == -1) {
                minLen = wLen;
                memo.put(w, 1);
                continue;
            }

            if (wLen > minLen) { // len(w2) = len(w1)+1
                String w2 = w.substring(0, wLen-1);
                if (memo.containsKey(w2)) {
                    List<String> prev = wordsInPath.get(w2);
                    if (memo.get(w2)+1 > longestChain) {
                        prev.add(w);
                        maxPath = prev;
                        longestChain = memo.get(w2)+1;
                        wordsInPath.put(w, prev);
                    }
                    curLen++;
                }
                memo.put(w, curLen);
            }
        }
        System.out.println("Max Path: " + maxPath.toString());
        return longestChain;
    }

    public static int longestStrChain2(String[] words) {
        // sorting the array in ascending order of words length, smaller words at the beginning
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        System.out.println(Arrays.toString(words));
        // <word, max chain length>
        Map<String, Integer> memo = new HashMap<>();
        int longestChain = 1;
        for(int i=0;i<words.length;i++) {
            int currentChainLen = 1;

            for(int j=0;j<words[i].length();j++) {
                StringBuilder word = new StringBuilder(words[i]);
                word.deleteCharAt(j); // remove char at j
                // memo.getOrDefault(word.toString(), 0) => previous chain length
                currentChainLen = Math.max(currentChainLen, memo.getOrDefault(word.toString(), 0)+1);
            }
            memo.put(words[i], currentChainLen); // save
            longestChain = Math.max(longestChain, currentChainLen);
        }

        return longestChain;
    }

    public static void main(String args[]) {
        //String[] words = new String[]{"den","ben", "dent", "dew", "dents"};
//        String[] words = new String[]{"den","ben", "dent", "dew", "dents"};
//        System.out.println(Arrays.toString(words));
//        System.out.println(LongestStringChain2.longestStrChain(words));

        String[] words2 = new String[]{"den","ben", "dent", "dew", "beni","dents","benig", "dewott","benign"};
        String[] words = new String[]{"bca","a","b","bda","ba", "bdca"};
        System.out.println(Arrays.toString(words));
//        System.out.println(LongestStringChain2.longestStrChain(words));
        System.out.println(LongestStringChain2.longestStrChain2(words));
        System.out.println(LongestStringChain.longestStrChain(words2));


    }
}
