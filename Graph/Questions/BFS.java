import java.util.*;
public class BFS
{
    //continue ka check is very imp
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
    //LevelOrder : BFS_CYCLE 
    public void levelOrder(ArrayList<Edge>graph[],int src,boolean[]vis)
    {
        LinkedList<Integer> que = new LinkedList<>();
        boolean isCycle = false;
        que.addLast(src);
        int level = 0;
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                int rv = que.removeFirst();
                if(vis[rv])
                {
                    isCycle = true;
                    continue;
                }
                vis[rv] = true;
                for(Edge e : graph[rv])
                {
                    if(!vis[e.v])
                        que.addLast(e.v);
                }
            }
            level++;
        }

        if(isCycle)
            System.out.println("Cycle is Present");
        else
            System.out.println("Cycle is not Present");
    }

    //LevelOrder : BFS_SHORTEST_PATH
    public void levelOrder(ArrayList<Edge>[]graph,int src,boolean[]vis)
    {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        vis[src] = true;
        int level = 0;
        while(que.size()!=0)
        {
            int size = que.size();
            System.out.print("level " + level + " :");
            while(size-->0)
            {
                int rv = que.removeFirst();
                System.out.print(rv + ",");
                for(Edge e : graph[rv])
                {
                    vis[e.v] = true;
                    que.addLast(e.v);
                }
            }
            level++;
            System.out.println();
        }
    }
    //785. Is Graph Bipartite?
    class Solution {
    public boolean isBipartite(int[][] graph) {
        
        int n = graph.length;
        int[] vis = new int[n];
        Arrays.fill(vis,-1);
        
        boolean res = true;
        for(int i=0;i<n;i++)
        {
            if(vis[i]==-1)
            {
                boolean rres = bfs(graph,vis,i);
                if(rres == false)
                    return rres;
            }
        }
        return res;
    }
    
    public boolean bfs(int[][] graph,int[]vis,int src)
    {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        boolean isBipartite = true;
        int color = 0;
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                int rv = que.removeFirst();
                
                if(vis[rv]!=-1)
                {
                    if(vis[rv] != color)
                    {
                        isBipartite = false;
                        return isBipartite;
                    }
                    else
                    {
                        continue;
                    }
                }
                
                vis[rv] = color;
                
                for(int ele : graph[rv])
                {
                    if(vis[ele]==-1)
                    {
                        que.addLast(ele);
                    }
                }
            }
            color = (color + 1)%2;
        }
        return isBipartite;
    }
}
//886. Possible Bipartition
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        
        //Construct Graph
        for(int [] arr : dislikes)
        {
            int u = arr[0];
            int v = arr[1];
            arr[0] = u-1;
            arr[1] = v-1;
        }
        
        ArrayList<Integer> [] graph = new ArrayList[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        
        for(int [] arr : dislikes)
        {
            graph[arr[0]].add(arr[1]);
            graph[arr[1]].add(arr[0]);
        }
        
        
        int[] vis = new int[n];
        Arrays.fill(vis,-1);
        
        boolean res = true;
        for(int i=0;i<n;i++)
        {
            if(vis[i]==-1)
            {
                boolean rres = bfs(graph,vis,i);
                if(rres == false)
                    return rres;
            }
        }
        return res;
    }
    
    public boolean bfs(ArrayList<Integer> [] graph,int[]vis,int src)
    {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        boolean isBipartite = true;
        int color = 0;
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                int rv = que.removeFirst();
                
                if(vis[rv]!=-1)
                {
                    if(vis[rv] != color)
                    {
                        isBipartite = false;
                        return isBipartite;
                    }
                    else
                    {
                        continue;
                    }
                }
                
                vis[rv] = color;
                
                for(int ele : graph[rv])
                {
                    if(vis[ele]==-1)
                    {
                        que.addLast(ele);
                    }
                }
            }
            color = (color + 1)%2;
        }
        return isBipartite;
    }
}
//Rotten Oranges
class Solution {
    public int orangesRotting(int[][] grid) {
        
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;        
        int[][]dir = {{0,1},{0,-1},{-1,0},{1,0}};
            
        for(int i=0;i<n;i++)
        {
           for(int j=0;j<m;j++)
           {
                if(grid[i][j] == 1)
                    count++;
           }
        }
        
        LinkedList<Integer> que = new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
           {
                if(grid[i][j]==2)
                    que.addLast(i*m+j);
           }
        }
        
        if(count == 0)
            return 0;
        
        int level = 0;
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                int rv = que.removeFirst();
                
                if(grid[rv/m][rv%m]!= 2)
                    count--; 
                
                grid[rv/m][rv%m] = 2;  
                
                if(count == 0)
                    return level;
                
                for(int d =0;d<dir.length;d++)
                {
                    int nr = rv/m + dir[d][0];
                    int nc = rv%m + dir[d][1];
                    
                    if(nr>=0 && nr<n && nc>=0 && nc<m && grid[nr][nc] == 1)
                    {
                        que.addLast(nr*m+nc);
                    }
                }                
            }
            level++;
        }
        return -1;
    }
}
//1091. Shortest Path in Binary Matrix
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        int n = grid.length;
        int m = grid[0].length;
        
        if(n==0 && m==0)
            return 0;
        if(grid[0][0] != 0 || grid[n-1][n-1] !=0)
            return -1;
        
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(0);
        int level = 0;
        while(que.size()!=0)
        {
            int size =que.size();
            while(size-->0)
            {
                int rv = que.removeFirst();
                
                if(grid[rv/m][rv%m] == 1)
                    continue;
                
                grid[rv/m][rv%m] = 1;
                
                if(rv/m == n-1 && rv%m == m-1)
                    return level + 1;
                
                for(int d=0;d<dir.length;d++)
                {
                    int nr = rv/m + dir[d][0];
                    int nc = rv%m + dir[d][1];
                    
                    if(nr>=0 && nr<n && nc>=0 && nc<m && grid[nr][nc] == 0)
                    {
                        que.addLast(nr*m+nc);
                    }
                }
            }
            level++;
        }
        
        return -1;
    }
}
//542. 01 Matrix : IMP
class Solution {
    public int[][] updateMatrix(int[][] grid) {
        
       int n = grid.length;
       int m = grid[0].length;
        
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        LinkedList<Integer> que = new LinkedList<>();
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
           {
                if(grid[i][j]==0)
                    que.addLast(i*m+j);
                if(grid[i][j] == 1)
                    grid[i][j] = -1;
           }
        }
        
        int level = 0;
        while(que.size()!=0)
        {
            int size =que.size();
            while(size-->0)
            {
                int rv = que.removeFirst();
                for(int d=0;d<dir.length;d++)
                {
                    int nr = rv/m + dir[d][0];
                    int nc = rv%m + dir[d][1];
                    
                    if(nr>=0 && nr<n && nc>=0 && nc<m && grid[nr][nc]==-1)
                    {
                        grid[nr][nc] = grid[rv/m][rv%m]  + 1;
                        que.addLast(nr*m+nc);
                    }
                }
            }
            level++;
        }
        
        return grid;
    }
}
//1020. Number of Enclaves
class Solution {
    public int numEnclaves(int[][] grid) {
        
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        LinkedList<Integer> que = new LinkedList<>();
        
        for(int i=0;i<n;i++)
        {
            if(grid[i][0] == 1)
                que.addLast(i*m);
            if(grid[i][m-1] == 1)
                 que.addLast(i*m + m-1);
        }
        
        for(int j=0;j<m;j++)
        {
            if(grid[0][j] == 1)
                que.addLast(j);
            if(grid[n-1][j] == 1)
                 que.addLast((n-1)*m + j);
        }
        
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                int rv = que.removeFirst();
                
                if(grid[rv/m][rv%m] == 2)
                    continue; 
                
                grid[rv/m][rv%m] = 2;
                
                for(int d=0;d<dir.length;d++)
                {
                    int nr = rv/m + dir[d][0];
                    int nc = rv%m + dir[d][1];
                    
                    if(nr>=0 && nr<n && nc>=0 && nc<m && grid[nr][nc] == 1)
                    {
                         que.addLast(nr*m + nc);
                    }
                }
                
            }
        }
        
        int count = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j] == 1)
                    count++;
                if(grid[i][j] == 2)
                    grid[i][j] = 1;
            }
        }
        return count;   
    }
}
//663 · Walls and Gates
public class Solution {
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    public void wallsAndGates(int[][] grid) {

       int n = grid.length;
       int m = grid[0].length;
        
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        LinkedList<Integer> que = new LinkedList<>();
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
           {
                if(grid[i][j]==0)
                    que.addLast(i*m+j);
           }
        }
        
        int level = 0;
        while(que.size()!=0)
        {
            int size =que.size();
            while(size-->0)
            {
                int rv = que.removeFirst();
                for(int d=0;d<dir.length;d++)
                {
                    int nr = rv/m + dir[d][0];
                    int nc = rv%m + dir[d][1];
                    
                    if(nr>=0 && nr<n && nc>=0 && nc<m && grid[nr][nc]==2147483647)
                    {
                        grid[nr][nc] = level + 1;
                        que.addLast(nr*m+nc);
                    }
                }
            }
            level++;
        }
        
    }
}
//1730. Shortest Path to Get Food :Premium
//2123 : premimum
//787 · The Maze
public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
       public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
        LinkedList<Integer> que = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        que.add(sr * m + sc);
        vis[sr][sc] = true;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst(), i = idx / m, j = idx % m;
                for (int[] d : dir) {
                    int r = i, c = j;
                    while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                        r += d[0];
                        c += d[1];
                    }
                    r -= d[0];
                    c -= d[1];
                    if (vis[r][c])
                        continue;
                    vis[r][c] = true;
                    que.addLast(r * m + c);
                    if (r == er && c == ec)
                        return true;
                }
            }
        }
        return false;
    }
}
//788 · The Maze II
public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
        PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });
        boolean[][] vis = new boolean[n][m];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        que.add(new int[]{sr * m + sc,0});
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int[]arr = que.remove();
                int idx = arr[0], i = idx / m, j = idx % m;
                 if (vis[i][j])
                        continue;
                vis[i][j] = true;
                if(i == er && j == ec)
                        return arr[1];

                for (int[] d : dir) {
                    int dis = arr[1];
                    int r = i, c = j;

                    while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                        r += d[0];
                        c += d[1];
                        dis++;
                    }

                    r -= d[0];
                    c -= d[1];
                    dis--;
                    que.add(new int[]{r * m + c,dis});
                }

            }
        }

        return -1;
    }
}
//789 · The Maze III //https://www.lintcode.com/problem/789
public class Solution {
    /**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */
    public class pair implements Comparable<pair>{
        int r;
        int c;
        int dis;
        String path;
        pair(int r,int c,int dis,String path)
        {
            this.r = r;
            this.c = c;
            this.dis = dis;
            this.path = path;
        }
        @Override
        public int compareTo(pair o) {
            if (this.dis != o.dis)
                return this.dis - o.dis;
            else
                return this.path.compareTo(o.path);
        }
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {

        int n = maze.length,m = maze[0].length,sr = ball[0],sc = ball[1],dr= hole[0],dc = hole[1];
        int[][]dir = {{-1,0},{0,1},{1,0},{0,-1}};
        String[]dirs = {"u","r","d","l"};
        PriorityQueue<pair> pque = new PriorityQueue<>();
        boolean[][] vis = new boolean[n][m];
        pque.add(new pair(sr,sc,0,""));
        while(pque.size()!=0)
        {
            int size = pque.size();
            while(size-->0)
            {
                pair rp = pque.remove();
                if(vis[rp.r][rp.c])
                    continue;
                vis[rp.r][rp.c] = true;

                if(rp.r == dr && rp.c == dc)
                    return rp.path;

                for(int d=0;d<dir.length;d++)
                {
                    int r = rp.r;
                    int c = rp.c;
                    int distance = rp.dis;
                    while(r>=0 && r<n && c>=0 && c<m && maze[r][c] == 0 && (r!=dr || c!=dc))
                    {
                        r+=dir[d][0];
                        c+=dir[d][1];
                        distance++;
                    }

                    if(r!=dr || c!=dc)
                    {
                        distance--;
                        r-=dir[d][0];
                        c-=dir[d][1];
                    }
                    pque.add(new pair(r,c,distance,rp.path + dirs[d]));
                }
            }
        }
       return "impossible";
    }
}
}
