class Solution(object):
    def computeArea(self, A, B, C, D, E, F, G, H):
        """
        :type A: int rectangle_1-left
        :type B: int rectangle_1-down
        :type C: int rectangle_1-right
        :type D: int rectangle_1-up
        
        :type E: int rectangle_2-left
        :type F: int rectangle_2-down
        :type G: int rectangle_2-right
        :type H: int rectangle_2-up
        :rtype: int
        """
        
        x_width=0
        y_height=0
        x_list=[]
        y_list=[]
        area=0
        if A>=G or C<=E or F>=D or H<=B:
            commen_area=0
        else :
            x_list.append(A)
            x_list.append(C)
            x_list.append(E)
            x_list.append(G)
            
            y_list.append(B)
            y_list.append(F)
            y_list.append(D)
            y_list.append(H)
            
            x_list.sort()   
            y_list.sort()

            x_width=abs(x_list[2]-x_list[1])
            y_width=abs(y_list[2]-y_list[1])
            commen_area=x_width*y_width
            
        area=(C-A)*(D-B)+(H-F)*(G-E)-commen_area
        return area
        
s=Solution()
A=-3
B=0 
C=3 
D=4 
E=0 
F=-1
G=9 
H=2
print(s.computeArea(A, B, C, D, E, F, G, H))   #res return 6  

A=-2
B=-2
C=2
D=2
E=3
F=3
G=4
H=4 

print(s.computeArea(A, B, C, D, E, F, G, H))   #res return 6           
