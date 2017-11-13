package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/16.
 */
import AlgorithmTraining.G55Utils.Java.ArrayUtil;

import java.util.*;
class Leet547x {
    public int findCircleNum(int[][] M) {
        if (M.length == 0 || M[0].length == 0)
            return 0;
        assert M.length == M[0].length;
        int friendCnt = M.length;
        Integer[] peoplerlation = new Integer[friendCnt];
        for (int i = 0; i < friendCnt; i++)
            peoplerlation[i] = i;
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if (M[i][j] == 1) {
                    //System.out.println(i + ":" + j);
                    int root0 = getRoot(peoplerlation, i);
                    int root1 = getRoot(peoplerlation, j);
                    peoplerlation[root0] = peoplerlation[root1];
                }
            }
        }
        Integer[] finalCircle = new Integer[friendCnt];
        for (int i = 0; i < friendCnt; i++) {
            finalCircle[i] = getRoot(peoplerlation, i);
        }
        //ArrayUtil.showArr(peoplerlation);
        Set<Integer> relationSet = new HashSet(Arrays.asList(finalCircle));
        return relationSet.size();
    }

    public int getRoot(Integer[] relation, int curIdx) {
        while (relation[curIdx] != curIdx) {
            curIdx = relation[curIdx];
        }
        return curIdx;
    }
}

public class Leet547x_t {
    public static void main(String[] args) {
        int[][] M =  {{1,1,0},
                                {1,1,1},
                            {0,1,1}};
        M = new int[][] {{1,1,0},
                {1,1,0},
                {0,0,1}};
        Leet547x leet = new Leet547x();
        assert leet.findCircleNum(M) == 2;
    }
}
