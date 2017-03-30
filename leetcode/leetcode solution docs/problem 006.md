# leetcode 6
## Question
#### ZigZag Conversion
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

```
P   A   H   N
A P L S I I G
Y   I   R
```

And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:


```
string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
```

## Answer
算出z形的一个周期 然后将不同周期中的同一偏移位置的元素组成一个数组
然后根据打印顺序重新进行组装即可
例如 图：
![img](http://img.blog.csdn.net/20131124115024218?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvemhvdXdvcmxkMTY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)
第一步 编为列表

```
[0,6]
[1,7]
[2,8]
[3,9]
[4]
[5]
```

第二步 组装新的列表

```
[0,6]
[1,5,7]
[2,4,8]
[3,9]
```
第三步

```
return "".join([0,6]+[1,5,7]+[2,4,8]+[3,9])
```


##### code

```
class Solution(object):
    def convert(self, s, numRows):
        """
        :type s: str
        :type numRows: int
        :rtype: str
        """
        # 1 perido=n+n-2
        # 1 line index%perido==0
        # 2 line index%perido==1 or perido-1
        # 3 line index%perido==2 or perido-2
        #...
        # last line index%perido==row-1
        perido=2*numRows-2
        s_len=len(s)
        if s_len==0:
            return ""
        if numRows==1:
            return s       
        
        
        rows=[None,]*(2*numRows-2)
        for z in range((2*numRows-2)):
            rows[z]=[]
        
        out_row=[None,]*numRows
        for z in range(numRows):
            rows[z]=[]            
       
        for k in range(perido):
            z=k             
            while z<s_len:
                rows[k].append(s[z])                
                z+=perido
            print(rows[k])
        
        for i in range(numRows):
            if i ==0 :
                out_row[0]=rows[0]                
            elif i==numRows-1:
                out_row[numRows-1]=rows[numRows-1]
            else:
                out_row[i]=[None,]*(len(rows[i])+len(rows[perido-i]))
                out_row[i][0::2]=rows[i][:]
                out_row[i][1::2]=rows[perido-i][:]
             
             
        sums=[]
        for i in out_row:
            sums+=i
            
        return "".join(sums)
```

        
        
        