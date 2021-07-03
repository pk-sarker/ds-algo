package pattern.TwoPointer;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
 * Find two lines, which, together with the x-axis forms a container, such that the container contains
 * the most water.
 *
 * Notice that you may not slant the container.
 */
public class ContainerWithMostWater {
    public int findMaxContainerSizeBruteForce(int[] heights) {
        int maxArea = 0;
        int n = heights.length;
        if (heights.length<=0) {
            return maxArea;
        }
        for(int i=0; i<n;i++) {
            for(int j=i+1; j<n;j++) {
                int area = (j-i) * Math.min(heights[i],heights[j]);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }

    public int findMaxContainerSize(int[] heights) {
        int maxArea = 0;
        int n = heights.length;
        int start=0, end = n-1;

        while(start < n && end>start) {
            int area = (end-start) * Math.min(heights[start],heights[end]);
            maxArea = Math.max(area, maxArea);
            System.out.println("("+start+","+end+") -> " + maxArea);
            if (heights[start] < heights[end]) {
                start++;  // max area is calculated by taking heights[start], try next one
            } else {
                end--; // max area is calculated by taking heights[end], try next one
            }
        }

        return maxArea;
    }

    public static void main(String args[]) {

        ContainerWithMostWater obj = new ContainerWithMostWater();
        System.out.println("Input:[1,8,6,2,5,4,8,3,7]\nOutput:"+obj.findMaxContainerSizeBruteForce(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println("Input:[1,8,6,2,5,4,8,3,7]\nOutput:"+obj.findMaxContainerSize(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println("Input:[3,9,6,2,5,11,8,3,7]\nOutput:"+obj.findMaxContainerSize(new int[]{3,15,6,2,5,20,8,3,7}));
    }
}
