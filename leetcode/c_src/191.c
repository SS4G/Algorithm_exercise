#include<stdio.h>
#include<stdint.h> 
int hammingWeight(uint32_t n);
int main()
{
    int res;
    res=hammingWeight((uint32_t)11);
    printf("has bit 1 %d\n",res);
    return 0;
}


int hammingWeight(uint32_t n) {
    int i=0;
    int sum=0;
    for(i=0,sum=0;i<8;i++)
    {
        switch (n&((uint32_t)0x0000000f))
        {
            case 0x0 : sum+=0;break;
            case 0x1 : sum+=1;break;
            case 0x2 : sum+=1;break;
            case 0x3 : sum+=2;break;
            case 0x4 : sum+=1;break;
            case 0x5 : sum+=2;break;
            case 0x6 : sum+=2;break;
            case 0x7 : sum+=3;break;
            case 0x8 : sum+=1;break;
            case 0x9 : sum+=2;break;
            case 0xa : sum+=2;break;
            case 0xb : sum+=3;break;
            case 0xc : sum+=2;break;
            case 0xd : sum+=3;break;
            case 0xe : sum+=3;break;
            case 0xf : sum+=4;break;
            default  : sum+=0;break;
        }
        n>>=4;
    }
    return sum;
}