//650. 2 Keys Keyboard
//https://leetcode.com/problems/2-keys-keyboard/
class Solution {
    public int minSteps(int n) { 
        if(n==1)
            return 0;
        if(n==2)
            return 2;
        for(int i=n-1;i>=0;i--)
        {
            if(n%i==0)
                return minSteps(i) + n/i;
        }
        return n;
    }
}
//867 : 4 Keys Keyboard
//Special Keyboard
class Solution{
    static int optimalKeys(int n){
         if(n<=6)
            return n;
        int max = -(int)1e9;
        
        int[] dp = new int[n+2];
        
        for(int i=1;i<=6;i++)
        {
            dp[i] = i;
        }
        
        for(int i=7;i<=n;i++)
        {
            for(int j=i-3;j>=1;j--)
            {
                max = Math.max(max,dp[j]*(i-j-1));
            }
            dp[i] = max; 
        }
        return dp[n];
    }
}
//991. Broken Calculator
class Solution {
    public int brokenCalc(int startValue, int target) {
        if(startValue>=target)
        {
            return startValue-target;
        }
        if(target%2==0)
        {
            return brokenCalc(startValue,target/2) + 1;
        }
        return brokenCalc(startValue,target+1) + 1;
    }
}
//2139. Minimum Moves to Reach Target Score
class Solution {
    public int minMoves(int target, int maxDoubles) {
        return minMoves2(target,maxDoubles)-1;
    }
    public int minMoves2(int target,int maxDoubles) {
        if(target == 1)
            return 1;
        int min =(int)1e9;
        if(maxDoubles == 0)
            return target;
        if(target%2==0 && maxDoubles>0)
            min = Math.min(min,minMoves2(target/2,maxDoubles-1));
        if(target%2!=0 ||(target%2==0 && maxDoubles==0))
            min = Math.min(min,minMoves2(target-1,maxDoubles));   
        return min + 1;
    }
}
//662. Maximum Width of Binary Tree
class Solution {
    public class pair{
        TreeNode root;
        int val;
        pair(TreeNode root,int val)
        {
            this.root = root;
            this.val = val;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root,0));
        int maxCount = -1;
        while(que.size()!=0)
        {
            int size = que.size();
            maxCount = Math.max(maxCount,que.getLast().val-que.getFirst().val+1);
            while(size-->0)
            {
                pair rv = que.removeFirst();
                
                if(rv.root.left!=null)
                    que.addLast(new pair(rv.root.left,2*rv.val + 1));
                if(rv.root.right!=null)
                    que.addLast(new pair(rv.root.right,2*rv.val + 2));
            }  
        }
        return maxCount;
    }
}
//Recover Binary Search Tree
class Solution {
    
     public static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }

        return node;
    }
    
    public void recoverTree(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null, a = null, b = null;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if (prev != null && prev.val > curr.val) {
                    if (a == null)
                        a = prev;
                    b = curr;
                }
                prev = curr;
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) { // thread creation block
                    rightMostNode.right = curr; // thread is created
                    curr = curr.left;
                } else { // thread destroy block
                    rightMostNode.right = null; // thread is cut down

                    if (prev != null && prev.val > curr.val) {
                        if (a == null)
                            a = prev;
                        b = curr;
                    }

                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        if (a != null) {
            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
    }
}
//
class Solution {
     public int checkRecord(int n) {
        int MOD = (int)(1e9 + 7);

        if(n == 1) return 3;
        else if(n == 2) return 8;

        /**
             P = Number of sequences ending in 'P'
             A = Number of sequences ending in 'A'
             L = Number of sequences ending in 'L'

             P_NO_A = Number of sequences ending in 'P' and doesn't have 'A'
             L_NO_A = Number of sequences ending in 'L' and doesn't have 'A'
        */
        long[] P = new long[n + 1];
        long[] A = new long[n + 1];
        long[] L = new long[n + 1];
        long[] P_NO_A = new long[n + 1];
        long[] L_NO_A = new long[n + 1];

        /**
             When i = 1

             Number of sequences ending in 'P'
             P

             Number of sequences ending in 'A'
             A

             Number of sequences ending in 'L'
             L

             Number of sequences ending in 'P' and doesn't have 'A'
             P

             Number of sequences ending in 'L' and doesn't have 'A'
             L
        */
        P[1] = 1;
        A[1] = 1;
        L[1] = 1;
        P_NO_A[1] = 1;
        L_NO_A[1] = 1;

        /**
             When i = 2

             Number of sequences ending in 'P'
             PP
             LP
             AP

             Number of sequences ending in 'A'
             PA
             LA
             Not AA because more than one 'A' is not regarded as rewardable

             Number of sequences ending in 'L'
             PL
             AL
             LL

             Number of sequences ending in 'P' and doesn't have 'A'
             PP
             LP

             Number of sequences ending in 'L' and doesn't have 'A'
             PL
             LL
        */
        P[2] = 3;
        A[2] = 2;
        L[2] = 3;
        P_NO_A[2] = 2;
        L_NO_A[2] = 2;

        /**
             When i = 3

             Number of sequences ending in 'P'
             PPP
             APP
             LPP
             PAP
             Not AAP because more than one 'A' is not regarded as rewardable
             LAP
             PLP
             ALP
             LLP

             Number of sequences ending in 'A'
             PPA
             Not APA because more than one 'A' is not regarded as rewardable
             LPA
             Not PAA because more than one 'A' is not regarded as rewardable
             Not AAA because more than one 'A' is not regarded as rewardable
             Not LAA because more than one 'A' is not regarded as rewardable
             PLA
             Not ALA because more than one 'A' is not regarded as rewardable
             LLA

             Number of sequences ending in 'L'
             PPL
             APL
             LPL
             PAL
             Not AAL because more than one 'A' is not regarded as rewardable
             LAL
             PLL
             ALL
             Not LLL because more than two continuous 'L' is not regarded as rewardable

             Number of sequences ending in 'P' and doesn't have 'A'
             PPP
             Not APP because it has A
             LPP
             Not PAP because it has A
             Not AAP because it has A
             Not LAP because it has A
             PLP
             Not ALP because it has A
             LLP

             Number of sequences ending in 'L' and doesn't have 'A'
             PPL
             Not APL because it has A
             LPL
             Not PAL because it has A
             Not AAL because it has A
             Not LAL because it has A
             PLL
             Not ALL because it has A
             Not LLL because it has more than two continuous 'L'
        */
        P[3] = 8;
        A[3] = 4;
        L[3] = 7;
        P_NO_A[3] = 4;
        L_NO_A[3] = 3;

        /**
             Rule for P:                                                               You can append P to any Seq
             P(n) = P(n - 1) + L(n - 1) + A(n - 1)

             Rule for L: There can't be more than two continuous L
             L(n) =  P(n - 1)                                                          You can append L to Sequences ending in P
                   + A(n - 1)                                                          You can append L to Sequences ending in A
                   + if(n - 2 == 'A' || n - 2 == 'P') P(n - 2) + A(n - 2)              You can append L to Sequence, if Prev To Prev Character is NOT L

             Rule for A: There can't be more than one A
             A(n) =   P_NO_A(n - 1)                                                    You can append A to Sequences ending in P and has no 'A'
                    + L_NO_A(n - 1)                                                    You can append A to Sequences ending in L and has no 'A'


             P_NO_A(n) = A(n)                                                          You can append P to any Seq
             L_NO_A(n) = A(n - 1) + A(n - 2)
        */
        for(int i = 4; i <= n; i++) {
            P[i] = (P[i - 1] + L[i - 1] + A[i - 1]) % MOD;
            L[i] = (P[i - 1] + A[i - 1] + P[i - 2] + A[i - 2]) % MOD;
            A[i] = (P_NO_A[i - 1] + L_NO_A[i - 1]) % MOD;

            P_NO_A[i] = A[i];
            L_NO_A[i] = (A[i - 1] + A[i - 2]) % MOD;
        }

        return (int)((P[n] + L[n] + A[n]) % MOD);
    }
}
