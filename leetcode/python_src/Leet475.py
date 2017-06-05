from operator import itemgetter
import time
import testcase
class Solution(object):
    def __init__(self):
        self.time0=0
        self.time1=0
        self.time2=0
        self.time3=0
        self.time4=0

    def findRadius(self, houses, heaters):
        """
        :type houses: List[int]
        :type heaters: List[int]
        :rtype: int
        """
        all_list=[]
        #use tuple (position , type)
        all_list.append((-1,2))# type 2 boundary
        all_list.append((1000000001,2))# type 2 boundary
        self.time0 = time.time()
        for i in houses:
            all_list.append((i,0))# 0 type= house
        for j in heaters:
            all_list.append((j,1))
        self.time1 = time.time()
        all_list_sorted=sorted(all_list,key=itemgetter(0),reverse=False)
        length = len(all_list_sorted)
        max_min_distance=-1
        #print(all_list_sorted)
        self.time2 = time.time()
        for i in range(1,length-1):
            left_distance = 1000000001
            right_distance= 1000000001
            house_rad = -1
            if all_list_sorted[i][1]==0:#this point is house

                #go right
                j = i
                while all_list_sorted[j][1] != 2 and all_list_sorted[j][1] != 1 :
                    j+=1
                if all_list_sorted[j][1]==1:
                    #print("reach right",i)
                    left_distance =abs(all_list_sorted[j][0]-all_list_sorted[i][0])
                elif all_list_sorted[j][1]==2:
                    left_distance =1000000001

                #go left
                k = i
                while all_list_sorted[k][1] != 2 and all_list_sorted[k][1] != 1 :
                    k -= 1
                if all_list_sorted[k][1] == 1:
                    #print("reach left",i)
                    right_distance=abs(all_list_sorted[i][0]-all_list_sorted[k][0])
                elif all_list_sorted[k][1] == 2:
                    right_distance =1000000001
                house_rad=min((left_distance, right_distance))
            max_min_distance = max(house_rad,max_min_distance)
        self.time3 = time.time()
        return max_min_distance
S=Solution()
print(S.findRadius(testcase.houses,testcase.heaters))
print(S.time1-S.time0,S.time2-S.time1,S.time3-S.time2)
