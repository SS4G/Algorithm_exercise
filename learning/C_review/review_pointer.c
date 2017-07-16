# include <stdio.h>
void func();
void funa(int id);
void funb(int id);
void fund(char x);
int main() {
//func();
void (*callBackPtr)(int);
callBackPtr = funa;
callBackPtr(199);
callBackPtr = funb;
callBackPtr(1200);
callBackPtr = fund;
callBackPtr('K');
return 0;
}

void funa(int id) {
    printf("this is funa args:%d\n", id);
}
void funb(int id) {
    printf("this is funb args:%d\n", id);
}
void fund(char x) {
    printf("this is fund args:%c\n", x);
}

void func() {
    int a = 56;
    int *ptra = &a;
    printf("addr of a is %p\n", &a);
    printf("val of ptra is %p\n", ptra);

    int arr[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int k;
    for (k = 0; k < 10; k++) {
        printf("%d ", arr[k]);
    }
    printf("arr val %p\n", arr);
    printf("arr[0] val %p\n", &(arr[0]));
    printf("arr[3] val %d\n", *(arr + 3));
    char *x = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char (*cPtr0)[2] = (char (*)[2])x;
    int i;
    for (i = 0; i < 3; i++) {
        printf("Val = %c", *((char*)(cPtr0 + i)));
    }


}
