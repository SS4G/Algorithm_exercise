#include <stdio.h>
int hammingDistance(int x, int y) {
    int i=0;
    int res=x^y;
    int distance=0;
    for(i=0;i<32;i++){
        if(res&0x00000001)
            distance++;
        res>>=1;
    }
    return distance;
}
int main()
{
    
    
}
