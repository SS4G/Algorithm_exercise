package AlgorithmTraining.exercise.leetcode2nd;

/**
 * Created by 903 on 2017/7/21.

 */
class Leet011 {
    public int maxArea(int[] height) {
        int maxVal = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            maxVal = Math.max(area, maxVal);
            if (height[left] > height[right])
                right --;
            else
                left ++;
        }
        return maxVal;
    }
}

public class Leet011_t {
    public static void main(String[] args) {
        Leet011 leet = new Leet011();
        int[] height = {1,1, 3, 3, 3};
        System.out.println(leet.maxArea(height));
    }
}
