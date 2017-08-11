package AlgorithmTraining.leetcode2nd;

  import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by BUPT_SS4G on 2017/7/17.
 *
 */

//Definition for a binary tree node.
class TreeNode {
    public int val;
    public TreeNode left = null;
    public TreeNode right = null;
    public TreeNode(int x) { val = x; }
}


class Leet297 {
    public ArrayList<TreeNode> serialize0(TreeNode root) {
        ArrayList<TreeNode> serializeFifo = new ArrayList<>(10000);
        serializeFifo.add(root);
        int ptrOffset = 0;
        TreeNode ptr = null;
        while (ptrOffset < serializeFifo.size()) {
            ptr = serializeFifo.get(ptrOffset);
            if (ptr != null) {
                serializeFifo.add(ptr.left);
                serializeFifo.add(ptr.right);
            }
            ptrOffset++;
        }
        return serializeFifo;
    }
    public TreeNode deserialize0(List<String> arr) {
        if (arr.get(0).equals("None"))
            return null;
        ArrayList<TreeNode> ser = new ArrayList<>(5000);
        for (int i = 0; i < arr.size(); i++) {
            ser.add(arr.get(i).equals("None")? null: new TreeNode(Integer.parseInt(arr.get(i))));
        }
        // node array ready
        TreeNode rootPtr = ser.get(0);
        int rootIndex = 0;
        int addIndex = 1;
        while (rootIndex < arr.size()) {
            TreeNode curRoot = ser.get(rootIndex);
            if (curRoot != null) {
                if (addIndex < ser.size()) {
                    curRoot.left = ser.get(addIndex);
                    addIndex ++;
                }

                if (addIndex < ser.size()) {
                    curRoot.right = ser.get(addIndex);
                    addIndex ++;
                }
            }
            rootIndex ++;
        }
        return rootPtr;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        ArrayList<TreeNode> ser = serialize0(root);
        int tail = ser.size() - 1;
        while (tail >= 0 && ser.get(tail) == null)
            tail --;
        if (tail == -1)
            return "None";
        StringBuilder sb = new StringBuilder(Math.max(5 * tail, 16));
        for (int i = 0; i <= tail; i++) {
            sb.append(ser.get(i) == null? "None": ser.get(i).val);
            if (i != tail)
                sb.append(",");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] splited = data.split(",");
        return deserialize0(Arrays.asList(splited));
    }
}

public class Leet297_t {
    public static void main(String[] args) {
        Leet297 s = new Leet297();
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);

        n0.left = n1;
        n0.right = n2;
        n2.left = n3;
        n2.right = n4;
        System.out.println(s.serialize(n0));
        System.out.println(s.serialize(null));
        TreeNode root = s.deserialize(s.serialize(n0));
        System.out.println(root.left.val);
        System.out.println(root.right.val);
        System.out.println(root.right.left.val);
        System.out.println(root.right.right.val);
        System.out.println(root.left.left);
        System.out.println(root.left.right);
    }
}
