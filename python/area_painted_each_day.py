from typing import List

"""
There is a long and thin painting that can be represented by a number line. 
You are given a 0-indexed 2D integer array paint of length n, 
where paint[i] = [starti, endi]. This means that on the ith day you need to 
paint the area between starti and endi.

Painting the same area multiple times will create an uneven painting so 
you only want to paint each area of the painting at most once.

Return an integer array worklog of length n, where worklog[i] is the amount 
of new area that you painted on the ith day.

Input: paint = [[1,4],[4,7],[5,8]]
Output: [3,3,1]

Input: paint = [[1,4],[5,8],[4,7]]
Output: [3,3,1]

Input: paint = [[1,5],[2,4]]
Output: [4,0]

"""

class Solution:
    def amount_painted(self, paint: List[List[int]]) -> List[int]:
        line, res = [0] * 1000, [0] * len(paint)
        for i, (start, end) in enumerate(paint):
            while start < end:
                jump = max(start + 1, line[start])
                res[i] += 1 if line[start] == 0 else 0
                line[start] = max(line[start], end)  # compression
                start = jump
        return res



if __name__ == "__main__":
    sol = Solution()
    print(sol.amount_painted([[1,4],[4,7],[5,8]]))