## 458. Poor Pigs
There are 1000 buckets, one and only one of them contains poison, the rest are filled with water. They all look the same. If a pig drinks that poison it will die within 15 minutes. What is the minimum amount of pigs you need to figure out which bucket contains the poison within one hour.

Answer this question, and write an algorithm for the follow-up general case.

Follow-up:

If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the "poison" bucket within p minutes? There is exact one bucket with poison.
#### tips
If we can only test one round for 1000 buckets. We need to use 10 pigs( 2 ^ 10 = 1024 > 1000).  
We can change 1 to 1000 to 10 bit binary format, such as  
1 = 000...001  
2 = 000...010  
3 = 000...011  
From right to left, if the bit is 1 we feed these buckets which binary format' 10th bit is 1 to pig 1. ( all event buckets which ends with 1 will be feed to piggy 1)  
Feed these buckets which 9th bit is 1 to piggy 2 as so on. Feed these 1st bit is 1 to piggy 10.  
If the i-th piggy is dead, it means these buckets which i-th bit is 1 may contain poison and are feeding to the piggy.  
Then we check ten piggy and we obtain the 10 bit of buckets.  
Piggy 1st, 2nd, ....,9th, 10th,  
Status Dead, Alive, ..., Dead, Alive  
Binary: 1,0, ..., 1, 0  

For minutesToTest and minutesToDie. We can at most test   minutesToTest/minutesToDie round.  
We can change to buckets id to (t + 1) base.  
Eg. 1000 = 5 ^ 6  
1st, 2nd, 3td, 4th, 5th  
4, 3, 4 , 2, 1  
First round, feed these these buckets which 1st bit is 4 to pig 1 and so on. The five pigs are feed with the buckets which i-th bit is 4.  
If the i-th pig is die, it means the i-th bit of buckets is 4. Otherwise, the i-th pig will alive and the i-th bit of buckets may be 0,1,2,3. We can use the alive pigs to continue the test.  
For the next round, feed these these buckets which 1st bit is 3 to alive pig 1 and so on.  
Utill the 4th round, we feed these buckets which 1st bit is 1 to alive pig and so on.  
So the answer is:
(t + 1) ^ ans = buckets

#### ps 
关于t+1 的来源 因为在只进行一轮测试时 0 代表不吃 1 代表第一轮吃  
类推 在n轮中 0代表始终不吃 1代表第一次吃 2代表第二次吃 这样 利用这个不吃的状态   可以使得使用的猪更少
##### mycode with Java
```
public class Solution {
     public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if(buckets==1)
            return 0;
        int test_round=minutesToTest/minutesToDie;
        int num_base=test_round+1;
        return (int)(Math.log((double)(buckets-1))/Math.log((double)num_base))+1;
    }
}
```
