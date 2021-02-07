# Solution

#### Sum of Two Numbers
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example: 
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1].

Input: nums = [11,15,7,-6,1], target = 9
Output: [1,3]
Output: Because nums[1] + nums[3] == 9, we return [1, 3].
```

**Solution**
Brute-force solution will be compare every two numbers in the given array and check their 
sum with target. We will need two loops to get the combinations with every two numbers which
will cause *O(n^2)* time complexity and *O(1)* space complexity.

In the above solution we are evaluating each number twice, first pick one number and then pick 
another from the list. Can we do it with single pass.

Here is the formula for the problem: `num1 + num2 = target`, we can modify a little, `num1 = target - num2`.
For a given array  and target, target is constant in the equation. Target value is not changing. Then 
there are two variables only, `num1` and `num2`. 

The idea is to save every number that is visited so far, also keep their index. Then for every new number 
 we will calculate `target-num2` and check if we have seen the value. If  `num1 + num2 = target` then
 at some point there will be a match. Then we get the index from the previously seen number and 
 use current index as result.

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(n)* 

#### Best Time to Buy and Sell Stock
You are given an array prices where `prices[i]` is the price of a given stock on the *i-th* day.

You want to maximize your profit by choosing a single day to buy one stock and choosing 
a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot 
achieve any profit, return 0.

Example: 
```
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
```
**Solution**:
Maximum profit depends on two things buy in minimum price and sell in maximum price, with condition that
selling price has to be a later date then buying date. 
For single pass linear time solution we need to think of few cases:
* Only minimum buying price won't guarantee maximum profit, there has to be a selling price after that day with more deviation.
* Any prices which  is maximum in the list won't maximize profit, consider like this: `[6,8,1,4,5,7,1]`. `8` is maximum but there  is only
one purchase possible before that which is `6` which doesn't maximize  the profit.

So we can keep track of minimum and maximum profit. We will update the minimum value as soon as 
there is a price less than the current minimum. And, we will calculate profit if  
the difference between current price and minimum price is greater than last maximum profit.

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(1)* 

[Implementation ](./java/com/ds/practice/BuyAndSellStockLE.java)
 
A slightly modified version of the problem: Now you need to return the indices of the days
to buy and sell to have maximum profit.

It will be the same approach as before with little change, we will keep track of 
the day to purchase(minimum  price) temporarily. A temporary purchase day will be 
fixed when we have a profit which is greater than last profit.\
[Implementation ](./java/com/ds/practice/BuyAndSellStockLE.java) 

#### Verifying an Alien Dictionary
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

**Solution**: 
Solution is simple for this problem. We need to parse alphabets and store their position in a hash map. Then use this hashmap
to compare lexicographic ordering.
Another idea is that in a list of words, say `w1, w2, w3 .. wn`, if all the words ordered lexicographically then if we pick any two
consecutive words they will be ordered as well.

[Implementation ](./java/com/ds/practice/VerifyingAnAlienDictionary.java)


#### Maximum subarray sum
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Input: nums = [1]
Output: 1

Input: nums = [0]
Output: 0

Input: nums = [-1]
Output: -1
``` 

**Solution**
Key point to notice: sum of contiguous subarray, that means there will be start and end of elements. 
Summing the whole array won't guarantee maximum sum. So how do we solve this in linear time. 

As the sum will be the result of contiguous subarray, we need to carry over the previous sum.
At each step we need to calculate maximum sum at current position. and keep a record of maximum sum seen so far.

How to determine wheather we should carry over previous sum or start form current element?
If the sum of previous sum and current number is less than current number then we can decide 
to start from current number as previous sum is decreasing the sum. And we keep the max sum in 
a variable which will store max sum seen so far, not the current max sum.

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(1)* 

Implementation: [Java](./java/com/ds/practice/SubArrayMaximumSum.java)  [Python](./python/SubArrayMaximumSum.py)
 
#### Valid Parentheses
Given a string s containing just the characters `(`, `)`, `{`, `}`, `[` and `]`, determine if the input string is valid.

An input string is valid if:

* Open brackets must be closed by the same type of brackets.
* Open brackets must be closed in the correct order.

Example:
```
Input: s = "()"
Output: true

Input: s = "()[]{}"
Output: true

Input: s = "(]"
Output: false

Input: s = "([)]"
Output: false

Input: s = "{[]}"
Output: true
```

**Solution**:
Scan the string by each character. Use a stack to store the opening brackets. Keep
the opening brackets in the stack. Keep pushing in the stack until a closing bracket is read.
We need a hash table to store the opening bracket for each closing bracket.
 
If a closing bracket is read then pop from the stack. For a valid parentheses the popped character 
should be the opening of same time. The idea is the bracket late should close first. At any point if a bracket is 
opened then it must close before any other closing bracket. 

*Time Complexity*: *O(n)*\
*Space Complexity*: *O(n)* 

Implementation: [Java](./java/com/ds/practice/ValidateParentheses.java)

### Merge two sorted linked lists
Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.

Example:
```
Input: l1 = [1, 3, 6, 8], l2 = [2, 3, 7]
Output: [1, 2, 3, 6, 7, 8]
```

**Solution 1**:
We can solve this problem recursively by using the following formula: 
```
if list1[0]<list2[0]:
    list1[0]+merge(list1[1:],list2)
else:
    list2[0]+merge(list1,list2[1:])
```
**Time Complexity:**\
*O( m + n)*, *m* and *n* are number of items in two lists

**Space Complexity:**\
*O(m + n)*. The first call to mergeTwoLists does not return until the ends of both *list1* and *list2* have been reached, so *n + m* stack frames consume *O(n + m)* space.

[Implementation - Java](./java/src/com/ds/practice/MergeSortedLinkedList/MergeSortedList.java)

**Solution 2:**
We can solve this problem by using two pointers, one each at each linked list. 
Loop over until reach end of any list. Consider *l1* be a pointer in one of the linked list and *l2* be a pointer of other linked list.
Both of them are pointing at the head of linked lists. There will be two other pointers, *head*, *previous*. *head* will be initialized 
with -1, then the next node will be the smallest. *previous* pointer will be the previous node of last smallest node.

```
Step 1: Initialize head and previous pointer, point to a node with value -1 
Step 2: compare node at l1 and l2, find the lowest node. 
        a) point previous.next to the lowest node
        b) move pointer with lowest value to next. If l1 had the lowest element then move l1 
            to next and point previous to l1 previous value, the last lowest value
         
Step 3: Repeat until any one of the linked list reaches to the end.
        a) At the end of a linked list the node will be null.
Step 4: Add remaining elements 
        a) If l1 ended then point last lowest nodes next to l2.
        b) If l2 ended then point last lowest nodes next to l1.
```

**Time Complexity:**\
*O( m + n)*, *m* and *n* are number of items in two lists

**Space Complexity:**\
*O(1)*

### Add numbers represented in string
Given two numbers, write a function to calculate the sum of the numbers. Input numbers are in string.

Note:
* numbers contains only digits 0-9.
* numbers does not contain any leading zero.
* must not use any built-in BigInteger library or convert the inputs to integer directly.

Example:
```
num1: 123 
num2: 987
Sum: 1110
```
**Solution**:
This is elementary math. Add to digits at a time from the right and carry over carry to left if there is any. Use a new string to
store the result. 

**Time Complexity:**\
*O(M)*, *M* is maximum lengths of the numbers. 

**Space Complexity:**\
*O(M)*, *M* is maximum lengths of the numbers + 1, This if for result string.

[Implementation - Java](./java/com/ds/practice/AddTwoNumbers.java)


#### Reverse Integer
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

Example: 
```
Input: x = 987
Output: 789

Input: x = -987
Output: -789

Input: x = 1000
Output: 1

Input: x = 2147483647
Output: 0

Input: x = -2147483648
Output: 0
```

**Solution**:
The idea is to get the last digit from the number and place in the first position.

To get the last digit we can use modulas operation. After getting last digit we need
to reset the input, for this we will divide the number by 10. Then we multiply previously 
reversed value by 10 and add last digit.

For example, num1 = 123.\
Iteration 1:\
reversed = 0
last digit = num1 % 10 = 123 % 10 = 3
num1 = num1/10 = 123 / 10 = 12
reversed = reversed * 10 + last digit = 0 * 10 + 3 = 3

Iteration 2:\
last digit = num1 % 10 = 12 % 10 = 2
num1 = num1/10 = 12 / 10 = 1
reversed = reversed * 10 + last digit = 3 * 10 + 2 = 32

Iteration 3:\
last digit = num1 % 10 = 1 % 10 = 1
num1 = num1/10 = 1 / 10 = 0
reversed = reversed * 10 + last digit = 32 * 10 + 1 = 321

We need to check 32-bit integer range before updating reversed value:
```
// positive limit
if reversed > 2^32/10 || reversed == 2^32 && last digit > 7: 
    return 0
// negative limit
if reversed < 2^32-1/10 || reversed == 2^32-1 && last digit < 8: 
    return 0
```

**Time Complexity:**\
*O(log(x))*, There are roughly *log_10(x)* digits in *x*.

**Space Complexity:**\
*O(1)*

[Implementation - Java](./java/com/ds/practice/ReverseInteger.java)


#### Sort logs

You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

* Each word after the identifier will consist only of lowercase letters, or;
* Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.

Example:
```
Input: ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"] 
Output: [let1 art can, let3 art zero, let2 own kit dig, dig1 8 1 5 1, dig2 3 6]

Input: ["t kvr", "r 3 1", "i 403", "7 so", "t 54"] 
Output: [t kvr, 7 so, r 3 1, i 403, t 54]

Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"] 
Output: [g1 act car, a8 act zoo, ab1 off key dog, a1 9 2 3 1, zo4 4 7]

Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"] 
Output: [a2 act car, g1 act car, a8 act zoo, ab1 off key dog, a1 9 2 3 1, zo4 4 7]

```

**Solution**:
We can write a custom comparator and sort the array using the comparator.
We need to sort only the letter-logs, keep the digit logs at the same place.

You may write your own comparator instead of built-in comparators.

[Implementation - Java](./java/com/ds/practice/SortLogs.java)

#### Palindrome Linked List
Given a singly linked list, determine if it is a palindrome.

Example:
```
Input: 1
Output: true

Input: 1->2
Output: false

Input: 1 -> 1 -> 1
Output: true

Input: 1->2->2->1
Output: true

Input: 1 -> 2 -> 3 -> 1
Output: false

Input: 1 -> 3 -> 2 -> 3 -> 1
Output: true
```

**Solution**:
We can find the middle node of linked list. Then, reverse the linked list from the middle to the end.
If a linked list is a palindrome then the linked list from head to middle node or first half and the reversed 2nd half will be same.

*Time complexity : O(n)*, where *n* is the number of nodes in the Linked List.\
*Space complexity : O(1)*.

[Implementation - Java](./java/com/ds/practice/PalindromeLinkedList.java)

#### Design min-stack
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

* push(x) -- Push element x onto stack.
* pop() -- Removes the element on top of the stack.
* top() -- Get the top element.
* getMin() -- Retrieve the minimum element in the stack.

Example: 
```
Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
```
**Solution**
We can use a builtin stack to meet push, pop and top functionality. But we need to modify the stack functionality 
to implement `getMin()`. We can keep track of minimum value seen so far and store 
in the stack.
For example:
```
push(-2);
min = [-2];

push(0);
min = [-2,-2];

push(-3);
min = [-2,-2,-3];

getMin(); // return -3

pop();
min = [-2, -2]

top();    // return 0

getMin(); // return -2
``` 

[Implementation - Java](./java/com/ds/practice/MinStack.java)


#### Subdomain Visit Count
A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.

Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".

We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.

Example:
```
Example 1:
Input: 
["9001 discuss.leetcode.com"]
Output: 
["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
Explanation: 
We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.

Example 2:
Input: 
["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output: 
["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
Explanation: 
We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.

```

**Solution**\
This problem is related to string operation and hash table. We iterate over each *count-paired domain*:
* First split the string by space to separate visit count and domain
* Then split the second portion or pervious result(the domain) by `dot`.
* Now iteratee over the sub-domains achieved after splitting by dot:
    * for each domain store the count in  hash table 
    * update the domain by prepending the sub-domains


*Time complexity : O(n)*\
*Space complexity : O(n)* 

[Implementation - Java](./java/com/ds/practice/SubDomainCount.java)

#### Design HashMap
Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

* put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
* get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
* remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:
```
MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1 
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found) 
```

[Implementation - Java](./java/com/ds/practice/DesignHashMap.java)

#### Find longest common prefix
Given an array of strings, find longest common  prefix. If there is no common prefix, return an empty string "".

Example:
```
Input: strs = ["flower","flow","flight"]
Output: "fl"

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```

**Solution**:\
There are different ways we can solve this problem.

**Solution 1**: As the longest common prefix will be common for all the strings we can first find longest compare prefix for first two string, then find 
longest common prefix of the first result and next word as long as we get a result other than empty string. We can consider the process as horizontal scanning.

```
LCP(S1...Sn) = LCP(LCP(LCP(S1,S2),S3),..Sn)

Example: [good, google, goo, god]
LCP(good, google) = goo
LCP(goo, goo) = goo
LCP(goo, god) = go

Example: [good, google, goo, god, dog]
LCP(good, google) = goo
LCP(goo, goo) = goo
LCP(goo, god) = go
LCP(go, dog) = ""
```

Worse case result will be when you have some common prefix in n-1 strings and the last string doesn't 
have any common prefix, if n is large it will be worse.
**Time Complexity:**\
*O(S)* S is sum of all the characters in all strings.\
**Space Complexity:**\
*O(1)*

**Solution 2**: Consider  a case where there is a very short string at the end of the array. Then according to 
horizontal scanning  it will take more operations. To optimize we my use vertical scanning approach.
We can check characters of all the given string vertically. Like first compare first 
character of all the strings. If first character is common for all then we move to next character at 2nd postion; otherwise
terminate the check and return result. The result will be from beginning to last common character.\
**Time Complexity:**\
*O(S)* S is sum of all the characters in all strings.\
**Space Complexity:**\
*O(1)*

**Solution 3**: We can use divide and conquer approach. Keep split the strings until there are two strings in a 
group. As soon as there are one/two strings, find longest common prefix. Then merge the results by finding 
longest common prefix of those results.

```
LCP(S1...Sn) = LCP(LCP(S1..Sk),LCP(Sk+1...Sn)) , where LCP(S_1 ... S_n) is the 
longest common prefix in set of strings [S_1 .... S_n] ,1 < k < n_1

[good, gold, gone, google, goo, god]

Divide -> [good, gold, gone]       [google, goo, god]
Divide -> [good, gold] [gone]      [google, goo] [god]
LCP           [go]     [gone]          [goo]     [god]
Conquer ->        [go]                       [go]
Conquer ->                     [go]
``` 

**Time Complexity:**\
*O(S)* S is sum of all the characters in all strings.\
**Space Complexity:**\
*O(m lon n)*, There is a memory overhead since we store recursive calls in the execution stack. There are *n log n* recursive calls, each store need mm space to store the result, 
so space complexity is *O(m log n)*

**Solution 4**:
Binary search, The idea is to apply binary search method to find the string with maximum value *L*, which is common prefix 
of all of the strings. The algorithm searches space is the interval (*0....minLen*), where *minLen* is minimum string length 
and the maximum possible common prefix. Each time search space is divided in two equal parts, one of them is discarded, 
because it is sure that it doesn't contain the solution. There are two possible cases:
* `S[1...mid]` is not a common string. This means that for each `j > i` `S[1..j]` is not a common string and we discard the second half of the search space.
* `S[1...mid]` is common string. This means that for each `i < j` `S[1..i]` is a common string and we discard the first half of the search space, 
   because we try to find longer common prefix.

Example:
```
                [factory, factions, fact, factional, facture]
                                    |
                                "factory"
                             /             \
                          fact              ory        middle index = 3
               fact in (factions, fact, 
                factional, facture)
                
                as all of words has prefix "fact", now  expand to right 
                                "factory"
                             /             \
                          fact            o - ry
              "facto" in factions - no,
              "facto" in fact - no,
              "facto" in factional - no,
              "facto" in facture - no
            
             LCP  is fact         
```

[Implementation - Java](./java/com/ds/practice/LongestCommonPrefix.java)

### Validate palindrome
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

**Solution**:
We can use two pointer, one will scan from left to right another from  right to left. We will return false if the
 *n-th* character from the last is not same as *n-th* character from  beginning.

**Time Complexity:**\
*O(n)*
**Space Complexity:**\
*O(1)*

[Implementation - Java](./java/com/ds/practice/ValidPalindrome.java)

#### Fizz Buzz
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for 
the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:
```
n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
```

**Solution**:
Simple solution will be iterate over the range(1 to n) and checking if a number is divisible by 3 and/or 5 which will decide "Fizz", "Buzz", "FizzBuzz" or number.

Consider if there are more mappings: like 
* "Fizz" for multiple of 3
* "Buzz" for multiple of 5
* "Jazz" for multiple of 11
For three mapping there are 8 conditions(all combinations) to check.
```
Divisible by 3
Divisible by 5
Divisible by 11
Divisible by 3 and 5
Divisible by 3 and 11
Divisible by 11 and 3
Divisible by 3 and 5 and 11
Not divisible by 3 or 5 or 11.
```
Instead of writing nested if-else condition we can decouple it using string concatenation.

```
result = ""
if num % 3 == 0:
    result += "Fizz"
if num % 5 == 0:
    result += "Buzz"
if num % 11 == 0:
    result += "Buzz"
```

If the number are conditions are too many then we can make the mapping check dynamic by using hash table, and 
It will be easy to remove/update any mapping with minor code change


**Time Complexity:**\
*O(n)*
**Space Complexity:**\
*O(1)* For hash map approach space complexity will be *O(m)*, *m* is the number of mapping.\
[Implementation - Java](./java/com/ds/practice/FizzBuzz.java)

#### Minimum Moves to Equal Array Elements
Given a non-empty integer array of size *n*, find the minimum number of moves required 
to make all array elements equal, where a move is incrementing *n - 1* elements by *1*.

Example:
```
Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

Input:
[1,2,5]

Output:
5

[1,2,5] => [2,3,5] => [3,4,5] => [4,5,5] => [5,6,5] => [6,6,6]
```
**Solution**
Few key points to solve this problem:
* to make something equal we need to increase the ones which are lower
* Should not increase the maximum number, increasing the maximum number will lead to unresolved situation.

*Brute force solution:*\ 
First we find the maximum number and increment all the numbers except maximum till maximum number. Actually,
we need to increment *n* times such that the minimum number equal to the maximum. After that,  if 
all the numbers are not same then find maximum and minimum again and repeat the process. \
Time complexity: *O(n^2 * k)* and Space Complexity: *O(1)*\

We can optimize previous approach by incrementing in chunks. In previous approach we incremented every number 
by 1 at each iteration. Actually we have to increment the minimum number to maximum number, so we can calculate
difference between minimum and maximum and increment each number by that difference.
Time complexity: *O(n^2)* and Space Complexity: *O(1)*\

We can do further optimization by reducing the computation of searching minimum and maximum 
in the array. We can sort the array to have constant computation to find  minimum and maximum number.
Another idea is that we may not need to update the numbers, we can compute minimum number of moves
using minimum and maximum information.

```
Minimum number of moves = sum of (num[i] - num[min])
```
Lets verify how that will work: Lets take a example `[a0,a1,a2....an]`. Consider `a0= 1` and `an=4`, then 
to make *a0* equal to *an* we need to increment *a0* (*diff0=an-a0*) times. Now, count will be *count = diff0*.
After the moves all the numbers *a1,a2 ... a_n-1* will also increment by *diff0*. New maximum value will be a_n-1. Now if a_n-1 is greater than a0 then 
now it will take diff1 = a_n-1 - a0 increment to make a0 equal to new maximum. Repeat this process.

Time complexity: *O(n log n)*, *O(log n)* for sorting 
and Space Complexity: *O(1)*\

We can do further optimization, sorting was reducing maximum and minimum number finding. We can do one single round 
to find the minimum number and use difference between minimum number and all other number to count minimum moves.

Time complexity: *O(n)*  and Space Complexity: *O(1)*\

[Implementation - Java](./java/com/ds/practice/MinimumMovesToEqualArray.java)

#### Convert a string to palindrome with at most one deletion
Given a non-empty string `s`, you may delete at most one character. Judge whether you can make it a palindrome.

Example:
```
Input: "aba"
Output: True

Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
```

**Solution**:
We can find the middle index and iterate till middle index. At each iteration we will check the character at i-th index from the begining and i-th character form the end.
If they are not maching then we should check two things:
* If we remove i-th character from the begining then will it be a valid palindrome ?
* If we remove i-th character from the end then will it be a valid palindrome ?

If one of the above is true then we conclude that the string is valid palindrome after 
deleting one character; otherwise false.

**Time Complexity:**\
*O(n)*, *n* is the length of the string.
**Space Complexity:**\
*O(1)*
[Implementation - Java](./java/com/ds/practice/ConvertAStringToPalindrome.java)

#### Move zeros
Given an array nums, write a function to move all 0's to the end of it while
 maintaining the relative order of the non-zero elements.
 
Example:
```
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
``` 

**Solution**
We could have just swapped the zeors to last position but it will break relative ordering 
of the non-zero numbers. So we will have a pointer variable refer  to the array index of 
next non-zero element position. Initially it will be set to zero then increments on every swap. 
We will do swap when the current index is pointing to 0.

**Time Complexity:**\
*O(n)*
**Space Complexity:**\
*O(1)*
[Implementation - Java](./java/com/ds/practice/MoveZeros.java)

#### Palindrome Number
Given an integer x, return true if x is palindrome integer.

An integer is a palindrome when it reads the same backward as forward. 

Could you solve it without converting the integer to a string?

For example, 121 is palindrome while 123 is not.
```
Input: x = 121
Output: true

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Input: x = -101
Output: false
```

**Solution**
The simple solution would be convert the number to string and then compare the characters.

We can revert the number and  check if given number is same as reverted number.

**Time Complexity:**\
*O(n)*
**Space Complexity:**\
*O(1)*\
[Implementation - Java](./java/com/ds/practice/PalindromeNumber.java)


#### Construct K Palindrome Strings
Given a string and an integer *k*. You should construct *k* non-empty palindrome strings using all the characters in the string.

Return *True* if you can use all the characters in the string to construct *k* palindrome strings or *False* otherwise.

Example:
```
Input: s = "annabelle", k = 2
Output: true
Explanation: You can construct two palindromes using all characters in s.
Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"

Input: s = "good", k = 4
Output: true
Explanation: The only possible solution is to put each character in a separate string.

Input: s = "yzyzyzyzyzyzyzy", k = 2
Output: true
Explanation: Simply you can put all z's in one string and all y's in the other string. Both strings will be palindrome.
```

**Solution**
Maximum number of palindrome from a  given string will be the length of the string by taking 
each character as single character palindrome. So if *k* > string length then we can't construct 
that many palindrome. 

Now if the *k* < string length it may be possible  to construct *k* palindrome.
Creating palindrome will depend on the number of character count. If all the character
appearance count is even then surely we can construct k palindrome. 
If a string is a palindrome, and if we pick one letter then for each letter there has to be 
a pair so that the same latter will be in same position if we read the string left to right or right to left.
Otherwise the letter is at the middle of the palindrome string.

In a palindrom string there can be only one letter whose character count is odd:
```
aabaa - here b is odd and count is 1
aabbbaa - here b is odd and count is 3
abcdcba -  here d is odd and count is 1
```
So in a given string if there are two letter with odd character count then
to create palindrome we need to split the string in to two palindrome string because
each palindrom string can have at most one letter with odd character count.

So, we will scan the string and keep the letter count in a hash table. Count the number of 
letter with odd character count. If k is less than the number of letters with odd character count
then return false otherwise return true.

**Time Complexity:**\
*O(n)*
**Space Complexity:**\
*O(n)* for hash table \
[Implementation - Java](./java/com/ds/practice/ConstructKPalindromeStrings.java)

#### Text Justification
Given an array of words and a width *maxWidth*, format the text such that each line has exactly *maxWidth* characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces `' '` when necessary 
so that each line has exactly *maxWidth* characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, 
the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:
* A word is defined as a character sequence consisting of non-space characters only.
* Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
* The input array words contains at least one word.


[Implementation - Java](./java/com/ds/practice/TextJustification.java)

#### Rearrange Spaces Between Words
You are given a string text of words that are placed among some number of spaces. 
Each word consists of one or more lowercase English letters and are separated by 
at least one space. It's guaranteed that text contains at least one word.

Rearrange the spaces so that there is an equal number of spaces between every pair 
of adjacent words and that number is maximized. If you cannot redistribute all the 
spaces equally, place the extra spaces at the end, meaning the returned string 
should be the same length as text.

Return the string after rearranging the spaces.

Example: 
```
Input: text = "  this   is  a sentence "
Output: "this   is   a   sentence"
Explanation: There are a total of 9 spaces and 4 words. 
We can evenly divide the 9 spaces between the words: 9 / (4-1) = 3 spaces.

Input: text = " practice   makes   perfect"
Output: "practice   makes   perfect "
Explanation: There are a total of 7 spaces and 3 words. 7 / (3-1) = 3 spaces plus 1 extra space. 
We place this extra space at the end of the string.

Input: text = "hello   world"
Output: "hello   world"

Input: text = "  walks  udp package   into  bar a"
Output: "walks  udp  package  into  bar  a "

Input: text = "a"
Output: "a"
```

[Implementation - Java](./java/com/ds/practice/RearrangeSpacesBetweenWords.java)


#### Reconstruct Itinerary
Given a list of airline tickets represented by pairs of departure and arrival airports
 [from, to], reconstruct the itinerary in order. All of the tickets belong to a man 
 who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
* If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
* All airports are represented by three capital letters (IATA code).
* You may assume all tickets form at least one valid itinerary.
* One must use all the tickets once and only once.

Example:
```
Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
```

[Implementation - Java](./java/com/ds/practice/ReconstructItinerary.java)














