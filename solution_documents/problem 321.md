## 321. Create Maximum Number

Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:

```
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]
```


Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]
#### tips
既然结果是两个序列合并出来的 那么必然是第一个序列取n个数字 第二个序列取 m个 m+n=k

所以 必然是其中的某一种组合
所以首先应该从m中取出a个最大的 从n中取出b个最大的 只有都是最大的 合并出来才可能是最大的

假设 真正的结果中 第一个序列取到了a个数字 
第二个序列取到了b个数字 a+b=k

所以 现在需要求得就是如何把这a 和 b恋歌长度的子串合并为最大的 这需要使用类似合并链表的方法来合并

但是需要特殊处理 指针所指的内容 相等的情况
具体详见merge()函数

#### mycode
```Java
class Leet321x {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] candit = new int[k];
        Arrays.fill(candit, 0);
        int totalLen = nums1.length + nums2.length;
        int totalAbort = totalLen - k;
        int abort1 = 0;
        int abort2 = totalLen;
        for (int i = 0; i < totalAbort + 1; i++) {
            abort1 = i;
            abort2 = totalAbort - i;
            if (nums1.length - abort1 < 0 || nums2.length - abort2 < 0)
                continue;
            int[] n1 = getMaxaxNumber(nums1, nums1.length - abort1);
            int[] n2 = getMaxaxNumber(nums2, nums2.length - abort2);
            int[] tmp = merge(n1, n2);
            candit = compareArr(candit, tmp) > 0 ? candit : tmp;
        }
        return candit;
    }

    public boolean greater(int[] num1, int nst1, int[] num2, int nst2) {
        while (nst1 < num1.length && nst2 < num2.length && num1[nst1] == num2[nst2]) {
            nst1++;
            nst2++;
        }
        return nst2 == num2.length || (nst1 < num1.length && num1[nst1] > num2[nst2]);
    }

    public int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int aPtr = 0;
        int bPtr = 0;
        int wPtr = 0;
        while (aPtr < a.length && bPtr < b.length) {
            if (a[aPtr] < b[bPtr]) {
                result[wPtr++] = b[bPtr++];
            }
            else if (a[aPtr] > b[bPtr]) {
                result[wPtr++] = a[aPtr++];
            }
            else {
                result[wPtr++] = greater(a, aPtr, b, bPtr) ? a[aPtr++] : b[bPtr++];
            }
        }

        while (aPtr < a.length)
            result[wPtr++] = a[aPtr++];

        while (bPtr < b.length)
            result[wPtr++] = b[bPtr++];

        return result;
    }

    public int[] getMaxaxNumber(int[] nums, int k) {
        if (k == 0)
            return new int[0];
        int[] stack = new int[k];
        int i = 0;
        int stackPtr = 0;
        while (i < nums.length) {
            if (stackPtr == 0)
                stack[stackPtr++] = nums[i];//push
            else if (stack[stackPtr - 1] < nums[i]) {
                while (stackPtr > 0 && stack[stackPtr - 1] < nums[i] && (k - stackPtr) < nums.length - i) {
                    stackPtr--;//pop()
                }
                stack[stackPtr++] = nums[i];
            }
            else {
                if (stackPtr < k)
                    stack[stackPtr++] = nums[i];
            }
            i++;
        }
        return stack;
    }

    public int compareArr(int[] a, int[] b) {
        assert a.length == b.length: "WTF";
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i])
                return -1;
            else if (a[i] > b[i])
                return 1;
        }
        return 0;
    }
}
```
