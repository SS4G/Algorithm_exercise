## 393. UTF-8 Validation Add to List

A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

```
Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
```

Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

Note:
The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.


```
Example 1:

data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.
```

Return true.  
It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.

```
Example 2:

data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.
```

Return false.  
The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
The next byte is a continuation byte which starts with 10 and that's correct.
But the second continuation byte does not start with 10, so it is invalid.
Subscribe to see which companies asked this question.
#### tips
简单的小状态机
就用位运算来操作没啥难度
#### mycode
```Python
class Solution(object):
    def validUtf8(self, data):
        """
        :type data: List[int]
        :rtype: bool
        """

        i = 0
        while i < len(data):
            num = data[i]
            types = self.judge(num)
            if types == 6:
                return False
            # print("i=", i, " types=", types)
            j = 1
            while j < types:
                if i+j >= len(data):
                    return False
                num = data[i+j]
                if self.judge(num) != 5:
                    return False
                j += 1
            i = i + types
        return True

    def judge(self, x):
        x &= 0xff
        FIRSTMASK_2B = 0xc0
        FIRSTMASK_3B = 0xe0
        FIRSTMASK_4B = 0xf0
        SECONDMASK = 0x80
        #print("bin = ", bin(x), x)
        if x & 0x80 == 0:
            #print("type = 1")
            return 1  # 1byte 1st
        if x & 0xe0 == FIRSTMASK_2B:
            #print("type = 2")
            return 2  # 2byte 1st
        if x & 0xf0 == FIRSTMASK_3B:
            #print("type = 3")
            return 3  # 3byte 1st
        if x & 0xf8 == FIRSTMASK_4B:
            #print("type = 4")
            return 4  # 4byte 1st
        if x & 0xc0 == SECONDMASK:
            #print("type = 5")
            return 5  # nbyte start
        #print("type = 6")
        return 6 # unknow type

if __name__ == "__main__":
    s = Solution()
    data = [39, 89, 227, 83, 132, 95, 10, 0]
    # print(s.judge(227))
    print(s.validUtf8(data))
```
