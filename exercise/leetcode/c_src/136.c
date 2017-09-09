#include <stdio.h>
int main()
{
    
return 0;
}
int singleNumber(int* nums, int numsSize) {
    int i,sum=0;
    for(i=0;i<numsSize;i++)
        sum^=nums[i];
    return sum;
}