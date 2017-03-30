# leetcode 168
## Question
#### Excel Sheet Column Title
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> A

## Answer
这个不是一般的进制转换问题
需要特殊处理
我的方法是
数字 与标题的对应关系分段来看的话是标准的进制转换

AAA->ZZZ 对应 0-26*26*26-1  这是标准的进制转换
但是还要考虑之前的 
A->Z  AA->ZZ
所以 需要先确定数值的段标 然后根据分段的标志 使原数减去段标志处的值 换算为标准的 进制转换问题

##### code
```Python
class Solution(object):
    def convertToTitle(self, n):
        """
        :type n: int
        :rtype: str
        """
        n-=1
        start_point=0# include start_point 段起始段标
        end_point=26 # include end_point 段终结段标
        width=1
        pows=676    #26*26
        while n>=end_point:#判断n所属的段
            end_point=end_point+pows
            pows*=26
            width+=1
        start_point=end_point-int(pows//26)  
        return self.range_0_convert(n-start_point,width)#在所属的段内进行进制转换                 
        
    
    def range_0_convert(self,x,width):
        """
        标准的进制转换函数
        由于需要用字符表示结果，所以需要用width来
        指定结果的宽度 保证高位在数值上为0时用字符A来填充
        """
        chap_dict0="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        list_s=[]
        print(x)
        for i in range(width):
            list_s.append(chap_dict0[x%26])
            x//=26
            x=int(x)
        list_s.reverse()
        return "".join(list_s)
```

### 高手的方法
高手巧妙的消除了来自A AA 等对不齐的干扰
精髓自己领会

```Cpp
class Solution {
public:
    string convertToTitle(int n) {
        string ret = "";
        while(n)
        {
            ret = (char)((n-1)%26+'A') + ret;
            n = (n-1)/26;
        }
        return ret;
    }
};
```
