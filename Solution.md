# Solution

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

#### Best Time to Buy and Sell Stock
You are given an array prices where `prices[i]` is the price of a given stock on the *i-th* day.

You want to maximize your profit by choosing a single day to buy one stock and choosing 
a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot 
achieve any profit, return 0.

Example: 
```
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
```
**Solution**:
Maximum profit depends on two things buy in minimum price and sell in maximum price, with condition that
selling price has to be a later date then buying date. 
For single pass linear time solution we need to think of few cases:
* Only minimum buying price won't guarantee maximum profit, there has to be a selling price after that day with more deviation.
* Any prices which  is maximum in the list won't maximize profit, consider like this: `[6,8,1,4,5,7,1]`. `8` is maximum but there  is only
one purchase possible before that which is `6` which doesn't maximize  the profit.

So we can keep track of minimum and maximum profit. We will update the minimum value as soon as 
there is a price less than the current minimum. And, we will calculate profit if  
the difference between current price and minimum price is greater than last maximum profit.

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(1)* 

[Implementation ](./java/com/ds/practice/BuyAndSellStockLE.java)
 
A slightly modified version of the problem: Now you need to return the indices of the days
to buy and sell to have maximum profit.

It will be the same approach as before with little change, we will keep track of 
the day to purchase(minimum  price) temporarily. A temporary purchase day will be 
fixed when we have a profit which is greater than last profit.\
[Implementation ](./java/com/ds/practice/BuyAndSellStockLE.java) 

#### Verifying an Alien Dictionary
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

**Solution**: 
Solution is simple for this problem. We need to parse alphabets and store their position in a hash map. Then use this hashmap
to compare lexicographic ordering.
Another idea is that in a list of words, say `w1, w2, w3 .. wn`, if all the words ordered lexicographically then if we pick any two
consecutive words they will be ordered as well.

[Implementation ](./java/com/ds/practice/VerifyingAnAlienDictionary.java)


#### Maximum subarray sum
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Input: nums = [1]
Output: 1

Input: nums = [0]
Output: 0

Input: nums = [-1]
Output: -1
``` 

**Solution**
Key point to notice: sum of contiguous subarray, that means there will be start and end of elements. 
Summing the whole array won't guarantee maximum sum. So how do we solve this in linear time. 

As the sum will be the result of contiguous subarray, we need to carry over the previous sum.
At each step we need to calculate maximum sum at current position. and keep a record of maximum sum seen so far.

How to determine wheather we should carry over previous sum or start form current element?
If the sum of previous sum and current number is less than current number then we can decide 
to start from current number as previous sum is decreasing the sum. And we keep the max sum in 
a variable which will store max sum seen so far, not the current max sum.

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(1)* 

Implementation: [Java](./java/com/ds/practice/SubArrayMaximumSum.java)  [Python](./python/SubArrayMaximumSum.py)
 
#### Valid Parentheses
Given a string s containing just the characters `(`, `)`, `{`, `}`, `[` and `]`, determine if the input string is valid.

An input string is valid if:

* Open brackets must be closed by the same type of brackets.
* Open brackets must be closed in the correct order.

Example:
```
Input: s = "()"
Output: true

Input: s = "()[]{}"
Output: true

Input: s = "(]"
Output: false

Input: s = "([)]"
Output: false

Input: s = "{[]}"
Output: true
```

**Solution**:
Scan the string by each character. Use a stack to store the opening brackets. Keep
the opening brackets in the stack. Keep pushing in the stack until a closing bracket is read.
We need a hash table to store the opening bracket for each closing bracket.
 
If a closing bracket is read then pop from the stack. For a valid parentheses the popped character 
should be the opening of same time. The idea is the bracket late should close first. At any point if a bracket is 
opened then it must close before any other closing bracket. 

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(n)* 

Implementation: [Java](./java/com/ds/practice/ValidateParentheses.java)