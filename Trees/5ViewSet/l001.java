import java.util.*;
public class l001{

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

  public static void levelOrder(TreeNode root)
  {
      LinkedList<TreeNode> que = new LinkedList<>();
      que.addLast(root.val);
      int level = 0;
      while(que.size()!=0)
      {
          int size = que.size();
          System.out.print("level " + level + ": ")
          while(size-->0)
          {
              TreeNode rv = que.removeFirst();
              System.out.print(rv.val + ", ");
              if(rv.left!=null)
                que.addLast(rv.left);
              if(rv.right!=null)
                que.addLast(rv.right);
          }
          level++;
          System.out.println();  
      }  
  }
    //Left View
    class Tree
    {
    //Function to return list containing elements of left view of binary tree.
    ArrayList<Integer> leftView(Node root)
    {
    ArrayList<Integer> ans = new ArrayList<>();
    if(root == null)
        return ans;
    LinkedList<Node> que = new LinkedList<>();
      que.addLast(root);
      int level = 0;
      while(que.size()!=0)
      {
          int size = que.size();
          ans.add(que.getFirst().data);
          while(size-->0)
          {
              Node rv = que.removeFirst();
              if(rv.left!=null)
                que.addLast(rv.left);
              if(rv.right!=null)
                que.addLast(rv.right);
          }
          level++;
      }
      return ans;
    }
}
  //Right View
 class Solution{
    //Function to return list containing elements of right view of binary tree.
    ArrayList<Integer> rightView(Node root) {
    
    ArrayList<Integer> ans = new ArrayList<>();
    if(root == null)
        return ans;
    LinkedList<Node> que = new LinkedList<>();
      que.addLast(root);
      int level = 0;
      while(que.size()!=0)
      {
          int size = que.size();
          ans.add(que.getLast().data);
          while(size-->0)
          {
              Node rv = que.removeFirst();
              if(rv.left!=null)
                que.addLast(rv.left);
              if(rv.right!=null)
                que.addLast(rv.right);
          }
          level++;
      }
      return ans;
    }
}
//Vertical Traversal of Binary Tree
class Solution
{
    //Function to find the vertical order traversal of Binary Tree.
    static ArrayList <Integer> verticalOrder(Node root)
    {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null)
            return ans;
        
        ArrayList<ArrayList<Integer>> bans = new ArrayList<>();
        int[]arr = new int[2];
        dfs(root,arr,0);
        int dis = arr[1]-arr[0]+1;
        
        for(int i=0;i<=dis;i++)
            bans.add(new ArrayList<>());
        
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root,-arr[0]));
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                pair rp = que.removeFirst();
                bans.get(rp.val).add(rp.node.data);
                if(rp.node.left!=null)
                    que.addLast(new pair(rp.node.left,rp.val-1));
                if(rp.node.right!=null)
                    que.addLast(new pair(rp.node.right,rp.val+1));       
            }
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        for(ArrayList<Integer> ar : bans)
        {
            for(int ele : ar)
            res.add(ele);
        }
        return res;
    }
    
    public static class pair{
        Node node;
        int val;
        pair(Node node,int val)
        {
            this.node = node;
            this.val = val;
        }
    }
    
    public static void dfs(Node root,int[]arr,int idx)
    {
        if(root == null)
            return;
        arr[0] = Math.min(arr[0],idx);
        arr[1] = Math.max(arr[1],idx);
        dfs(root.left,arr,idx-1);
        dfs(root.right,arr,idx+1);
    }   
}
//Top View of Binary Tree 
class Solution
{
    //Function to return a list of nodes visible from the top view 
    //from left to right in Binary Tree.
    static ArrayList<Integer> topView(Node root)
    {
     ArrayList<Integer> ans = new ArrayList<>();
        if(root == null)
            return ans;
        
        ArrayList<ArrayList<Integer>> bans = new ArrayList<>();
        int[]arr = new int[2];
        dfs(root,arr,0);
        int dis = arr[1]-arr[0]+1;
        
        for(int i=0;i<=dis;i++)
            bans.add(new ArrayList<>());
        
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root,-arr[0]));
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                pair rp = que.removeFirst();
                bans.get(rp.val).add(rp.node.data);
                if(rp.node.left!=null)
                    que.addLast(new pair(rp.node.left,rp.val-1));
                if(rp.node.right!=null)
                    que.addLast(new pair(rp.node.right,rp.val+1));       
            }
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        for(ArrayList<Integer> ar : bans)
        {
            if(ar.size()>0)
            res.add(ar.get(0));
        }
        return res;
    }
    
    public static class pair{
        Node node;
        int val;
        pair(Node node,int val)
        {
            this.node = node;
            this.val = val;
        }
    }
    
    public static void dfs(Node root,int[]arr,int idx)
    {
        if(root == null)
            return;
        arr[0] = Math.min(arr[0],idx);
        arr[1] = Math.max(arr[1],idx);
  
        dfs(root.left,arr,idx-1);
        dfs(root.right,arr,idx+1);
    }
}
//Bottom View of Binary Tree
class Solution
{
    //Function to return a list containing the bottom view of the given tree.
    public ArrayList <Integer> bottomView(Node root)
    {
     ArrayList<Integer> ans = new ArrayList<>();
        if(root == null)
            return ans;
        
        ArrayList<ArrayList<Integer>> bans = new ArrayList<>();
        int[]arr = new int[2];
        dfs(root,arr,0);
        int dis = arr[1]-arr[0]+1;
        
        for(int i=0;i<=dis;i++)
            bans.add(new ArrayList<>());
        
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root,-arr[0]));
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                pair rp = que.removeFirst();
                bans.get(rp.val).add(rp.node.data);
                if(rp.node.left!=null)
                    que.addLast(new pair(rp.node.left,rp.val-1));
                if(rp.node.right!=null)
                    que.addLast(new pair(rp.node.right,rp.val+1));       
            }
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        for(ArrayList<Integer> ar : bans)
        {
            if(ar.size()>0)
            res.add(ar.get(ar.size()-1));
        }
        return res;
    }
    
    public static class pair{
        Node node;
        int val;
        pair(Node node,int val)
        {
            this.node = node;
            this.val = val;
        }
    }
    
    public static void dfs(Node root,int[]arr,int idx)
    {
        if(root == null)
            return;
        arr[0] = Math.min(arr[0],idx);
        arr[1] = Math.max(arr[1],idx);
        dfs(root.left,arr,idx-1);
        dfs(root.right,arr,idx+1);
    }
}
// there is locha in top and bottom view agar see nb iske liye ek array mein horizontal level maintain karna padega
//LINT CODE : //651 · Binary Tree Vertical Order Traversal
public class Solution {
    /**
     * @param root: the root of tree
     * @return: the vertical order traversal
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> bans = new ArrayList<>();
         if(root == null)
            return bans;
        int[]arr = new int[2];
        dfs(root,arr,0);
        int dis = arr[1]-arr[0]+1;
        
        for(int i=0;i<dis;i++)
            bans.add(new ArrayList<>());
        
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root,-arr[0]));
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                pair rp = que.removeFirst();
                bans.get(rp.val).add(rp.node.val);
                if(rp.node.left!=null)
                    que.addLast(new pair(rp.node.left,rp.val-1));
                if(rp.node.right!=null)
                    que.addLast(new pair(rp.node.right,rp.val+1));       
            }
        }
        return bans;
    }
    public static class pair{
        TreeNode node;
        int val;
        pair(TreeNode node,int val)
        {
            this.node = node;
            this.val = val;
        }
    }
    
    public static void dfs(TreeNode root,int[]arr,int idx)
    {
        if(root == null)
            return;
        arr[0] = Math.min(arr[0],idx);
        arr[1] = Math.max(arr[1],idx);
        dfs(root.left,arr,idx-1);
        dfs(root.right,arr,idx+1);
    }   
}

//987. Vertical Order Traversal of a Binary Tree
class Solution {
    public static class v2pair {
        TreeNode node = null;
        int vl = 0;
        int hl = 0;

        v2pair(TreeNode node, int hl, int vl) {
            this.node = node;
            this.hl = hl;
            this.vl = vl;
        }
    }
    public static void widthOfShadow(TreeNode root, int vl, int[] minMax) {
        if (root == null)
            return;

        minMax[0] = Math.min(minMax[0], vl);
        minMax[1] = Math.max(minMax[1], vl);

        widthOfShadow(root.left, vl - 1, minMax);
        widthOfShadow(root.right, vl + 1, minMax);
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
         List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i< width; i++)
            ans.add(new ArrayList<>());

        PriorityQueue<v2pair> que = new PriorityQueue<>((a,b)->{
            if(a.hl!=b.hl)
                return a.hl - b.hl;
            else if(a.vl!=b.vl)
                return a.vl - b.vl;
            else
                return a.node.val - b.node.val;
        });
        que.add(new v2pair(root, 0, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                v2pair p = que.remove();
                TreeNode node = p.node;
                int hl = p.hl;
                int vl = p.vl;

                ans.get(vl).add(node.val);
                if (node.left != null)
                    que.add(new v2pair(node.left, hl + 1, vl - 1));
                if (node.right != null)
                    que.add(new v2pair(node.right, hl + 1, vl + 1));
            }
        }

        return ans;
    }
}

}