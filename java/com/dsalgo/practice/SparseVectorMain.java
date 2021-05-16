package com.dsalgo.practice;

public class SparseVectorMain {

    public static void main(String args[]) {
        SparseVector spObj1 = new SparseVector(new int[]{1,0,0,2,3});
        SparseVector spObj2 = new SparseVector(new int[]{0,3,0,4,0});

        System.out.println("> " + spObj1.dotProduct(spObj2));
    }
}
