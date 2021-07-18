# Binary Search

- [Find number in bitonic array](#find-number-in-bitonic-array) - [Code](./SearchInBitonicArray.java)
- [Smallest or equal number](#smallest-or-equal-number) - [Code](./SmallerOrEqualElements.java)
- [Find First and Last Position of Element in Sorted Array](#find-first-and-last-position-of-element-in-sorted-array) - [Code](./Find1stAnd2ndPositionInSortedArray.java)


#### Find number in bitonic array
Given a bitonic sequence of *n* distinct elements, write a program to find a given element in the bitonic sequence in *O(log n)* time.

A Bitonic Sequence is a sequence of numbers which is first strictly increasing then after a point strictly decreasing.
```
[1,3,6,8,11,7,4,2]
    /\           /\        /\
   /  \   or    /  \  or  /  \
  /    \            \    /
```

Return a single integer denoting the position (0 index based) of the target element in the array if the target element doesn't exist in array return *-1*.

Example:
```
Input: A = [3, 9, 10, 20, 17, 5, 1], B = 20
Output: 3

Input: A = [3, 9, 10, 20, 17, 5, 1], B = 5
Output: 5

Input: A = [5, 6, 7, 8, 9, 10, 3, 2, 1], B = 30
Output: -1
```

**Solution**\
Key observation is that in Bitonic array is like a wave with peak at the middle or somewhere between start and end. 
All the smaller number are at the starting or end. Numbers in the Bitonic array are ordered but combination of incremental
and decremental sequence. 

To apply binary search we need detect if the middle is in incremental sequence or decremental sequence. If the middle is in 
incremental sequence then we can just apply normal binary search, if target is greater than middle element. 

In incremental sequence if target number is less than the current middle element then the search scope increases, we need to 
check both in left and right side, because the smaller number are at the both ends. 

**Algorithm:**\
1. Take the middle of the array
2. Compare the middle element with one of its neighbor to see if the max is on the right or on the left
3. Compare the middle element with the desired value
4. If the middle element is smaller than the desired value AND the max is on the left side, then do bitonic search on the left subarray (we are sure that the value is not in the right subarray)
5. If the middle element is smaller than the desired value AND the max is on the right side, then do bitonic search on the right subarray
6. If the middle element is bigger than the desired value, then do descending binary search on the right subarray and ascending binary search on the left subarray. 

*Time Complexity*: *O( 3 log n) = O(log n)* \ 

[Implementation](./SearchInBitonicArray.java)


#### Smallest or equal number
Given an sorted array A of size N. Find number of elements which are less than or equal to B.
Expected Time Complexity O(log N)

Example Input
```
Input: A = [1, 3, 4, 4, 6], B = 4
Output: 4
Input 2: A = [1, 2, 5, 5], B = 3
Output: 2
```

*Time Complexity*: *O(log n)* \ 

[Implementation](./SmallerOrEqualElements.java)

