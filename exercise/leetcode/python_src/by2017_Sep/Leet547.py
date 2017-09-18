class Solution(object):
    def findCircleNum(self, M):
        """
        :type M: List[List[int]]
        :rtype: int
        """
        relation = [i for i in range(len(M))] # searched by index
        for i in range(len(M)):
            for j in range(i+1, len(M)):

                if M[i][j]:
                    tmp0 = j
                    while relation[tmp0] != tmp0:
                        tmp0 = relation[tmp0]
                    tmp1 = i
                    while relation[tmp1] != tmp1:
                        tmp1 = relation[tmp1]
                    relation[tmp0] = tmp1
        cnt = 0
        for i in range(len(M)):
            if relation[i] == i:
                cnt += 1
        return cnt

if __name__ == "__main__":
    s = Solution()
    mat = [[1, 0, 0, 1], [0, 1, 1, 0], [0, 1, 1, 1], [1, 0, 1, 1]]
    mat = [[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]]
    #mat = [[1, 1, 1], [1, 1, 1], [1, 1, 1], ]
    print(s.findCircleNum(mat))

