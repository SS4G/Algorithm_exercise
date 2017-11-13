package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/13.
 */
class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random;
    RandomListNode(int x) {
        label = x;
    }
}

class Leet138x {
    RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;
        RandomListNode dummy = new RandomListNode(-1);
        dummy.next = head;
        insertNode(dummy);
        //showRandList(dummy.next);
        pointToRandom(dummy);
        //showRandList(dummy.next);
        RandomListNode r = splitFromOriginalList(dummy);
        //showRandList(r);
        //showRandList(head);
        return r;
    }

    public void insertNode(RandomListNode dummy) {
        RandomListNode insertPtr = dummy;
        while (insertPtr != null) {
            //RandomListNode nextNode = new RandomListNode(insertPtr.label + 100);
            RandomListNode nextNode = new RandomListNode(insertPtr.label);
            nextNode.next = insertPtr.next;
            insertPtr.next = nextNode;
            insertPtr = insertPtr.next.next;
        }
    }

    public void pointToRandom(RandomListNode dummy) {
        RandomListNode pointToPtr = dummy;
        while (pointToPtr != null) {
            if (pointToPtr.random != null) {
                pointToPtr.next.random = pointToPtr.random.next;
            }
            pointToPtr = pointToPtr.next.next;
        }
    }

    public RandomListNode splitFromOriginalList(RandomListNode dummy) {
        RandomListNode pointToPtr = dummy;
        RandomListNode newListHead = new RandomListNode(1);
        RandomListNode newListPtr = newListHead;
        pointToPtr = dummy;
        pointToPtr = pointToPtr.next.next;
        while (pointToPtr != null) {
            newListPtr.next = pointToPtr.next;
            newListPtr = newListPtr.next;
            pointToPtr.next = pointToPtr.next.next;
            pointToPtr = pointToPtr.next;
        }
        return newListHead.next;
    }

    public static void showRandList(RandomListNode randHead) {
        RandomListNode randPtr = randHead;
        while (randPtr != null) {
            System.out.print(randPtr.label);
            System.out.print("->");
            randPtr = randPtr.next;
        }
        System.out.println("#");
    }
}

public class Leet138x_t {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6};
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode addPtr = dummy;
        int j = 0;
        for (int i : arr) {
            addPtr.next = new RandomListNode(i);
            addPtr = addPtr.next;
        }
        showRandList(dummy.next);
        Leet138x leet = new Leet138x();
        RandomListNode copied = leet.copyRandomList(dummy.next);
        //showRandList(copied);
        //showRandList(dummy.next);
    }

    public static void showRandList(RandomListNode randHead) {
        RandomListNode randPtr = randHead;
        while (randPtr != null) {
            System.out.print(randPtr.label);
            System.out.print("->");
            randPtr = randPtr.next;
        }
        System.out.println("#");
    }
}
