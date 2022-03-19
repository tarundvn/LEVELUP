import java.util.*;
public class DFS
{
    public static class Edge
    {
        int v;
        int w;
        Edge(int v,int w)
        {
            this.v = v;
            this.w = w;
        }
    }

//Find Path
class Solution {
    public boolean validPath(int n, int[][] edges, int start, int end) {

        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        
        for(int i=0;i<edges.length;i++)
        {
            int u = edges[i][0];
            int v = edges[i][1];
            graph[u].add(v);
            graph[v].add(u);
        }
        
        boolean[] vis = new boolean[n];
        return validPath(graph,start,end,vis);
    }
    
    public boolean validPath(ArrayList<Integer>[] graph,int st,int end,boolean[]vis)
    {
        if(st == end)
            return true;
        vis[st] = true;
        boolean res = false;
        for(int e : graph[st])
        {
            if(!vis[e])
            res = res || validPath(graph,e,end,vis);
        }
        return res;
    }
}
//All Path
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] edges) {
        
        int n = edges.length;
        List<List<Integer>> bans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        boolean[] vis = new boolean[n];
        dfs(0,n-1,edges,vis,sans,bans);
        return bans;
    }
    public int dfs(int start,int end,int[][]edges,boolean[]vis,List<Integer> sans,List<List<Integer>> bans)
    {
        if(start == end)
        {
            sans.add(start);
            bans.add(new ArrayList<>(sans));
            sans.remove(sans.size()-1);
            return 1;
        }
        
        int count = 0;
        vis[start] = true;
        
        for(int ele : edges[start])
        {
            if(!vis[ele])
            {
                sans.add(start);
                count += dfs(ele,end,edges,vis,sans,bans);
                sans.remove(sans.size()-1);
            }
        }
        
        vis[start] = false;
        return count;
    }
}
//preOrder
    public static void preOrder(ArrayList<Edge>[] graph, int src, boolean[] vis, int wsf, String psf) {
        System.out.println(src + " -> " + (psf + src) + "@" + wsf);
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                preOrder(graph, e.v, vis, wsf + e.w, psf + src);
        }

        vis[src] = false;
    }
//postOrder
    public static void postOrder(ArrayList<Edge>[] graph, int src, boolean[] vis, int wsf, String psf) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                postOrder(graph, e.v, vis, wsf + e.w, psf + src);
        }

        System.out.println(src + " -> " + (psf + src) + "@" + wsf);
        vis[src] = false;
    }
//
public class pair{

    int wt;
    String hpath;

    pair(int wt,String hpath)
    {
        this.wt = wt;
        this.hpath = hpath;
    }

}
    public static pair heaviestPath(ArrayList<Edge>[]graph,boolean[]vis,int src,int dest)
    {
        if(src == dest)
        {
            pair bp = new pair(0,"" + src);
            return bp;
        }

        pair mp = new pair(-1,"");
        vis[src] = true;
        for(Edge e : graph[src])
        {
            if(!vis[e.v])
            {
                pair rp = heaviestPath(graph,vis,e.v,dest);
                if(rp.wt!=-1 && rp.wt + e.w > mp.wt)
                {
                    mp.wt = rp.wt + e.w;
                    mp.hpath = rp.hpath + src;
                }
            }
        }
        vis[src] = false;
        return mp;
    }
    //1319. Number of Operations to Make Network Connected
    class Solution {
    public int makeConnected(int n, int[][] connections) {
        
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<graph.length;i++)
        {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] arr : connections)
        {
            int u = arr[0];
            int v = arr[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        
        boolean []vis = new boolean[n];
        int components = 0;
        for(int i=0;i<n;i++)
        {
            if(!vis[i])
            {
                dfs(graph,vis,i);
                components++;
            }
        }
        
        return n-1 <=connections.length ? components -1 : -1;
    }
    
    public void dfs(ArrayList<Integer>[] graph,boolean[]vis,int src)
    {
        vis[src] = true;
        for(int ele : graph[src])
        {
            if(!vis[ele])
                dfs(graph,vis,ele);
        }
    }
}
//HAMILTONIAN PATH
    public static void hamintonainPathAndCycle(ArrayList<Edge>[] graph, int src, int osrc, int edgeCount, boolean[] vis,
            String psf, ArrayList<String> ans) {
        if (edgeCount == graph.length - 1) {
            psf += src;
            int idx = findEdge(graph, src, osrc);
            if (idx != -1)
                psf += '*';

            ans.add(psf);
            return;
        }

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                hamintonainPathAndCycle(graph, e.v, osrc, edgeCount + 1, vis, psf + src, ans);
        }
        vis[src] = false;
    }
// SHP SHORTEST HAMILTONIAN PATH
// 943. Find the Shortest Superstring
// TSP

// 200. Number of Islands
class Solution {
    public int numIslands(char[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        // if(n==0 || m==0) return 0;
      
        int [][]dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int count = 0; 
        
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j]=='1')
                {
                    dfs(grid,i,j,dir);
                    count++;
                }   
            }   
        }
        return count;
    }
    public void dfs(char[][] grid,int r,int c,int[][]dir)
    {
        grid[r][c] = '0';
        for(int i=0;i<dir.length;i++)
        {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];
            
            if(nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc]=='1')
            {
                dfs(grid,nr,nc,dir);
            }
            
        }
    }
}
//695. Max Area of Island
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
      
        int m = grid.length;
        int n = grid[0].length;
        if(n==0 || m==0) return 0;
      
        int [][]dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int max = 0; 
        
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j]==1)
                {
                    max = Math.max(max,dfs(grid,i,j,dir));
                }
       
            }   
        }
        
        return max;
        
    }
    public int dfs(int[][] grid,int r,int c,int[][]dir)
    {
        grid[r][c] = 0;
        int count = 1;
        for(int i=0;i<dir.length;i++)
        {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];
            
            if(nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc]==1)
            {
                count+= dfs(grid,nr,nc,dir);
            }
        }
        return count;
    }
}
//463. Island Perimeter
class Solution {
     public int islandPerimeter(int[][] grid) {
        
    if (grid.length == 0 || grid[0].length == 0)
        return 0;

    int nbrs = 0, count = 0;
    int n = grid.length;
    int m = grid[0].length;
    int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
         
         
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
            {
                count++;
                
                for (int d = 0; d < dir.length; d++)
                {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                        nbrs++;
                }
            }
        }
    }
         
         return count*4 - nbrs;
        
    }
}
//130. Surrounded Regions
class Solution {
    
    public void solve(char[][] board) {
        
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        
        for(int i=0;i<board.length;i++)
        {
            if(board[i][0] == 'O')
                dfs(board,i,0,dir);
            if(board[i][board[0].length-1] == 'O')
                dfs(board,i,board[0].length-1,dir);
        }
        
        for(int j=0;j<board[0].length;j++)
        {
             if(board[0][j] == 'O')
                dfs(board,0,j,dir);
            if(board[board.length-1][j] == 'O')
                dfs(board,board.length-1,j,dir);
        }
        
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                if(board[i][j] == 'T')
                    board[i][j] = 'O';
            }
        }
        
    }
    
    public void dfs(char[][] board,int r,int c,int[][]dir)
    {
        board[r][c] = 'T';
        for(int d=0;d<dir.length;d++)
        {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            
            if(nr>=0 && nr<board.length && nc>=0 && nc<board[0].length && board[nr][nc] == 'O')
            {
                dfs(board,nr,nc,dir);
            }
        }
    }
}
//860 · Number of Distinct Islands
public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
     static StringBuilder str;
     static HashSet<String> set;
    public int numberofDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
        String[]dirs = {"U","R","D","L"};
        str = new StringBuilder();
        set = new HashSet<>();
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j]==1)
                {
                    dfs(grid,i,j,dir,dirs);
                    set.add(str.toString());
                    str = new StringBuilder();
                }   
            }   
        }
        return set.size();
    }
    public void dfs(int[][] grid,int r,int c,int[][]dir,String[]dirs)
    {
        grid[r][c] = 0;
        for(int i=0;i<dir.length;i++)
        {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];
            
            if(nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc]==1)
            {
                str.append(dirs[i]);
                dfs(grid,nr,nc,dir,dirs);
            }
        }
        str.append("b");
    }
}
//Journey to Moon : TLE
class Result {

    /*
     * Complete the 'journeyToMoon' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY astronaut
     */

    public static int journeyToMoon(int n, List<List<Integer>> astronaut) {
        
        ArrayList<Integer>[] graph = new ArrayList[n];
        boolean [] vis = new boolean[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        
        for(List<Integer> arr : astronaut)
        {
            int u = arr.get(0);
            int v = arr.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
    
        List<Integer> ans = new ArrayList<>(); 
        for(int i=0;i<n;i++)
        {
            if(!vis[i])
            {
                ans.add(gcc(graph,vis,i));
            }
        }
       
        int res = 0;
        for(int i=0;i<ans.size();i++)
        {
            for(int j=i+1;j<ans.size();j++)
            {
                res+= ans.get(i)*ans.get(j);
            }
        }
        return res;
    }
    
    public static int gcc(ArrayList<Integer>[] graph,boolean[]vis,int src)
    {
        vis[src] = true;
        int count = 1;
        
        for(int ele : graph[src])
        {
            if(!vis[ele])
                count+= gcc(graph,vis,ele);
        }
        return count;
    }

}
//JTM : MODIFIED : will pass in long datatype
class Result {

    /*
     * Complete the 'journeyToMoon' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY astronaut
     */

    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
        
        ArrayList<Integer>[] graph = new ArrayList[n];
        boolean [] vis = new boolean[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        
        for(List<Integer> arr : astronaut)
        {
            int u = arr.get(0);
            int v = arr.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
    
        List<Integer> ans = new ArrayList<>(); 
        for(int i=0;i<n;i++)
        {
            if(!vis[i])
            {
                ans.add(gcc(graph,vis,i));
            }
        }
       
        long sum = 0;
        long res = 0;
        for(int size : ans)
        {
            res += sum*size;
            sum += size;    
        }   
        return res;
    
    }
    
    public static int gcc(ArrayList<Integer>[] graph,boolean[]vis,int src)
    {
        vis[src] = true;
        int count = 1;
        
        for(int ele : graph[src])
        {
            if(!vis[ele])
                count+= gcc(graph,vis,ele);
        }
        return count;
    }

}
}
//1905. Count Sub Islands
class Solution {
    boolean res = true;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
    
        int ans = 0;
        int[][] dir ={{0,1},{0,-1},{1,0},{-1,0}};
        for(int i=0;i<grid2.length;i++)
        {
            for(int j=0;j<grid2[0].length;j++)
            {
                if(grid1[i][j] == 1 && grid2[i][j]==1)
                {
                   floodfill(i,j,grid2,grid1,dir);
                        if(res) ans++;
                    res = true;
                }   
            }
        }
        return ans;
    }
    
    public void floodfill(int i,int j,int[][]grid2,int[][]grid1,int[][]dir)
    {
        if(grid1[i][j]==0)
             res = false;
        
        grid2[i][j] = 0;
        
        for(int d = 0;d<dir.length;d++)
        {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            
            if(r>=0 && r<grid2.length && c>=0 && c<grid2[0].length && grid2[r][c]==1)
            {
                floodfill(r,c,grid2,grid1,dir);
            }
        }
    }
}