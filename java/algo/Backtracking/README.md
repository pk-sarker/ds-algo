# Recursion
- [Permutations](./Permutations.java)
- [Letter Combinations of a Phone Number](./PhoneNumberLetterCombinations.java)
- [Letter Combination](./LetterCombination.java)
- [Sudoku Solver](./SudokuSolver.java)
- [Jump Game](#jump-game) 


## Jump Game
You are given an integer array _nums_. You are initially positioned at the array's first index, 
and each element in the array represents your maximum jump length at that position.

Return `true` if you can reach the last index, or `false` otherwise.

**Solutions**
The naive approach will be trying every possible jumps from each position. We can use backtracking.

Pseudo code
```text
JumpGame(int currentPosition, int[] nums):
    if currentPosition == length(nums)-1:
        return true
    
    maxJumpFromCurrentPosition = min(currentPosition+nums[currentPosition], length(nums)-1)
    for nextPosition <- currentPosition+1 to maxJumpFromCurrentPosition:
        if (JumpGame( nextPosition, nums) == true):
            return true;
    
    return false;
```
[Implementation using Backtracking](./JumpGame.java)

Time complexity : _O(2^n)_. There are _2^n_ (upper bound) ways of jumping from the 
first position to the last, where _n_ is the length of array `nums`.

We can improve the backtracking solution by remembering what we have 
already calculated. We can use dynamic programming, use a 1-dimensional 
storage to remember if we can reach to last index from current index.

Algorithm:
```text
Step 1: Initially set all the elements in the `memo` as Unknown/-1. And 
        Set 1 in the last position
Step 2: If the current index is known then
    - If its knows then return the value.
    - Otherwise perform the backtracking
Step 3: Once we determine the value of the current index, we store it in the memo table
```