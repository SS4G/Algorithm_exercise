## 605. Can Place Flowers My SubmissionsBack To Contest

Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:

```
Input: flowerbed = [1,0,0,0,1], n = 1
```
Output: True
Example 2:

```
Input: flowerbed = [1,0,0,0,1], n = 2
```
Output: False
Note:
- The input array won't violate no-adjacent-flowers rule.
- The input array size is in the range of [1, 20000].
- n is a non-negative integer which won't exceed the input array size.

#### tips 
没啥好说的 一朵一朵的往里面插就好 复杂度也就是·个O(n)
或者找 连续三个空位的 就插一朵 反正不要想得太复杂
#### mycode
```
class Solution(object):
    def canPlaceFlowers(self, flowerbed, n):
        """
        :type flowerbed: List[int]
        :type n: int
        :rtype: bool
        """
        i = 0
        if n == 0:
            return True
        elif len(flowerbed) == 1:  # n > 0
            if flowerbed[0] == 0 and n <= 1:
                return True
            else:
                return False

        continusO = 0
        i = 0
        while i < len(flowerbed):
            if i == 0:
                if flowerbed[0] == 0 and flowerbed[1] == 0:
                    n -= 1
                    flowerbed[0] = 1
                    i += 2
                else:
                    i += 1
            elif i == len(flowerbed) - 1:
                if flowerbed[i] == 0 and flowerbed[i - 1] == 0:
                    n -= 1
                    flowerbed[i] = 1
                    i += 2
                else:
                    i += 1
            else:
                if flowerbed[i] == 0 and flowerbed[i-1] == 0 and flowerbed[i+1] == 0:
                    flowerbed[i] = 1
                    n -= 1
                    i += 2
                else:
                    i += 1
        return not (n > 0)
```
