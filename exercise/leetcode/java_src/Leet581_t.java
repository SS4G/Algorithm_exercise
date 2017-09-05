package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/16.
 */
class Leet581{
    public int findUnsortedSubarray(int[] nums) {
        int pre = Integer.MIN_VALUE;
        int bottomSub = Integer.MAX_VALUE;
        int topSub = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (pre > nums[i]) {
                if (nums[i] < bottomSub) {
                    bottomSub = nums[i];
                }
                if (pre > topSub) {
                   topSub = pre;
                }
            }
            pre = nums[i];
        }
        //System.out.println("bottomSub=:"+bottomSub);
        //System.out.println("topSub=:"+topSub);
        int bottomFirstIndex = -1;
        int topFirstIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>=bottomSub) {
                while(nums[i] == bottomSub)
                    i++;
                bottomFirstIndex = i;
                break;
            }
        }
        for (int i = nums.length-1; i >= 0; i--) {
            if (nums[i]<=topSub) {
                while(nums[i] == topSub)
                    i--;
                topFirstIndex = i;
                break;
            }
        }
        //System.out.println("topFirstIndex = "+topFirstIndex);
        //System.out.println("bottomFirstIndex = "+bottomFirstIndex);
        boolean alreadyAscending = (topFirstIndex == -1) && (bottomFirstIndex == -1);
        return alreadyAscending ? 0 : topFirstIndex - bottomFirstIndex + 1;
    }
}
public class Leet581_t{
    public static void main(String[] args) {
        Leet581 s = new Leet581();
        int[][] testcases = {
                {2, 6, 4, 8, 10, 9, 15}, //5
                {2, 4, 4, 4, 6, 4, 8, 10, 9, 10, 10, 15}, //5
                {2}, //0
                {2, 2, 2, 2, 2}, //0
                {5, 4, 3, 2, 1}, //5
                {1, 2, 3, 4, 5},//{0},
        };
        for (int[] testcase : testcases) {
            System.out.println(s.findUnsortedSubarray(testcase));
        }
    }
}