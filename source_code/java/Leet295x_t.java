package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.PriorityQueue;

/**
 * Created by G5501 on 2017/10/15.
 */
class Leet295x {

}

    class MedianFinder {
        PriorityQueue<Integer> smallerHeap;
        PriorityQueue<Integer> greaterHeap;
        int totalSize;
        /** initialize your data structure here. */
        public MedianFinder() {
            smallerHeap = new PriorityQueue<>();
            greaterHeap = new PriorityQueue<>();
            totalSize = 0;
        }

        public void addNum(int num) {
            greaterHeap.offer(num);
            if (greaterHeap.size() - smallerHeap.size() > 1) { // size equal after invoke
                smallerHeap.offer(-greaterHeap.poll());
            }
            else {
                if (smallerHeap.size() > 0 && (-smallerHeap.peek()) > greaterHeap.peek()) {
                    smallerHeap.offer(-greaterHeap.poll());
                    greaterHeap.offer(-smallerHeap.poll());
                }
            }
        }

        public double findMedian() {
            if (greaterHeap.size() == smallerHeap.size())
                return ((double)(greaterHeap.peek()) + (double)(-smallerHeap.peek())) / 2;
            else
                return (double)greaterHeap.peek();
        }
    }

public class Leet295x_t {
    public static void main(String[] args) {
        MedianFinder med = new MedianFinder();
        med.addNum(1);
        assert med.findMedian() - 1D < 0.01;
        med.addNum(5);
        assert med.findMedian() - 3D < 0.01;
        med.addNum(6);
        assert med.findMedian() - 5D < 0.01;
        med.addNum(4);
        assert med.findMedian() - 4.5D < 0.01;
        med.addNum(2);
        assert med.findMedian() - 4D < 0.01;
        med.addNum(1);
        assert med.findMedian() - 3D < 0.01;
    }
}
