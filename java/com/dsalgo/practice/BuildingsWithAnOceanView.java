package com.dsalgo.practice;

import java.util.Arrays;

/**
 * There are n buildings in a line. You are given an integer array heights of size n that represents
 * the heights of the buildings in the line.
 *
 * The ocean is to the right of the buildings. A building has an ocean view if the building can see
 * the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its
 * right have a smaller height.
 *
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 *
 */
public class BuildingsWithAnOceanView {

    public int[] findBuildings(int[] heights) {
        int[] indices = new int[heights.length];
        int idxPointer = heights.length - 1;
        int maxHeight = Integer.MIN_VALUE;
        for(int i = heights.length - 1; i>=0; i--) {
            if (heights[i] > maxHeight) {
                indices[idxPointer--] = i;
                maxHeight = heights[i];
            }
        }
        if (idxPointer < heights.length - 1) {
            return Arrays.copyOfRange(indices, idxPointer+1, heights.length);
        }
        return new int[]{};
    }

    public static void main(String args[]) {
        BuildingsWithAnOceanView obj = new BuildingsWithAnOceanView();
        System.out.println("Input: [4,2,3,1] \nOutput: "+Arrays.toString(obj.findBuildings(new int[]{4,2,3,1})));

        System.out.println("Input: [4,3,2,1] \nOutput: "+Arrays.toString(obj.findBuildings(new int[]{4,3,2,1})));

        System.out.println("Input: [1,2,3,4] \nOutput: "+Arrays.toString(obj.findBuildings(new int[]{1,2,3,4})));

        System.out.println("Input: [2,2,2,2] \nOutput: "+Arrays.toString(obj.findBuildings(new int[]{2,2,2,2})));
    }
}
