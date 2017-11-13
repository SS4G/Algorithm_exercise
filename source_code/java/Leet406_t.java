package AlgorithmTraining.exercise.leetcode.java_src;

/**
 *
 * Created by Administrator on 2017/5/13.
 */
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
            //showQueue(queue);
            //System.out.println("now = "+queueNow);
            People insertPeople = queue[queueNow];
            lastModify = queueNow + 1;
            while (true) {
                //System.out.println(insertIndex);
                //showQueue(queue);
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
    /*private void showQueue(People[] queue) {
        for (People a : queue) {
            System.out.println("height = "+a.height+ " demand = "+a.demand+" satisfied = "+a.satisfied);
        }
        System.out.println("-------------------------------");
    }*/
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
