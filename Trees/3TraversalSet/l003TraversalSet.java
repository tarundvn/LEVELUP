public class l003TraversalSet {

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

  public static TreeNode getRightMostNode(TreeNode root, TreeNode par) {
    TreeNode curr = root;
    while (curr.right != null && curr.right != par) {
      curr = curr.right;
    }
    return curr;
  }

  public static ArrayList<Integer> morrisInOrderTraversal(TreeNode root) {
    ArrayList<Integer> ans = new ArrayList<>();

    TreeNode curr = root;
    while (curr != null) {
      TreeNode left = curr.left;
      if (left == null) {
        ans.add(curr.val);
        curr = curr.right;
      } else {
        TreeNode rightMostNode = getRightMostNode(left, curr);
        if (rightMostNode.right != curr) {
          rightMostNode.right = curr; //thread created
          curr = curr.left;
        } else {
          rightMostNode.right = null;
          ans.add(curr.val);
          curr = curr.right;
        }
      }
    }
    return ans;
  }

  public static ArrayList<Integer> morrisPreOrderTraversal(TreeNode root) {
    ArrayList<Integer> ans = new ArrayList<>();
    TreeNode curr = root;
    while (curr != null) {
      TreeNode left = curr.left;
      if (left == null) {
        ans.add(curr.val);
        curr = curr.right;
      } else {
        TreeNode rightMostNode = getRightMostNode(left, curr);
        if (rightMostNode.right != curr) {
          rightMostNode.right = curr; //thread created
          ans.add(curr.val);
          curr = curr.left;
        } else {
          rightMostNode.right = null;
          curr = curr.right;
        }
      }
    }
    return ans;
  }

  //Validate BST
  public boolean isValidBST(TreeNode root) {
    return morrisInOrderTraversal(root);
  }

  public TreeNode getRightMostNode(TreeNode root, TreeNode par) {
    TreeNode curr = root;
    while (curr.right != null && curr.right != par) {
      curr = curr.right;
    }
    return curr;
  }

  public boolean morrisInOrderTraversal(TreeNode root) {
    TreeNode curr = root;
    long val = -(long) 1e13;
    boolean flag = true;
    while (curr != null) {
      TreeNode left = curr.left;
      if (left == null) {
        if (val < curr.val) {
          val = curr.val;
        } else {
          flag = false;
        }
        curr = curr.right;
      } else {
        TreeNode rightMostNode = getRightMostNode(left, curr);
        if (rightMostNode.right != curr) {
          rightMostNode.right = curr;
          curr = curr.left;
        } else {
          rightMostNode.right = null;
          if (val < curr.val) {
            val = curr.val;
          } else {
            flag = false;
          }
          curr = curr.right;
        }
      }
    }
    return flag;
  }

  //
  public void addAllLeft(TreeNode root, LinkedList<TreeNode> st) {
    TreeNode lt = root;
    while (lt != null) {
      st.addFirst(lt);
      lt = lt.left;
    }
  }

  public List<Integer> levelOrderUsingStack(TreeNode root) {
    LinkedList<TreeNode> st = new LinkedList<>();
    List<Integer> ans = new ArrayList<>();
    TreeNode curr = root;
    addAllLeft(curr);
    while (st.size() != 0) {
      TreeNode rv = st.removeFirst();
      ans.add(rv.val);
      if (rv.right != null) {
        addAllLeft(rv.right);
      }
    }
    return ans;
  }

  //Bst iterator
  class BSTIterator {

    private LinkedList<TreeNode> st = new LinkedList<>();

    public BSTIterator(TreeNode root) {
      getAllLeft(root);
    }

    private void getAllLeft(TreeNode root) {
      while (root != null) {
        st.addFirst(root);
        root = root.left;
      }
    }

    public int next() {
      TreeNode rv = st.removeFirst();
      getAllLeft(rv.right);
      return rv.val;
    }

    public boolean hasNext() {
      return st.size() != 0;
    }
  }

  //BST Iterator Morris
  class BSTIterator {

    private TreeNode curr = null;

    public BSTIterator(TreeNode root) {
      curr = root;
    }

    private TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
      while (node.right != null && node.right != curr) {
        node = node.right;
      }

      return node;
    }

    public int next() {
      int rv = -1;
      while (curr != null) {
        TreeNode left = curr.left;
        if (left == null) {
          rv = curr.val;
          curr = curr.right;
          break;
        } else {
          TreeNode rightMostNode = getRightMostNode(left, curr);
          if (rightMostNode.right == null) { // thread creation block
            rightMostNode.right = curr; // thread is created
            curr = curr.left;
          } else { // thread destroy block
            rightMostNode.right = null; // thread is cut down

            rv = curr.val;
            curr = curr.right;
            break;
          }
        }
      }

      return rv;
    }

    public boolean hasNext() {
      return curr != null;
    }
  }

  //Kth Smallest in a BST
  class Solution {

    public int kthSmallest(TreeNode root, int k) {
      k--;
      TreeNode curr = root;
      while (curr != null) {
        TreeNode left = curr.left;
        if (left == null) {
          if (k == 0) return curr.val; else k--;

          curr = curr.right;
        } else {
          TreeNode rightMostNode = getRightMostNode(left, curr);
          if (rightMostNode.right != curr) {
            rightMostNode.right = curr; //thread created
            curr = curr.left;
          } else {
            rightMostNode.right = null;

            if (k == 0) return curr.val; else k--;

            curr = curr.right;
          }
        }
      }
      return -1;
    }

    public static TreeNode getRightMostNode(TreeNode root, TreeNode par) {
      TreeNode curr = root;
      while (curr.right != null && curr.right != par) {
        curr = curr.right;
      }
      return curr;
    }
  }

  //Convert BST into DLL
  public class Solution {

    public TreeNode treeToDoublyList(TreeNode root) {
      TreeNode dummy = new TreeNode(-1);
      TreeNode prev = dummy;

      TreeNode curr = root;
      while (curr != null) {
        TreeNode left = curr.left;
        if (left == null) {
          prev.right = curr;
          curr.left = prev;
          prev = curr;

          curr = curr.right;
        } else {
          TreeNode rightMostNode = getRightMostNode(left, curr);
          if (rightMostNode.right != curr) {
            rightMostNode.right = curr; //thread created
            curr = curr.left;
          } else {
            rightMostNode.right = null;
            prev.right = curr;
            curr.left = prev;
            prev = curr;
            curr = curr.right;
          }
        }
      }

      TreeNode head = dummy.right;
      head.left = null;
      dummy.right = null;
      head.left = prev;
      prev.right = head;
      return head;
    }

    public static TreeNode getRightMostNode(TreeNode root, TreeNode par) {
      TreeNode curr = root;
      while (curr.right != null && curr.right != par) {
        curr = curr.right;
      }
      return curr;
    }
  }

  //Sorted LL to BST
  class Solution {

    public TreeNode sortedListToBST(ListNode head) {
      if (head == null) return null;
      if (head.next == null) return new TreeNode(head.val);

      ListNode midprev = middle(head);
      ListNode mid = midprev.next;
      ListNode midnext = mid.next;

      midprev.next = null;
      mid.next = null;

      TreeNode ans = new TreeNode(mid.val);
      ans.left = sortedListToBST(head);
      ans.right = sortedListToBST(midnext);

      return ans;
    }

    public ListNode middle(ListNode head) {
      if (head == null) return head;
      ListNode dummy = new ListNode(-101);
      dummy.next = head;
      ListNode slow = dummy;
      ListNode fast = head;
      while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      return slow == dummy ? head : slow;
    }
  }
  //Sorted LL to BST
  //SortedDLL to BST
  //BT TO BST(very imp for interview)
}
