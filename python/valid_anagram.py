"""
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false
"""
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False
        hashMapS = {}
        hashMapT = {}

        for i in range(len(s)):
            hashMapS[s[i]] = 1 + hashMapS.get(s[i], 0)
            hashMapT[t[i]] = 1 + hashMapT.get(t[i], 0)

        for c in hashMapS:
            if hashMapS[c] != hashMapT.get(c, 0):
                return False
        return True

if __name__ == "__main__":
    sol = Solution()
    print("s=anagram, t=nagaram")
    res = sol.isAnagram("anagram", "nagaram")
    print(f"Result: {res}")

    print("s=rat, t=car")
    res = sol.isAnagram("rat", "car")
    print(f"Result: {res}")