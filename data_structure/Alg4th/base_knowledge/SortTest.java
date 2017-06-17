package AlgorithmTraining.data_structure.Alg4th.base_knowledge;

import java.util.*;
/**
 * Created by BUPT_SS4G on 2017/6/6.
 */
class MySort {
    //Bubble sort
    public static int[] bubbleSort(int[] arr) {
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
        //System.out.println("lo = "+lo+" hi= "+hi);
        if (hi - lo <= 5) {
            insertSort(arr, lo, hi);
            return ;
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
        for (int k = 0; k < hi-lo; k++) { //write back
            arr[lo+k] = copy[k];
        }
    }

    private static int[] mergeArray(int[] arr1, int l1, int h1) {

    }

    //quick sort
    public static int[] quickSort(int[] arr) {
        int[] res = quickSort(arr, 0, arr.length);
        return res;
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
        while (lPtr <= hPtr) {
            while (arr[lPtr] <= key)
                lPtr++;
            while (arr[hPtr] > key)
                hPtr--;
            int tmp = arr[lPtr];
            arr[lPtr] = arr[hPtr];
            arr[hPtr] = tmp;
        }
        int tmp = arr[hPtr];
        arr[hPtr] = arr[lo];
        arr[lo] = tmp;
        quickSort(arr, lo, hPtr);
        quickSort(arr, hPtr + 1, hi);
        return arr;
    }

    //quick sort 3split
    public static int[] quickSort3Split(int[] arr) {
        return quickSort3Split(arr, 0, arr.length);
    }

    public static int[] quickSort3Split(int[] arr, int lo, int hi) {
        int lPtr = lo + 1;
        int hPtr = hi - 1;
        int checkPtr = lo + 1;
        int key = arr[lo];
        int tmp;
        if (hi - lo < 5) {
            insertSort(arr, lo, hi);
        }
        while (checkPtr < hPtr) {
            if (arr[checkPtr] < key) {
                tmp = arr[lPtr];
                arr[lPtr] = arr[checkPtr];
                arr[checkPtr] = tmp;
                checkPtr++;
                lPtr += 1;
            }
            else if (arr[checkPtr] == key){
                checkPtr++;
            }
            else {
                tmp = arr[checkPtr];
                arr[checkPtr] = arr[hPtr];
                arr[hPtr] = tmp;
                hPtr--;
            }
        }
        tmp = arr[lPtr - 1];
        arr[lPtr - 1] = key;
        arr[lo] = tmp;
        quickSort3Split(arr, lo, lPtr - 1);
        quickSort3Split(arr, lPtr, hi);
        return arr;
    }

    //radix sort
    public static int[] radixSort(int[] arr) {
        int[][] bucket0 = new int[10][1000];
        int[][] bucket1 = new int[10][1000];
        int[] lengthRec0 = new int[10];
        int[] lengthRec1 = new int[10];
        //arguement check
        for (int a: arr) {
            assert a >= 0: "invalid input:  args >= 0 is not meet!";
        }
        assert arr.length < 1000: "your input array is too long!";


        boolean bucketFlag = true; // to use bucket0 if true else bucket1
        int max = Integer.MIN_VALUE;
        for (int a: arr) {
            max = Math.max(a, max);
        }
        int currentBit = 0;
        while (max > 0) {
            max /= 10;
            currentBit += 1;
        }
        if (currentBit == 0) {
            return arr;
        }
        else {
            for (int i = 1; i <= currentBit; i++) {
                
                if (i == 0) {
                    for (int j = 0; j < 10; j++)
                        lengthRec0[j] = 0;
                    for (int b: arr) {
                        int whichBucket = b % 10;
                        bucket0[whichBucket][lengthRec0[whichBucket]] = b;
                        lengthRec0[whichBucket]++;
                    }
                    bucketFlag = !bucketFlag;
                }
                else {
                    if (bucketFlag)
                        sortBybit(bucket1, bucket0, lengthRec1, lengthRec0, i);
                    else
                        sortBybit(bucket0, bucket1, lengthRec0, lengthRec1, i);
                    bucketFlag = !bucketFlag;
                }
            }
            int[] result = new int[arr.length];
            int[][] resultBucket;
            int[] resultRec;
            if (bucketFlag) {
                resultBucket = bucket1;
                resultRec = lengthRec1;
            }
            else {
                resultBucket = bucket0;
                resultRec = lengthRec0;
            }
            int k = 0;
            for (int y = 0; y < resultBucket.length; y++) {
                for (int i = 0; i < resultRec[i]; i++) {
                    result[k] = resultBucket[y][i];
                    k++;
                }
            }
            return result;
        }
    }

    private static void sortBybit(int[][] fullBUcket, int[][] emptyBucket, int[] fullRec, int[] emptyRec, int bit) {
        for (int j = 0; j < fullBUcket.length; j++) {
            for (int k = 0; k < fullRec[j]; k++) {
                int bitValue = getBitValue(fullBUcket[j][k], bit);
                emptyBucket[bitValue][emptyRec[bitValue]] = bitValue;
                emptyRec[bitValue]++;
            }
        }
        for (int j = 0; j < fullRec.length; j++) {
            fullRec[j] = 0;
        }
    }
    private static int getBitValue(int num, int bit) {
        assert bit >= 1: "arguement bit must > 1";
        int modVal = (int)Math.pow(10, bit);
        int divVal = (int)Math.pow(10, bit - 1);
        return (num % modVal) / divVal;
    }
}

public class SortTest {
    private static boolean isSorted(int[] arr) {
        //全部按自然序 排序 从小到大
        if (arr.length <= 1)
            return true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1])
                return false;
        }
        return true;
    }
    private static int[][] genTestCase() {
        int[][] testcases = {
            {1, },
            {1, 2},
            {1, 2, 3},
            {1, 1},
            {1, 1, 1},
            {4, 1},
            {4, 3, 1},
            {1, 4, 3, 5, 6, 7, 8, 10, 11, 9, 1, 0, 7, 4, 2, 3, 6, 6, 8, 20, 25 },
        };
        return testcases;
    }
    private static void showArr(int[] arr, String info) {
        System.out.println(info);
        for (int a: arr) {
            System.out.print(a+",");
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        int[][] cases = genTestCase();
        for (int[] casex: cases) {
            int[] res = MySort.mergeSort(casex);
            showArr(res, "");
            assert isSorted(res);
        }


        /*String[] types = {"Bubble", "Insert", "Select", "Shell", "Heap", "Merge", "Quick", "Quick3", "Radix"};
        for (String type: types) {
            int[][] testcases = genTestCase();
            try {
                int[] casex;
                switch (type) {
                    case "Bubble":
                        System.out.println("bubble sorting");
                        for (int[] case0: testcases) {
                            int[] res = MySort.bubbleSort(case0);
                            assert isSorted(res): "WA:";
                        }
                        break;
                    case "Insert":
                        System.out.println("insert sorting");
                        for (int[] case0: testcases) {
                            int[] res = MySort.insertSort(case0);
                            assert isSorted(res): "WA:";
                        }
                        break;
                    case "Select":
                        System.out.println("select sorting");
                        for (int[] case0: testcases) {
                            int[] res = MySort.selectSort(case0);
                            assert isSorted(res): "WA:";
                        }
                        break;
                    case "Shell" :
                        System.out.println("shell sorting");
                        for (int[] case0: testcases) {
                            int[] res = MySort.shellSort(case0);
                            assert isSorted(res): "WA:";
                        }
                        break;
                    case "Heap"  :
                        System.out.println("heap sorting");
                        for (int[] case0: testcases) {
                            int[] res = MySort.heapSort(case0);
                            assert isSorted(res): "WA:";
                        }
                        break;
                    case "Merge" :
                        System.out.println("merge sorting");
                        for (int[] case0: testcases) {
                            int[] res = MySort.mergeSort(case0);
                            assert isSorted(res): "WA:";
                        }
                        break;
                    case "Quick" :
                        System.out.println("quick sorting");
                        for (int[] case0: testcases) {
                            int[] res = MySort.quickSort(case0);
                            assert isSorted(res): "WA:";
                        }
                        break;
                    case "Quick3":
                        System.out.println("quick3 sorting");
                        for (int[] case0: testcases) {
                            int[] res = MySort.quickSort3Split(case0);
                            assert isSorted(res): "WA:";
                        }
                        break;
                    case "Radix" :
                        System.out.println("radix sorting");
                        for (int[] case0: testcases) {
                            int[] res = MySort.radixSort(case0);
                            assert isSorted(res): "WA:";
                        }
                        break;
                    default:System.out.println(""); break;
                }
            }
            catch (AssertionError e) {
                System.out.println("at sort type "+type);
            }
        }*/
    }
}
