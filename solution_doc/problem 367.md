## 367. Valid Perfect Square
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.


```
Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False
```

#### tips
- 有两种方法 使用二分法迭代
- 使用 1+3+5+7.... 因为 2n-1 的等差数列求和的结果是 n^2 复杂度O(sqrt(num))  
- 当然还可以用牛顿球根q法

##### mycode

```
//1+3+5+7
public boolean isPerfectSquare(int num) {
     int i = 1;
     while (num > 0) {
         num -= i;
         i += 2;
     }
     return num == 0;
 }
```

```
//二分法
public class leet367 {
    public boolean isPerfectSquare(int num) {
        long res=this.find_sqrt_root((long)num);
        return res>0?true:false;
    }
    long find_sqrt_root(long num){
        boolean root_exist=true;
        long root_left=1;
        long root_right=num;
        long root_mid=(root_left+root_right)>>>1;
        long mid_res=root_mid*root_mid;
        if(root_right==0)
            return 0;
        else if(root_right==1)
            return 1;
        while(root_left*root_left!=num && root_right*root_right!=num){
            System.out.println(root_left+":"+root_right);
            if(abs(root_left,root_right)<=1){
                root_exist=false;
                break;
            }
            else{
                root_mid=(root_left+root_right)>>>1;
                mid_res=root_mid*root_mid;
                if(mid_res<num)
                    root_left=root_mid;
                else
                    root_right=root_mid;
            }
        }
        if(!root_exist)
            return -1;
        else if(root_exist && root_left*root_left==num  )
            return root_left;
        else if(root_exist && root_right*root_right==num)
            return root_right;
        else
            return -1;
    }
    long abs(long a,long b){
        return a-b>=0?a-b:b-a;
    }
}
```

```
//一种简便的二分法 通过巧妙的利用上下限的变换来求得
public boolean isPerfectSquare(int num) {
        int low = 1, high = num;
        while (low <= high) {
            long mid = (low + high) >>> 1;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        return false;
    }
```





