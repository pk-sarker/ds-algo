# Two Pointer

- [Container With Most Water](#container-with-most-water) - [Java](./ContainerWithMostWater.java)
- [Trapping Rain Water](#trapping-rain-water) - [Java](./TrappingRainWater.java)
- Merge Strings Alternately - [Java](./MergeStringsAlternately.java)

#### Container With Most Water
Given n non-negative integers `a1, a2, ..., an` , where each represents a point at coordinate `(i, ai)`. `n` vertical lines are 
drawn such that the two endpoints of the line `i` is at `(i, ai)` and `(i, 0)`. Find two lines, which, together with the x-axis 
forms a container, such that the container contains the most water.

Notice that you may not slant the container.

Example:
```
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
```

**Solution**
Brute-force solution will be check each combination of the heights, calculate the area and keep updating the maximum area. Time complexity will be `O(n^2)`.

To optimize the solution we can use two important idea

* To have a bigger container which may contain more water depends on two factors:
    - container height is not that long but much more wider, or not that wider but much taller. If we look at the area calculation formula, area = width X height. So area can be bigger either width or height is bigger.
    - Container height is restricted by the lower height between two sides.

We can use two pointer, one at the beginning and another one at the end or the given heights array. Then we move each pointer towoards opposite direction, start towards end and end pointer towards start. We move start pointer to next position if height of current pointer is less than the height of end pointer. Do the same for end pointer.

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(n)* 

[Implementation](./ContainerWithMostWater.java)

#### Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

Example:
```
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Input: height = [4,2,0,3,2,5]
Output: 9
```
**Solution**
To trap water we need to have a `U` shape. If we consider three consecutive bars then, the middle bar has to be
less than left and right bar. And the amount of water trapped by the middle bar is the difference between lowest of 
left, right bar and the middle bar. For example, if the height of three bars are `[3,1,5]`, then trapped water in middle
bar = `min(3,5) - 1 = 2`
Important observation is that, trapped rain water depends on the lowest bar.

We can apply two pointer approach. We keep track of max height from the left and right. If 
left max height is less than right max height then we try to find a bar from left which is 
less than left max height and calculate trapped water. Example: `[4,5,3,2,4,...5,4,7]`. 

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(n)* 

[Implementation](./TrappingRainWater.java)