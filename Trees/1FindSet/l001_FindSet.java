import java.util.*;

//FIND SET
public class l001_FindSet {

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

  public static int maximum(TreeNode root) {
    return root == null
      ? -(int) 1e9
      : Math.max(root.val, Math.max(maximum(root.left), maximum(root.right)));
  }

  public static int minimum(TreeNode root) {
    return root == null
      ? (int) 1e9
      : Math.min(root.val, Math.min(minimum(root.left), minimum(root.right)));
  }

  public static boolean find(TreeNode root, int data) {
    if (root == null) return false;

    if (root.val == data) return true;

    return find(root.left, data) || find(root.right, data);
  }

  //Return Type
  public static ArrayList<TreeNode> RootToNodePath(
    TreeNode root,
    TreeNode node
  ) {
    if (root == null) return new ArrayList<>();
    if (root == node) {
      ArrayList<TreeNode> bans = new ArrayList<>();
      bans.add(node);
      return bans;
    }
    ArrayList<TreeNode> lres = RootToNodePath(root.left, node);
    if (lres.size() != 0) {
      lres.add(root);
      return lres;
    }
    ArrayList<TreeNode> rres = RootToNodePath(root.right, node);
    if (rres.size() != 0) {
      rres.add(root);
      return lres;
    }
    return new ArrayList<>();
  }

  //Void Type
  public static boolean helperRootToNodePath(
    TreeNode root,
    TreeNode node,
    ArrayList<TreeNode> ans
  ) {
    if (root == null) return false;
    if (root == node) {
      ans.add(root);
      return true;
    }
    boolean lres = helperRootToNodePath(root.left, node, ans);
    if (lres) {
      ans.add(root);
      return lres;
    }
    boolean rres = helperRootToNodePath(root.right, node, ans);
    if (rres) {
      ans.add(root);
      return rres;
    }
    return false;
  }

  public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    ArrayList<Integer> smallAns = new ArrayList<>();

    rootToAllLeafPath(root, ans, smallAns);
    return ans;
  }

  public static void rootToAllLeafPath(
    TreeNode root,
    ArrayList<ArrayList<Integer>> ans,
    ArrayList<Integer> smallAns
  ) {
    if (root == null) return;
    if (root.left == null && root.right == null) {
      smallAns.add(root.val);
      bans.add(new ArrayList<>(sans));
      smallAns.remove(root.size() - 1);
      return;
    }

    smallAns.add(root.val);
    rootToAllLeafPath(root.left, ans, smallAns);
    rootToAllLeafPath(root.right, ans, smallAns);
    smallAns.remove(root.size() - 1);
  }

  public static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans) {
    if (root == null || (root.left == null && root.right == null)) return;

    if (root.left == null || root.right == null) {
      ans.add(root.val);
      //NOTE--> idhar return nhi aayega...
    }

    exactlyOneChild(root.left, ans);
    exactlyOneChild(root.right, ans);
  }

  public ArrayList<Integer> exactlyOneChild(TreeNode root) {
    ArrayList<Integer> ans = new ArrayList<>();
    exactlyOneChild(root);
    return ans;
  }

  public static int countExactlyOneChild(TreeNode node) {
    if (node == null || (node.left == null && node.right == null)) return 0;

    int leftSinglechildCount = countExactlyOneChild(node.left);
    int rightSinglechildCount = countExactlyOneChild(node.right);

    int ans = leftSinglechildCount + rightSinglechildCount;
    if (node.left == null || node.right == null) ans++;
    return ans;
  }

  //Kdown
  public static void kDown(TreeNode root, int k, ArrayList<Integer> ans) {
    if (root == null) return;

    if (k == 0) {
      ans.add(root.val);
      return;
    }

    kDown(root.left, k - 1, ans);
    kDown(root.right, k - 1, ans);
  }

  //Kdownblocker
  public static void KDown(
    TreeNode root,
    TreeNode blocker,
    int k,
    ArrayList<Integer> ans
  ) {
    if (root == null || root == blocker) return;

    if (k == 0) {
      ans.add(root.val);
      return;
    }

    kDown(root.left, k - 1, ans);
    kDown(root.right, k - 1, ans);
  }

  //K far BFS
  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    HashMap<TreeNode, TreeNode> map = new HashMap<>();
    map.put(root, null);
    makeMap(root, map);
    List<Integer> ans = new ArrayList<>();
    HashSet<TreeNode> set = new HashSet<>();

    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.addLast(target);
    set.add(target);
    int level = 0;
    while (queue.size() != 0) {
      int size = queue.size();
      if (level == k) {
        while (queue.size() != 0) {
          ans.add(queue.removeFirst().val);
        }
        break;
      }
      while (size-- > 0) {
        TreeNode rv = queue.removeFirst();
        if (rv.left != null && !set.contains(rv.left)) {
          queue.addLast(rv.left);
          set.add(rv.left);
        }
        if (rv.right != null && !set.contains(rv.right)) {
          queue.addLast(rv.right);
          set.add(rv.right);
        }
        if (
          map.getOrDefault(rv, null) != null &&
          !set.contains(map.getOrDefault(rv, null))
        ) {
          queue.addLast(map.getOrDefault(rv, null));
          set.add(map.getOrDefault(rv, null));
        }
      }
      level++;
    }
    return ans;
  }

  //K far with rootToNodePath
  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    List<TreeNode> roToNopath = new ArrayList<>();
    find(root, roToNopath, target);
    List<Integer> ans = new ArrayList<>();

    TreeNode blocker = null;
    for (TreeNode tn : roToNopath) {
      KDown(tn, blocker, k, ans);
      blocker = tn;
      k--;
    }
    return ans;
  }

  public boolean find(TreeNode root, List<TreeNode> ans, TreeNode node) {
    if (root == null) return false;
    if (root == node) {
      ans.add(root);
      return true;
    }
    boolean lres = find(root.left, ans, node);
    if (lres) {
      ans.add(root);
      return lres;
    }
    boolean rres = find(root.right, ans, node);
    if (rres) {
      ans.add(root);
      return rres;
    }
    return false;
  }

  public void KDown(TreeNode root, TreeNode blocker, int k, List<Integer> ans) {
    if (root == null || k < 0 || root == blocker) return;
    if (k == 0) {
      ans.add(root.val);
      return;
    }
    KDown(root.left, blocker, k - 1, ans);
    KDown(root.right, blocker, k - 1, ans);
  }

  //K Far
  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    List<Integer> ans = new ArrayList<>();
    find(root, target, ans, k);
    return ans;
  }

  public int find(TreeNode root, TreeNode node, List<Integer> ans, int k) {
    if (root == null) return -1;
    if (root == node) {
      KDown(root, null, k, ans);
      return 1;
    }
    int lres = find(root.left, node, ans, k);
    if (lres != -1) {
      KDown(root, root.left, k - lres, ans);
      return lres + 1;
    }
    int rres = find(root.right, node, ans, k);
    if (rres != -1) {
      KDown(root, root.right, k - rres, ans);
      return rres + 1;
    }
    return -1;
  }

  public void KDown(TreeNode root, TreeNode blocker, int k, List<Integer> ans) {
    if (root == null || k < 0 || root == blocker) return;
    if (k == 0) {
      ans.add(root.val);
      return;
    }
    KDown(root.left, blocker, k - 1, ans);
    KDown(root.right, blocker, k - 1, ans);
  }

  // RootToNodePath returns with letter path
  // ans will be in form of LRLRLRR...
  public static boolean helperRootToNodePath(
    TreeNode root,
    TreeNode node,
    ArrayList<TreeNode> ans,
    String str
  ) {
    if (root == null) return false;
    if (root == node) {
      System.out.println(str);
      return true;
    }
    boolean lres = helperRootToNodePath(root.left, node, ans, str + 'L');
    if (lres) return lres;
    boolean rres = helperRootToNodePath(root.right, node, ans, str + 'R');
    if (rres) return rres;

    return false;
  }

  public static void burningTreeNode(TreeNode root,int time,TreeNode blockNode,ArrayList<ArrayList<Integer>> ans) {
    if (root == null || root == blockNode) return;
    if (time == ans.size()) ans.add(new ArrayList<>()); 
    ans.get(time).add(root.val);

    burningTreeNode(root.left, time + 1, blockNode, ans);
    burningTreeNode(root.right, time + 1, blockNode, ans);
  }

  public static int burningTree(
    TreeNode root,
    int fireNode,
    ArrayList<ArrayList<Integer>> ans
  ) {
    if (root == null) return -1;
    if (root.val == fireNode) {
      burningTreeNode(root, 0, null, ans);
      return 1;
    }

    int lt = burningTree(root.left, fireNode, ans);
    if (lt != -1) {
      burningTreeNode(root, lt, root.left, ans);
      return lt + 1;
    }

    int rt = burningTree(root.right, fireNode, ans);
    if (rt != -1) {
      burningTreeNode(root, lt, root.right, ans);
      return rt + 1;
    }

    return -1;
  }

  public static void burningTree(TreeNode root, int data) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    burningTree(root, data, ans);
  }

  // Node with water and fire.
  public static void burningTreeNodeWithWater(
    TreeNode root,
    int time,
    TreeNode blockNode,
    HashSet<Integer> waterSet,
    ArrayList<ArrayList<Integer>> ans
  ) {
    if (
      root == null || root == blockNode || waterSet.contains(root.val)
    ) return;
    if (time == ans.size()) ans.add(new ArrayList<>()); // if(time == ans.size()) ans.push_back({});
    ans.get(time).add(root.val);

    burningTreeNodeWithWater(root.left, time + 1, blockNode, waterSet, ans);
    burningTreeNodeWithWater(root.right, time + 1, blockNode, waterSet, ans);
  }

  public static int burningTreeWithWater(
    TreeNode root,
    int fireNode,
    HashSet<Integer> waterSet,
    ArrayList<ArrayList<Integer>> ans
  ) {
    if (root == null) return -1;
    if (root.val == fireNode) {
      if (!waterSet.contains(root.val)) { // foor cpp : map.find(root->val) != map.end();
        burningTreeNodeWithWater(root, 0, null, waterSet, ans);
        return 1;
      }
      return -2; // fire node is present but it have water.
    }

    int lt = burningTreeWithWater(root.left, fireNode, waterSet, ans);
    if (lt > 0) {
      if (!waterSet.contains(root.val)) {
        burningTreeNodeWithWater(root, lt, root.left, waterSet, ans);
        return lt + 1;
      }
      return -2; // fire node is present but it have water.
    }

    if (lt == -2) return -2;

    int rt = burningTreeWithWater(root.right, fireNode, waterSet, ans);
    if (rt > 0) {
      if (!waterSet.contains(root.val)) {
        burningTreeNodeWithWater(root, rt, root.right, waterSet, ans);
        return rt + 1;
      }
      return -2; // fire node is present but it have water.
    }
    if (rt == -2) return -2;

    return -1;
  }

  public static void burningTreeWithWater(TreeNode root, int data) {
    HashSet<Integer> waterSet = new HashSet<>(); // unordered_set<int> map;
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    burningTreeWithWater(root, data, waterSet, ans);
    System.out.println(ans);
  }

  //min time to burn tree
  //LCA

  public static void main(String[] args) {}
}
