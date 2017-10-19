package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.*;

/**
 * Created by G5501 on 2017/10/11.
 */

class Leet052x {
    int cnt = 0;
    public int totalNQueens(int n) {
        cnt = 0;
        List<List<Integer>> output = new ArrayList<>();
        LinkedList<Integer> placeRecord = new LinkedList<>();
        List<Set<Integer>> bannedSet = new ArrayList<>();
        bannedSet.add(new HashSet<>());
        bannedSet.add(new HashSet<>());
        bannedSet.add(new HashSet<>());
        bannedSet.add(new HashSet<>());
        place(output, placeRecord, 0, n, bannedSet);
        return cnt;
    }

    private void place(List<List<Integer>> output, LinkedList<Integer> placeRecord, int x, int n, List<Set<Integer>> bannedSet) {
        if (x == n) {
            //output.add(new ArrayList<>(placeRecord));
            cnt++;
        }
        else {
            for (int i = 0; i < n; i++) {
                if (valid(bannedSet, x, i)) {
                    addToSet(bannedSet, x, i);
                    //placeRecord.addLast(i);
                    place(output, placeRecord, x + 1, n, bannedSet);
                    //placeRecord.removeLast();
                    removeFromSet(bannedSet, x, i);
                }
            }
        }
    }

    //bannedSet 0 --> x
    //bannedSet 1 --> y
    //bannedSet 2 --> x + y
    //bannedSet 3 --> x - y

    private boolean valid(List<Set<Integer>> bannedSet, int x, int y) {
        return !(bannedSet.get(0).contains(x) || bannedSet.get(1).contains(y) || bannedSet.get(2).contains(x + y) || bannedSet.get(3).contains(x - y));
    }

    private void addToSet(List<Set<Integer>> bannedSet, int x, int y) {
        bannedSet.get(0).add(x);
        bannedSet.get(1).add(y);
        bannedSet.get(2).add(x + y);
        bannedSet.get(3).add(x - y);
    }

    private void removeFromSet(List<Set<Integer>> bannedSet, int x, int y) {
        bannedSet.get(0).remove(x);
        bannedSet.get(1).remove(y);
        bannedSet.get(2).remove(x + y);
        bannedSet.get(3).remove(x - y);
    }
}

public class Leet052x_t {
    public static void main(String[] args) {
        Leet052x leet = new Leet052x();
        //System.out.println(leet.mapToBoard(Arrays.asList(new Integer[]{0, 1, 2, 1})));
        System.out.println(leet.totalNQueens(4));
    }
}
