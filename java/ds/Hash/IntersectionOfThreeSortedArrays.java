package ds.Hash;

import java.util.ArrayList;
import java.util.List;

/**
 * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order,
 * return a sorted array of only the integers that appeared in all three arrays.
 *
 * Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
 * Output: [1,5]
 * Explanation: Only 1 and 5 appeared in the three arrays.
 *
 * Input: arr1 = [197,418,523,876,1356], arr2 = [501,880,1593,1710,1870], arr3 = [521,682,1337,1395,1764]
 * Output: []
 *
 */
public class IntersectionOfThreeSortedArrays {

    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int p1 = 0, p2  = 0,  p3 = 0;
        List<Integer> result = new ArrayList<>();
        int n1 = arr1.length, n2 = arr2.length, n3=arr3.length;
        while(p1<n1 && p2<n2 && p3 < n3) {
            if (arr1[p1] == arr2[p2] && arr1[p1] == arr3[p3]) {
                result.add(arr1[p1]);
                p1++;
                p2++;
                p3++;
            } else {
                if (arr1[p1] < arr2[p2]) {
                    p1++;
                } else if(arr2[p2] < arr3[p3]) {
                    p2++;
                } else {
                    p3++;
                }
            }

        }
        return result;
    }

    public List<Integer> arraysIntersection3Pointer(int[] arr1, int[] arr2, int[] arr3) {
        int p1 = 0, p2  = 0,  p3 = 0;
        int n = Math.min(Math.min(arr1.length, arr2.length), arr3.length);
        List<Integer> result = new ArrayList<>();
        for( int i=0; i<arr1.length; i++) {
            boolean foundInAll = true;
            while(p2 < arr2.length) {
                if (arr2[p2] > arr1[p1]) {
                    foundInAll = false;
                    break;
                }
                if (arr1[p1] == arr2[p2]) {
                    foundInAll = foundInAll & true;
                }
                p2++;
            }

            while(p3 < arr3.length) {
                if (arr3[p3] > arr1[p1]) {
                    foundInAll = false;
                    break;
                }
                if (arr1[p1] == arr3[p3]) {
                    foundInAll = foundInAll & true;
                }
                p3++;
            }

            if (foundInAll) {
                result.add(arr1[p1]);
            }
            p1++;
        }
        return result;
    }

    public static void main(String args[]) {
        IntersectionOfThreeSortedArrays obj = new IntersectionOfThreeSortedArrays();
        System.out.println("\nInput: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]\nOutput: " + obj.arraysIntersection(new int[]{1,2,3,4,5}, new int[]{1,2,5,7,9}, new int[]{1,3,4,5,8}).toString());

        System.out.println("\nInput: arr1 = [1,2,3,4,5], arr2 = [2,5,7,9], arr3 = [1,4,5]\nOutput: " + obj.arraysIntersection3Pointer(new int[]{1,2,3,4,5}, new int[]{2,5,7,9}, new int[]{1,4,5}).toString());

//        System.out.println("\nInput: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]\nOutput: " + obj.arraysIntersection3Pointer(new int[]{1,2,3,4,5}, new int[]{1,2,5,7,9}, new int[]{1,3,4,5,8}).toString());
//
//        System.out.println("\nInput: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]\nOutput: " + obj.arraysIntersection3Pointer(new int[]{1,2,3,4,5}, new int[]{1,2,5,7,9}, new int[]{1,3,4,5,8}).toString());
    }
}
