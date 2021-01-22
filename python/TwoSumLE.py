# Given an array of integers nums and an integer target, return indices of the two numbers
# such that they add up to target.
#
# You may assume that each input would have exactly one solution, and you may not use the
# same element twice.
#
# You can return the answer in any order.
#
# Input: nums = [2,7,11,15], target = 9
# Output: [0,1]
# Output: Because nums[0] + nums[1] == 9, we return [0, 1].
#
# Input: nums = [3,2,4], target = 6
# Output: [1,2]
#
# Input: nums = [3,3], target = 6
# Output: [0,1]
#
class TwoSumLE:
    def two_sum(self, nums, target):
        if len(nums) == 0:
            return None

        hash_map = dict()
        counter = 0
        for number in nums:
            diff = target - number
            if diff in hash_map.keys():
                return [hash_map.get(diff), counter]
            hash_map[number] = counter
            counter = counter + 1
        return []


sol = TwoSumLE()
print('\nInput: [15, 11, 7, 1, 2] target: 9 \nOutput: {}'.format(sol.two_sum([15, 11, 7, 1, 2], 9)))

print('\nInput: [11, 15, 7, 1, -3, -6] target: 9 \nOutput: {}'.format(sol.two_sum([11, 15, 7, 1, -3, -6], 9)))
