package AlgorithmTraining.data_structure.Alg4th.base_knowledge;

/**
 * Created by mi on 17-4-1.
 */
public class NaiveMatchStr {
    public static void main(String[] args){
        //testbench
        String[] srcs = {"ABCDEFGHIJK", "ADEABCABCBC", "AAAAAAAAA", "DDS", "XXXXXX"};
        String[] pats = {"CDE", "BC", "AAAA", "PPPPP", "J"};
        int[] str_result = {2, 4, 0, -1, -1};
        for(int i = 0; i < 5; i++){
            assert naive_match_str(srcs[i], pats[i]) == str_result[i]: "not equal";
        }
        System.out.println("all test case passed");
    }

    /**
     * 用于朴素匹配 sourec中的pattern
     * 平均复杂度为 O((n-m+1)*m)
     * @param source  被搜索的主串 长度n
     * @param pattern 要在主串中搜索的模式 长度m
     * @return 首次出现 pattern的起始下标 起始下标从0 开始
     */
    static int naive_match_str(String source, String pattern) {
        char [] src_arr = source.toCharArray();
        char [] pat_arr = pattern.toCharArray();
        int i = 0;
        int j = 0;
        if(src_arr.length == 0 || pat_arr.length == 0)
            return -1; // empty string defined as not found

        for(i = 0; i < src_arr.length-pat_arr.length+1; i++)
            for(j = 0; j < pat_arr.length; j++){
                if(src_arr[i+j] != pat_arr[j]){
                    break;
                }
                else if(j==pat_arr.length-1){
                    return i;
                }
            }
        return -1; // pattern string is not found in source
    }
}
