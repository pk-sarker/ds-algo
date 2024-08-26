"""
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
"""
import collections
from typing import List


class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        """
        Complexity: O (m.n), n is the number of strings,
        and m is the lengths of each string.
        """
        result = collections.defaultdict(list)

        for s in strs:
            count = [0]*26
            for i in range(len(s)):
                count[ord(s[i])-ord("a")] += 1
            result[tuple(count)].append(s)

        return result.values()

    def groupAnagramsV2(self, strs: List[str]) -> List[List[str]]:
        """
        Complexity: O(n log n)
        """
        result = collections.defaultdict(list)
        for s in strs:
            key = sorted(s)  # O(n log n)
            key = ''.join(key)
            result[key].append(s)

        return result.values()


if __name__ == "__main__":
    sol = Solution()
    print('s=["eat","tea","tan","ate","nat","bat"]')
    res = sol.groupAnagrams(["eat","tea","tan","ate","nat","bat"])
    print(f"Result: {res}")
    res = sol.groupAnagramsV2(["eat", "tea", "tan", "ate", "nat", "bat"])
    print(f"Result: {res}")

    print('s=["a"]')
    res = sol.groupAnagrams(["a"])
    print(f"Result: {res}")
    res = sol.groupAnagramsV2(["a"])
    print(f"Result: {res}")

