import collections


class Solution(object):
    def findItinerary(self, tickets):
        """
        :type tickets: List[List[str]]
        :rtype: List[str]
        """
        startDict = collections.defaultdict(list)
        endDict = collections.defaultdict(list)
        for i in range(len(tickets)):
            startDict[tickets[i][0]].append(i)
            endDict[tickets[i][1]].append(i)

