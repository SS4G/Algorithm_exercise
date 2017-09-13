package AlgorithmTraining.exercise.toOffer;

import com.sun.deploy.util.SyncAccess;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * Created by szh-920 on 17-9-9.
 *
 */

class Solution024 {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length <= 0)
            return false;
        return validRecure(sequence, 0, sequence.length);
    }

    private boolean validRecure(int[] arr, int start, int end) {
        // start include
        //end exclude
        if (end - start <= 1)
            return true;
        int midPos = findPos(arr, arr[end - 1], start, end - 1); // mid Pos is next midValue position
        if (midPos >= 0) {
            return validRecure(arr, start, midPos) && validRecure(arr, midPos + 1, end - 1);
        }
        else if (midPos == -1) {
            //System.out.println("--");
            return validRecure(arr, start, end - 1);
        }
        else {
            return false;
        }
    }

    private int findPos(int[] arr, int midVal, int start, int end) {
        int lo = start;
        int hi = end - 1;
        //System.out.println(start+":"+end +"-"+midVal);
        while (lo < end && arr[lo] < midVal) {
            lo++;
        }

        while (hi >= start && arr[hi] > midVal) {
            hi--;
        }

        if (lo > hi) {
            return hi;
        }
        else {
            //System.out.println(start+":"+end);
            return -2;
        }
    }
}

public class No024 {
    public static void main(String[] args) {
        Solution024 s = new Solution024();
        int[] arr = {1, 10, 2, 6, 8, 7, 5};
        //arr = new int[]{4, 6, 7, 5};
        //arr = new int[]{1, 2, 3, 4, 5};
        //arr = new int[]{5,4,3,2,1};
        System.out.println(s.VerifySquenceOfBST(arr));
    }
}
