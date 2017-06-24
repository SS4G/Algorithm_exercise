import collections
class Solution(object):
    def groupAnagrams(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """

        angDict = collections.defaultdict(list)
        for s in strs:
            a = list(s)
            a.sort()
            angDict[tuple(a)].append(s)
        return [angDict[k] for k in angDict]

if __name__ == "__main__":
    s = Solution()
    arr = ["eat", "tea", "tan", "ate", "nat", "bat"]
    print(s.groupAnagrams(arr))