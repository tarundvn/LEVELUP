import java.util.*;
public class l001 {
  
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

  //height is in general in terms of edges
  public static int height(TreeNode root) {
    return root == null
      ? -1
      : Math.max(height(root.left), height(root.right)) + 1;
  }

  public static int diameter_01(TreeNode root) {
    if (root == null) return 0;
    int lr = diameter_01(root.left);
    int rr = diameter_01(root.right);
    int lh = height(root.left);
    int rh = height(root.right);
    return Math.max(Math.max(lr, rr), lh + rh + 2);
  }

  //{d,h}
  public static int[] diameter_02(TreeNode root) {
    if (root == null) return new int[] { 0, -1 };
    int[] lr = diameter_02(root.left);
    int[] rr = diameter_02(root.right);

    int[] mr = new int[2];
    mr[0] = Math.max(Math.max(lr[0], rr[0]), lr[1] + rr[1] + 2);
    mr[1] = Math.max(lr[1], rr[1]) + 1;

    return mr;
  }

  static int maxDiameter = -(int)1e9;
  public static int diameter_03(TreeNode root)
  {
    if(root == null)
      return -1;
    
    int lh = diameter_03(root.left);
    int rh = diameter_03(root.right);

    maxDiameter = Math.max(maxDiameter,lh+rh+2);
    return Math.max(lh,rh) + 1;
  }

  //112. Path Sum
  public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) return false;

    if (root.left == null && root.right == null) return root.val == targetSum;

    boolean mres = false;
    mres = mres || hasPathSum(root.left, targetSum - root.val);
    mres = mres || hasPathSum(root.right, targetSum - root.val);
    return mres;
  }

  //113. Path Sum II
  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> bans = new ArrayList<>();
    List<Integer> sans = new ArrayList<>();
    hasPathSum(root, targetSum, bans, sans);
    return bans;
  }

  public boolean hasPathSum(
    TreeNode root,
    int targetSum,
    List<List<Integer>> bans,
    List<Integer> sans
  ) {
    if (root == null) return false;

    if (root.left == null && root.right == null) {
      if (root.val == targetSum) {
        sans.add(root.val);
        bans.add(new ArrayList<>(sans));
        sans.remove(sans.size() - 1);
        return true;
      }
      return false;
    }

    boolean mres = false;
    sans.add(root.val);
    boolean lres = hasPathSum(root.left, targetSum - root.val, bans, sans);
    boolean rres = hasPathSum(root.right, targetSum - root.val, bans, sans);
    mres = lres || rres;
    sans.remove(sans.size() - 1);
    return mres;
  }

  //Maximum Path Sum In Between Two Leaves Of Binary Tree
  public static int maxPathSum(TreeNode root) {
    int[] ans = maxPathSum_01(root);
    return ans[0];
  }

  public static int[] maxPathSum_01(TreeNode root) {
    if (root == null) {
      return new int[] { -(int) 1e9, -(int) 1e9 };
    }

    if (root.left == null && root.right == null) {
      return new int[] { -(int) 1e9, root.val };
    }

    int[] lr = maxPathSum_01(root.left);
    int[] rr = maxPathSum_01(root.right);

    int[] myRes = new int[2];
    myRes[0] = Math.max(lr[0], rr[0]);
    if (root.left != null && root.right != null) {
      myRes[0] = Math.max(myRes[0], lr[1] + rr[1] + root.val);
    }

    myRes[1] = Math.max(lr[1], rr[1]) + root.val;
    return myRes;
  }
  // static ka faith hai ki left aur right ka max vo already store karke beth jayega

  static int longestPathSum = -(int)1e9;
  public static int maxPathSum_02(TreeNode root)
  {
    if (root == null) {
      return -(int) 1e9;
    }

    if (root.left == null && root.right == null) {
      return root.val;
    }

    int lr = maxPathSum_01(root.left);
    int rr = maxPathSum_01(root.right);

    if (root.left != null && root.right != null) {
      longestPathSum = Math.max(longestPathSum, lr + rr + root.val);
    }

    int myRes = Math.max(lr, rr) + root.val;
    return myRes;
  }

  //124. Binary Tree Maximum Path Sum
  class Solution {

    public static int max(int... arr) {
      int max = arr[0];
      for (int ele : arr) max = Math.max(max, ele);

      return max;
    }

    static int NodeToNodeMaxPathSum;
    public static int maxPathSum_(TreeNode root) {
      if (root == null) return 0;

      int lrtn = maxPathSum_(root.left); // left root To Node
      int rrtn = maxPathSum_(root.right); // right root To Node

      int rootToNode = Math.max(lrtn, rrtn) + root.val;
      NodeToNodeMaxPathSum =
        max(NodeToNodeMaxPathSum, rootToNode, root.val, lrtn + root.val + rrtn);

      return max(rootToNode, root.val);
    }
    
    public static int maxPathSum(TreeNode root) {
      NodeToNodeMaxPathSum = -(int) 1e9;
      maxPathSum_(root);
      return NodeToNodeMaxPathSum;
    }
  }
}