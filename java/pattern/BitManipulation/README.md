# Bit Manipulation
- [Check odd even](#check-odd-even) - [Java](./BitManipulation1.java)
- [Detect signs](#detect-signs) - [Java](./BitManipulation1.java)
- [Swap two numbers without using third variable](./BitManipulation1.java)
- [Single Number](#single-number) - [Java](./BitManipulation1.java)



#### Check odd even
Given a integer *n*, write a function to check if *n* is even.

Example:
```
Input: 21
Output: false

Input: 20
Output: true
```

[Implementation - Java](./BitManipulation1.java)

#### Detect signs
Given two integer *m* and *n*, detect if two integers have opposite signs or not.

Example:
```
Input: m=4, n=-7
Output: true

Input: m=4, n=7
Output: false
```

The expression output `x ^ y`  is negative if `x` and `y`  have opposite signs.
We know that for a positive number, the leftmost bit is 0, and for a negative number,
it is 1. Now for similar sign integers, the XOR operator will set the leftmost bit of output as 0,
and for opposite sign integers, it will set the leftmost bit as 1.

```
00..000100    ^               (x = 4)
00..001001                    (y = 7)
----------
00..001100                    positive number
 
 
00..000100    ^               (x = 4)
11..111001                    (y = -7)
----------
11..111101                    negative number
```
[Implementation - Java](./BitManipulation1.java)

#### Single Number
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

Note: Could you implement a solution with a linear runtime complexity and without using extra memory?

Example:
```
Example 1:

Input: nums = [2,2,1]
Output: 1
Example 2:

Input: nums = [4,1,2,1,2]
Output: 4
Example 3:

Input: nums = [1]
Output: 1
```

**Solution:**
There are different ways we can solve this problem. Bruteforce approach would be for each number we iterate
over the remaining numbers and check if there is any duplicate. The time complexity of this approach will be *O(n^2)*.

We can improve the time complexity by keeping the numbers in a hash table which will give us constant read (*O(1)*).
Then the approach will be two pass, in first pass we create the hash table with all the numbers and in the second pass
we check if the occurrence is more than 1 or not. Time complexity will be *O(n)* and space complexity *O(n)*.

We can further improve the time complexity by using mathematical concept. We need to find all the unique/distinct
numbers in the list. Then sum the unique number and multiply by 2. Then from that sum we deduct the total sum which will
give us the number which appeared once only.

Equation:
```
(a+b+c) * 2 = (a + b + a + c + b) + c
c = (a+b+c) * 2  - (a + b + a + c + b)
c = sum of unique numbers * 2 - total sum
```
Time complexity will be *O(n)*, single pass and space complexity *O(n)*.

Another way is the use bit manipulation, we can use `XOR` operation. `XOR` of two numbers that are equal will be zero.
That means if we do `XOR` for all the numbers then lastly we will have the number that appeared once.

Time complexity will be *O(n)* and space complexity *O(1)*.

[Implementation - Java](./BitManipulation1.java)