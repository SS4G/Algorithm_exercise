package AlgorithmTraining.leetcode2nd;


/**
 *
 * Created by 903 on 2017/7/19.
 */
class Leet006 {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        int perido = numRows * 2 - 2;
        int[] idxs = new int[numRows];
        char[][] rows = new char[numRows][s.length()];

        for (int i = 0; i < s.length(); i ++) {
            int row = mapToRow(i, numRows, perido);
            rows[row][idxs[row]] = s.charAt(i);
            idxs[row] ++;
        }

        char[] res = new char[s.length()];
        int idx = 0;
        for (int i = 0; i < rows.length; i ++)
            for (int j = 0; j < idxs[i]; j ++) {
                res[idx] = rows[i][j];
                idx ++;
            }
        return new String(res);
    }
    private int mapToRow(int idx, int numRows, int perido) {
        int offset = idx % perido;
        if (offset < numRows)
            return offset;
        else
            return numRows - 1 - (offset - numRows + 1);
    }
}
public class Leet006_t {
    public static void main(String[] args) {
        Leet006 leet = new Leet006();
        String s = "123456789";
        int n = 3;
        System.out.println(leet.convert(s, n));
    }
}
