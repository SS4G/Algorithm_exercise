## 406. Queue Reconstruction by Height Add to List

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.


```
Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
```

#### tips
先对序列按照 先高后低的顺序排列。同样高度的 需求值小的在前。
然后使用一种策略进行插入。
插入的策略是：首先=在插入前给每个 people设置一个satisfied域表明当前有多少高度大于等于这个人的人在他前面 ，
然后从头部开始 选取一个要开始插入的人 insertNow 将当前如果这个人的satisfied域大于其本身的demand 就将他按照类似于插入排序法的方法将他前移。直到他的demand 的到满足。这个过程中也要修改对应的其他被移动的人的satisfied域。如果一个被移动的人的satisfied域被改变了， 那么下次的insertNow 就选择最靠前面的那个被改变的元素开始对其进行进行重新向前插入。

正确性证明：
首先使用归纳法 假设前面某一段序列 queue[0:e] 已经全部满足条件。
这时需要插入queue[e] 此时的queue[e]能够向前插入的条件是 queue[e].satisfied > queue[e].demand
将他向前移动 直到 queue[e].satisfied == queue[e].demand 
将insertNow插入到指定位置后，其后必然有比他小的元素 的satisfied>demand 所以可以将后续不满足条件的元素在逐个前移，总能最终保持 queue[0:e+1]的元素全都满足条件
在一开始使得大的元素排在前面会使得所有的元素的 satisfied>=demand 所以在移动过程中不会出现satisfied < demand 的情况。不会出错



#### mycode
```Java
class People {
    public int height;
    public int demand;
    public int satisfied;
    public People(int height, int demand, int satisfied) {
        this.height = height;
        this.demand = demand;
        this.satisfied = satisfied;
    }
}
class Leet406 {
    public int[][] reconstructQueue(int[][] people) {
        People[] queue = new People[people.length];
        for (int i = 0; i < people.length; i++) {
            queue[i] = new People(people[i][0], people[i][1], 0);
        }
        People tmp;
        for (int j = 0; j < people.length; j++) {
            for (int k = j; k < people.length; k++) {
                if (queue[j].height <  queue[k].height || (queue[j].height == queue[k].height && queue[j].demand > queue[k].demand)) {
                    tmp = queue[j];
                    queue[j] = queue[k];
                    queue[k] = tmp;
                }
            }
        }
        int queueNow = 1;
        int insertIndex = 0;
        int lastModify = 0;
        for (int j = 0; j < queue.length; j++)
            queue[j].satisfied = getGreater(queue, j, queue[j].height);

        while (queueNow < people.length) {
            insertIndex = queueNow - 1;
            People insertPeople = queue[queueNow];
            lastModify = queueNow + 1;
            while (true) {

                if (insertIndex < 0) {// insert to the index 0
                    break;
                }

                boolean moreHigherBefore = insertPeople.satisfied > insertPeople.demand;
                boolean lowerButNotSatisfied = (queue[insertIndex].height < insertPeople.height) &&
                        (queue[insertIndex].satisfied < queue[insertIndex].demand);
                if (!(moreHigherBefore || lowerButNotSatisfied))
                    break;
                queue[insertIndex+1] = queue[insertIndex];
                if (queue[insertIndex+1].height < insertPeople.height) {
                    queue[insertIndex+1].satisfied++;
                    lastModify = insertIndex+1;
                }
                else if (queue[insertIndex+1].height == insertPeople.height) {
                    queue[insertIndex+1].satisfied++;
                    insertPeople.satisfied--;
                    lastModify = insertIndex+1;
                }
                else if (queue[insertIndex+1].height > insertPeople.height){
                    insertPeople.satisfied--;
                }
                insertIndex--;
            }
            queue[insertIndex+1] = insertPeople;
            queueNow = lastModify;//ready to move next element
        }
        int[][] res = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
                res[i][0] = queue[i].height;
                res[i][1] = queue[i].demand;
        }
        return res;
    }

    private int getGreater(People[] queue, int endIndex, int thisHeight) {
        //endindex exclude
        int cnt = 0;
        for (int index = 0; index < endIndex; index++) {
            if (queue[index].height >= thisHeight)
                cnt ++;
        }
        return cnt;
    }
}
public class Leet406_t {
    public static void main(String[] args) {
        Leet406 s = new Leet406();
        int[][] people = {  {8,2},
                {4,2},
                {4,5},
                {2,0},
                {7,2},
                {1,4},
                {9,1},
                {3,1},
                {9,0},
                {1,0}};
        //[[1,0],[2,0],[9,0],[3,1],[1,4],[9,1],[4,2],[7,2],[8,2],[4,5]]
        int[][] res = s.reconstructQueue(people);
        for (int[] a : res) {
            for (int b: a) {
                System.out.print(b+",");
            }
            System.out.println("");
        }
    }
}
```
