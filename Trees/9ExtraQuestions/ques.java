public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public class pair{
        int size = 0;
        int lics = 0;
        int ldecs = 0;
        int rics = 0;
        int rdecs = 0;

        pair()
        {

        }
        pair(int size,int lics,int ldecs,int rics,int rdecs)
        {
            this.size = size;
            this.lics = lics;
            this.ldecs = ldecs;
            this.rics = rics;
            this.rdecs = rdecs;
        }
    }
    public pair longestConsecutive(TreeNode root) {
        
        if(root == null)
            return new pair();

        if(root.left == null && root.right == null)
            return new pair(1,1,1,1,1);
        
        pair lp = longestConsecutive(root.left);
        pair rp = longestConsecutive(root.right);
        pair mp = new pair(1,1,1,1,1);

        if(root.left!=null && root.left.val < root.val)
        {
            mp.lics = Math.max(lp.rics,lp.lics) + 1;
        }
        else if(root.left!=null && root.left.val > root.val)
        {
            mp.ldecs = Math.max(lp.rdecs,lp.ldecs) + 1;
        }
        else
        {
            mp.lics = 1;
            mp.ldecs = 1;
        }

        if(root.right!=null && root.val > root.right.val)
        {
            mp.rics = Math.max(rp.rics,rp.lics) + 1;
        }
        else if(root.right!=null && root.right.val > root.val)
        {
            mp.rdecs = Math.max(rp.rdecs,rp.ldecs) + 1;
        }
        else
        {
            mp.rics = 1;
            mp.rdecs = 1;
        }
        int msize = Math.max(mp.lics + mp.rdecs -1 , mp.ldecs + mp.rics -1);
        int val =  Math.max(lp.size,rp.size);
        mp.size = Math.max(msize,val);
        return mp;
    }
    public int longestConsecutive2(TreeNode root) {
        pair ans =  longestConsecutive(root);
        return ans.size;
    }
}
//Binary Tree Longest Consecutive Sequence II
public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public class pair{
        int size = 0;
        int lics = 0;
        int ldecs = 0;
        int rics = 0;
        int rdecs = 0;

        pair()
        {

        }

        pair(int size,int lics,int ldecs,int rics,int rdecs)
        {
            this.size = size;
            this.lics = lics;
            this.ldecs = ldecs;
            this.rics = rics;
            this.rdecs = rdecs;
        }
    }

    public pair longestConsecutive(TreeNode root) {
        
        if(root == null)
            return new pair();

        if(root.left == null && root.right == null)
            return new pair(1,1,1,1,1);
        
        pair lp = longestConsecutive(root.left);
        pair rp = longestConsecutive(root.right);
        pair mp = new pair(1,1,1,1,1);

        if(root.left!=null && root.left.val + 1 == root.val)
        {
            mp.lics = Math.max(lp.rics,lp.lics) + 1;
        }
        else if(root.left!=null && root.left.val == root.val + 1)
        {
            mp.ldecs = Math.max(lp.rdecs,lp.ldecs) + 1;
        }
        else
        {
            mp.lics = 1;
            mp.ldecs = 1;
        }

        if(root.right!=null && root.val == root.right.val + 1)
        {
            mp.rics = Math.max(rp.rics,rp.lics) + 1;
        }
        else if(root.right!=null && root.right.val == root.val + 1)
        {
            mp.rdecs = Math.max(rp.rdecs,rp.ldecs) + 1;
        }
        else
        {
            mp.rics = 1;
            mp.rdecs = 1;
        }
    
        int msize = Math.max(mp.lics + mp.rdecs -1 , mp.ldecs + mp.rics -1);
        int val =  Math.max(lp.size,rp.size);
        mp.size = Math.max(msize,val);
        return mp;
    }

    public int longestConsecutive2(TreeNode root) {
        pair ans =  longestConsecutive(root);
        return ans.size;
    }
}
//337. House Robber III
class Solution {
    public int rob(TreeNode root) 
    {    
        HashMap<TreeNode,Integer> map = new HashMap<>();
        return robb(root,map);
    }
    
    public int robb(TreeNode root,HashMap<TreeNode,Integer> map) {
        if(root==null)
        {
            map.put(root,0);
            return 0;
        }
        
        if(map.containsKey(root))
            return map.get(root);
        
        if(root.left==null && root.right==null)
        {
            map.put(root,root.val);
            return root.val;
        }
        int max = -(int)1e9;
        max = Math.max(max,robb(root.left,map)+robb(root.right,map));
        int val = 0;
        val+= root.left == null ? 0 : (robb(root.left.left,map) + robb(root.left.right,map));             
        val+= root.right == null ? 0: (robb(root.right.left,map) + robb(root.right.right,map));
        max = Math.max(max,root.val + val);
        map.put(root,max);
        return max;
    }
}
//337. House Robber III : [Greedy]
public int[] rob_(TreeNode root) {
        if (root == null)
            return new int[] { 0, 0 };

        int[] lr = rob_(root.left);
        int[] rr = rob_(root.right);

        int[] ans = new int[2];
        ans[0] = lr[1] + root.val + rr[1];
        ans[1] = Math.max(lr[0], lr[1]) + Math.max(rr[0], rr[1]);

        return ans;
    }

    public int rob(TreeNode root) {
        int[] ans = rob_(root);
        return Math.max(ans[1], ans[0]);
    }
//1372. Longest ZigZag Path in a Binary Tree
class Solution {
    int ans;
    public int longestZigZag(TreeNode root) {
        ans = 0;
        int[]arr = longestZigZag1(root);
        return ans;
    }
    
    public int[] longestZigZag1(TreeNode root) {
        
        if(root == null)
            return new int[]{-1,-1};
        int[] lans = longestZigZag1(root.left);
        int[] rans = longestZigZag1(root.right);
        int[] mans = new int[2];
        mans[0] = lans[1] + 1;
        mans[1] = rans[0] + 1;
        ans = Math.max(ans,Math.max(mans[1],mans[0]));
        return mans;
    }
}
// 979. Distribute Coins in Binary Tree
class Solution {
    int moves = 0; 
    public int distributeCoins1(TreeNode root) {
        if(root == null)
            return 0;
        int lans = distributeCoins1(root.left);
        int rans = distributeCoins1(root.right);
        moves += Math.abs(lans) + Math.abs(rans);
        return lans + rans + (root.val - 1);
    }
    
     public int distributeCoins(TreeNode root) {
         if(distributeCoins1(root)!=0)
             return -1;
         return moves;
     }
}
// 1443. Minimum Time to Collect All Apples in a Tree
class Solution {
    
    public class pair{
        int val;
        boolean flag;
        pair(int val,boolean flag)
        {
            this.val = val;
            this.flag = flag;
        }
    }
    
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        
        ArrayList<Integer>[] tree = new ArrayList[n];
        for(int i=0;i<n;i++)
            tree[i] = new ArrayList<>();
        
        for(int [] arr : edges)
        {
            int u = arr[0];
            int v = arr[1];
            tree[u].add(v);
            tree[v].add(u);
        }
        boolean[]vis = new boolean[n];
        return MinTime(0,tree,hasApple,vis).val;
    }
    
    public pair MinTime(int idx, ArrayList<Integer>[] tree, List<Boolean> hasApple,boolean[]vis) {
        
        vis[idx] = true;
        pair mp = new pair(0,hasApple.get(idx));
        for(int ele : tree[idx])
        {
            if(vis[ele] == false)
            {
                pair rp = MinTime(ele,tree,hasApple,vis);
                mp.val += rp.flag == true ? rp.val + 2 : 0;
                mp.flag = rp.flag || mp.flag;
            }
        }
        return mp;
    }  
}
//IMP
//1110. Delete Nodes And Return Forest
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] arr) {
        
        HashSet<Integer> set = new HashSet<>();
        for(int ele : arr)
            set.add(ele);
        List<TreeNode> ans = new ArrayList<>();
        delNodes(root,set,ans);
        if(!set.contains(root.val))
        {
            ans.add(root);
        }
        return ans;
    }
    public TreeNode delNodes(TreeNode root,HashSet<Integer> set,List<TreeNode> ans)
    {
        if(root == null)
            return null;
        root.left = delNodes(root.left,set,ans);
        root.right = delNodes(root.right,set,ans);
        if(set.contains(root.val))
        {
            if(root.left!=null)
                ans.add(root.left);
            if(root.right!=null)
                ans.add(root.right);
            return null;
        }
        return root;
    }
}
//

public static String matrixSolver(int[][] arr)
{
    int[][] starts = {{0,0},{0,3},{0,6},{3,0},{3,3},{3,6},{6,0},{6,3},{6,6}};
    String ans = "";
    int idx = 1;
    for(int[] x : starts)
    {
        boolean y = helper(arr,x[0],x[1]);
        if(y)
            ans = ans + (idx) + " ";
        idx++;
    }
    ans.trim();
    return ans;
}

public static boolean helper(int[][] arr,int sr,int sc)
{
    for(int i = sr; i < (sr + 3); i++)
    {
        for(int j = sc; j < (sc + 3); j++)
        {
            boolean ans = isvalid(i,j,arr,arr[i][j]);
            if(ans == false)
                return false;
        }
    }
    return true;
}

public static boolean isvalid(int r, int c,int[][] arr,int num) {
         
        int c1 = 0;
        for (int i = 0; i < board[0].length; i++) {
            if (board[r][i] == num)
                c1++;
        }
         
         if(c1>1) return false;

        int c2 = 0;
         
        for (int i = 0; i < board.length; i++) {
            if (board[i][c] == num)
                c2++;
        }
         
         if(c2>1) return false; 

        int rmi = r / 3 * 3;
        int cmi = c / 3 * 3;
        int count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[rmi + i][cmi + j] == num)
                    count++;
            }
        }
            
        if(count>1) return false;

        return true;

    }


    public class shl {

  public static void main(String[] args) {
    char[][] arr = {
      { '1', '2', '3', '4', '5', '6', '7', '8', '9' },
      { 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
      { 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
      { '1', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
      { 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
      { 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
      { 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
      { 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
      { 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
    };
    System.out.println(matrixSolver(arr));
  }

  public static String matrixSolver(char[][] arr) {
    int[][] starts = {
      { 0, 0 },
      { 0, 3 },
      { 0, 6 },
      { 3, 0 },
      { 3, 3 },
      { 3, 6 },
      { 6, 0 },
      { 6, 3 },
      { 6, 6 },
    };
    String ans = "";
    int idx = 1;
    for (int[] x : starts) {
      boolean y = helper(arr, x[0], x[1]);
      if (y == false) ans = ans + (idx) + " ";
      idx++;
    }
    ans.trim();
    return ans;
  }

  public static boolean helper(char[][] arr, int sr, int sc) {
    for (int i = sr; i < (sr + 3); i++) {
      for (int j = sc; j < (sc + 3); j++) {
        if (ans[i][j] == 'x') {
          boolean ans = isvalid(i, j, arr, arr[i][j]);
          if (ans == false) return false;
        }
      }
    }
    return true;
  }

  public static boolean isvalid(int r, int c, char[][] arr, char num) {
    int c1 = 0;
    for (int i = 0; i < board[0].length; i++) {
      if (board[r][i] == num) c1++;
    }

    if (c1 > 1) return false;

    int c2 = 0;

    for (int i = 0; i < board.length; i++) {
      if (board[i][c] == num) c2++;
    }

    if (c2 > 1) return false;

    int rmi = r / 3 * 3;
    int cmi = c / 3 * 3;
    int count = 0;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[rmi + i][cmi + j] == num) count++;
      }
    }

    if (count > 1) return false;

    return true;
  }
}
