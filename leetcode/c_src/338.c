#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
int* countBits(int num, int* returnSize) ;
int cal(uint32_t num);
/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
 int main()
 {
    int *ptr=NULL;
    int k,t;
    ptr=countBits(5,&k);

    for(t=0;t<k;t++)
    {
        printf("%d ",ptr[t]);
    }        
    return 0;
 }
 
 
int* countBits(int num, int* returnSize) 
{
    
    *returnSize=num+1;
    int i;
    int *res=(int*)malloc(sizeof(int)*(num+1));
    for(i=0;i<=num;i++)
    {
        res[i]=cal(i);
    }
    return res;
}
int cal(uint32_t num)
{
    int j;
    uint32_t mask;
    int bit_cnt;
    for(j=0,mask=(uint32_t)0x00000001,bit_cnt=0;j<32;j++)
    {
        if(num&mask)
            bit_cnt++;
        
        mask<<=1;
    }
    return bit_cnt;
}


