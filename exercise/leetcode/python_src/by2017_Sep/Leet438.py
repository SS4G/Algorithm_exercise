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
            
s=Solution()
print(s.findAnagrams("cbaebabacd","abc"))
            
            
            
        
        
