package pattern.TwoPointer;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 *
 */
public class TrappingRainWater {

    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, result = 0;

        while(left < right) {
            if (height[left] < height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    result += (leftMax - height[left]);
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    result += (rightMax-height[right]);
                }
                right--;
            }
        }

        return result;

    }

    public static void main(String args[]) {
        System.out.println("\nInput: [0,1,0,2,1,0,1,3,2,1,2,1] \nOutput: " + TrappingRainWater.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));

    }
}
