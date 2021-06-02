package com.dsalgo.practice;

public class MonotonicArray {
    public boolean isMonotonic(int[] A) {
        if (A.length == 1) {
            return true;
        }
        int increasing = -1;
        for(int i=0; i< A.length-1;i++) {
            if (increasing == -1 && A[i] != A[i+1]) {
                increasing = A[i] < A[i+1] ? 0:1;
                continue;
            }
            if ((increasing == 0 && A[i] > A[i+1] ) || (increasing == 1 && A[i] < A[i+1] )) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        MonotonicArray obj = new MonotonicArray();

        System.out.println(obj.isMonotonic(new int[]{0}));
        System.out.println(obj.isMonotonic(new int[]{0,0}));
        System.out.println(obj.isMonotonic(new int[]{0,0,1}));
        System.out.println(obj.isMonotonic(new int[]{0,0,1,2,1}));
    }
}
