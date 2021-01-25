#
# Given an integer array nums, find the contiguous subarray (containing at least one number)
# which has the largest sum and return its sum.
#
class SubArrayMaximumSum:
    def max_sub_array(self, nums):
        if len(nums) == 1:
            return nums[0];

        sum = nums[0]
        max_sum = nums[0]

        for i in range(0, len(nums)):
            sum = max(nums[i], sum + nums[i])
            max_sum = max(max_sum, sum)

        return max_sum


sol = SubArrayMaximumSum()

print('\nInput: [-2,1,-3,4,-1,2,1,-5,4] \nOutput: {}'.format(sol.max_sub_array([-2, 1, -3, 4, -1, 2, 1, -5, 4])))
