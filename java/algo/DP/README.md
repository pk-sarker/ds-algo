# Dynamic Programming

- [Number of Good Ways to Split a String](#number-of-good-ways-to-split-a-string) - [Code](./CountStringSplit.java)
- [Longest String Chain](#longest-string-chain) - [Code](./LongestStringChain.java)
- [Count Square Submatrices with All Ones](#count-square-submatrices-with-all-ones)  - [Code](./CountSquareSubmatricesWithOnes.java)
- [Minimum Cost For Tickets](./MinimumCostForTickets.java)
- [Partition Equal Subset Sum](./PartitionEqualSubsetSum.java)

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

#### Longest String Chain
You are given an array of words where each word consists of lowercase English letters.

`word_A` is a predecessor of `word_B` if and only if we can insert exactly one letter anywhere in `word_A` without changing the 
order of the other characters to make it equal to `word_B`.

For example, `abc` is a predecessor of `abac`, while `cba` is not a predecessor of `bcad`.
A word chain is a sequence of words `[word_1, word_2, ..., word_k]` with `k >= 1`, where `word_1` is a predecessor of `word_2`, `word_2` 
is a predecessor of `word_3`, and so on. A single word is trivially a word chain with `k == 1`.

Return the length of the longest possible word chain with words chosen from the given list of words.

Example:
```
Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
```

**Solution**
Two way we can think of the solution, we take the smallest word and try to add a letter to that
and check if the new word is there in the list. This way we need to try each 26 letters and there will be 
n-1 positions to at the letter, where n is the length of the current word.

Another approach will be based on idea is that we will remove a letter from a word and check 
if the new word exist or not. We will start with the smaller word and then move to the bigger ones because
smaller words will be a substring of the bigger words except a letter. 

So first we sort the array of words in ascending order of the word length. Then for each word
we remove one letter from the word and check if the word exists in memo.

*Time Complexity*: *O(n log n)*\
*Space Complexity*: *O(n)* 

[Implementation](./LongestStringChain.java)

#### Count Square Submatrices with All Ones
Given a `m * n` matrix of ones and zeros, return how many square submatrices have all ones.

Example:
```
Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
``` 

*Time Complexity*: *O(m*n)*\
*Space Complexity*: *O(1)* 

[Implementation](./CountSquareSubmatricesWithOnes.java)