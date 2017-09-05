#include <stdio.h>
#include <stdbool.h>
bool isPowerOfFour(int num) {
    uint32_t num_copy=(uint32_t)num;
    switch(num_copy)
    {
        case 0x00000001:return true;break;
        case 0x00000004:return true;break;
        case 0x00000010:return true;break;
        case 0x00000040:return true;break;
        case 0x00000100:return true;break;
        case 0x00000400:return true;break;
        case 0x00001000:return true;break;
        case 0x00004000:return true;break;
        case 0x00010000:return true;break;
        case 0x00040000:return true;break;
        case 0x00100000:return true;break;
        case 0x00400000:return true;break;
        case 0x01000000:return true;break;
        case 0x04000000:return true;break;
        case 0x10000000:return true;break;
        case 0x40000000:return true;break;
        default:return false;
    }    
}