/*
 * Given an array of integers nums and an integer target, return indices of the two numbers such
 * that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 *
 * You can return the answer in any order.
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
// With arrow function.
// let two_sum = (nums, target) => { }
function two_sum(nums, target) {
    if (nums == null || target == null) {
        return [];
    }
    if (nums.length === 0){
        return [];
    }
    let hashMap = new Map();
    let i;
    for(i=0; i<nums.length;i++) {
        let diff = target - nums[i];
        if (hashMap.has(diff)) {
            return [hashMap.get(diff), i];
        }
        hashMap.set(nums[i], i);
    }
    return [];
}


let result = two_sum([15,11,7,1,-6], 9);
console.log("Input: [15,11,7,1,-6] target: 9 \nOutput: " + result);