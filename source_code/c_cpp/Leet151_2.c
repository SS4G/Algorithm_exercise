#include <stdio.h>
#include <string.h>
#define LEADING_SPACE 0
#define NON_SPACE 1
#define ONE_SPACE 2
void exchangeChar(char* s, int indexA, int indexB) {
    s[indexB] = s[indexA]^s[indexB];
    s[indexA] = s[indexA]^s[indexB];
    s[indexB] = s[indexA]^s[indexB];
}
void preProcessor(char *s) {
    //O(n)
    int valid = 0;
    int tobeValid = 0;
    int state = LEADING_SPACE;
    int lastSpaceIndex = 0;
    while (s[tobeValid] != '\0') {
    switch(state) {
        case LEADING_SPACE:
            if (s[tobeValid] != ' ') {
                state = NON_SPACE;
                s[valid] = s[tobeValid];
                valid ++;
                lastSpaceIndex = valid;
            }
            break;
        case NON_SPACE:
            if (s[tobeValid] == ' ') {
                state = ONE_SPACE;
            }
            else {
                s[valid] = s[tobeValid];
                valid ++;
                lastSpaceIndex = valid;
            }
            break;
        case ONE_SPACE:
            if (s[tobeValid] != ' ') {
                state = NON_SPACE;
                s[valid] = ' ';
                valid ++;
                s[valid] = s[tobeValid];
                //printf("aassert %c %c\n",s[tobeValid],s[valid]);
                valid ++;
                lastSpaceIndex = valid;
            }
            break;
        default: printf("unknow\n");
        }
        tobeValid++;
    }
    s[lastSpaceIndex] = '\0';
}
void reversePart(char *s, int sta, int end)
{
    int half = (end - sta) >> 1;
    int i;
    for(i=0;i<half;i++)
        exchangeChar(s, sta+i, end-1-i);
}
char isSpliter(char x) {
    return x == '\0' || x == ' ';
}
void reverseWords(char *s) {

    preProcessor(s);
    //printf("%s\n", s);
    int length = strlen(s);
    reversePart(s, 0, length);
    //printf("%s\n", s);
    int i = 0;
    char flag = 1;
    int st = 0;
    int end = 1;
    for (i=0;i<length+1;i++) {
        //printf("%d\n", s[i]);
        //printf("ascii = %d, ch = %c i=%d\n", s[i], s[i], i);
        if (isSpliter(s[i]) && flag == 1)
        {
            flag = 0;
            end = i;
            reversePart(s, st, end);
            //printf("st = %d ed = %d\n", st, end);
            //printf("ascii = %d, i=%d\n", s[i], i);
        }
        else if (flag == 0 && !isSpliter(s[i])) {
            st = i;
            flag = 1;
        }
    }
}
int main() {
    char arr[100] = {"hello world!"};
    //reversePart(arr, 7, 12);
    //printf("%s", arr);
    reverseWords(arr);
    printf("%s", arr);
    return 0;
}