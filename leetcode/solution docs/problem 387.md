# leetcode 387
## Question
#### First Unique Character in a String
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
## Answer 
最傻逼的办法就是对字符挨个调用count 函数
但对于很长的测试用例 在最坏的状况下为O(N^2)基本会超时
所以，由于只有小写字母，只对a~z调用count()将count()返回为1的结果进行检查即可 复杂度O(n) 代码用python会超时 所以用C count方法自己实现
##### code

```
int count(const char* src,char x)
{
    int i=0,k=0;
    while(src[i]!='\0')
    {
        if(src[i]==x)
            k++;
        i++;
    }
        return k;
}
int get_first_index(const char* src,char x)
{ 
    int i=0;
    while(src[i]!='\0')
    {
        if(src[i]==x)
            return i;
        i++;
    }
    return -1;
}



int firstUniqChar(char* s) {
       
    int i=0;
    int numbers[26];
    int first_index=10000000;
    int flag=0;
    int tmp_first_index=0;
    for(i=0;i<26;i++)
    {
        numbers[i]=count(s,i+'a');
    }

    for(i=0;i<26;i++)
    {
        if(numbers[i]==1)
        {
            flag=1;
            tmp_first_index=get_first_index(s,i+'a');
          
            if(tmp_first_index<first_index)
                first_index=tmp_first_index; 
        }                       
    }   
    if(!flag)    
        return -1; 
    else 
        return first_index;
}
```

