class Solution(object):
    def compareVersion(self, version1, version2):
        """
        :type version1: str
        :type version2: str
        :rtype: int
        """
        list_str_ver1=version1.split('.')
        list_str_ver2=version2.split('.')        
        list_int_ver1=[]
        list_int_ver2=[]
        for k in list_str_ver1:
            list_int_ver1.append(int(k))
        for k in list_str_ver2:
            list_int_ver2.append(int(k))            
            
        l1=len(list_int_ver1)
        l2=len(list_int_ver2)
        min_len=l1 if l1<=l2 else l2
        for i in range(min_len):
            if list_int_ver1[i]<list_int_ver2[i]:
                return -1
            elif list_int_ver1[i]>list_int_ver2[i]:
                return 1
        
        if l1==l2:#l1==l2==min_len
            return 0
        elif l1>l2:#
            if sum(list_int_ver1[i+1:])>0: 
                return 1
            else :
                return 0
        else :#
            if sum(list_int_ver2[i+1:])>0: 
                return -1
            else :
                return 0

        
        
s=Solution()
v1="1"
v2="0"
print(s.compareVersion(v1,v2))#should return 1
v1="1.0"
v2="0.1"
print(s.compareVersion(v1,v2))#should return 1
v1="1.0.2"
v2="1.0.1"
print(s.compareVersion(v1,v2))#should return 1
v1="1.0.1"
v2="1.0.1"
print(s.compareVersion(v1,v2))
v1="0.0.1"
v2="1.0.1"
print(s.compareVersion(v1,v2))
v1="1.0.1.1"
v2="1.0.1"
print(s.compareVersion(v1,v2))    
v1="1.0"
v2="1"
print(s.compareVersion(v1,v2))   
print([1,1]==[1,1])        
            