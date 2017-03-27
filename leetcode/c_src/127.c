#include <stdio.h>
int count_five(int x);
int main()
{
    printf("sd");
    return 0;
}
int trailingZeroes(int n) {
    if(n<5)
        return 0;
    int tmp=5;
    int sum=0;
    while(tmp<=n)
    {
        sum+=count_five(tmp);
        tmp+=5; 
    }       
    return sum;
}
int count_five(int x)
{
        int cnt=0;
        while(x>0)
        {
            if(x%5==0)
            {
                cnt+=1;
                x/=5;
            }                
            else  break;
                
        }            
        return cnt;
}