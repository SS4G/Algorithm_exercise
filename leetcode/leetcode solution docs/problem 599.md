## 599. Minimum Index Sum of Two Lists My SubmissionsBack To Contest

Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

Example 1:
Input:

```
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
```

Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".
Example 2:
Input:

```
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
```

Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
##### Note:
- The length of both lists will be in the range of [1, 1000].
- The length of strings in both lists will be in the range of [1, 30].
- The index is starting from 0 to the list length minus 1.
- No duplicates in both lists.  

#### tips
用字典记录第一个 list的索引
用第二个list去查找 找到就计算index的和 把最小index和的 字符串保存起来

#### mycode

```Python
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

        result = [("dummy", 100000000), ] #init val 
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
```
