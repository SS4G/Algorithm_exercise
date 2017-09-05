# leetcode 118
## Question
#### Pascal's Triangle
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return


```Python
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
```

## Answer
简单的杨慧三角 没什么说的直接上代码 beat45%

##### code
```
class Solution(object):
    def generate(self, numRows):
        """
        :type numRows: int
        :rtype: List[List[int]]
        """
        ll=[]
        if numRows ==0:
            return []
        for i in range(numRows):
            length=i+1
            if i==0:
                ll.append([1])
            elif i==1:
                last_l=[1,1]
                ll.append(last_l)                
            else:
                tmp_l=[1]*(i+1)
                for p in range(i-1):
                    tmp_l[p+1]=last_l[p]+last_l[p+1]
                ll.append(tmp_l)
                last_l=tmp_l
        return ll
```
