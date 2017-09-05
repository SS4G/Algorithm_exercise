## 413. Arithmetic Slices
A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:


```
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
```

The following sequence is not arithmetic.


```
1, 1, 2, 5, 7
```


A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.



```
Example:

A = [1, 2, 3, 4]
```


return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

#### tips
使用长度为三的滑动窗口来进行操作 然后算出连续的序列长度 对每一个连续序列长度计算arithmetic情况数进行求和
##### mycode with Java
```
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        //int length=A.length;
        int continue_arth=0;
        boolean is_tri_arth=false;
        boolean tri_arth_first_detect=false;
        int arth_sum=0;
        for(int i=0;i<=A.length-3;i++){
            is_tri_arth=tri_num_arth(A,i);
            if(is_tri_arth && ! tri_arth_first_detect){
                continue_arth=3;
                tri_arth_first_detect=true;
                if(i==A.length-3)
                    arth_sum+=calculate_arth_num(continue_arth);
            }
            else if(is_tri_arth && tri_arth_first_detect){
                continue_arth+=1;
                if(i==A.length-3)
                    arth_sum+=calculate_arth_num(continue_arth);
            }
            else{
                tri_arth_first_detect=false;
                arth_sum+=calculate_arth_num(continue_arth);//calculate nums
                continue_arth=0;
            }
        }
        return arth_sum;
    }
    boolean tri_num_arth(int[] arr,int start_index){
        return (arr[start_index]-arr[start_index+1])==(arr[start_index+1]-arr[start_index+2]);
    }
    int calculate_arth_num(int arth_len){
        if(arth_len>=3)
            return (arth_len-1)*(arth_len-2)/2;
        else
            return 0;
    }
}
```
