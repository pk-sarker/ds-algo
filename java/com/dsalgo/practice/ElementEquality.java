package com.dsalgo.practice;

public class ElementEquality {
    static int indexEqualsValueSearch(int[] arr) {
        int start = 0, end = arr.length - 1;

        while(start<=end) {
            int mid = start + (end - start)/2;
            if (arr[mid]-mid == 0 && (mid == 0 || arr[mid-1] - (mid-1) < 0)) {
                return mid;
            } else if(arr[mid]-mid < 0) { // arr[mid] < mid // [0, 1, 1, 3]
                start++;
            } else {
                end--;
            }
        }
        return -1;
    }
    public static void main(String args[]) {
        System.out.println("\nInput: [-8,0,2,5] \nOutput: " + ElementEquality.indexEqualsValueSearch(new int[]{-8,0,2,5}));

        System.out.println("\nInput: [0] \nOutput: " + ElementEquality.indexEqualsValueSearch(new int[]{0}));

        System.out.println("\nInput: [-6,-5,-4,-1,1,3,5,7] \nOutput: " + ElementEquality.indexEqualsValueSearch(new int[]{-6,-5,-4,-1,1,3,5,7}));
    }

}
