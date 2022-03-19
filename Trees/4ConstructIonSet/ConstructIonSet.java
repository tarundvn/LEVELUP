import java.util.*;

public class ConstructIonSet {

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  //Serialize and Deserialize BST
  public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      return serialize(root, new StringBuilder());
    }

    private String serialize(TreeNode root, StringBuilder sb) {
      TreeNode curr = root;
      while (curr != null) {
        TreeNode left = curr.left;
        if (left == null) {
          sb.append(curr.val + " ");
          curr = curr.right;
        } else {
          TreeNode temp = getRightMostNode(left, curr);
          if (temp.right != curr) {
            temp.right = curr;
            sb.append(curr.val + " ");
            curr = curr.left;
          } else {
            temp.right = null;
            curr = curr.right;
          }
        }
      }
      return sb.toString();
    }

    private TreeNode getRightMostNode(TreeNode root, TreeNode par) {
      TreeNode curr = root;
      while (curr.right != null && curr.right != par) {
        curr = curr.right;
      }
      return curr;
    }

    // Decodes your encoded data to tree.
    static int idx;

    public TreeNode deserialize(String data) {
      if (data.length() == 0) return null;
      String[] arr = data.split(" ");
      int[] ans = new int[arr.length];
      int i = 0;
      for (String ele : arr) {
        ans[i] = Integer.parseInt(ele);
        i++;
      }
      idx = 0;
      int lr = -1;
      int rr = (int) 1e4 + 1;
      return preorderToBST(ans, lr, rr);
    }

    private TreeNode preorderToBST(int[] arr, int lr, int rr) {
      if (idx >= arr.length || arr[idx] < lr || arr[idx] > rr) return null;

      TreeNode node = new TreeNode(arr[idx++]);
      node.left = preorderToBST(arr, lr, node.val);
      node.right = preorderToBST(arr, node.val, rr);
      return node;
    }
  }

  //Construct BST from InOrder Traversal
  public static TreeNode BSTfromInOrderTraversal(int[] arr, int si, int ei) {
    if (si > ei) return null;
    if (si == ei) return new TreeNode(arr[si]);
    int mid = (si + (ei - si) / 2);
    TreeNode root = new TreeNode(arr[mid]);
    mid.left = BSTfromInOrderTraversal(arr, si, mid - 1);
    mid.right = BSTfromInOrderTraversal(arr, mid + 1, ei);
  }

  public static TreeNode BSTfromInOrderTraversal(int[] arr) {
    return BSTfromInOrderTraversal(arr, 0, arr.length - 1);
  }

  //Construct BST from PreOrder Traversal
  class Solution {

    private int idx;

    private TreeNode bstFromPreorder(int[] preorder, int lr, int rr) {
      if (
        idx >= preorder.length || preorder[idx] < lr || preorder[idx] > rr
      ) return null;

      TreeNode node = new TreeNode(preorder[idx--]);
      node.left = bstFromPreorder(preorder, lr, node.val);
      node.right = bstFromPreorder(preorder, node.val, rr);
      return node;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
      idx = preorder.length - 1;
      int n = preorder.length;
      int lr = 0; //LeetCode constraints
      int rr = 1001; //LeetCode constraints
      return bstFromPreorder(preorder, lr, rr);
    }
  }

  //Construct BST from PostOrder Traversal
  class Solution {

    private int idx;

    private TreeNode bstFromPreorder(int[] preorder, int lr, int rr) {
      if (idx < 0 || preorder[idx] < lr || preorder[idx] > rr) return null;

      TreeNode node = new TreeNode(preorder[idx--]);
      node.right = bstFromPreorder(preorder, node.val, rr);
      node.left = bstFromPreorder(preorder, lr, node.val);
      return node;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
      idx = preorder.length - 1;
      int n = preorder.length;
      int lr = 0;
      int rr = 1001;
      return bstFromPreorder(preorder, lr, rr);
    }
  }

  //Construct BST from LevelOrder Traversal

  //Construct Binary Tree from Preorder and Inorder Traversal
  class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
      int n = preorder.length;
      return buildTree(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    public TreeNode buildTree(
      int[] preorder,
      int psi,
      int pei,
      int[] inorder,
      int isi,
      int iei
    ) {
      if (isi > iei) return null;

      int idx = isi;
      while (preorder[psi] != inorder[idx]) idx++;

      int tnel = idx - isi;
      TreeNode node = new TreeNode(preorder[psi]);
      node.left =
        buildTree(preorder, psi + 1, psi + tnel, inorder, isi, idx - 1);
      node.right =
        buildTree(preorder, psi + tnel + 1, pei, inorder, idx + 1, iei);
      return node;
    }
  }

  //Construct Binary Tree from Inorder and Postorder Traversal
  class Solution {

    public TreeNode postInTree(
      int[] post,
      int psi,
      int pei,
      int[] in,
      int isi,
      int iei
    ) {
      if (psi > pei) return null;

      TreeNode node = new TreeNode(post[pei]);
      int idx = isi;
      while (in[idx] != post[pei]) idx++;

      int tnel = idx - isi;

      node.left = postInTree(post, psi, psi + tnel - 1, in, isi, idx - 1);
      node.right = postInTree(post, psi + tnel, pei - 1, in, idx + 1, iei);

      return node;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
      int n = postorder.length;
      return postInTree(postorder, 0, n - 1, inorder, 0, n - 1);
    }
  }

  //Construct Binary Tree from Preorder and Postorder Traversal
  public static TreeNode constructFromPrePost(
    int[] postOrder,
    int ppsi,
    int ppei,
    int[] preOrder,
    int psi,
    int pei
  ) {
    if (psi > pei) return null;

    TreeNode root = new TreeNode(preOrder[psi]);
    if (psi == pei) return root;

    int idx = ppsi;
    while (postOrder[idx] != preOrder[psi + 1]) idx++;

    int tnel = idx - ppsi + 1;
    root.left =
      constructFromPrePost(
        postOrder,
        ppsi,
        ppsi + tnel - 1,
        preOrder,
        psi + 1,
        psi + tnel
      );
    root.right =
      constructFromPrePost(
        postOrder,
        ppsi + tnel,
        pei - 1,
        preOrder,
        psi + tnel + 1,
        pei
      );

    return root;
  }

  public static TreeNode constructFromPrePost(int[] preOrder, int[] postorder) {
    int n = postorder.length;
    return constructFromPrePost(postorder, 0, n - 1, preOrder, 0, n - 1);
  }

  //LintCode 659
  public class Solution {

    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
      StringBuilder sb = new StringBuilder();
      for (String str : strs) {
        sb.append(str + " tarun ");
      }
      return sb.toString();
    }

    /*
     * @param str: A string
     * @return: dcodes a single string to a list of strings
     */
    public List<String> decode(String str) {
      String[] arr = str.split(" tarun ");
      List<String> ans = new ArrayList<>();
      for (String st : arr) {
        ans.add(st);
      }
      return ans;
    }
  }

  //searlize desarilize bt 2 methods
  //searlize desarilize bt 2 methods

  // is balanced bt
  class Solution {

    public boolean isBalanced(TreeNode root) {
      if (root == null) return true;

      boolean res = true;
      if (
        Math.max(height(root.left), height(root.right)) -
        Math.min(height(root.left), height(root.right)) >
        1
      ) res = false;
      res = res && isBalanced(root.right) && isBalanced(root.left);
      return res;
    }

    public int height(TreeNode root) {
      if (root == null) return 0;
      return Math.max(height(root.left), height(root.right)) + 1;
    }
  }

  //Largest BST
  class Solution {

    // Return the size of the largest sub-tree which is also a BST
    public static class pair {

      boolean isBST;
      int min;
      int max;
      int size;
      Node largestroot;

      pair(boolean isBST, int min, int max, int size, Node largestroot) {
        this.isBST = isBST;
        this.min = min;
        this.max = max;
        this.size = size;
        this.largestroot = largestroot;
      }
    }

    public static pair largestBst_(Node root) {
      if (root == null) {
        pair bp = new pair(true, (int) 1e9, -(int) 1e9, 0, null);
        return bp;
      }

      pair lp = largestBst_(root.left);
      pair rp = largestBst_(root.right);

      pair mp = new pair(false, (int) 1e9, -(int) 1e9, 0, null);

      if (lp.isBST && rp.isBST && root.data > lp.max && root.data < rp.min) {
        mp.isBST = true;
        mp.min = Math.min(lp.min, root.data);
        mp.max = Math.max(rp.max, root.data);
        mp.size = lp.size + rp.size + 1;
        mp.largestroot = root;
      } else {
        if (lp.size > rp.size) {
          mp.size = lp.size;
          mp.largestroot = lp.largestroot;
        } else {
          mp.size = rp.size;
          mp.largestroot = rp.largestroot;
        }
      }

      return mp;
    }

    public static int largestBst(Node root) {
      return largestBst_(root).size;
    }
  }

  //Predecessor O(N)
  public class Solution {

    /**
     * @param root: the given BST
     * @param p: the given node
     * @return: the in-order predecessor of the given node in the BST
     */
    public static TreeNode getRightMostNode(TreeNode root, TreeNode par) {
      TreeNode curr = root;
      while (curr.right != null && curr.right != par) {
        curr = curr.right;
      }
      return curr;
    }

    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
      TreeNode pre = null;
      TreeNode curr = root;
      while (curr != null) {
        TreeNode left = curr.left;
        if (left == null) {
          if (curr == p) return pre;
          pre = curr;
          curr = curr.right;
        } else {
          TreeNode rightMostNode = getRightMostNode(left, curr);
          if (rightMostNode.right != curr) {
            rightMostNode.right = curr; //thread created
            curr = curr.left;
          } else {
            rightMostNode.right = null;
            if (curr == p) return pre;
            pre = curr;
            curr = curr.right;
          }
        }
      }
      return pre;
    }
  }

  //Successor : O(N)
  public class Solution {

    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public static TreeNode getRightMostNode(TreeNode root, TreeNode par) {
      TreeNode curr = root;
      while (curr.right != null && curr.right != par) {
        curr = curr.right;
      }
      return curr;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
      int val = 500;
      TreeNode curr = root;
      while (curr != null) {
        TreeNode left = curr.left;
        if (left == null) {
          if (val == 1) return curr;
          if (curr == p) val = 1;
          curr = curr.right;
        } else {
          TreeNode rightMostNode = getRightMostNode(left, curr);
          if (rightMostNode.right != curr) {
            rightMostNode.right = curr; //thread created
            curr = curr.left;
          } else {
            rightMostNode.right = null;
            if (val == 1) return curr;
            if (curr == p) val = 1;
            curr = curr.right;
          }
        }
      }
      return null;
    }
  }
}