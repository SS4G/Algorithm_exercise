int* twoSum(int* nums,int numsSize,int target)
{
int* arr;
int i=0,j=1;

arr = (int *)malloc(sizeof(int)*100);

for(;j<=numsSize-1;j++)
{

    if(nums[i]+nums[j]==target)
        {
            arr[0]=i;
            arr[1]=j;
            break;
        }

    if(j==numsSize-1)
    { i++;
      j=i;
    }

}

return arr;
}