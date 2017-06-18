## 581. Shortest Unsorted Continuous Subarray Add to List

Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.


```
Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
```

Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.

#### tips
首先我们得找到逆序 也就是前一个数字比后一个数字大的情况 然后找出逆序对应的最大 与最小
这一点画个图就明白了
![img](http://note.youdao.com/yws/public/resource/21705fd168ffa8ff84a653895d1701c9/xmlnote/0ca613a3fc01ee9998b8b590fce924f5/15991)
#### mycode

```Java
//beats 87%
class Leet581{
    public int findUnsortedSubarray(int[] nums) {
        int pre = Integer.MIN_VALUE;
        int bottomSub = Integer.MAX_VALUE;
        int topSub = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (pre > nums[i]) {
                if (nums[i] < bottomSub) {
                    bottomSub = nums[i];
                }
                if (pre > topSub) {
                   topSub = pre;
                }
            }
            pre = nums[i];
        }
        //System.out.println("bottomSub=:"+bottomSub);
        //System.out.println("topSub=:"+topSub);
        int bottomFirstIndex = -1;
        int topFirstIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>=bottomSub) {
                while(nums[i] == bottomSub)
                    i++;
                bottomFirstIndex = i;
                break;
            }
        }
        for (int i = nums.length-1; i >= 0; i--) {
            if (nums[i]<=topSub) {
                while(nums[i] == topSub)
                    i--;
                topFirstIndex = i;
                break;
            }
        }
        //System.out.println("topFirstIndex = "+topFirstIndex);
        //System.out.println("bottomFirstIndex = "+bottomFirstIndex);
        boolean alreadyAscending = (topFirstIndex == -1) && (bottomFirstIndex == -1);
        return alreadyAscending ? 0 : topFirstIndex - bottomFirstIndex + 1;
    }
}
public class Leet581_t{
    public static void main(String[] args) {
        Leet581 s = new Leet581();
        int[][] testcases = {
                {2, 6, 4, 8, 10, 9, 15}, //5
                {2, 4, 4, 4, 6, 4, 8, 10, 9, 10, 10, 15}, //5
                {2}, //0
                {2, 2, 2, 2, 2}, //0
                {5, 4, 3, 2, 1}, //5
                {1, 2, 3, 4, 5},//{0},
        };
        for (int[] testcase : testcases) {
            System.out.println(s.findUnsortedSubarray(testcase));
        }
    }
}
```
