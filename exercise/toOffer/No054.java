package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/13.
 */
class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}

class Solution054 {
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if (pNode == null)
            return null;
        if (pNode.right != null) {
            TreeLinkNode nextNode = pNode.right;
            while (nextNode.left != null) {
                nextNode = nextNode.left;
            }
            return nextNode;
        }
        else {
            TreeLinkNode last;
            do {
                last = pNode;
                pNode = pNode.next;
            } while (pNode != null && pNode.right == last);
            return pNode;
        }
    }
}


public class No054 {

    public static void main(String[] args) {

    }
}
