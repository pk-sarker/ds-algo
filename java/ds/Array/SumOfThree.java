package ds.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfThree {
    public static List<List<Integer>> threeSum(int[] arr) {
        int n = arr.length;
        List<List<Integer>> triplets = new ArrayList<>();
        if (n<3) {
            return triplets;
        }
        Arrays.sort(arr);
        int right=n-1;
        for(int i=0; i<n; i++) {
            if (i>0 && arr[i] == arr[i-1]) {
                continue;
            }
            int target = -arr[i];
            int left = i+1;
            int currentSum = 0;
            while(left < right) {
                currentSum = arr[left] + arr[right];
                if (currentSum == target) {
                    triplets.add(Arrays.asList(arr[left], arr[right], -target));
                    left++;
                    right--;

                    // skip same element to avoid duplicate triplets
                    while(left < right && arr[left] == arr[left-1]) {
                        left++;
                    }
                    // skip same element to avoid duplicate triplets
                    while(left < right && arr[right] == arr[right+1]) {
                        right--;
                    }
                } else if (currentSum > target) {
                    right--;
                } else {
                    left++;
                }
            }

        }
        return triplets;
    }


    public static void main(String args[]) {
        System.out.println(SumOfThree.threeSum(new int[]{-1,0,1,2,-1,-4}).toString());
        System.out.println(SumOfThree.threeSum(new int[]{12, 3, 1, 2, -6, 5, -8, 6}).toString());
    }
}
