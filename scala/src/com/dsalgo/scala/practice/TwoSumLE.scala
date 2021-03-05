package com.dsalgo.scala.practice

import scala.collection.mutable
import scala.collection.mutable.HashMap

/**
 * ##################
 * #####  EASY  #####
 * ##################
 *
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
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
object TwoSumLE {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    if (nums.length ==0) return Array();
    var hashMap= new HashMap[Int, Int]();
    for(i <- 0 to nums.length) {
      var diff = target - nums(i);
      if (hashMap.contains(diff)) {
        return Array(hashMap.getOrElse(diff, 0), i);
      }
      hashMap.addOne((nums(i), i));
    }
    return Array();
  }

  def main(args: Array[String]): Unit = {
    var res = twoSum(Array[Int](11,15,7,1,-6), 9)
    println("\nInput: [11,15,7,1,-6], target=9 \nOutput " + res.toList);
  }
}
