package AlgorithmTraining.exercise.toOffer;
/**
 * Created by G5501 on 2017/9/10.
 */
import java.util.*;
class Solution030_2{
    public RandomListNode Clone(RandomListNode pHead)
    {
        Map<RandomListNode, Integer> n2i = new HashMap<>();
        RandomListNode ptr = pHead;
        int i = 0;
        while (ptr != null) {
            n2i.put(ptr, i);
            i++;
            ptr = ptr.next;
        }

        RandomListNode dummy = new RandomListNode(1);
        ptr = pHead;
        RandomListNode ptr2 = dummy;
        Map<Integer, RandomListNode> i2n = new HashMap<>();
        int j = 0;
        while (ptr != null) {
            ptr2.next = new RandomListNode(ptr.label);
            ptr2 = ptr2.next;
            i2n.put(j, ptr2);
            ptr = ptr.next;
            j++;
        }

        ptr = pHead;
        ptr2 = dummy.next;
        while (ptr != null) {
            if (ptr.random != null) {
                int idx = n2i.get(ptr.random);
                RandomListNode randNode = i2n.get(idx);
                ptr2.random = randNode;
            }
            ptr = ptr.next;
            ptr2 = ptr2.next;
        }
        return dummy.next;
    }
}

public class No030_2 {
    public static void main(String[] args) {
        Solution030_2 s = new Solution030_2();
        RandomListNode p1 = new RandomListNode(1);
        RandomListNode p2 = new RandomListNode(2);
        RandomListNode p3 = new RandomListNode(3);
        RandomListNode p4 = new RandomListNode(4);
        RandomListNode p5 = new RandomListNode(5);

        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;

        p1.random = p3;
        p3.random = p5;
        p2.random = p4;
        p4.random = p5;

        RandomListNode newP = s.Clone(p1);
        RandomListNode P1 = newP;
        RandomListNode P2 = newP.next;
        RandomListNode P3 = newP.next.next;
        RandomListNode P4 = newP.next.next.next;
        RandomListNode P5 = newP.next.next.next.next;
        System.out.println(P1.next.label);
        System.out.println(P2.next.label);
        System.out.println(P3.next.label);
        System.out.println(P4.next.label);
        System.out.println(P1.random.label);
        System.out.println(P2.random.label);
        System.out.println(P3.random.label);
        System.out.println(P4.random.label);
    }
}
