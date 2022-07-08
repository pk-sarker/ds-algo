package algo.Searching.BinarySearch;

/**
 * Given a sorted array of distinct integers and a target value,
 * return the index if the target is found. If not, return the index
 * where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {

        int left = 0, right = nums.length-1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > target) {
                right = mid-1;
            }
            if (nums[mid] < target) {
                left = mid+1;
            }
        }
        return left;
    }

    public static void main(String args[]) {
        SearchInsertPosition obj = new SearchInsertPosition();

        System.out.println("Input: [1,3,5,6], target = 5\nOutput: " + obj.searchInsert(new int[]{1,3,5,6}, 5));
        System.out.println("Input: [1,3,5,6], target = 2\nOutput: " + obj.searchInsert(new int[]{1,3,5,6}, 2));
        System.out.println("Input: [1,3,5,6], target = 9\nOutput: " + obj.searchInsert(new int[]{1,3,5,6}, 9));
    }
}
