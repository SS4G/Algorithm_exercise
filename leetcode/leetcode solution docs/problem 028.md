# leetcode 028
## Question
#### implement strStr()
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
## Answer
python str类的find 函数可以直接实现该功能 但对于本题这样做豪无意义

所以用C语言实现

要注意的是就是 一些特殊的用例 比如空字符串 "" needle 和 haystack 相等的情况

##### code 

```
int strStr(char* haystack, char* needle)
{
        int i=0;
        i=0;
        int needle_len=strlen(needle);
        int stack_len =strlen(haystack);
        //以下处理各种特殊情况
        if(needle_len==0&&stack_len==0)
            return 0;
        else if(needle_len==0&&stack_len!=0)
            return 0;
        else if(needle_len!=0&&stack_len==0)
            return -1;
        else if(needle_len==stack_len&&strncmp(haystack,needle,needle_len)==0)
            return 0;
        else if(needle_len>stack_len)
            return  -1;                  
        int end_index=stack_len-needle_len+1;
        //处理排除了特殊情况后的一般情况
        while(i<end_index)
        {        
            print("i=%d",i);    
            if(strncmp(haystack+i,needle,needle_len)==0)
            {
                printf("get pattern");
                return i;
            }                
            i++;
        }       
        return -1;
}
```

###### 关于自己造轮子
本次提交中自己实现了字符串的 strcmp strlen 等函数 发现效率确实很低
所以工作中 不要轻易自己造轮子



