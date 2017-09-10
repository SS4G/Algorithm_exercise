package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/10.
 */
import java.util.*;
class Solution035 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (k == 0 || k > input.length) {
            return new ArrayList<>();
        }
        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            heap[i] = Integer.MAX_VALUE;
        }
        for (int i : input) {
            if (i < heap[0]) {
                heap[0] = i;
                sink(heap, 0);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i : heap) {
            result.add(i);
        }
        Collections.sort(result);
        return result;
    }

    private void sink(int[] heap, int index) {
        int leftChild = getLeftChild(index);
        int rightChild = getRightChild(index);
        if (leftChild < heap.length && rightChild < heap.length) {
            int maxSonVal = Math.max(heap[leftChild], heap[rightChild]);
            int maxSonIdx = heap[leftChild] > heap[rightChild] ? leftChild: rightChild;
            if (maxSonVal > heap[index]) {
                swap(heap, maxSonIdx, index);
                sink(heap, maxSonIdx);
            }
        }
        else if (leftChild < heap.length) {
            if (heap[index] < heap[leftChild]) {
                swap(heap, index, leftChild);
            }
        }
    }

    private void swap(int[] arr, int xIdx, int yIdx) {
        int tmp;
        tmp = arr[xIdx];
        arr[xIdx] = arr[yIdx];
        arr[yIdx] = tmp;
    }
    private int getFather(int idx) {
        return (idx - 1) >> 1;
    }

    private int getLeftChild(int idx) {
        return (idx << 1) + 1;
    }

    private int getRightChild(int idx) {
        return (idx << 1) + 2;
    }

}

public class No035 {
    public static void main(String[] args) {
        int[] input = {8, 1, 2, 3, 6, 5, 4, 7, 9, 10};
        Solution035 s = new Solution035();
        System.out.println(s.GetLeastNumbers_Solution(input, 5));
    }
}
