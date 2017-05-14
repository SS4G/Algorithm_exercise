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


    }
    private void swapTwoPeople(People[] queue, int peopleAIndex, int peopleBIndex) {
        // peopleA , peopleB -> peopleB , peopleA
        People tmpPeople = queue[peopleAIndex];
        queue[peopleAIndex] = queue[peopleBIndex];
        queue[peopleBIndex] = tmpPeople;
        if (queue[peopleAIndex].height >= queue[peopleBIndex].height) {
            queue[peopleBIndex].demand += 1;
        }
    }
    private int fixQueue(int[][] queue, int startIndex, int endIndex, int justInserted) {
        //satisfied: the number satisfied for the current people now
        //startIndex: the index to start check (include)
        //endIndex: the index to end check (exclude)
        //justInserted: the people height just inserted

    }
}
public class Leet406_t {
    public static void main(String[] args) {
        Leet406 s = new Leet406();
    }
}
