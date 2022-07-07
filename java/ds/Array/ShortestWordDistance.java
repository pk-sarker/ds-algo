package ds.Array;

/**
 * Given an array of strings wordsDict and two different strings that already
 * exist in the array word1 and word2, return the shortest distance between
 * these two words in the list.
 *
 * Example:
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
 * Output: 3
 */
public class ShortestWordDistance {

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int w1Index = -1, w2Index = -1, minDistance = wordsDict.length;

        for(int i=0; i<wordsDict.length; i++) {
            if (wordsDict[i].equals(word1)) {
                w1Index = i;
            }
            if (wordsDict[i].equals(word2)) {
                w2Index = i;
            }

            if( w1Index > -1 && w2Index > -1) {
                minDistance = Math.min(minDistance, Math.abs(w1Index - w2Index));
            }
        }
        return minDistance;
    }

    public static void main(String args[]) {
        ShortestWordDistance obj = new ShortestWordDistance();
        int minDistance = obj.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice");
        System.out.println("Min Distance between 'coding' and 'practice': " + minDistance);

        int minDistance2 = obj.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes","practice"}, "coding", "practice");
        System.out.println("Min Distance between 'coding' and 'practice': " + minDistance2);
    }
}
