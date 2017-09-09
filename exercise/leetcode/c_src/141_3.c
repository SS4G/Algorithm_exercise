#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef int bool;

#define true 1
#define false 0 
//Definition for singly-linked list.
struct ListNode {
    int val;
    struct ListNode *next;
};
bool hasCycle(struct ListNode *head);
bool is_in(const struct ListNode **list,const struct ListNode *ptr,int length);
int main()
{
    struct ListNode a,b,c,d,e;
    a.val=NULL;
    b.val=1;
    c.val=2;
    d.val=3;
    e.val=4;
    a.next=&b;
    b.next=&c;
    c.next=&d;
    d.next=&e;
    e.next=&a;
    printf("%d",hasCycle(NULL));   
    return 0;
}
bool hasCycle(struct ListNode *head)
{
    struct ListNode *ptr_array[100000];
    memset(ptr_array,0,sizeof(100000));
    struct ListNode *tmp=head;
    int j;
    j=0;
    while (tmp!=NULL)
    { 
        printf("--%d--\n",tmp->val);
        if(is_in(ptr_array,tmp,j))
            return true;
        ptr_array[j]=tmp;
        j++;
        tmp=tmp->next;
    }    
    return false;
}
bool is_in(const struct ListNode **list,const struct ListNode *ptr,int length)
{
    int i=0;
    for(i=0;i<length;i++)
    {
        if(ptr==list[i])
            return true;
    }
    return false;
}