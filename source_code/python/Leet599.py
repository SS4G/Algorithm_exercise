class Solution(object):
    def findRestaurant(self, list1, list2):
        """
        :type list1: List[str]
        :type list2: List[str]
        :rtype: List[str]
        """
        if len(list2) == 0 or len(list1) == 0:
            return []

        recipeDict = {}
        for i in range(len(list1)):
            recipeDict[list1[i]] = i

        result = [("dummy", 100000000), ]
        for i in range(len(list2)):
            if list2[i] in recipeDict:
                if result[0][1] > i+recipeDict[list2[i]]:
                    result = [(list2[i], i+recipeDict[list2[i]]), ]
                elif result[0][1] == i+recipeDict[list2[i]]:
                    result.append((list2[i], i+recipeDict[list2[i]]))

        return [i[0] for i in result]

if __name__ == "__main__":
    s = Solution()
    l1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]
    l2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
    l1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]
    l2 = ["Tapioca Express", "Shogun", "Burger King"]
    print(s.findRestaurant(l1, l2))