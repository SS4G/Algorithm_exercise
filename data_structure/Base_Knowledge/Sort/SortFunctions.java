package AlgorithmTraining.data_structure.Base_Knowledge.Sort;

/**
 *
 * Created by BUPT_SS4G on 17-4-7.
 */
class SortTemplate {
    // hi 为数组的上边界（不包含）
    // lo 为数组的下边界（包含）
    protected static boolean lessThan(Comparable a, Comparable b){
        int cmp = a.compareTo(b);
        return cmp < 0;
    }

    protected static boolean greaterThan(Comparable a, Comparable b) {
        int cmp = a.compareTo(b);
        return cmp > 0;
    }

    protected static boolean equalWith(Comparable a, Comparable b) {
        int cmp = a.compareTo(b);
        return cmp == 0;
    }

    protected static void reverse(Comparable[] arr) {
        int halfLength = arr.length >>> 1;
        Comparable tmp = null;
        for(int i = 0; i < halfLength; i++) {
            tmp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = tmp;
        }
    }

    protected static void exch(Comparable[] arr, int index_a, int index_b){
        Comparable tmp = arr[index_a];
        arr[index_a] = arr[index_b];
        arr[index_b] = tmp;
    }

    protected static boolean isSorted(Comparable[] arr) {
        if (arr.length <= 1)
            return true;
        for (int j = 1; j < arr.length; j ++ ) {
            if (lessThan(arr[j], arr[j-1])) {
                return false;
            }
        }
        return true;
    }

    static Comparable findMaxElement(Comparable[] arr, int lo_include, int hi_exclude) {
        Comparable maxElement = null;
        for (int i = lo_include; i < hi_exclude; i++) {
            if (i == lo_include)
                maxElement = arr[lo_include];
            else if (lessThan(maxElement, arr[i]))
                maxElement = arr[i];
        }
        return maxElement;
    }

    static Comparable findMinElement(Comparable[] arr, int lo_include, int hi_exclude) {
        Comparable maxElement = null;
        for (int i = lo_include; i < hi_exclude; i++) {
            if (i == lo_include)
                maxElement = arr[lo_include];
            else if (greaterThan(maxElement, arr[i]))
                maxElement = arr[i];
        }
        return maxElement;
    }

    static int findMaxElementIndex(Comparable[] arr, int lo_include, int hi_exclude) {
        Comparable maxElement = null;
        int maxIndex = -1;
        for (int i = lo_include; i < hi_exclude; i++) {
            if (i == lo_include) {
                maxElement = arr[lo_include];
                maxIndex = lo_include;
            }
            else if (lessThan(maxElement, arr[i])) {
                maxElement = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    static int findMinElementIndex(Comparable[] arr, int lo_include, int hi_exclude) {
        Comparable minElement = null;
        int minIndex = -1;
        for (int i = lo_include; i < hi_exclude; i++) {
            if (i == lo_include) {
                minElement = arr[lo_include];
                minIndex = lo_include;
            }
            else if (greaterThan(minElement, arr[i])) {
                minElement = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void showArr(Object[] arr, String info) {
        System.out.println(info);
        for(Object o : arr) {
            System.out.print(o);
            System.out.print(",");
        }
        System.out.println("\n");
    }
    // test
    public static void main(String[] args) {
        // reverse test
        Integer[] x = new Integer[]{1, 2, 3};
        reverse(x);
        for(Integer a : x)
            System.out.println(a);
    }
}

/**
 * 冒泡排序
 */
class BubbleSort extends SortTemplate {
    public static Comparable[] sort(Comparable[] arr){
        int hi = arr.length;
        int lo = 0;
        for(int i=lo; i < hi; i++)
            for(int j=i; j < hi; j++) {
                if (!lessThan(arr[i], arr[j])) {
                    exch(arr, i, j);
                }
            }
        return arr;
    }
}

/**
 * 选择排序
 */
class SelectSort extends SortTemplate {
    public static Comparable[] sort(Comparable[] arr) {
        int hi = arr.length;
        int lo = 0;
        int minElementIndex = -1;
        for (int i = 0; i < hi; i++) {
            minElementIndex = findMinElementIndex(arr, i, hi);
            exch(arr, minElementIndex, i);
        }
        return arr;
    }
}

/**
 * 插入排序
 */
class InsertSort extends SortTemplate {
    public static Comparable[] sort(Comparable[] arr) {
        int i = 0, j = 0;
        Comparable tmp = null;
        while (i < arr.length) {
            tmp = arr[i];
            j = i-1;
            while (j >= 0) {
                if (greaterThan(arr[j], tmp)) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                else
                    break;
            }
            arr[j + 1] = tmp;
            i++;
        }
        return arr;
    }
}

/**
 * 希尔排序 使用2的幂 作为希尔序列
 */
class ShellSort extends SortTemplate {
    private static void insertStepSort(Comparable[] arr, int offset, int step) {
        // assert offset < step
        int i = offset, j = 0;
        Comparable tmp = null;
        while (i < arr.length) {
            tmp = arr[i];
            j = i - step;
            while (j >= 0) {
                if (greaterThan(arr[j], tmp)) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                else
                    break;
            }
            arr[j + step] = tmp;
            i += step;
        }
    }
    public static Comparable[] sort(Comparable[] arr) {
        int h = 1;
        int halfLength = arr.length >>> 1;
        //产生希尔排序的序列 本次使用的是2的幂
        while (h < (halfLength)) {
            // h = 2*h
            h <<= 1;
        }
        //按照
        while (h >= 1) {
            for (int i = 0; i < h; i++) {
                insertStepSort(arr, i, h);
            }
            h >>>= 1;
        }
        return arr;
    }
    public static void main(String[] args) {
        Integer[] arr0 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        showArr(arr0, "integr array before shell sort:");
        insertStepSort(arr0, 0, 1);
        showArr(arr0, "integer array after shell sort");
    }
}

/**
 * 归并排序
 */
class MergeSort extends SortTemplate {
    //使用原地merge的方法
    //辅助数组arr_copy需要事先分配好 和arr 大小相同
    //int li0/1, int hi0/1 两段段数组的上下界 含li 不含hi
    //[li0, hi0)  在 [li1, hi1)左侧 两段相邻但不重叠
    private static void merge(Comparable[] arr, Comparable[] arr_copy, int li0, int hi0, int li1, int hi1) {
        int index = li0;
        int index0 = li0;
        int index1 = li1;
        while (index0 < hi0 && index1 < hi1) {
            //int z0 = ((Integer)arr[index0]).intValue();
            //int z1 = ((Integer)arr[index1]).intValue();
            //System.out.println(z0+":"+z1);
            if (lessThan(arr[index0], arr[index1])) {
                arr_copy[index] = arr[index0];
                index0++;
            }
            else {
                arr_copy[index] = arr[index1];
                index1++;
            }
            index++;
        }

        if (index0 >= hi0) { //sub array 0 fill to new array complete
            while (index1 < hi1) {
                arr_copy[index] = arr[index1];
                index1++;
                index++;
            }
        }

        if (index1 >= hi1) { //sub array 0 fill to new array complete
            while (index0 < hi0) {
                arr_copy[index] = arr[index0];
                index0++;
                index++;
            }
        }
    }

    public static Comparable[] sort(Comparable[] arr){
        int size = arr.length;
        int tmpSize = arr.length;
        boolean turnFlag = true;
        Comparable[] arr_copy = new Comparable[size];
        if (size > 1) {
            while (tmpSize > 2) tmpSize >>>= 1;
            int splitSzie = tmpSize;  // 要归并的长度的2倍 即当前归并的子序列的长度为1
            int offset;
            int li0, hi0, li1, hi1;
            while ((splitSzie >>> 1) < size) { //splitSize 首次大于数组长度
                offset = 0;
                while (offset < size) { //两段两段的合并
                    li0 = offset;
                    hi0 = (offset + (splitSzie >>> 1)) >= size ? size : (offset + (splitSzie >>> 1));
                    li1 = hi0;
                    hi1 = offset + splitSzie >= size ? size : offset + splitSzie;
                    if (turnFlag)
                        merge(arr, arr_copy, li0, hi0, li1, hi1);
                    else
                        merge(arr_copy, arr, li0, hi0, li1, hi1);
                    offset += splitSzie; //偏移到下一归并块
                }
                splitSzie <<= 1;
                turnFlag = !turnFlag; //进行交替的拷贝
            }
            return turnFlag ? arr : arr_copy;
        }
        else
            return arr;
    }

    public static void main(String[] args) {
        Integer[] a = {1, 5, 3, 7, 9};
        Integer[] mini = {11, 9, 7, 5, 3, 1, -1, -2, -3};
        Integer[] a_c = new Integer[a.length];
        Comparable[] s = sort(mini);
        showArr(s, "after");
    }
}

/**
 * 快速排序三切分法
 */
class QuickSort3Split extends SortTemplate {
    private static void sortPart(Comparable[] arr, int lo, int hi) {
        Comparable v = null;
        int cur = lo;
        int lt, gt;
        if (hi - lo >= 2) {
            v = arr[lo];
            cur = lo + 1;
            lt = lo + 1;
            gt = hi - 1;
            while (cur <= gt) {
                if (lessThan(arr[cur], v)) {
                    exch(arr, cur, lt);
                    cur++;
                    lt++;
                }
                else if (greaterThan(arr[cur], v)) {
                    exch(arr, cur, gt);
                    gt--;
                }
                else
                    cur++;
            }
            if (lt > lo + 1) {
                exch(arr, lt-1, lo);
            }
            sortPart(arr, lo, lt);
            sortPart(arr, gt+1, hi);
        }
        // length <= 1 return directly
    }

    public static Comparable[] sort(Comparable[] arr) {
        sortPart(arr, 0, arr.length);
        return arr;
    }
    public static void main(String[] args) {
        final int CASE_SIZE = 100;
        Integer[] arr = new Integer[CASE_SIZE];

        for(int i = 0; i< CASE_SIZE; i++) {
            arr[i] = new Integer(i);
        }
        sort(arr);
        showArr(arr, "3split sort result");
    }
}

/**
 * 普通交换快速排序
 */
class QuickSortNaive extends SortTemplate {
    private static void sortPart(Comparable[] arr, int lo, int hi) {
        //sort part is [lo, hi) hi is exclude
        Comparable v = null;
        int lt, gt;
        lt = lo + 1;
        gt = hi - 1;
        if (hi - lo >= 2) {
            v = arr[lo];
            while (true) {
                while (lt < hi && lessThan(arr[lt], v)) lt++; // stop at element no less than v
                while (gt > lo && !lessThan(arr[gt], v)) gt--; // stop at element less than v
                if (lt >= gt) break;
                exch(arr, lt, gt);
            }
            exch(arr, gt, lo);
            sortPart(arr, lo, gt);
            sortPart(arr, gt + 1, hi);
        }
    }
    public static Comparable[] sort(Comparable[] arr) {
        sortPart(arr, 0, arr.length);
        return arr;
    }

    public static void main(String[] args) {
        //Integer[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] arr = {10, 2};
        sortPart(arr, 0, arr.length);
        showArr(arr, "part result");
    }
}

class HeapSort extends SortTemplate {
    //使用的完全二叉树索引是从０开始的　
    private static int getFather(int thisIndex) {
        return (thisIndex - 1) >>> 1;
    }

    private static int getLeft(int thisIndex) {
        return (thisIndex << 1) + 1; //warning!!  priority << is lower than +-
    }

    private static int getRight(int thisIndex) {
        return (thisIndex << 1) + 2;
    }

    //将堆调整为小顶堆
    //该函数已经假设除了堆顶　其左右子树都已经是堆
    //worst condition  is O(lg(n))
    private static void adjustHeap(Comparable[] arr, int topIndex, int heapLength) {

        int tmpTopIndex = topIndex;
        int leftTop, rightTop, minSonInedex;
        while (tmpTopIndex < heapLength) {
            leftTop = getLeft(tmpTopIndex);
            rightTop = getRight(tmpTopIndex);
            //找出子堆中最小的堆的顶
            if (leftTop < heapLength && rightTop < heapLength)
                minSonInedex = lessThan(arr[leftTop], arr[rightTop]) ? leftTop: rightTop;
            else if (leftTop < heapLength && rightTop >= heapLength) //只有左子堆
                minSonInedex = leftTop;
            else//没有子堆
                minSonInedex = -1;

            if (minSonInedex != -1) {
                if (lessThan(arr[minSonInedex], arr[tmpTopIndex])) {
                    //堆顶比最小的子堆顶大 需要调整
                    exch(arr, minSonInedex, tmpTopIndex); //将较小的子堆的顶交换到当前堆顶
                    tmpTopIndex = minSonInedex;//继续检查下面的子堆是否符合要求
                }
                else
                    break;
            }
            else
                break; //没有左右子树　直接返回
        }
    }

    public static Comparable[] sort(Comparable[] arr) {
        Comparable[] arrHeap = new Comparable[arr.length];
        for(int u = 0; u < arr.length; u++) arrHeap[u] = arr[u];
        //创建初始堆
        for(int u = 0; u < arr.length; u++) adjustHeap(arrHeap, u, arrHeap.length);
        //排序输出
        for(int u = 0; u < arr.length; u++) {
            arr[u] = arrHeap[0];
            exch(arrHeap, 0, arr.length - 1 - u);
            adjustHeap(arrHeap,0, arr.length - 1 - u);
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(getLeft(0));
        System.out.println(getRight(0));
        System.out.println(getLeft(1));
        //System.out.println(getLeft());
        //System.out.println(getLeft(0));
    }
}
