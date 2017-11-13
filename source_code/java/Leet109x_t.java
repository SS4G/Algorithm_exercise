package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;
import java.util.*;
/**
 * Created by G5501 on 2017/10/12.
 */
class Leet109x {
    TreeNode sortedListToBST(ListNode head) {
        List<Integer> storage = new ArrayList<>();
        while (head != null) {
            storage.add(head.val);
            head = head.next;
        }
        return recureBuild(storage, 0, storage.size());
    }

    TreeNode recureBuild(List<Integer> arr, int st, int ed) {// st include ed exclude
        if (ed - st == 0) {
            return null;
        }
        else if (ed - st == 1) {
            return new TreeNode(arr.get(st));
        }
        else if (ed - st == 2) {
            TreeNode root = new TreeNode(arr.get(st + 1));
            TreeNode left = new TreeNode(arr.get(st));
            root.left = left;
            return root;
        }
        else if (ed - st == 3) {
            TreeNode root = new TreeNode(arr.get(st + 1));
            TreeNode left = new TreeNode(arr.get(st));
            TreeNode right = new TreeNode(arr.get(st + 2));
            root.left = left;
            root.right = right;
            return root;
        }
        else {
            int mid = (ed + st) >> 1;
            TreeNode root = new TreeNode(arr.get(mid));
            TreeNode left = recureBuild(arr, st, mid);
            TreeNode right = recureBuild(arr, mid + 1, ed);
            root.left = left;
            root.right = right;
            return root;
        }
    }
}

public class Leet109x_t {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        arr = new int[]{};
        ListNode head = LinkedListUtil.generateLinkedList(arr);
        Leet109x leet = new Leet109x();
        TreeNode root = leet.sortedListToBST(head);
        TreeUtil.showTree(root);
    }
}
