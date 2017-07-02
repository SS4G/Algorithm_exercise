class LogSystem(object):
    def __init__(self):
        self.storage = []
        self.full = [2017, 12, 31, 23, 59, 59]
        self.zero = [2000, 1, 1, 0, 0, 0]

    def put(self, id, timestamp):
        """
        :type id: int
        :type timestamp: str
        :rtype: void
        """
        self.storage.append((tuple(self.toTime(timestamp)), id))

    def retrieve(self, s, e, gra):
        """
        :type s: str
        :type e: str
        :type gra: str
        :rtype: List[int]
        """
        start = self.toTime(s)
        end = self.toTime(e)
        setSt = {"Year": 1, "Month": 2, "Day": 3, "Hour": 4, "Minute": 5}
        if gra != "Second":
            for i in range(setSt[gra], 6):
                start[i] = self.zero[i]
                end[i] = self.full[i]
        start = tuple(start)
        end = tuple(end)
        res = []
        for i in self.storage:
            if start <= i[0] <= end:
                res.append(i[1])
        return res

    def toTime(self, str0):
        return [int(j) for j in str0.split(":")]


        # Your LogSystem object will be instantiated and called as such:
        # obj = LogSystem()
        # obj.put(id,timestamp)
        # param_2 = obj.retrieve(s,e,gra)

if __name__ == "__main__":
    s = LogSystem()
    #s.put(1, "2017:01:01:23:59:59")
    #s.put(2, "2017:01:01:22:59:59")
    #s.put(3, "2016:01:01:00:00:00")
    #r0 = s.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year")
    #r1 = s.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour")
    s.put(1, "2017:01:01:23:59:59")
    s.put(2, "2017:01:01:22:59:59")
    s.put(3, "2016:01:01:00:00:00")
    r0 = s.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year")
    r1 = s.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour")

    print(r0)
    print(r1)