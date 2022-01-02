# Binary Search
- [Split Array Largest Sum](#split-array-largest-sum)


###  Split Array Largest Sum

Given an array nums which consists of non-negative integers and an integer *m*, you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these *m* subarrays.

Example:
```
Input: nums = [7,2,5,10,8], m = 2
Output: 18, [7,2,5][10,8]

Input: nums = [7,2,5,10,8], m = 3
Output: 14, [7,2,5][10][8]
```

**Solution**
Brute force approach is find all the subarrays and keep the minimum of all sums.
Then the time complexity is `O(m^m)`, where *n* is the length of the array and *m* 
is the number of sub arrays.

We can try to optimize. There are few things to notice
* array contains all non-negative numbers, which means continuous subarray sum will be always grater than the last one.
* If there is 1 split(*m*) only, then the sub-array sum will be the sum of all the elements in the array. This is the upper limit
* If the number of split(*m*) is same as the number of elements in the array then each number will be a sub-array. Then the lowest sum will be the maximum number in the array.

*Time Complexity*: *O(n * log(sumofarray)).* \ 

**Implementation**: [Python](./split_array_largest_sum.py) [Java](../java/algo/Searching/BinarySearch/SplitArrayLargestSum.java)

