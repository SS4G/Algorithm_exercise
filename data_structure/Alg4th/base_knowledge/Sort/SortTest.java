package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Sort;

/**
 *
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
        //相当于一个选择复杂度为log(n)的选择排序 每次将堆顶的元素即最小的元素取出 然后随便将一个未排序的元素放在堆顶
        //让堆重新恢复有序状态 堆顶的元素仍然是剩余元素中最小的
        heapify(arr);
        int[] res = new int[arr.length];
        for (int j = 0; j < res.length; j++) {
            res[j] = arr[0];
            insertToMinHeap(arr, Integer.MAX_VALUE, 0); //想象成 重的元素沉下去 轻的元素浮上来
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
        //将顶部元素删除 用要插入的值替代
        heap[start] = val;
        int ptr = start;
        int tmpPtr;
        int tmp;
        while (true) {
            int lPtr = getLeftChild(ptr);
            int rPtr = getRightChild(ptr);
            int minPtr;
            if (lPtr < heap.length && rPtr < heap.length) {  //左右孩子均存在
                if (heap[ptr] > heap[lPtr] || heap[ptr] > heap[rPtr]) {
                    if (heap[ptr] > heap[lPtr] && heap[ptr] > heap[rPtr]) { //小于左右孩子
                        minPtr = heap[lPtr] < heap[rPtr]? lPtr: rPtr;
                    }
                    else { //小于左右孩子之一
                        if (heap[ptr] > heap[lPtr])
                            minPtr = lPtr;
                        else
                            minPtr = rPtr;
                    }
                }
                else //当前的节点最小 交换指针是他本身
                    return ;
            }
            else if (lPtr < heap.length) { //只存在左孩子
                if (heap[ptr] > heap[lPtr])
                    minPtr = lPtr;
                else
                    return ;
            }
            else //没有孩子
                return ;
            tmp = heap[ptr];
            heap[ptr] = heap[minPtr];
            heap[minPtr] = tmp;
            ptr = minPtr;
        }
    }

    private static void heapify(int[] heap) {
        int[] copy = new int[heap.length];
        for (int i = 0; i < heap.length; i++) {
            copy[i] = heap[i];
        }
        for (int i = 0; i < heap.length; i++) {
            heap[i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < heap.length; i++) {
            insertToMinHeap(heap, copy[i], 0);
        }
        //showArr(heap, "heap");
    }

    //littel insert sort
    private static int[] insertSort(int[] arr, int lo, int hi) {
        int i = lo;
        while (i < hi) {
            int j = i - 1;
            int val = arr[i];
            while (j >= lo && val < arr[j]) {
                arr[j + 1] = arr[j];
                j -= 1;
            }
            arr[j + 1] = val;
            i += 1;
        }
        return arr;
    }

    //Merge Sort
    public static int[] mergeSort(int[] arr) {
        mergeSortPart(arr, 0, arr.length);
        return arr;
    }

    private static void mergeSortPart(int[] arr, int lo, int hi) {
        // lo include
        // hi exclude
        if (hi - lo <= 2) { // 在区间较小是使用插入排序
            insertSort(arr, lo, hi);
            return ;
        }
        int mid = (hi + lo) >> 1;
        mergeSortPart(arr, lo, mid);
        mergeSortPart(arr, mid, hi);
        mergeArray(arr, lo, mid, mid, hi);
    }

    private static void mergeArray(int[] arr0, int l0, int h0, int l1, int h1) {
        int[] copy = new int[h0 - l0 + h1 - l1];
        int k0 = l0;
        int k1 = l1;
        int k2 = 0;
        while (k0 < h0 && k1 < h1) {
            if (arr0[k0] < arr0[k1]) {
                copy[k2] = arr0[k0];
                k0++;
            }
            else {
                copy[k2] = arr0[k1];
                k1++;
            }
            k2++;
        }
        while (k0 < h0) {
            copy[k2] = arr0[k0];
            k2++;
            k0++;
        }
        while (k1 < h1) {
            copy[k2] = arr0[k1];
            k2++;
            k1++;
        }
        for (int k = 0; k < copy.length; k++) { //write back
            arr0[l0+k] = copy[k];
        }
    }

    //quick sort
    public static int[] quickSort(int[] arr) {
        int[] res = quickSort(arr, 0, arr.length);
        return res;
    }

    private static int[] quickSort(int[] arr, int lo, int hi) {
        //lo include
        //hi exclude
        if (hi - lo <= 3) {
            insertSort(arr, lo, hi);
            return arr;
        }

        int key = arr[lo];
        int lPtr = lo + 1 ;
        int hPtr = hi - 1;
        int tmp;
        while (lPtr <= hPtr) {
            while (lPtr < hi && arr[lPtr] <= key)
                lPtr++;
            while (hPtr > 0 && arr[hPtr] > key)
                hPtr--;
            if (lPtr > hPtr)
                break;
            tmp = arr[lPtr];
            arr[lPtr] = arr[hPtr];
            arr[hPtr] = tmp;
        }

        tmp = arr[hPtr];
        arr[hPtr] = arr[lo];
        arr[lo] = tmp;
        //showArr(arr, lo, hi, "key="+key);
        quickSort(arr, lo, hPtr);
        quickSort(arr, hPtr + 1, hi);
        return arr;
    }

    //quick sort 3split
    public static int[] quickSort3Split(int[] arr) {
        return quickSort3Split(arr, 0, arr.length);
    }

    private static int[] quickSort3Split(int[] arr, int lo, int hi) {
        int lPtr = lo;
        int hPtr = hi - 1;
        int checkPtr = lo + 1;
        int tmp;
        if (hi - lo <= 3) {
            insertSort(arr, lo, hi);
            return arr;
        }
        int key = arr[lo];
        while (checkPtr <= hPtr) {
            if (arr[checkPtr] < key) {
                tmp = arr[lPtr];
                arr[lPtr] = arr[checkPtr];
                arr[checkPtr] = tmp;
                checkPtr++;
                lPtr++;
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
        arr[hPtr] = key;
        quickSort3Split(arr, lo, hPtr);
        quickSort3Split(arr, hPtr + 1, hi);
        return arr;
    }

    //radix sort
    public static int[] radixSort(int[] arr) {
        int[][] bucket0 = new int[10][1000];
        int[][] bucket1 = new int[10][1000];
        int[] lengthRec0 = new int[10];
        int[] lengthRec1 = new int[10];
        //argument check
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
                if (i == 1) {
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
                    if (bucketFlag) {
                        sortBybit(bucket1, bucket0, lengthRec1, lengthRec0, i);
                    }
                    else {
                        sortBybit(bucket0, bucket1, lengthRec0, lengthRec1, i);
                    }
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

            for (int y = 0; y < 10; y++) {
                for (int i = 0; i < resultRec[y]; i++) {
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
                emptyBucket[bitValue][emptyRec[bitValue]] = fullBUcket[j][k];
                emptyRec[bitValue]++;
            }
        }
        for (int j = 0; j < fullRec.length; j++) {
            fullRec[j] = 0; // clear rec
        }
    }

    private static int getBitValue(int num, int bit) {
        assert bit >= 1: "arguement bit must > 1";
        int modVal = (int)Math.pow(10, bit);
        int divVal = (int)Math.pow(10, bit - 1);
        return (num % modVal) / divVal;
    }

    private static void showBucket(int[][] bucket, int[] bucketRec, String info) {
        System.out.println(info);
        for (int i = 0; i < 10; i++) {
            System.out.print("bucket:"+i+" ");
            for (int j = 0; j < bucketRec[i]; j++) {
                System.out.print(bucket[i][j]+",");
            }
            System.out.println("");
        }
    }

    private static void showArr(int[] arr, int lo, int hi, String info) {
        System.out.println(info);
        for (int a = lo; a < hi; a++) {
            System.out.print(arr[a] + ",");
        }
        System.out.println("");
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
            {},
            {1, },
            {1, 2},
            {1, 2, 3},
            {1, 1},
            {1, 1, 1},
            {4, 1},
            {4, 3, 1},
            {1, 4, 9, 10, 7, 2, 1, 2, 3, 4, 2},
            {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
            {1, 1, 3, 3, 5, 6, 7, 8, 7, 6, 9, 5, 4},
            {1, 4, 3, 5, 6, 7, 8, 10, 11, 9, 1, 0, 7, 4, 2, 3, 6, 6, 8, 20, 25 },
            {134, 424, 312, 58, 623, 7, 908, 110, 11, 9, 31, 10, 27, 0, 23, 322, 6221, 623, 843, 2540, 32125},
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
        int[] casex = {134, 424, 312, 58, 623, 7, 908, 110, 11, 9, 31, 10, 27, 0, 23, 322, 6221, 623, 843, 2540, 32125};

        String[] types = {"Bubble", "Insert", "Select", "Shell", "Heap", "Merge", "Quick", "Quick3", "Radix"};

        for (String type: types) {
            int[][] testcases = genTestCase();
            try {
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
        }
    }
}
