package algo.Searching.BinarySearch;

/**
 * Given an sorted array A of size N. Find number of elements which are less than or equal to B.
 * Expected Time Complexity O(log N)
 *
 * Example Input
 * Input: A = [1, 3, 4, 4, 6], B = 4
 * Output: 4
 * Input 2: A = [1, 2, 5, 5], B = 3
 * Output: 2
 */
public class SmallerOrEqualElements {

    public int getSmaller(int[] nums, int n) {
        return binarySearchHelper(nums, n, 0, nums.length);
    }

    /**
     * Recursion:
     */
    public int binarySearchHelper(int[] nums, int t, int low, int high) {
        if (low >= high) {
            return low;
        }
        int mid = low + (high - low)/2;
        if (t >= nums[mid]) {
            return binarySearchHelper(nums, t, mid+1, high);
        } else {
            return binarySearchHelper(nums, t, low, mid);
        }
    }

    public int getSmallerItr(int[] nums, int n) {

        int low = 0, high = nums.length - 1;

        while(low <= high) {
            int mid = low + (high-low)/2;

            if (nums[mid] == n) {
                return nums[mid];
            }
            if (n > nums[mid]) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return nums[low];
    }

    public int getSmallerNumIndexItr(int[] nums, int n) {

        int low = 0, high = nums.length - 1;
        int idx = 0;

        while(low <= high) {
            int mid = low + (high-low)/2;

            if (nums[mid] == n) {
                idx= mid;
                if(mid < nums.length-1 && nums[mid+1] == n ){
                    low=mid+1;
                }
                else
                    break;
            }
            if (n > nums[mid]) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }

        if (idx == 0) {
            return low;
        }
        return idx+1;
    }

    public static void main(String args[]) {
        SmallerOrEqualElements obj = new SmallerOrEqualElements();
        System.out.println(obj.getSmaller(new int[]{1,1,2,3,4,5,5,5,5,6,10,11,12,12,15,17,19,21,24,24,25,27,43,47,56,67,73,81,88,93,95,95}, 5));

        System.out.println(obj.getSmallerItr(new int[]{1,1,2,3,4,5,5,5,5,6,10,11,12,12,15,17,19,21,24,24,25,27,43,47,56,67,73,81,88,93,95,95}, 5));

        System.out.println(obj.getSmallerNumIndexItr(new int[]{1,1,2,3,4,5,5,5,5,6,10,11,12,12,15,17,19,21,24,24,25,27,43,47,56,67,73,81,88,93,95,95}, 5));
    }
}