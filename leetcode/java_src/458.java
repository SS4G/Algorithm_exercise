/**
 * Created by VULCAN on 2017/2/5.
 */

public class Leet458 {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if(buckets==1)
            return 0;
        int test_round=minutesToTest/minutesToDie;
        int num_base=test_round;
        return (int)(Math.log((double)(buckets-1))/Math.log((double)num_base))+1;
    }
}
