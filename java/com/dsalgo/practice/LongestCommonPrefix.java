package com.ds.practice;

public class LongestCommonPrefix {

    // Horizontal scanning
    public static String longestCommonPrefixHorizontalScanning(String[] strs) {
        int n =  strs.length;
        if (n == 0) {
            return "";
        }
        String commonPrefix = strs[0];
        for(int i=1; i<n; i++) {
            while(strs[i].indexOf(commonPrefix) != 0) {
                commonPrefix = commonPrefix.substring(0, commonPrefix.length() - 1);
                if (commonPrefix == "") {
                    return "";
                }
            }
        }
        return commonPrefix;
    }

    // Vertical Scanning
    public static String longestCommonPrefixVerticalScanning(String[] strs) {
        int n = strs.length;
        if (n == 0) {
            return "";
        }
        String commonPrefix = strs[0];
        boolean matched = true;
        for(int i=0; i < commonPrefix.length(); i++) {
            for(int j=1; j < strs.length;  j++) {
                if (strs[j].charAt(i) != commonPrefix.charAt(i)) {
                    //return commonPrefix.substring(0, i);
                    matched = false;
                    break;
                }
            }
            if (!matched) {
                if (i <=1 ) {
                    return "";
                } else {
                    return commonPrefix.substring(0, i);
                }
            }
        }

        return commonPrefix;
    }

    // Binary Search
    public static String longestCommonPrefixBinarySearch(String[] strs) {
        int n = strs.length;
        if (strs == null || n == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            minLen = Math.min(minLen, strs[i].length());
        }
        int low = 1, high = minLen;

        while(low <= high) {
            int middle = (low + high)/2;
            if (LongestCommonPrefix.isCommonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle-1;
            }
        }
        String comonPrefix = strs[0].substring(0, (low + high)/2 );
        return comonPrefix;
    }

    public static boolean isCommonPrefix(String[] strs, int length) {
        String prefix = strs[0].substring(0, length);

        for(int i=1; i< strs.length; i++) {
            if (!strs[i].startsWith(prefix)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        String[] strs = new String[]{"florescence","flower","flow", "floral", "flourish"};
        System.out.println("\nUsing Horizontal Scanning:\nInput: [\"florescence\",\"flower\",\"flow\", \"floral\", \"flourish\"] \nOutput: " + LongestCommonPrefix.longestCommonPrefixHorizontalScanning(strs));
        System.out.println("\nUsing Vertical Scanning:\nInput: [\"florescence\",\"flower\",\"flow\", \"floral\", \"flourish\"] \nOutput: " + LongestCommonPrefix.longestCommonPrefixVerticalScanning(strs));
        System.out.println("\nUsing Binary Search:\nInput: [\"florescence\",\"flower\",\"flow\", \"floral\", \"flourish\"] \nOutput: " + LongestCommonPrefix.longestCommonPrefixBinarySearch(strs));
    }
}
