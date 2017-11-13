## 295. Find Median from Data Stream
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
```
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5
```


Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2

#### tips 
使用两个堆 一个大顶堆一个小顶堆 大顶堆用来记录较小的一般数字 小顶堆用来记录较大的一半数字 每次插入后注意调整两个堆 大顶堆永远比小顶堆最多大1 如果大于2 就要把大顶堆中的最小者放到小顶堆里面

还要注意的是 当大顶堆比小顶堆大1个元素后
如果此时大顶堆的顶比小顶堆的顶小 那么就要把大顶堆的顶元素放到小顶堆 再把新的小顶堆的顶部元素放到大顶堆


#### mycode

```
class MedianFinder {
        PriorityQueue<Integer> smallerHeap;
        PriorityQueue<Integer> greaterHeap;
        int totalSize;
        /** initialize your data structure here. */
        public MedianFinder() {
            smallerHeap = new PriorityQueue<>();
            greaterHeap = new PriorityQueue<>();
            totalSize = 0;
        }

        public void addNum(int num) {
            greaterHeap.offer(num);
            if (greaterHeap.size() - smallerHeap.size() > 1) { // size equal after invoke
                smallerHeap.offer(-greaterHeap.poll());
            }
            else {
                if (smallerHeap.size() > 0 && (-smallerHeap.peek()) > greaterHeap.peek()) {
                    smallerHeap.offer(-greaterHeap.poll());
                    greaterHeap.offer(-smallerHeap.poll());
                }
            }
        }

        public double findMedian() {
            if (greaterHeap.size() == smallerHeap.size())
                return ((double)(greaterHeap.peek()) + (double)(-smallerHeap.peek())) / 2;
            else
                return (double)greaterHeap.peek();
        }
    }
```
