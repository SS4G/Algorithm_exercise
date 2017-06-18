## 551. Student Attendance Record I
You are given a string representing an attendance record for a student. The record only contains the following three characters:


```
'A' : Absent.
'L' : Late.
'P' : Present.
```

A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.


```
Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False
```


#### tips
简单状态机
#### my code
```Java
public class Solution {
    public static boolean checkRecord(String s) {
        char[] sArr = s.toCharArray();
        int[] a = {0, 0};
        int lMax = 0;
        for (char c : sArr) {
            if (c == 'L') {
                a[0]++;
            }
            else if (c == 'A') {
                a[1]++;
                lMax = lMax > a[0]? lMax : a[0];
                a[0] = 0;
            }
            else {
                lMax = lMax > a[0]? lMax : a[0];
                a[0] = 0;
            }
        }
        lMax = lMax > a[0]? lMax : a[0]; //注意尾部连续L的处理 要将结果写回
        return lMax <= 2 && a[1] <= 1;
    }
}
```
