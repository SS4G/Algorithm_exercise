package AlgorithmTraining.data_structure.Alg4th.base_knowledge.String;

/**
 * Created by mi on 17-4-1.
 */
public class NaiveMatch implements StringFinder{
    /**
     * 用于朴素匹配 sourec中的pattern
     * 平均复杂度为 O((n-m+1)*m)
     * @param source  被搜索的主串 长度n
     * @param pattern 要在主串中搜索的模式 长度m
     * @return 首次出现 pattern的起始下标 起始下标从0 开始
     */
    public int find(String source, String pattern) {
        char [] srcArr = source.toCharArray();
        char [] patArr = pattern.toCharArray();
        int i = 0;
        int j = 0;
        if(srcArr.length == 0 || patArr.length == 0)
            return -1; // empty string defined as not found

        for(i = 0; i < srcArr.length - patArr.length + 1; i++) {
            for(j = 0; j < patArr.length; j++) {
                if(srcArr[i+j] != patArr[j]) {
                    break;
                }
                else if(j == patArr.length - 1){
                    return i;
                }
            }
        }
        return -1; // pattern string is not found in source
    }
}
