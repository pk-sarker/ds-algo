# Two Pointer

- [Container With Most Water](#container-with-most-water) - [Java](./ContainerWithMostWater.java)


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