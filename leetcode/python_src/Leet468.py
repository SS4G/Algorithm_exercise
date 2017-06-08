import re
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
