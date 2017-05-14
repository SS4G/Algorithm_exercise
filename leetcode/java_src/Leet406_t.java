package AlgorithmTraining.leetcode.java_src;

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
                System.out.println(insertIndex);
                if (!(insertPeople.satisfied > insertPeople.demand
                    || (insertPeople.height >= queue[insertIndex].height
                            && queue[insertIndex].satisfied < queue[insertIndex].demand)))
                        break;

                queue[insertIndex+1] = queue[insertIndex];
                if (queue[insertIndex+1].height < insertPeople.height) {
                    queue[insertIndex+1].satisfied++;
                    lastModify = insertIndex+1;
                }
                else {
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
    private void showQueue(People[] queue) {
        for (People a : queue) {
            System.out.print("height = "+a.height+ " ");
        }
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
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] res = s.reconstructQueue(people);
        for (int[] a : res) {
            for (int b: a) {
                System.out.print(b+",");
            }
            System.out.println("");
        }
    }
}
