## 502. IPO
Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.

You are given several projects. For each project i, it has a pure profit Pi and a minimum capital of Ci is needed to start the corresponding project. Initially, you have W capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.

To sum up, pick a list of at most k distinct projects from given projects to maximize your final capital, and output your final maximized capital.

Example 1:

```
Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
```

Output: 4

Explanation: Since your initial capital is 0, you can only start the project indexed 0.
             After finishing it you will obtain profit 1 and your capital becomes 1.
             With capital 1, you can either start the project indexed 1 or the project indexed 2.
             Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
             Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
Note:
- You may assume all numbers in the input are non-negative integers.
- The length of Profits array and Capital array will not exceed 50,000.
- The answer is guaranteed to fit in a 32-bit signed integer.
- 


#### tips
原则就是 在当前能够做的project里面 选择利润最大的来做就可以

所以 先将所有的项目按照起始资本从小到达排序
然后创建一个优先队列 这个队列中按照 纯利润 从大到小排序 即 利润大的优先级高


每次首先从 可以开始的项目里面来选取一个利润最大的 加上这个利润后 继续计入可以新开始的项目 也就是吧project的添加指针向后移动 每次添加了最大利润的项目 可以使得后续的选择余地更大

#### mycode

```
class ProjectElement {
    public int initCapital;
    public int profit;
    ProjectElement(int cap, int profit) {
        this.profit = profit;
        initCapital = cap;
    }
}

class Leet502x {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        List<ProjectElement> projects = new ArrayList<>();
        for (int i = 0; i < Profits.length; i++) {
            projects.add(new ProjectElement(Capital[i], Profits[i]));
        }

        projects.sort(new Comparator<ProjectElement>() {
          public int compare(ProjectElement e1, ProjectElement e2) {
              return e1.initCapital - e2.initCapital;
          }
        });

        PriorityQueue<ProjectElement> pq = new PriorityQueue<>(new Comparator<ProjectElement>() {
            @Override
            public int compare(ProjectElement o1, ProjectElement o2) {
                return o2.profit - o1.profit; //利润大的排在前面
            }
        });

        int idx = 0;
        while (idx < projects.size() && projects.get(idx).initCapital <= W) {
            pq.offer(projects.get(idx));
            idx++;
        }

        for (int i = 0; i < k; i++) {
            if (pq.size() == 0)
                break;
            W += pq.poll().profit;
            while (idx < projects.size() && projects.get(idx).initCapital <= W) {
                pq.offer(projects.get(idx));
                idx++;
            }
        }
        return W;
    }
}
```
