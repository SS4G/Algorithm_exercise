package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/11.
 */
import java.util.*;
class Leet051x {
    public List<List<String>> solveNQueens(int n) {
        List<List<Integer>> output = new ArrayList<>();
        LinkedList<Integer> placeRecord = new LinkedList<>();
        List<Set<Integer>> bannedSet = new ArrayList<>();
        bannedSet.add(new HashSet<>());
        bannedSet.add(new HashSet<>());
        bannedSet.add(new HashSet<>());
        bannedSet.add(new HashSet<>());
        place(output, placeRecord, 0, n, bannedSet);
        List<List<String>> allRes = new ArrayList<>();
        for (List<Integer> oneRes : output) {
            allRes.add(mapToBoard(oneRes));
        }
        return allRes;
    }

    public List<String> mapToBoard(List<Integer> placeRecord) {
        List<StringBuilder> sblist = new ArrayList<>(placeRecord.size());
        for (int i = 0; i < placeRecord.size(); i++) {
            sblist.add(new StringBuilder());
        }

        for (int x = 0; x < placeRecord.size(); x++) {
            int y = placeRecord.get(x);
            for (int j = 0; j < placeRecord.size(); j++) {
                sblist.get(j).append(j != y ? '.' : 'Q');
            }
        }

        List<String> list = new ArrayList<>();
        for (StringBuilder sb : sblist) {
            list.add(sb.toString());
        }
        return list;
    }

    private void place(List<List<Integer>> output, LinkedList<Integer> placeRecord, int x, int n, List<Set<Integer>> bannedSet) {
        if (x == n) {
            output.add(new ArrayList<>(placeRecord));
        }
        else {
            for (int i = 0; i < n; i++) {
                if (valid(bannedSet, x, i)) {
                    addToSet(bannedSet, x, i);
                    placeRecord.addLast(i);
                    place(output, placeRecord, x + 1, n, bannedSet);
                    placeRecord.removeLast();
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

public class Leet051x_t {
    public static void main(String[] args) {
        Leet051x leet = new Leet051x();
        //System.out.println(leet.mapToBoard(Arrays.asList(new Integer[]{0, 1, 2, 1})));
        System.out.println(leet.solveNQueens(4));
    }
}
