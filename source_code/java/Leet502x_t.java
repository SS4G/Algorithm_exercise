package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.*;


/**
 * Created by BUPT_SS4G on 2017/10/19.
 *
 */
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
            @Override
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

public class Leet502x_t {
    public static void main(String[] args) {
        Leet502x leet = new Leet502x();
        int[] cap = {0,1,1};
        int[] pro = {1,2,3};
        assert leet.findMaximizedCapital(2, 0, pro, cap) == 4;
        cap = new int[]{0,1,1,3,4};
        pro = new int[]{1,2,3,6,10};
        assert leet.findMaximizedCapital(3, 0, pro, cap) == 14;
    }
}
