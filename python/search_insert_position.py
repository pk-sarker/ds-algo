"""
Search Insert Position
Given a sorted array of distinct integers and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [1,3,5,6], target = 5
Output: 2

Example 2:
Input: nums = [1,3,5,6], target = 2
Output: 1

Example 3:
Input: nums = [1,3,5,6], target = 8
Output: 4
"""
from typing import List


class Solution:

    def search_insert(self, nums: List[int], target: int) -> int:
        left = 0
        right = len(nums)-1
        while left <= right:
            mid = left + int((right-left)/2)

            if nums[mid] == target:
                return mid
            if nums[mid] > target:
                right = mid-1
                continue
            if nums[mid] < target:
                left = mid +1
        return left

if __name__ == "__main__":
    sol = Solution()
    position = sol.search_insert([1,3,5,6], 5)
    print(f"Input: nums = [1,3,5,6], target = 5\nOutput: {position}")