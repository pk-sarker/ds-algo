package com.dsalgo.practice;

import java.util.Arrays;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique
 * (except for the order that it is in.)
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 */
public class KClosestPointsFromOrigin {

    public int[][] kClosest(int[][] points, int K) {
        quickSelect(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void quickSelect(int[][] points, int low, int high, int K) {
        if (low >= high) {
            return ;
        }
        //int k = ThreadLocalRandom.current().nextInt(low, high);
        // swap(points, low, k);
        int mid = partition(points, low, high);
        int leftLength = mid - low + 1;
        if (K < leftLength){
            quickSelect(points, low, mid - 1, K);
        } else if (K > leftLength) {
            quickSelect(points, mid + 1, high, K - leftLength);
        }
    }

    public int partition(int[][] points, int low,  int high)  {
        int orgLow = low, orgHigh = high;
        int pivot = distance(points, low);
        low++;  // pivot is at low index, so the searching range should start from low+1
        while(true) {
            while(low < high && distance(points, low) < pivot) {
                low++;
            }
            // at this point low is pointing to a point where the distance is greater than pivot

            while(low <= high && distance(points, high) >= pivot) {
                high--;
            }
            // at this point high is pointing to a point where the distance is less than pivot

            if (low>=high) {
                break;
            }
            swap(points, low, high);
        }


        // at this point 'high' points to the next immediate point whose distance is less than pivot
        // and orgLow is the actual low.
        swap(points, orgLow, high); // swaping with orgLow because pivot was calculated from original low value
        return high;
    }

    public int distance(int[][] points, int i) {
        return points[i][0]*points[i][0] + points[i][1]*points[i][1];
    }

    public void swap(int[][] points, int i, int j) {
        int[] t;
        t = points[i];
        points[i] = points[j];
        points[j] = t;
    }

    public int[][] kClosestWithRegularSort(int[][] points, int K) {
        int N = points.length;
//        int[] dists = new int[N];
//        for (int i = 0; i < N; ++i)
//            dists[i] = points[i][0]*points[i][0] + points[i][1]*points[i][1];
//
//        Arrays.sort(dists); // O(n log n)
//
//        int distK = dists[K-1];
//
//        int[][] ans = new int[K][2];
//        int t = 0;
//        for (int i = 0; i < N; ++i) {
//            Integer cdist = points[i][0]*points[i][0] + points[i][1]*points[i][1];
//            if (cdist <= distK) {
//                ans[t++] = points[i];
//            }
//        }
//        return ans;
        Arrays.sort(points, (a, b) -> a[0]*a[0]+a[1]*a[1] - b[0]*b[0]+b[1]*b[1]);
        return Arrays.copyOfRange(points, 0, K);
    }

    public static void main(String args[]) {
        KClosestPointsFromOrigin ob = new KClosestPointsFromOrigin();
        System.out.println("\nInput: points = [[1,3],[-2,2]], K = 1 \nOutput: " + Arrays.deepToString(ob.kClosest(new  int[][]{{1,3},{-2,2}},1)));

        System.out.println("\nInput: points = [[3,3],[5,-1],[3,-2],[-2,4]], K = 2 \nOutput: " + Arrays.deepToString(ob.kClosest(new  int[][]{{3,3},{5,-1},{3,-2},{-2,4}},2)));
        System.out.println("\n Input: points = [[3,3],[5,-1],[3,-2],[-2,4]], K = 2 \nOutput: " + Arrays.deepToString(ob.kClosestWithRegularSort(new  int[][]{{3,3},{5,-1},{3,-2},{-2,4}},2)));
        System.out.println("\nInput: points = [[3,3],[5,-1],[3,-2],[-2,4]], K = 3 \nOutput: " + Arrays.deepToString(ob.kClosest(new  int[][]{{3,3},{5,-1},{3,-2},{-2,4}},3)));
        System.out.println("\n Input: points = [[3,3],[5,-1],[3,-2],[-2,4]], K = 3 \nOutput: " + Arrays.deepToString(ob.kClosestWithRegularSort(new  int[][]{{3,3},{5,-1},{3,-2},{-2,4}},3)));


    }
}
