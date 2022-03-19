//741. Cherry Pickup
//Backtracking Soluution
class Solution {
    int max = 0;
    int store;
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        if(n==0 || grid[n-1][n-1] == -1)
            return max;
        store = grid[n-1][n-1];
        if(n==1)
            return store;
        cherryUp(0,0,n-1,n-1,grid,0);
        return max;
    }
    public int cherryUp(int sr,int sc,int er,int ec,int[][] grid,int val)
    {
        if(sr == er && sc == ec)
        {
            return cherryDown(er,ec,0,0,grid,val+grid[er][ec]);
        }
        
        int cherries = grid[sr][sc];
        grid[sr][sc] = 0;
        int count = 0;
        if(sr+1 <= er && grid[sr+1][sc] != -1)
            count+= cherryUp(sr+1,sc,er,ec,grid,val + cherries);
        if(sc+1 <= ec && grid[sr][sc+1] != -1)
            count+= cherryUp(sr,sc+1,er,ec,grid,val + cherries);
        grid[sr][sc] = cherries;
        return count;
    }
    
    public int cherryDown(int sr,int sc,int er,int ec,int[][] grid,int val)
    {
        if(sr == er && sc == ec)
        {
            max = Math.max(max,val - store);
            return 1;
        }
        
        int cherries = grid[sr][sc];
        grid[sr][sc] = 0;
        int count = 0;
        if(sr-1 >= 0 && grid[sr-1][sc] != -1)
            count+= cherryDown(sr-1,sc,er,ec,grid,val + cherries);
        if(sc-1 >= 0 && grid[sr][sc-1] != -1)
            count+= cherryDown(sr,sc-1,er,ec,grid,val + cherries);
        grid[sr][sc] = cherries;
        return count;
    }
}
//4D Dp Solution
 class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][][] dp = new int[n][n][n][n];
        for(int[][][]a: dp)
            for(int[][]b : a)
                for(int[]c : b)
                    Arrays.fill(c,Integer.MIN_VALUE);
        return Math.max(0,cherryPickup(0,0,0,0,grid,dp,n));
    }
     
    public int cherryPickup(int r1, int c1, int r2,int c2, int[][] arr, int[][][][] dp,int n) {
		
       if (r1 == arr.length - 1 && c1 == arr.length - 1 && r2 == arr.length - 1 && c2 == arr.length - 1)
			return dp[r1][c1][r2][c2] = arr[r1][c1];
        
        if(dp[r1][c1][r2][c2]!=Integer.MIN_VALUE)
            return dp[r1][c1][r2][c2];
        
        int cherries = 0;
        if(r1==r2 && c1==c2)
            cherries+=arr[r1][c1];
        else
            cherries+=arr[r1][c1] + arr[r2][c2];
        
        int max = Integer.MIN_VALUE;
        //hh
        if(c1+1<n && c2+1<n && arr[r1][c1+1]!=-1 && arr[r2][c2+1]!=-1)
            max = Math.max(max,cherryPickup(r1,c1+1,r2,c2+1,arr,dp,n));
        //hv
        if(c1+1<n && r2+1<n && arr[r1][c1+1]!=-1 && arr[r2+1][c2]!=-1)
            max = Math.max(max,cherryPickup(r1,c1+1,r2+1,c2,arr,dp,n));
        //vh
        if(r1+1<n && c2+1<n && arr[r1+1][c1]!=-1 && arr[r2][c2+1]!=-1)
            max = Math.max(max,cherryPickup(r1+1,c1,r2,c2+1,arr,dp,n));
        //vv
        if(r1+1<n && r2+1<n && arr[r1+1][c1]!=-1 && arr[r2+1][c2]!=-1)
            max = Math.max(max,cherryPickup(r1+1,c1,r2+1,c2,arr,dp,n));
        return dp[r1][c1][r2][c2] = max + cherries;
	}
}
//GoldMine Dp
//1463. Cherry Pickup II
