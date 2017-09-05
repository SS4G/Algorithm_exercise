import collections
import operator
from AlgorithmTraining.G55Utils.Py.Utils import *
class Solution:
    def averageOfLevels(self, root):
        """
        :type root: TreeNode
        :rtype: List[float]
        """
        dictRec = collections.defaultdict(list)
        self.averageHelper(root, 0, dictRec)
        result = []
        for k in dictRec:
            result.append(((sum(dictRec[k]) / len(dictRec[k]), k)))
        result.sort(key=operator.itemgetter(1))
        return [i[0] for i in result]

    def averageHelper(self, root, level, dictRec):
        if root is None:
            return None
        else:
            dictRec[level].append(root.val)
            self.averageHelper(root.left, level + 1, dictRec)
            self.averageHelper(root.right, level + 1, dictRec)
            return None

if __name__ == "__main__":
    s = Solution()
    arr = [3, 9, 20, None, None, 15, 7]
    root = TreeUtil.deserialize(arr)
    res = s.averageOfLevels(root)
    print(res)
