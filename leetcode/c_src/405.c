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
int main()
{    
    char * result;
    result=toHex(0);
    printf(result);
    return 0;
}

