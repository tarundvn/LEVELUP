// Problem Statement
// You just graduated from the business school of MagicLand and are about to start your own business. You will be buying some magical potions from the great wizard of MagicLand and selling them at your hometown NinjaLand.
// The great wizard sells ‘N’ different types of potions where the ‘i’th (1 <= ‘i’ <= ‘N’) potion is of the price ‘A[i]’. You can convert a potion of type ‘X[i]’ to a potion of type ‘Y[i]’ (1 <= ‘i’ <= ‘M’).
// The conversion of potion from one type to another is non-transitive. Let's say we can convert a potion from D to E, and E to F, it doesn't mean that we can convert potion D to F.
// What is the maximum amount of profit you can make from buying and selling a single potion?
// Input :
// 2
// 2 1
// 3 9
// 1 2
// 3 2
// 7 5 3
// 1 2
// 2 3
// Output : 
// 6
// 0
import java.util.*;
public class Solution {
    public static int maximumProfit(int n, int m, int[] a, int[] x, int[] y) {
        //Construct Graph
        ArrayList<Integer>[]graph = new ArrayList[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        for(int i=0;i<m;i++)
        {
            int u = x[i] - 1;
            int v = y[i] - 1;
            graph[u].add(v);
        }
        int max = -(int)1e9;
        int[]dp = new int[n];
        Arrays.fill(dp,-1);
        for(int i =0;i<n;i++)
        {
            int val = dfs(graph,i,dp,a);
            int ele = val - a[i];
            max = Math.max(max,ele >= 0 ? ele : 0);
        }
        return max;
    }
    
    public static int dfs(ArrayList<Integer>[]graph,int src,int[]dp,int[]a)
    {
        if(graph[src].size() == 0)
            return dp[src] = a[src];
        if(dp[src]!= -1)
            return dp[src];
        int max = a[src];
        for(int v : graph[src])
        {
            max = Math.max(max,dfs(graph,v,dp,a));
        }        
		return dp[src] = max;
    }
}