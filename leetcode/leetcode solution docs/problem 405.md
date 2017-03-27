# leetcode 405
## Question
####  Convert a Number to Hexadecimal

Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

```
Input:
26

Output:
"1a"
Example 2:

Input:
-1

Output:
"ffffffff"
```
## Answer
使用与运算 不用担心负数 直接从内存中读取数据即可
##### code (C)
```
#include <stdio.h>
#include <stdlib.h>
char* toHex(int num) {
    int i=0;
    char chap_table[17]="0123456789abcdef";
    char *res;
    int index=0;
    res=(char*)malloc(sizeof(char)*9);
    res[8]='\0';
    for(i=0;i<8;i++)
    {
        res[i]=chap_table[((num&0xF0000000)>>28)];
        num<<=4;
        //printf("%d",num);
    }   
    index=0;
    while(res[index]=='0')
        index++;
    if(index==8)
        return res+7;
    else
        return res+index;
}
```
