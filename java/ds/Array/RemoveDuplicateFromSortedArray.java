package ds.Array;

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once.
 * The relative order of the elements should be kept the same.
 */
public class RemoveDuplicateFromSortedArray {
    public static int removeDuplicates(int[] nums) {
        int left = 0, right = 1;
        if (nums.length == 1 || nums.length == 0) {
            return nums.length;
        }
        while(right < nums.length) {
            if (nums[left] != nums[right]) {
                if (right-left > 1) {
                    //swap
                    // 1,2,2,2,3
                    // left=1, right=4
                    // 1,2,3
                    nums[left+1] = nums[right];
                }
                left++;
            }
            right++;
        }
        return left+1;
    }
    public static void main(String args[]) {
        System.out.println(RemoveDuplicateFromSortedArray.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }
}
