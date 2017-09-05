## 35. Search Insert Position
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.

```
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
```
#### tips
么啥好说的 挺简单的
##### mycode(beats 70%) 

```Java 
public class Solution {
    public int searchInsert(int[] nums, int target){
        int i;
        for(i=0 ; i<nums.length; i++){
            if(target<=nums[i]){
                return i;
            }
        }
        return i;
    }
}
```
