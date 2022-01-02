"""
Split Array Largest Sum
Given an array _nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.
"""

"""
Time complexity: O(n^m)
Space complexity: O(n)
"""


class SolutionBruteForce:
    def __init__(self):
        self._result = float("inf")
        self._nums = []
        self._m = 0
        self._n = 0

    def _dfs(self, pos, subarray_count, current_subarray_sum, current_max_sum):
        # if at end of the array
        if pos == self._n:
            if subarray_count == self._m:
                self._result = min(self._result, current_max_sum)
                return
            if subarray_count != self._m:
                return

        if pos > 0:
            self._dfs(
                pos + 1,
                subarray_count,
                current_subarray_sum + self._nums[pos],
                max(current_max_sum, current_subarray_sum + self._nums[pos]),
            )
        if subarray_count < self._m:
            self._dfs(pos + 1, subarray_count + 1, self._nums[pos], max(current_max_sum, self._nums[pos]))

    def split_array(self, _nums, M):
        self._m = M
        self._n = len(_nums)
        self._nums = _nums
        self._dfs(0, 0, 0, 0)
        return self._result


class Solution:
    def split_array(self, nums=[], m=0):
        lowest_sum = 0  # Lowest subarray sum, it will be the largest number
        maximum_sum = 0
        for num in nums:
            maximum_sum += num
            if lowest_sum < num:
                lowest_sum = num
        result = maximum_sum
        while lowest_sum <= maximum_sum:
            sum = 0
            mid = lowest_sum + ((maximum_sum - lowest_sum) // 2)
            subarray_count = 1
            for num in nums:
                if sum + num > mid:  # mid the max sum of the sumbarray
                    subarray_count = subarray_count + 1
                    sum = num
                else:
                    sum += num

            if subarray_count <= m:
                result = min(mid, result)
                maximum_sum = mid - 1
            else:
                lowest_sum = mid + 1

        return result


if __name__ == "__main__":
    print("Split Array Largest Sum")
    sol = SolutionBruteForce()
    print(sol.split_array([7, 2, 5, 10, 8], 2))
    print(sol.split_array([7, 2, 5, 10, 8], 3))

    sol2 = Solution()
    print(sol2.split_array([7, 2, 5, 10, 8], 2))
    print(sol2.split_array([7, 2, 5, 10, 8], 3))
