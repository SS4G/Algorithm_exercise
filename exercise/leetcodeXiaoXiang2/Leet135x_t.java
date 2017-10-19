package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.Arrays;

/**
 * Created by G5501 on 2017/10/13.
 */
import AlgorithmTraining.G55Utils.Java.*;
class Leet135x {
    public int candy(int[] ratings) {

        if (ratings.length == 0) {
            return 0;
        }
        else if (ratings.length == 1) {
            return 1;
        }
        else if (ratings.length == 2) {
            return ratings[0] != ratings[1] ? 3 : 2;
        }
        else {
            int[] newRatings = new int[ratings.length + 2];
            System.arraycopy(ratings, 0, newRatings, 1, ratings.length);
            newRatings[0] = Integer.MAX_VALUE;
            newRatings[newRatings.length - 1] = Integer.MAX_VALUE;
            int[] candyForChild = new int[ratings.length + 2];
            ratings = newRatings;
            for (int i = 0; i < candyForChild.length; i++)
                candyForChild[i] = 0;

            int peekCheckIdx = 0;
            int bottCheckIdx = 1;
            int leftPeek = 0, rightPeek = -1;
            //System.out.println(ratings.length);
            while (rightPeek < ratings.length - 1) {
                //System.out.println("rightPeek = " + rightPeek);
                bottCheckIdx = leftPeek + 1;
                while (!isBottom(ratings, bottCheckIdx)) {
                    bottCheckIdx++;
                }
                int leftBott = bottCheckIdx;
                //
                while (bottCheckIdx < ratings.length - 1 && isBottom(ratings, bottCheckIdx)) {
                    //System.out.println(bottCheckIdx);
                    candyForChild[bottCheckIdx] = 1;
                    bottCheckIdx++;
                }
                //ArrayUtil.showArr(candyForChild);
                int rightBott = bottCheckIdx - 1;
                //
                //candyForChild[bottCheckIdx - 1] = 1;
                peekCheckIdx = bottCheckIdx;
                while (peekCheckIdx < ratings.length - 1 && !isPeek(ratings, peekCheckIdx)) {
                    candyForChild[peekCheckIdx] = candyForChild[peekCheckIdx - 1] + 1;
                    peekCheckIdx++;
                }
                rightPeek = peekCheckIdx;
                int back = leftBott - 1;
                while (back != leftPeek) {
                    candyForChild[back] = candyForChild[back + 1] + 1;
                    back--;
                }
                //ArrayUtil.showArr(candyForChild);
                candyForChild[leftPeek] = leftPeek == 0 ? candyForChild[leftPeek + 1] + 1 : getPeekVal(ratings, leftPeek, candyForChild);
                //System.out.println("bot=" + bottCheckIdx + " leftP=" + leftPeek + " rightP=" + rightPeek);
                leftPeek = rightPeek;
            }
            ArrayUtil.showArr(candyForChild);
            return sum(candyForChild, 1, candyForChild.length - 1);
        }
    }

    public int getPeekVal(int[] rataing, int idx, int[] candyBeside) {
        if (rataing[idx] > rataing[idx - 1] && rataing[idx] > rataing[idx + 1]) {
            return Math.max(candyBeside[idx - 1], candyBeside[idx + 1]) + 1;
        }
        else {
            return rataing[idx] == rataing[idx - 1] ? candyBeside[idx + 1] + 1 : candyBeside[idx - 1] + 1;
        }
    }

    public int sum(int[] arr, int bg, int ed) {
        int sum = 0;
        for (int i = bg; i < ed; i++) {
            sum += arr[i];
        }
        return sum;
    }

    boolean isPeek(int[] ratings, int idx) {
        assert idx > 0 && idx < ratings.length - 1;
        return ratings[idx] >= ratings[idx - 1] && ratings[idx] >= ratings[idx + 1] && (!(ratings[idx] == ratings[idx - 1] && ratings[idx] == ratings[idx + 1]));
    }

    boolean isBottom(int[] ratings, int idx) {
        assert idx > 0 && idx < ratings.length - 1;
        return ratings[idx] <= ratings[idx - 1] && ratings[idx] <= ratings[idx + 1];
    }
}

public class Leet135x_t {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 3, 2, 1, 2, 3, 4, 5, 4};
        arr = new int[]{6, 5, 5, 5, 5, 6, 4, 4, 4, 6};
        //int[] a = {2, 3, 4, 3, 2, 1, 2, 3, 4, 5, 4, 3, 2, 6, 6, 6, 6, 7, 8, 9, 1};
        int[] a = {58,21,72,77,48,9,38,71,68,77,82,47,25,94,89,54,26,54,54,99,64,71,76,63,81,82,60,64,29,51,87,87,72,12,16,20,21,54,43,41,83,77,41,61,72,82,15,50,36,69,49,53,92,77,16,73,12,28,37,41,79,25,80,3,37,48,23,10,55,19,51,38,96,92,99,68,75,14,18,63,35,19,68,28,49,36,53,61,64,91,2,43,68,34,46,57,82,22,67,89};
        //,         2, 1, 2, 3, 2,1, 2, 3, 1,2,3,2,1,4,3,2,1,2,1,2,1,2,3,1,2,3,1,2,1,2,4,3,2,1,2,3,4,5,2,1,3,2,1,2,3,4,1,2,1,2,1,2,3,2,1,2,1,2,3,4,5,1,2,1,2,3,2,1,2,1,2,1,2,1,2,1,2,1,2,3,2,1,2,1,2,1,2,3,4,5,1,2,3,1,2,3,4,1,2,3,
        //2, 3, 4, 3, 2, 1, 2, 3, 4, 5, 4, 3, 2, 6, 6, 6, 6, 7, 8, 9, 1
        //1  2  4  3  2  1  2  3  4  5  3  2  1  2  1  1  1  2  3  4  1
        //1, 2, 4, 3, 2, 1, 2, 3, 4, 5, 3, 2, 1, 2, 3, 4, 5, 6, 7, 8, 1,
        Leet135x leet = new Leet135x();
        int[] s = new int[]{1, 2, 4, 3, 2, 1, 2, 3, 4, 5, 3, 2, 1, 2, 1, 1, 1, 2, 3, 4, 1};
        System.out.println(leet.sum(s, 0, s.length));
        System.out.println(leet.candy(a));
        //assert leet.candy(arr) == 13;
    }
}
