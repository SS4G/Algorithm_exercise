# The guess API is already defined for you.
# @param num, your guess
# @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
def guess(num):
    std=2
    if num>std:
        return -1
    elif num==std:
        return 0
    else :
        return 1

class Solution(object):
    def guessNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        #1,2 ->    if guess 2  =dead circle
        #1,2,3 ->  1 if guess is 2 =dead circle
        #1,2,3,4 ->2
        #result define
        HIGHER=1
        LOWER=-1
        EQUAL=0
        
        up=n
        down=1
        if up == down:
            return up
        elif up-down==1:
            return up if guess(up)==EQUAL else down        
        elif up-down==2:
            if guess(up)==EQUAL:
                return up
            elif guess(up-1)==EQUAL:
                return up-1
            elif guess(down)==EQUAL:
                return down
        
        my_num=(up+down)//2
        res=guess(my_num)
        while res!=EQUAL:
            if res==LOWER:
                up=my_num  
            else:
                down=my_num
            print("up=",up,"down=",down)
            if up-down<=2:
                if up == down:
                    return up
                elif up-down==1:
                    return up if guess(up)==EQUAL else down        
                elif up-down==2:
                    if guess(up)==EQUAL:
                        return up
                    elif guess(up-1)==EQUAL:
                        return up-1
                    elif guess(down)==EQUAL:
                        return down 
            
            my_num=(up+down)//2
            res=guess(my_num)            
        return my_num
s=Solution()
print(s.guessNumber(3))

            
            