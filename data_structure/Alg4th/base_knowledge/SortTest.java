package AlgorithmTraining.data_structure.Alg4th.base_knowledge;

import java.lang.reflect.Array;

/**
 * Created by BUPT_SS4G on 2017/6/6.
 */
class MySort {
    //Bubble sort
    public static int[] BubbleSort(int[] arr) {
        int tmp;
        for (int i = 0; i < arr.length; i++)
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        return arr;
    }

    //Select sort
    public static int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int restmin = Integer.MAX_VALUE;
            int restIndex = -1;
            for (int j = i; j < arr.length; j++) { //get min element
                if (restmin > arr[j]) {
                    restmin = arr[j];
                    restIndex = j;
                }
            }
            arr[restIndex] = arr[i];
            arr[i] = restmin;
        }
        return arr;
    }

    //insert Sort
    public static int[] insertSort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int tmp = arr[i];
            int j = i - 1;
            while (j >= 0 && tmp < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }
            if (j < 0)
                arr[0] = tmp;
            else {
                arr[j + 1] = tmp;
            }
            i++;
        }
        return arr;
    }

    //shell Sort
    public static int[] shellSort(int[] arr) {
        if (arr.length <= 1)
            return arr;
        int step = arr.length >> 1;
        while (step >= 1) {
            for (int offset = 0; offset < step; offset++) {
                stepInsertSort(arr, step, offset);
            }
            step >>= 1;
        }
        return arr;
    }

    private static void stepInsertSort(int[] arr, int step, int offset) {
        assert offset < step: "invalid arguments";
        int i = offset;
        while (i < arr.length) {
            int tmp = arr[i];
            int j = i - step;
            while (j >= 0 && tmp < arr[j]) {
                arr[j+step] = arr[j];
                j -= step;
            }
            if (j < 0)
                arr[offset] = tmp;
            else {
                arr[j + step] = tmp;
            }
            i+=step;
        }
    }

    //Heap Sort
    public static int[] heapSort(int[] arr) {
        heapify(arr);
        int[] res = new int[arr.length];
        for (int j = 0; j < res.length; j++) {
            res[j] = arr[0];
            int newTop = arr[arr.length - 1 - j];
            arr[arr.length - 1 - j] = Integer.MAX_VALUE;
            insertToMinHeap(arr, newTop, 0);
        }
        return res;
    }

    private static int getLeftChild(int father) {
        return (father << 1) + 1;
    }

    private static int getRightChild(int father) {
        return (father << 1) + 2;
    }

    private static int getFather(int child) {
        return child >> 1;
    }

    private static void insertToMinHeap(int[] heap, int val, int start) {
        heap[start] = val;
        int ptr = start;
        int tmpPtr = 0;
        while (true) {
            if (getLeftChild(ptr) < heap.length && getRightChild(ptr) < heap.length)
                tmpPtr = heap[getLeftChild(ptr)] < heap[getLeftChild(ptr)] ? getLeftChild(ptr): getRightChild(ptr);
            else if (getLeftChild(ptr) < heap.length)
                tmpPtr = getLeftChild(ptr);
            else
                return ;
            if (heap[ptr] < heap[tmpPtr]) {
                int tmp = heap[ptr];
                heap[ptr] = heap[tmpPtr];
                heap[tmpPtr] = tmp;
                ptr = tmpPtr;
            }
            else
                return;
        }
    }

    private static void heapify(int[] heap) {
        for (int i = 0; i < heap.length; i++) {
            insertToMinHeap(heap, heap[heap.length - 1 - i],heap.length - 1 - i);
        }
    }

    //littel insert sort
    public static int[] insertSort(int[] arr, int lo, int hi) {
        int i = lo;
        while (i < hi ) {
            int j = i - 1;
            int val = arr[i];
            while (j >= lo && val < arr[j]) {
                arr[j+1] = arr[j];
                j -= 1;
            }
            arr[j+1] = val;
            i += 1;
        }
        return arr;
    }

    //Merge Sort
    public static int[] mergeSort(int[] arr) {
        mergeSortPart(arr, 0, arr.length);
        return arr;
    }

    public static void mergeSortPart(int[] arr, int lo, int hi) {
        // lo include
        // hi exclude
        if (hi - lo <= 5) {
            insertSort(arr, lo, hi);
        }

        int[] copy = new int[hi - lo];
        int mid = (hi + lo) >> 1;
        int k0 = lo, k1 = mid, k2 = 0; // [lo~mid-1] [mid~hi-1]
        mergeSortPart(arr, lo, mid);
        mergeSortPart(arr, mid, hi);

        while (k0 <= mid-1 && k1 <= hi-1) {
            if (arr[k0] < arr[k1]) {
                copy[k2] = arr[k0];
                k0++;
            }
            else {
                copy[k2] = arr[k1];
                k1++;
            }
            k2++;
        }
        while (k0 <= mid - 1) {
            copy[k2] = arr[k0];
            k2++;
            k0++;
        }
        while (k1 <= hi - 1) {
            copy[k2] = arr[k1];
            k1++;
            k1++;
        }
        for (int k = 0; k < hi-lo; k++) {
            arr[lo+k] = copy[k];
        }
    }

    //quick sort
    public static int[] quickSort(int[] arr) {
        ;
        return null;
    }
    public static int[] quickSort(int[] arr, int lo, int hi) {
        //lo include
        //hi exclude
        if (hi - lo <= 5) {
            insertSort(arr, lo, hi);
        }
        int key = arr[lo];
        int lPtr = lo+1;
        int hPtr = hi-1;
        while (lPtr < hPtr) {
            while (arr[lPtr] <= key)
                lPtr++;
            while (arr[hPtr] > key)
                hPtr--;
            int tmp = arr[lPtr];
            arr[lPtr] = arr[hPtr];
            arr[hPtr] = tmp;
        }
        return null;
    }

    //quick sort 3split
    public static int[] quickSort3Split() {
        return null;
    }

    //radix sort

}

public class SortTest {
    private boolean isSorted(int[] arr) {
        //全部按自然序 排序 从小到大
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1])
                return false;
        }
        return true;
    }
}
