package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.*;

import java.util.Random;

/**
 * Created by G5501 on 2017/9/10.
 *
 */


class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

class Solution030 {
    public RandomListNode Clone(RandomListNode pHead)
    {
        insertDoubleNode(pHead);
        pointToRandom(pHead);
        return spilitRandom(pHead);
    }

    private void insertDoubleNode(RandomListNode head) {
        RandomListNode ptr = head;
        while (ptr != null) {
            RandomListNode inserted = new RandomListNode(ptr.label);
            inserted.next = ptr.next;
            ptr.next = inserted;
            ptr = ptr.next.next;
        }
    }

    private void pointToRandom(RandomListNode head) {
        RandomListNode ptr = head;
        while (ptr != null) {
            if (ptr.random != null)
                ptr.next.random = ptr.random.next;
            ptr = ptr.next.next;
        }
    }

    private RandomListNode spilitRandom(RandomListNode head) {
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode ptr = head;
        RandomListNode newPtr = dummy;
        while (ptr != null) {
            newPtr.next = ptr.next;
            ptr = ptr.next.next;
            newPtr = newPtr.next;
        }
        return dummy.next;
    }
}

public class No030 {
    public static void main(String[] args) {
        Solution030 s = new Solution030();
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
        //System.out.println(p5.random.label);
    }
}
