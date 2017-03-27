## binary tree index define
node index define：from 0 to n-1,total n nodes

level define：from 1 to k (total k levels)  
empty tree  ：level 0  
only root node : level 1

```     
-                   level:k
-      0            level:1
     /   \  
    1     2         level:2
   / \   / \
  3   4 5   6       level:3
  
  each level :No.k level start with node whose index is (k-1)*(k)/2  
  each level :No.k level has nodes 2^(k-1) 
```



