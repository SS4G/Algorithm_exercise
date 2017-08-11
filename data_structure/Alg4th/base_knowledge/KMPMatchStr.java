package AlgorithmTraining.data_structure.Alg4th.base_knowledge;

/**
 * Created by mi on 17-4-1.
 * $todo kmp is under constructing
 */
public class KMPMatchStr {
    public static void main(String[] args){
        //testbench
        String[] srcs = {"ABCDEFGHIJK", "ADEABCABCBC", "AAAAAAAAA", "DDS", "XXXXXX"};
        String[] pats = {"CDE", "BC", "AAAA", "PPPPP", "J"};
        int[] str_result = {2, 4, 0, -1, -1};
        for(int i = 0; i < 5; i++){
            assert KMP_match_str(srcs[i], pats[i]) == str_result[i]: "not equal";
        }
        System.out.println("all test case passed");
    }

    /**
     * 用于KMP匹配 sourec中的pattern
     * 复杂度O(m+n) m为 pattern长度 n为主串长度
     * @param source  被搜索的主串
     * @param pattern 要在主串中搜索的模式
     * @return 首次出现 pattern的起始下标 起始下标从0 开始
     *
     */
    static int KMP_match_str(String source, String pattern){
        char [] src_arr = source.toCharArray();
        char [] pat_arr = pattern.toCharArray();
        int i = 0;
        int j = 0;
        int[] pattern_next_j = calc_next_j(pattern);
        while(i<src_arr.length){
            if(src_arr[i] != pat_arr[j]){
                j = pattern_next_j[j];
            }
            else if(j == pat_arr.length-1){  //all pattern is compared and same
                return i;
            }
            else{  // src_arr[i] == pat_arr[j]
                i++;
                j++;
            }
        }
        return -1;
    }

    static int[] calc_next_j(String pattern){
        char[] pat_arr = pattern.toCharArray();
        for(int i = 0; i < pat_arr.length; i++){

        }
        return new int[]{1,2,3};
    }


}
