# Array

- [Sum of Two Numbers](#sum-of-two-numbers) - [Java](./SumOfTwo.java) [Python](../../../python/TwoSumLE.py) [Scala](../../../scala/src/com/ds/scala/practice/TwoSumLE.scala) [JavaScript](../../../javascript/two_sum.js)
- [First non-repeating character](#first-non-repeating-character) - [Java](./FirstNonRepeatingCharacter.java)
- [Validate Subsequence Array](#validate-subsequence-array) - [Java](./ValidSubsequence.java)

#### Sum of Two Numbers
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example: 
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1].

Input: nums = [11,15,7,-6,1], target = 9
Output: [1,3]
Output: Because nums[1] + nums[3] == 9, we return [1, 3].
```

**Solution**
Brute-force solution will be compare every two numbers in the given array and check their 
sum with target. We will need two loops to get the combinations with every two numbers which
will cause *O(n^2)* time complexity and *O(1)* space complexity.

In the above solution we are evaluating each number twice, first pick one number and then pick 
another from the list. Can we do it with single pass.

Here is the formula for the problem: `num1 + num2 = target`, we can modify a little, `num1 = target - num2`.
For a given array  and target, target is constant in the equation. Target value is not changing. Then 
there are two variables only, `num1` and `num2`. 

The idea is to save every number that is visited so far, also keep their index. Then for every new number 
 we will calculate `target-num2` and check if we have seen the value. If  `num1 + num2 = target` then
 at some point there will be a match. Then we get the index from the previously seen number and 
 use current index as result.

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(n)* 

#### First non-repeating character
Given a string s consisting of small English letters, find and return the first instance of a non-repeating character in it. 
If there is no such character, return `'_'`.

Example: 
```
Input: "abacabad"
Output: c
There are 2 non-repeating characters in the string: 'c' and 'd'. 
Return c since it appears in the string first.

Input: "abacabaabacaba"
Output: -
There are no characters in this string that do not repeat.
```

**Solution**
There are two key things, non-repeating character and the sequence, first.

We can think of two step or two pass solution. In the first step we do some processing that
will help us to find the non-repeating characters and then in the second step we find the first
non-repeating character.

In first pass, we can count the occurrence of each character in the string using hash map. Then 
in the second pass for each character, starting from the left, we check the occurrence count in the 
hash map. If the count is 1 then the character is the first non-repeating character.

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(n)* 

[Implementation](./FirstNonRepeatingCharacter.java)


#### Validate Subsequence Array
Given two non-empty arrays of integers, write a function that determines whether the second array is a subsequence of the first one.

A subsequence of an array is a set of numbers that aren't necessarily adjacent 
in the array but that are in the same order as they appear in the array. For instance, the numbers `[1, 3, 4]` form a subsequence of 
the array `[1, 2, 3, 4]` , and so do the numbers `[2, 4]`. 

Note that a single number in an array and the array itself are both valid subsequences of the array.        

Example:
```
Input: [5, 1, 22, 25, 6, -1, 8, 10],
Sequence: [5, 1, 22, 6, -1, 8, 10]
Output: true
```

**Solution**
We can iterate over the input array and have a pointer pointing the first number in the sequence.
While iterating over the input array, we check if the current number matches the sequence number. 
If matches then we increment the sequence pointer to check next number in the sequence. 

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(n)* 

[Implementation](./ValidSubsequence.java)