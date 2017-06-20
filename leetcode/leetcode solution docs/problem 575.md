## 575. Distribute Candies Add to List

Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister. Return the maximum number of kinds of candies the sister could gain.


```
Example 1:
Input: candies = [1,1,2,2,3,3]
Output: 3
```

Explanation:
There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too. 
The sister has three different kinds of candies. 

```
Example 2:
Input: candies = [1,1,2,3]
Output: 2
```

Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1]. 
The sister has two different kinds of candies, the brother has only one kind of candies. 
Note:

The length of the given array is in range [2, 10,000], and will be even.
The number in given array is in range [-100,000, 100,000].

#### tips
分为糖果种类小于 length/2 和 大于的情况讨论
前者 的结果是糖果种类 所以最大只能是这个值 后者是 length/2 毕竟妹妹就算每种一个也只能有length种

#### mycode

```
class Solution(object):
    def distributeCandies(self, candies):
        """
        :type candies: List[int]
        :rtype: int
        """
        kinds = len(set(candies))
        length = len(candies)
        return kinds if kinds < (length >> 1) else length >> 1
```
