#include <stdio.h>
#include <math.h>
typedef int bool;
#define true 1
#define false 0
int count(int *src,int x,int len);
bool isUgly(int num);
int main()
{
    int k,p;
    p=6;
    k=isUgly(p);
    printf("%d--%d\n",p,k);
    p=8;
    k=isUgly(p);
    printf("%d--%d\n",p,k);
    p=1;
    k=isUgly(p);
    printf("%d--%d\n",p,k);
    p=14;
    k=isUgly(p);
    printf("%d--%d\n",p,k);
    p=937351770;
    k=isUgly(p);
    printf("%d--%d\n",p,k);
}
int count(int *src,int x,int len);
bool isUgly(int num);
bool isUgly(int num) {
        int i,k,sum_2_3_5;
        int prime_list[1000];
        if(num==1)
            return true;
        else if(num<1)
            return false;

        i=2;
        k=0;
        printf("begin");
        while(num>1)
            if(num%i ==0 ){
                num=num/i;
                prime_list[k]=i;
                k+=1;  
            }
            else{
                i+=1;
            }            
        sum_2_3_5=count(prime_list,2,k)+count(prime_list,3,k)+count(prime_list,5,k);
        if (sum_2_3_5<k)
            return false;
        else
            return true;
}
int count(int *src,int x,int len)
{
    int i;
    int number=0;
    for(i=0;i<len;i++)
    {
        if(x==src[i])
            number+=1;
    }        
    return number;
}



