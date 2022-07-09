"""
Given an input string (s) and a pattern (p), implement wildcard pattern matching
with support for '?' and '*' where:
- '?' Matches any single character.
- '*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.


"""


def is_match(s: str, pattern: str) -> bool:
    def remove_duplicate_stars(p: str) -> str:
        new_pattern = []

        for char in p:
            if not new_pattern or char != '*':
                new_pattern.append(char)
            elif new_pattern[-1] != '*':
                new_pattern.append(char)
        return ''.join(new_pattern)

    def helper(s: str, p: str) -> bool:
        """
        The helper function will match string s and pattern p
        :param p:
        :param s:
        :param pattern:
        :return:
        """
        if (s, p) in dp:
            return dp[(s, p)]

        if s == p or p == '*':
            dp[(s, p)] = True
        elif s == '' or p == '':
            dp[(s, p)] = False
        elif p[0] == '?' or s[0] == p[0]:
            dp[(s, p)] = helper(s[1:], p[1:])
        elif p[0] == '*':
            dp[(s, p)] = helper(s, p[1:]) or helper(s[1:], p)
        else:
            dp[(s, p)] = False

        return dp[(s, p)]

    dp = {}
    p = remove_duplicate_stars(pattern)
    return helper(s, p)

if __name__ == "__main__":
    print("Input: s=aa, p=a? \nOutput: Match ? " + str(is_match("aa", "a?")))
    print("Input: s=aa, p=a \nOutput: Match ? " + str(is_match("aa", "a")))
