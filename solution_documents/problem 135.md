## 135. Candy
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

#### tips
这个题目看起来是不难 但是里面有很多的坑 所以难度是hard

这个题目的基本思想就是先找到若干个谷底 是这些的谷底值为1 然后谷底旁边的值是2 然后比2高的是3

比如 

```
-    rating 对应 {2, 1, 3, 4, 5, 3, 2, 1, 3, 7}
那么  对应的 candy{2, 1, 2, 3, 4, 3, 2, 1, 2, 3}
```

但是 连续的 如 2, 3, 3, 3, 3, 2 中间的三个3都是谷底
而 2, 3, 2 与 2, 3, 3, 2, 1 中 的3虽然都是峰顶 但是 计算方法却不一样 前者为两侧最大值加一 后者为 较小的那一侧加一 所以这些边界情况都要考虑


#### mycode
总体的代码思路是从左侧的一个峰顶开始 先找到一个谷底 然后在找到·右侧的峰顶 然后从中间向两边进行处理 然后最后再处理左峰顶 右峰顶因为信息不全放在下一轮处理

为了减少代码复杂度简化边界上两个点的处理 给rating两侧放上两个正无穷的值 这样保证真正的ranting两侧的值和其他的值相比没有什么特殊性

```
public int candy(int[] ratings) {

        if (ratings.length == 0) {
            return 0;
        }
        else if (ratings.length == 1) {
            return 1;
        }
        else if (ratings.length == 2) {
            return ratings[0] != ratings[1] ? 3 : 2;
        }
        else {
            int[] newRatings = new int[ratings.length + 2];
            System.arraycopy(ratings, 0, newRatings, 1, ratings.length);
            newRatings[0] = Integer.MAX_VALUE;
            newRatings[newRatings.length - 1] = Integer.MAX_VALUE;
            int[] candyForChild = new int[ratings.length + 2];
            ratings = newRatings;
            for (int i = 0; i < candyForChild.length; i++)
                candyForChild[i] = 0;

            int peekCheckIdx = 0;
            int bottCheckIdx = 1;
            int leftPeek = 0, rightPeek = -1;
            //System.out.println(ratings.length);
            while (rightPeek < ratings.length - 1) {
                //System.out.println("rightPeek = " + rightPeek);
                bottCheckIdx = leftPeek + 1;
                while (!isBottom(ratings, bottCheckIdx)) {
                    bottCheckIdx++;
                }
                int leftBott = bottCheckIdx;
                //
                while (bottCheckIdx < ratings.length - 1 && isBottom(ratings, bottCheckIdx)) {
                    //System.out.println(bottCheckIdx);
                    candyForChild[bottCheckIdx] = 1;
                    bottCheckIdx++;
                }
                //ArrayUtil.showArr(candyForChild);
                int rightBott = bottCheckIdx - 1;
                //
                //candyForChild[bottCheckIdx - 1] = 1;
                peekCheckIdx = bottCheckIdx;
                while (peekCheckIdx < ratings.length - 1 && !isPeek(ratings, peekCheckIdx)) {
                    candyForChild[peekCheckIdx] = candyForChild[peekCheckIdx - 1] + 1;
                    peekCheckIdx++;
                }
                rightPeek = peekCheckIdx;
                int back = leftBott - 1;
                while (back != leftPeek) {
                    candyForChild[back] = candyForChild[back + 1] + 1;
                    back--;
                }
                //ArrayUtil.showArr(candyForChild);
                candyForChild[leftPeek] = leftPeek == 0 ? candyForChild[leftPeek + 1] + 1 : getPeekVal(ratings, leftPeek, candyForChild);
                //System.out.println("bot=" + bottCheckIdx + " leftP=" + leftPeek + " rightP=" + rightPeek);
                leftPeek = rightPeek;
            }
            ArrayUtil.showArr(candyForChild);
            return sum(candyForChild, 1, candyForChild.length - 1);
        }
    }

    public int getPeekVal(int[] rataing, int idx, int[] candyBeside) {
        if (rataing[idx] > rataing[idx - 1] && rataing[idx] > rataing[idx + 1]) {
            return Math.max(candyBeside[idx - 1], candyBeside[idx + 1]) + 1;
        }
        else {
            return rataing[idx] == rataing[idx - 1] ? candyBeside[idx + 1] + 1 : candyBeside[idx - 1] + 1;
        }
    }

    public int sum(int[] arr, int bg, int ed) {
        int sum = 0;
        for (int i = bg; i < ed; i++) {
            sum += arr[i];
        }
        return sum;
    }

    boolean isPeek(int[] ratings, int idx) {
        assert idx > 0 && idx < ratings.length - 1;
        return ratings[idx] >= ratings[idx - 1] && ratings[idx] >= ratings[idx + 1] && (!(ratings[idx] == ratings[idx - 1] && ratings[idx] == ratings[idx + 1]));
    }

    boolean isBottom(int[] ratings, int idx) {
        assert idx > 0 && idx < ratings.length - 1;
        return ratings[idx] <= ratings[idx - 1] && ratings[idx] <= ratings[idx + 1];
    }
```
