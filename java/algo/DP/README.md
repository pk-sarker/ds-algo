# Dynamic Programming

- [Number of Good Ways to Split a String](#number-of-good-ways-to-split-a-string) - [Code](./CountStringSplit.java)


#### Number of Good Ways to Split a String
You are given a string `s`, a split is called good if you can split `s` into 2 non-empty strings 
`p` and `q` where its concatenation is equal to `s` and the number of distinct letters in `p` and `q` are the same.

Return the number of good splits you can make in `s`.

Example:
```
Input: s = "aacaba"
Output: 2
Explanation: There are 5 ways to split "aacaba" and 2 of them are good. 
("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.

Input: s = "abcd"
Output: 1
Explanation: Split the string as follows ("ab", "cd").

Input: s = "aaaaa"
Output: 4
Explanation: All possible splits are good.
```

**Solution**
If a string length is `n` then there are `n-1` ways to split the string in to two. The ask is simple, if we 
split the string at position `i` then number of distinct characters from *0* to *i* and *i+1* to end has to be same.

So we can start consider splitting the string from position 0 to n-1, we keep a set to count distinct characters till split position 
and count distinct characters from split position to the end. 

We can so some preprocessing to count the number of distinct characters till i-th position from the left, say `perfix[i]` and i-th position from right, say `suffix[i]`.
We can do this in two single pass, *O(n)*.

Then for each split position *i* we check if `perfix[i] = suffix[i+1]`

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(n)* 

[Implementation](./CountStringSplit.java)






 