## 468. Validate IP Address

Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,
```
172.16.254.1
```
;

Besides, leading zeros in the IPv4 is invalid. For example, the address 
```
172.16.254.01
```
 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 
```
2001:0db8:85a3:0000:0000:8a2e:0370:7334
```
 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 
```
2001:db8:85a3:0:0:8A2E:0370:7334
```
 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 
```
2001:0db8:85a3::8A2E:0370:7334
```
is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 
```
02001:0db8:85a3:0000:0000:8a2e:0370:7334
```
 is invalid.

Note: You may assume there is no extra space or special characters in the input string.

Example 1:

```
Input: "172.16.254.1"
```

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".
Example 2:

```
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
```

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".
Example 3:

```
Input: "256.256.256.256"
```

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.

#### tips
这个也没啥说的 就是用split分开以后 逐个部分去判断

#### mycode

```
class Solution(object):
    def validIPAddress(self, IP):
        """
        :type IP: str
        :rtype: str
        """
        res = self.validIP(IP)
        if res == 1:
            return "IPv4"
        elif res == 2:
            return "IPv6"
        else:
            return "Neither"

    def validIP(self, IP):
        ipv4s = IP.split(".")
        if len(ipv4s) == 4:
            for i in ipv4s:
                print(i)
                if not self.validIPv4Part(i):
                    return -1
            return 1
        else:
            ipv6s = IP.split(":")
            if len(ipv6s) == 8:
                for i in ipv6s:
                    if not self.validIpv6Part(i):
                        return -1
                return 2
            else:
                return -1

    def validIPv4Part(self, i):
        for c in i:
            if c not in "0123456789":
                return False
        if len(i) >= 4 or len(i) <= 0:
            return False
        if len(i) > 1 and i[0] == '0':
            return False
        else:
            if 0 <= int(i) <= 255:
                pass
            else:
                return False
        return True

    def validIpv6Part(self, s):
        if 1 <= len(s) <= 4:
            pass
        else:
            return False
        for i in s:
            if i not in "0123456789abcdefABCDEF":
                return False
        return True

if __name__ == "__main__":
    s = Solution()
    ips = ["172.16.254.1", "172.16.254.01",
           "2001:0db8:85a3:0000:0000:8a2e:0370:7334", "2001:db8:85a3:0:0:8A2E:0370:7334",
           "2001:0db8:85a3::8A2E:0370:7334", "02001:0db8:85a3:0000:0000:8a2e:0370:7334",
           "1e1.4.5.6", "192.0.0.1",
           ]
    res = ["IPv4", "Neither", "IPv6", "IPv6", "Neither", "Neither", "Neither", "IPv4"]
    print(s.validIPAddress("192.0.0.1"))
    """
        for i in range(len(ips)):
        ip = ips[i]
        assert s.validIPAddress(ip) == res[i], "WA "+str(i)+"true is "+res[i]

    """
```
