#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdint.h>
char* addBinary(char* a, char* b);
int main()
{
    
    printf("%s\n",addBinary("11", "1"));
    return 0;
}



char* addBinary(char* a, char* b) {
    uint32_t len_a=strlen(a);
    uint32_t len_b=strlen(b);
    int32_t index_a=len_a-1,index_b=len_b-1,res_index=0,i;
    uint32_t max_len,min_len;
    
    char* add_res=NULL;
    max_len=len_a>len_b?len_a:len_b;
    min_len=len_a<len_b?len_a:len_b;
    
    
    add_res=(char*)malloc(sizeof(char)*(max_len+2));
    
    memset(add_res,'\0',max_len+1);
    uint32_t k=0;
    uint8_t  tmp,carry;
    
    
    index_a=len_a-1;
    index_b=len_b-1;
    add_res[max_len+1]='\0';
    for(k=min_len,res_index=max_len,carry=0;k>0;k--)
    {  
        
        tmp=carry+(a[index_a]-'0')+(b[index_b]-'0');
        //printf("%d\n",tmp);
        add_res[res_index]=(tmp&0x01)+'0';//本位和
        carry=(tmp&0x02)>>1;   //进位
        index_a--;
        index_b--;
        res_index--;
    }
    if(index_a==-1)
    {
        while(index_b>=0)
        {
        tmp=carry+(b[index_b]-'0');
        add_res[res_index]=(tmp&0x01)+'0';//本位和
        carry=(tmp&0x02)>>1;   //进位
        index_b--;
        res_index--;
        }
        add_res[0]=carry+'0';
    }
    else if(index_b==-1)
    {
        while(index_a>=0)
        {
        tmp=carry+(a[index_a]-'0');
        add_res[res_index]=(tmp&0x01)+'0';//本位和
        carry=(tmp&0x02)>>1;   //进位
        index_a--;
        res_index--;
        }
        add_res[0]=carry+'0';
    }
    else {
        printf("Error\n");
    }
       if(add_res[0]=='1'||(add_res[0]=='0'&&strlen(add_res)==1))
          return add_res;
       else return add_res+1;
}