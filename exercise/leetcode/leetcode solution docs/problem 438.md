## 438. Find All Anagrams in a String
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.


```
Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
```
#### Tips
使用滑动窗口 来进行pattern与整体字符串的比较
要点是 滑动窗口时 无需每次滑动就去全部将窗口内的字符去从头统计一遍，要使用相对变化的方法 将一次滑动后 新进入的字符在原有基础上加一 将滑出的字符在原来的基础上减一即可 只有开始时要将整个窗口内的字符重新统计一遍

##### mycode
```
class Solution(object):
    def findAnagrams(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: List[int]
        """
        res=[]
        s_list=list(s)
        p_list=list(p)
        length_p=len(p_list)
        length_s=len(s_list)
        letter_dict_s={
        "a":0,"b":0,"c":0,"d":0,"e":0,"f":0,"g":0,"h":0,"i":0,"j":0,
        "k":0,"l":0,"m":0,"n":0,"o":0,"p":0,"q":0,"r":0,"s":0,"t":0,
        "u":0,"v":0,"w":0,"x":0,"y":0,"z":0,        
        }
        letter_dict_p={}
        if length_s<length_p:#impossible 
            return []            
            
        for p0 in p_list:
            if p0 not in letter_dict_p:
                letter_dict_p[p0]=1
            else:
                letter_dict_p[p0]+=1
                
        for s0 in letter_dict_s:
            letter_dict_s[s0]=0 
                
        for i in range(0,length_s-length_p+1):        
            equal_flag=True            
            if i==0:
                for letter in s_list[i:i+length_p]:
                    letter_dict_s[letter]+=1
                #print(letter_dict_s)   
            if i>0:
                letter_dict_s[s_list[i+length_p-1]]+=1
                letter_dict_s[s_list[i-1]]-=1
                      
            for j0 in letter_dict_p:
                if letter_dict_p[j0]!=letter_dict_s[j0]:
                    equal_flag=False
                    break

            if equal_flag :
                res.append(i)
        return res
```
