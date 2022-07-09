# Recursion
- [Wildcard Matching](#wildcard-matching) - [Java](./WildcardMatching.java) [Python](../../../python/wildcard_matching.py)


#### Wildcard Matching
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
- '?' Matches any single character.
- '*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example: 
```text
Input: s = "xx", p = "x"
Output: false
Explanation: "x" does not match the entire string "xx".

Input: s = "xx", p = "*"
Output: true
Explanation: '*' matches any sequence.

Input: s = "xy", p = "?z"
Output: false
Explanation: '?' matches 'x', but the second letter is 'y', which does not match 'z'.

Input: s = "xyzca", p = "?y*a"
Output: true

Input: s = "xyzca", p = "?y*a*"
Output: true
```

**Solution**
Approach 1: Using Recursion and memorization

Lets consider some simple cases first:
- if both the string and pattern is same(`s == p`) then we can conclude its a match and return `true`.  
- it the pattern contains `*` then we can match any character in the string, so if `p == '*'` then return `true`
- if the given string is empty or the pattern is empty then we can't match anything then return `true`.
  - For example: (s='abc', p=''), (s='' and p='a')
- if current character matches, `s[i] == p[i] or p[i] == '?'`, then we can proceed to the next character comparison, `match(s[i+1:], p[i+1:])`
- if the current pattern character is `*`, (p[i]='*'), then there are two options
  1. with * we don't match any character, process to next, `match(s[i:], p[i+1:])`
  2. with * we match a character in the string and proceed to next, `match(s[i+1:], p[i+1:])`
- if p[0] != s[0], return `false`
- if there are more than one consecutive stars then we can shrink that to one star. (p='a****b')


Implementation: [Python](../../../python/wildcard_matching.py) [Java](./WildcardMatching.java)
