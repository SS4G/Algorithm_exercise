# include <stdio.h>
# include <math.h>
# define True   1
# define False   0
# define E 0.000001
typedef int bool;
bool checkPerfectNumber(int num) {
    int i=1;
    int sums = 1;
    int sq = 0;

    double sq_double = sqrt((double)num);
    int complte_sqrt_flag = False;

    if(abs(sq_double-(int)(sq_double))<E)
    {
        sq = (int)(sq_double);
        complte_sqrt_flag = True;
    }
    else if(abs(sq_double-((int)(sq_double)+1))<E)
    {
        sq = (int)(sq_double)+1;
        complte_sqrt_flag = True;
    }
    else
        sq = (int)(sq_double)+1;

    for(i=2; i<sq; i++)
    {
        if(num%i==0){
            sums+=i;
            sums+=num/i;
        }
    }
    sums += complte_sqrt_flag?sq:0; 

    return sums==num?1:0;
}
int main()
{
    int a;
    int res = 0;
    while(1){
        scanf("%d",&a);
        res = checkPerfectNumber(a);
        printf("%d\n",res);
    }
    return 0;
}