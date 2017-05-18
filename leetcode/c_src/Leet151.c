#include <stdio.h>
#include <string.h>
#define LEADING_SPACE 0
#define NON_SPACE 1
#define ONE_SPACE 2
#define LEFT 1
#define RIGHT -1
void reverseWords(char *s) {
    preProcessor(s);
    int wordAmount = countWords(s);
    if (wordAmount%2==0) {

    }
    else {

    }
}
void preProcessor(char *s) {
    //O(n)
    int valid = 0;
    int tobeValid = 0;
    int state = LEADING_SPACE;
    int lastSpaceIndex = -1;
    while (s[tobeValid] != '\0') {
    switch(state) {
        case LEADING_SPACE:
            if (s[tobeValid] != ' ') {
                state = NON_SPACE;
                s[valid] = s[tobeValid];
                valid ++;
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
                valid ++;
            }
            break;
        default: printf("unknow\n");
        }
        tobeValid++;
    }
    s[lastSpaceIndex] = '\0';
}
int countWords(char *s) {
    int i = 0;
    int cnt = 0;
    while (s[i]!='\0') {
        if (s[i] == ' ')
            cnt++;
    }
    return cnt;
}
int reverseTwoWordsInPlace(char *s, int begin, int end) {
    // begin include
    // end exclude
    int firstSpace = findFirstSpace(s, begin);
    int lastSpace = findLastSpace(s, end-1)
    int lastWordSt = lastSpace+1;
    // "w0 xxx w1"
    int w0Length = firstSpace - begin
    int w1Length = end - lastWordSt;

    int w0st = begin;
    int w1st = lastWordSt;
    int w0ed = w0st+w0Length;
    int w1ed = w1st+w1Length;

    if (w0Length <= w1Length) {
        int offset = 0;
        while (offset < w0Length) {
            exchangeChar(s, w0st+offset, w1st+offset);
            offset++;
        }
        while (offset < w1Length) {
            char tmp = s[w1st+offset];
            moveString(s, w0st+offset, w1st+offset+1, RIGHT, 1);
            s[w0st+offset] = tmp;
            offset++;
        }
    }
    else {
        int offset = 0;
        while (offset < w1Length) {
            exchangeChar(s, w0ed-offset-1, w1ed-offset-1);
            offset++;
        }
        while (offset < w0Length) {
            char tmp = s[w0ed-offset-1];
            moveString(s, w0ed-offset-2, w1ed-offset-1, LEFT, 1);
            s[w1ed-offset-2] = tmp;
            offset++;
        }
    }
}
int moveString(char *s, int st, int end, int dir, int distance) {
    //dir >= 0 to left dir < 0 to right
    //st include
    //end exclude
    int ptr = 0;
    if (dir>=0) {
        ptr = st;
        while(ptr<end) {
            s[ptr-distance] = s[ptr];
            ptr+=1;
        }
    }
    else {
        ptr = end+distance-1;
        while(ptr>=st+distance){
            s[ptr] = s[ptr-distance];
            ptr-=1;
        }
    }
}
int findFirstSpace(char *s, int startIndex) {
    //second argument is an non-space char index
    int st = startIndex;
    while (s[st] != ' ' && s[st] != '\0')
        st++;
    return s[st] == '\0' ? -1: st;
}
int findLastSpace(char *s, int startIndex) {
    int back = startIndex;
    while (back>=0)
        if (s[back] == ' ')
            break;
        back--;
    return back;
}
int exchangeChar(char* s, int indexA, int indexB) {
    s[indexB] = s[indexA]^s[indexB];
    s[indexA] = s[indexA]^s[indexB];
    s[indexB] = s[indexA]^s[indexB];
}
int main() {
    char s[100] = {"  ABC   DEF  GHI  "};
    preProcessor(s);
    moveString(s, 4, 7, LEFT, 3);
    printf("%s,\n", s);
    return 0;
}