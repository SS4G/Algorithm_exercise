## 477. Total Hamming Distance

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

```
Example:
Input: 4, 14, 2

Output: 6
```


Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.  
Note:
Elements of the given array are in the range of 0 to 10^9
Length of the array will not exceed 10^4.
#### tips
统计每一位上的1的个数记为k
所以每一位上对total hamming distance 产生的贡献是
k*(nums.length-k)
然后把32位上的这个值加起来即可
#### mycode
```Java
public class Solution {
      private int[] bitCnt = new int[32];

    private void calcbit1s(int k) {
        for(int i = 0; i < 32; i++) {
            bitCnt[i] += (k & 0x00000001) != 0 ? 1 : 0;
            k >>>= 1;
        }
    }
    public int totalHammingDistance(int[] nums) {

        int sum = 0;
        for (int i = 0; i < 32; i++) {
            bitCnt[i] = 0;
        }
        for (int k : nums) {
            calcbit1s(k);
        }
        for (int j = 0; j < 32; j++) {
            sum += bitCnt[j] * (nums.length - bitCnt[j]);
        }
        return sum;
    }
}
```
