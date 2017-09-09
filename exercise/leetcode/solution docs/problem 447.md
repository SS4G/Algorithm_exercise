## 447. Number of Boomerangs   
Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).


```
Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
```

##### code 我的代码
```Python
class Solution(object):
    def cal_distance(self,pointa,pointb):
        """
        pointa :[x,y]
        pointb :[x,y]
        """
        return (pointa[0]-pointb[0])**2+(pointa[1]-pointb[1])**2

    def combine(self,x):
        if x<2:
            return 0
        else:
            return x*(x-1)//2                        
        
    def numberOfBoomerangs(self, points):
        """
        :type points: List[List[int]]
        :rtype: int
        """
        mat=[]
        length=len(points)
        for i in range(length):
            mat.append([0,]*length)       
        
        for i in range(length):
            for j in range(length):
                if i<=j :
                    mat[i][j]=self.cal_distance(points[i],points[j])
                    mat[j][i]=mat[i][j]    
        sum=0
        for i in range(length):
            mat[i].sort()
            print(mat[i])
            cur_cmp_val=mat[i][0]
            pre_index=0
            
            index=0
            while(index<length):
                if mat[i][index]!=cur_cmp_val:
                    cur_cmp_val=mat[i][index]
                    sum+=self.combine(index-pre_index)
                    pre_index=index 
                elif index==length-1:
                    cur_cmp_val=mat[i][index]
                    sum+=self.combine(index-pre_index+1)
                    pre_index=index
                index+=1
        return sum*2
```
   

##### code   一种使用距离作为字典键的方法 跟我的统计某个距离的做法一样 但是实现起来更简单
```Python
class Solution(object):
    def numberOfBoomerangs(self, points):
        count = 0
        for i in range(len(points)):
            h = {}
            for j in range(len(points)):
                if i != j:
                    dt = pow(points[i][0] - points[j][0], 2) + pow(points[i][1] - points[j][1], 2)
                    count += h.get(dt, 0)
                    h[dt] = h.get(dt, 0) + 1
        return count*2
```
