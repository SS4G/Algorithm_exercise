## 635. Design Log Storage System
You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

Design a log storage system to implement the following functions:

void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.


int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.

Example 1:

```
put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
```

- retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
- retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.


Note:
- There will be at most 300 operations of Put or Retrieve.
- Year ranges from [2000,2017]. Hour ranges from [00,23].
- Output for Retrieve has no order required.
- 

#### tips
这个可以使用python的 元组来表示log 符合按照次序进行比较的特性

需要注意的是粒度这个问题 这个意味着粒度以后的单位都将被忽略

#### mycode


```
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
```
