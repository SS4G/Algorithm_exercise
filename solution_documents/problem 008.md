# leetcode 8
## Question
#### String to Integer (atoi)
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
## Answer
主要在于对于各种可能的测试用例的考虑 以下是完整的测试用例情况
|input           | except output|
|----------------|------------|
|  ""            | 0          |
|  "123"         | 123        |
|  "000"         | 0          |
|  "456"         | 456        |
|  "034"         | 34         |
|  "-123"        | -123       |
|  "-0"          | 0          |
|  "7y8"         | 7          |
|  "zy9"         | 0          |    |
|  "+90"         | 90         |
|  "+0067"       | 67         |
|  "+    90"     | 0          |
|  "  +0067"     | 67         |
|  "++90"        | 0          |
|  " --0067"     | 0          |
|  "2147483648 " | 2147483648 |
|  "-2147483649" | -2147483649|
----
##### update
使用正则表达式来完成题目是个更加好的选择

##### code

```Python
class Solution(object):
  
    def is_digits2(self,x):
        p=['0','1','2','3','4','5','6','7','8','9']
        return x in p
        
    def myAtoi(self, str):
        """
        :type str: str
        :rtype: int
        """
        i=0        
        str1=str.strip()
        length_str=len(str1)
        if length_str==0:# input :""
            return 0
        elif length_str==1:#1 digits
            if self.is_digits2(str1[0]):
                return eval(str1)
            else:
                return 0
        else :#more than 1 digits 
        
            if str1[0]=='+' and self.is_digits2(str1[1]):#处理有一个正号的情况
                posflag=1
                res=self.positive_begin_trans(str1[1:])
                if res>2147483647:
                    return 2147483647
                else :
                    return res
            elif str1[0]=='-' and self.is_digits2(str1[1]):#处理有一个负号的情况
                posflag=0
                res=-self.positive_begin_trans(str1[1:])
                if res<-2147483648:
                    return -2147483648
                else :
                    return res
            elif self.is_digits2(str1[0]):#处理无正负号的数
                posflag=1
                res=self.positive_begin_trans(str1)
                if res>2147483647:
                    return 2147483647
                else :
                    return res
            else:#不符合规定的情况一律返回0
                return 0           
        return 0
    
    def positive_begin_trans(self,puc):
        """
        处理纯数字序列
        """
        i=0
        sum=0
        dict_digit_val={'0':0,'1':1,'2':2,'3':3,'4':4,'5':5,'6':6,'7':7,'8':8,'9':9}
        length=len(puc)
        j=0
        while j<length :
            if(not self.is_digits2(puc[i])):
                break
            sum*=10
            sum+=dict_digit_val[puc[i]]
            i+=1
            j+=1
        return sum
```

#### newcode
//使用正则表达式来完成
```
class Leet008 {
    public int myAtoi(String str) {
        Pattern checkPat = Pattern.compile("\\s*[\\+\\-]?\\d+");
        Pattern pat = Pattern.compile("[\\+\\-]?\\d+");
        Matcher m0 = pat.matcher(str);
        Matcher m1 = checkPat.matcher(str);
        if (!m1.lookingAt()) {
            return 0;
        }
        if (m0.find()) {
            String validPart = m0.group(0);
            return validStr(validPart);
        }
        else {
            return 0;
        }
    }

    private int validStr(String s) {
        boolean negFlag = false;
        String numPart;
        if (s.charAt(0) == '+') {
            negFlag = false;
            numPart = s.substring(1);
        }
        else if (s.charAt(0) == '-') {
            negFlag = true;
            numPart = s.substring(1);
        }
        else {
            negFlag = false;
            numPart = s;
        }
        if (numPart.length() > 12)
            return negFlag? Integer.MIN_VALUE: Integer.MAX_VALUE;

        long numVal = Long.parseLong(numPart);
        if (numVal <= 0x7fffffffL) {
            return negFlag? (int)(-numVal): (int)numVal;
        }
        else if (numVal == 0x80000000L) {
            return negFlag? (int)(-numVal): Integer.MAX_VALUE;
        }
        else {
            return negFlag? Integer.MIN_VALUE: Integer.MAX_VALUE;
        }
    }
}
```

