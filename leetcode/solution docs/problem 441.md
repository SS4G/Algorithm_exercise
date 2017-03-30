## 441. Arranging Coins   
You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

```
Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
```
#### mycode
虽然可以使用累加的方法来求解　但是在ｎ特别大的时候会很慢
所以最好使用二次函数的求根公式来求

需要注意的是浮点数的精度问题　比如求出的结果是3.9999999那么其实
很可能是４．０
(level^2+level)/2=n  注意不应该是等号　因为ｎ不是完整的等差数列的和

```Pyhon
class Solution(object):
    def arrangeCoins(self, n):
        """
        :type n: int
        :rtype: int
        """
        root=(-1+(1+8*n)**0.5)/2
        root_int=int(root)
        if abs(root-(root_int+1))<0.00000001:
            return root_int+1
        else:
            return root_int
```
