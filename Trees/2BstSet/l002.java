import java.util.*;

//BST
public class l002 {

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

     public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static TreeNode getLeftMost(TreeNode root) {
        if (root == null)
            return null;

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    public static TreeNode getRightMost(TreeNode root) {
        if (root == null)
            return null;

        while (root.right != null) {
            root = root.right;
        }

        return root;
    }

    // ceil and floor -> T : O(logN), S : O(1)
    public static void predSucc(TreeNode root, int data) {
        TreeNode curr = root, succ = null, pred = null;

        while (curr != null) {
            if (curr.val == data) {

                TreeNode leftMost = getLeftMost(curr.right);
                succ = leftMost != null ? leftMost : succ;

                TreeNode rightMost = getRightMost(curr.left);
                pred = rightMost != null ? rightMost : pred;
                break;

            } else if (curr.val < data) {
                pred = curr;
                curr = curr.right;
            } else {
                succ = curr;
                curr = curr.left;
            }
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);

        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        else
            root.left = insertIntoBST(root.left, val);

        return root;
    }

    public int getMin(TreeNode root) {

        while (root.left != null)
            root = root.left;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (root.val < key)
            root.right = deleteNode(root.right, key);
        else if (root.val > key)
            root.left = deleteNode(root.left, key);
        else {
            if (root.left == null || root.right == null) {
                TreeNode rNode = root.left != null ? root.left : root.right;
                root.left = root.right = null;
                // delete root;
                return rNode;
            }
            int minEle = getMin(root.right);
            root.val = minEle;
            root.right = deleteNode(root.right, minEle);
        }
        return root;
    }

    //insert delete in O(logn) iterative
    //510 leetcode very good ques inorder successor bst2
    //solve this on interview bit
}