class Solution(object):
    def __init__(self):
        self.HEATER_TYPE = 0x1
        self.HOUSE_TYPE = 0x2

    def findRadius(self, houses, heaters):
        """
        :type houses: List[int]
        :type heaters: List[int]
        :rtype: int
        """
        # element [pos, type, leftNearest, rightNearest]
        newList = []
        houses.sort()
        heaters.sort()
        i = 0
        j = 0
        while i < len(houses) and j < len(heaters):
            if houses[i] < heaters[j]:
                newList.append([houses[i], self.HOUSE_TYPE, -1, -1])
                i += 1
            elif houses[i] > heaters[j]:
                newList.append([heaters[j], self.HEATER_TYPE, -1, -1])
                j += 1
            else:
                newList.append([houses[i], self.HEATER_TYPE | self.HOUSE_TYPE, -1, -1])
                i += 1
                j += 1
        while i < len(houses):
            newList.append([houses[i], self.HOUSE_TYPE, -1, -1])
            i += 1
        while j < len(heaters):
            newList.append([heaters[j], self.HEATER_TYPE, -1, -1])
            j += 1

        print(newList)

        for k in range(len(newList)):
            if newList[k][1] & self.HOUSE_TYPE:
                self.getNearestHeater(newList, k)
        print(newList)
        return max([min(i[2], i[3]) for i in newList if i[1] & self.HOUSE_TYPE])

    def getNearestHeater(self, arr, houseIndex):
        k = houseIndex
        assert arr[k][1] & self.HOUSE_TYPE, "wtf"
        if arr[houseIndex][1] & self.HEATER_TYPE:
            left = 0
            right = 0
        elif k == 0:
            j = 0
            while j < len(arr):
                if arr[j][1] & self.HEATER_TYPE:
                    break
                j += 1
            left = 0xffffffff
            right = arr[j][0] - arr[k][0]
        elif k == len(arr) - 1:
            j = len(arr) - 1
            while j < len(arr):
                if arr[j][1] & self.HEATER_TYPE:
                    break
                j -= 1
            right = 0xffffffff
            left = arr[k][0] - arr[j][0]
        else:
            if arr[k - 1][1] & self.HEATER_TYPE:
                left = arr[k][0] - arr[k - 1][0]
            else:
                left = arr[k - 1][2] + (arr[k][0] - arr[k - 1][0])

            if arr[k + 1][1] & self.HEATER_TYPE:
                right = arr[k + 1][0] - arr[k][0]
            else:
                if arr[k - 1][1] & self.HEATER_TYPE:
                    j = k
                    while j < len(arr):
                        if arr[j][1] & self.HEATER_TYPE:
                            break
                        j += 1
                    if j >= len(arr):
                        right = 0xffffffff
                    else:
                        right = arr[j][0] - arr[k][0]
                else:
                    right = arr[k - 1][3] - (arr[k][0] - arr[k - 1][0])

        arr[k][2] = left
        arr[k][3] = right

if __name__ == "__main__":
    s = Solution()
    house = [942727722, 83454666, 108728549, 685118024]
    heater = [60806853, 571540977]
    r = s.findRadius(heaters=heater, houses=house)
    print(r)