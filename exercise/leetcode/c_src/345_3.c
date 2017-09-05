#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int in_judge(char x,char *list);
char* reverseVowels(char* s);
int main()
{
    char pu0[100]={"hello"};
    char pu1[100]={"leetcode"};
    char pu2[100]={" "};
    char pu3[100]={"a."};
    
    
    reverseVowels(pu0) ;
    reverseVowels(pu1) ;
    reverseVowels(pu2) ;
    reverseVowels(pu3) ;

    printf("pu0= %s\n",pu0);
    printf("pu1= %s\n",pu1);
    printf("pu2= %s\n",pu2);
    printf("pu3= %s\n",pu3);
    return 0;
}
char* reverseVowels(char* s) {
        char *list_s=s;
        int length,i,j;
        char tmp;
        char *vol_list="aeiouAEIOU";
        length=strlen(list_s);
        i=length-1;
        j=0;
        while((i>j)&&(i>=0)&&(j<=length-1))
        {
            while((!in_judge(list_s[i],vol_list))&&((i>j)&&(i>=0)&&(j<=length-1)))
                i-=1;
            while((!in_judge(list_s[j],vol_list))&&((i>j)&&(i>=0)&&(j<=length-1)))
                j+=1;            
            tmp=list_s[i];
            list_s[i]=list_s[j];
            list_s[j]=tmp;
            i-=1;
            j+=1;
        }       
        return s;
}
int in_judge(char x,char *list)
{
    int i=0;
    while(list[i]!='\0')
    {
        if(x==list[i])
            return 1;
        i++;
    }    
    return 0;
}


