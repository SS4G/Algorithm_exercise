import operator
class User:
    def __init__(self, userId):
        self.uid = -1
        self.newsFeed = []
        self.followees = set([])

class Twitter(object):
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.userDict = {}
        self.time = 0

    def postTweet(self, userId, tweetId):
        """
        Compose a new tweet.
        :type userId: int
        :type tweetId: int
        :rtype: void
        """
        if userId not in self.userDict:
            self.userDict[userId] = User(userId)
        self.userDict[userId].newsFeed.append((tweetId, self.time))
        self.time += 1

    def getNewsFeed(self, userId):
        """
        Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
        :type userId: int
        :rtype: List[int]
        """
        if userId not in self.userDict:
            self.userDict[userId] = User(userId)

        myTweets = self.getOnestweets(userId, 10)
        for followee in self.userDict[userId].followees:
            myTweets.extend(self.getOnestweets(followee, 10))
        myTweets.sort(key=operator.itemgetter(1), reverse=True)
        if len(myTweets) <= 10:
            return [i[0] for i in myTweets]
        else:
            return [i[0] for i in myTweets[:10]]

    def getOnestweets(self, userId, amount):
        if userId not in self.userDict:
            return []
        else:
            if len(self.userDict[userId].newsFeed) < amount:
                myTweets = self.userDict[userId].newsFeed[:]
            else:
                myTweets = self.userDict[userId].newsFeed[len(self.userDict[userId].newsFeed) - amount:]
            return myTweets

    def follow(self, followerId, followeeId):
        """
        Follower follows a followee. If the operation is invalid, it should be a no-op.
        :type followerId: int
        :type followeeId: int
        :rtype: void
        """
        if followerId not in self.userDict:
            self.userDict[followerId] = User(followerId)
        self.userDict[followerId].followees.add(followeeId)

    def unfollow(self, followerId, followeeId):
        """
        Follower unfollows a followee. If the operation is invalid, it should be a no-op.
        :type followerId: int
        :type followeeId: int
        :rtype: void
        """
        if followerId not in self.userDict:
            self.userDict[followerId] = User(followerId)
        else:
            if followeeId in self.userDict[followerId].followees:
                self.userDict[followerId].followees.remove(followeeId)
        # Your Twitter object will be instantiated and called as such:
        # obj = Twitter()
        # obj.postTweet(userId,tweetId)
        # param_2 = obj.getNewsFeed(userId)
        # obj.follow(followerId,followeeId)
        # obj.unfollow(followerId,followeeId)

if __name__ == "__main__":
    # operations = ["Twitter", "postTweet", "getNewsFeed", "follow", "getNewsFeed", "unfollow", "getNewsFeed"]
    # args =       [[],         [1, 1],      [1],          [2, 1],   [2],            [2, 1],     [2]]
    operations = ["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed","unfollow","getNewsFeed"]
    args =      [ [],       [1,5],       [1],          [1,2],   [2,6],      [1],          [1,2],     [1]]
    for i in range(len(operations)):
        op = operations[i]
        args0 = args[i]
        if op == "Twitter":
            obj = Twitter()
        elif op == "postTweet":
            obj.postTweet(args0[0], args0[1])
        elif op == "getNewsFeed":
            print(i, obj.getNewsFeed(args0[0]))
        elif op == "follow":
            obj.follow(args0[0], args0[1])
        elif op == "unfollow":
            obj.unfollow(args0[0], args0[1])
    # ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
    # [[],         [1, 5],      [1],           [1, 2],   [2, 6],      [1],           [1, 2],     [1]]