## 1. Two Sum Two Sum
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution.
Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

UPDATE (2016/2/13):
The return format had been changed to zero-based indices. Please read the above updated description carefully.


## tips

##### update 2017-07-14
可以使用hashmap 也可以使用先排序 然后从两端开始查找 前者复杂度为O（n） 后者复杂度为lgn
后者的方法可以应用在ksum中

#### mycode
```Java
//use hash map
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap <Integer, Integer> indiceMap = new HashMap<>(30000);
        for (int i = 0; i < nums.length; i++) {
            if (indiceMap.containsKey(target - nums[i])) {
                res[0] = indiceMap.get(target - nums[i]);
                res[1] = i;
                return res;
            }
            else
                indiceMap.put(nums[i], i);
        }
        return new int[2];
    }
```


```Java
//O(lgn) use sort()
public int[] twoSum(int[] nums, int target) {
        ArrayList<Integer[]> arr = new ArrayList<Integer[]>(30000);
        for (int i = 0; i < nums.length; i++) {
            Integer[] element = {nums[i], i};
            arr.add(element);
        }
        Comparator<Integer[]> cmp = new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                 if (o1[0] > o2[0])
                     return 1;
                 else if (o1[0] < o2[0])
                     return -1;
                 else
                     return 0;
            }
        };
        Collections.sort(arr, cmp);
        
        int lo = 0;
        int hi = nums.length - 1;
        int[] indice = new int[2];
        while (lo < hi) {
            if (arr.get(lo)[0] + arr.get(hi)[0] > target) {
                hi --;
            }
            else if (arr.get(lo)[0] + arr.get(hi)[0] < target) {
                lo ++;
            }
            else {
                indice[0] = arr.get(lo)[1];
                indice[1] = arr.get(hi)[1];
                break;
            }
        }
        return indice;
    }
```


-----
## tips
同样采用类似于冒泡排序的比较方法 
对于使用python的一处结果感到好奇，方法a是先通过循环找出能够达到target的值 然后在通过index（）来获取这两个值在原列表中的位置 方法b是每次循环时记录下index 找到达到target的值后直接保存当前的index
结果发现方法b反而比方法a慢2000ms 
说明每次保存index的操作并不高效 大部分时候保存的都是无用的index 

#### mycode

```Python
class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        nums_copy=nums
        a=0
        b=0
        same_flag=0

        k_index=0
        l_index=1
        for k in nums_copy:#a_index
            l_s_index=k_index+1
            l_index=l_s_index
            for l in nums_copy[l_s_index:]:#b_index 
                if l+k == target:
                    a=k_index
                    b=l_index
                    break
                l_index+=1
            k_index+=1
            
        index0=a
        index1=b
        res=[]    
        if index0>index1 :
            res=[index1,index0]
        else :
            res=[index0,index1]
        return res
```
