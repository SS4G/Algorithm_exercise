

int firstUniqChar(char* s) {
       
    int i=0;
    int numbers[26];
    int first_index=10000000;
    int flag=0;
    for(i=0;i<26;i++)
    {
        numbers[i]=count(s,i+'a');
    }
    for(i=0;i<26;i++)
    {
        if(numbers[i]==1)
        {
            flag=1
            tmp_first_index=get_first_index(s,i+'a');
            if(tmp_first_index<first_index)
                first_index=tmp_first_index; 
        }                       
    }   
    if(!flag)    
        return -1; 
    else 
        return first_index
}
int count(const char* src,char x)
{
    int i=0,k=0;
    while(src[i]!='\0')
    {
        if(src[i]==x)
            k++;
        i++;
    }
        return k;
}
int get_first_index(const char* src,char x)
{ 
    int i=0;
    while(src[i]!='\0')
    {
        if(src[i]==x)
            return x;
        i++;
    }
    return -1;
}