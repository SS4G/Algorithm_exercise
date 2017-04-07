package AlgorithmTraining.data_structure.Sort;

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
class BubleSort extends SortTemplate {
    public static void sort(Comparable[] arr){
        int hi = arr.length;
        int lo = 0;
        for(int i=lo; i < hi; i++)
            for(int j=i; j < hi; j++) {
                if (!lessThan(arr[i], arr[j])) {
                    exch(arr, i, j);
                }
            }
    }
}

/**
 * 选择排序
 */
class SelectSort extends SortTemplate {
    public static void sort(Comparable[] arr) {
        int hi = arr.length;
        int lo = 0;
        int minElementIndex = -1;
        for (int i = 0; i < hi; i++) {
            minElementIndex = findMinElementIndex(arr, i, hi);
            exch(arr, minElementIndex, i);
        }
    }
}

class InsertSort extends SortTemplate {
    public static void sort(Comparable[] arr) {
        int i = 0, j = 0;
        Comparable tmp = null;
        while (i < arr.length) {
            tmp = arr[i];
            j = i-1;
            while (j > 0) {
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
    }
}

class ShellSort extends SortTemplate {
    private static void insertStepSort(Comparable[] arr, int offset, int step) {
        // assert offset < step
        int i = offset, j = 0;
        Comparable tmp = null;
        while (i < arr.length) {
            tmp = arr[i];
            j = i - step;
            while (j > 0) {
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
    public static void sort(Comparable[] arr) {
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
    }
}

class MergeSort extends SortTemplate{
    //使用原地merge的方法
    //辅助数组arr_copy需要事先分配好 和arr 大小相同
    //int li0/1, int hi0/1 两段段数组的上下界 含li 不含hi
    //[li0, hi0)  在 [li1, hi1)左侧 两段相邻但不重叠
    private static void merge(Comparable[] arr, Comparable[] arr_copy, int li0, int hi0, int li1, int hi1) {
        int index = li0;
        int index0 = li0;
        int index1 = li1;
        while (index0 < hi0 && index1 < hi1) {
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

    public static void sort(Comparable[] arr){
        int size = arr.length;
        int half_size = size >>> 1;
        if (size > 1) {
            Comparable[] arr_copy = new Comparable[size];
            int splitSzie = 2;  // must be an even number
            int offset = 0;
            int li0, hi0, li1, hi1;
            while (splitSzie < half_size) {
                li0 = offset;
                hi0 = offset + (splitSzie >>> 1);
                li1 = offset + (splitSzie >>> 1);
                hi1 = offset + splitSzie > size ? size : offset + splitSzie;
                merge(arr, arr_copy, li0, hi0, li1, hi1);
            }
        }
    }
}



