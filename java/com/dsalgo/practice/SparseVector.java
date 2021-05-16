package com.dsalgo.practice;

import java.util.HashMap;
import java.util.Map;

public class SparseVector {
    Map<Integer, Integer> nonZeroMap = new HashMap<>();

    public SparseVector(int nums[]){
        for(int i=0;i<nums.length;i++) {
            if (nums[i]!=0) {
                this.nonZeroMap.put(i, nums[i]);
            }
        }
    }

    public int dotProduct(SparseVector vec) {
        int result = 0;
        if (this.nonZeroMap.size() < vec.nonZeroMap.size()) {
            result = calculate(this.nonZeroMap, vec.nonZeroMap);
        } else {
            result = calculate(vec.nonZeroMap, this.nonZeroMap);
        }

        return result;
    }

    private int calculate(Map<Integer, Integer> nonZeroMap1,  Map<Integer, Integer> nonZeroMap2) {
        int result = 0;
        for(int key: nonZeroMap1.keySet()) {
            if (nonZeroMap2.containsKey(key)) {
                result += (nonZeroMap1.get(key) * nonZeroMap2.get(key));
            }
        }
        return result;
    }

}
