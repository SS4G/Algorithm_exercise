class Solution(object):
    def isIsomorphic(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        length = len(s)
        zip_list=zip(s,t)
        map_dict={}
        for a,b in zip_list:#get a key-val 
            if a not in map_dict:#check if a is new key
                if b not in map_dict.values():#new key-val  
                    map_dict[a]=b
                else:# a not exist but b has existed ,a1->b a2->b situation occur!
                    return False
            else:
                if map_dict[a]!=b:# a->b1 a->b2 situation occured ,error
                    return False
                else:
                    continue
        return True
        
s=Solution()
print(s.isIsomorphic("abcde","fghij"))       
print(s.isIsomorphic("egg", "add"))       
print(s.isIsomorphic("paper", "title"))       
print(s.isIsomorphic("foo", "bar"))       
        
        