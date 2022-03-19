//207. Course Schedule
class Solution {
    public boolean canFinish(int n, int[][] pre) {
        
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        
        for(int []arr : pre)
        {
            int u = arr[0];
            int v = arr[1];
            graph[u].add(v);
        }
        
        int[]indegree = new int[n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<graph[i].size();j++)
            {
                indegree[graph[i].get(j)]++;
            }
        }
        
        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            if(indegree[i] == 0)
                que.addLast(i);
        }
        
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                int rv = que.removeFirst();
                ans.add(rv);
                for(int ele : graph[rv])
                {
                    if(--indegree[ele] == 0)
                        que.addLast(ele);
                }
            }
        }
        return ans.size() == n;
    }
}
//210. Course Schedule II
class Solution {
    public int[] findOrder(int n, int[][] pre) {
        
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        
        for(int []arr : pre)
        {
            int u = arr[0];
            int v = arr[1];
            graph[u].add(v);
        }
        
        int[]indegree = new int[n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<graph[i].size();j++)
            {
                indegree[graph[i].get(j)]++;
            }
        }
        
        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            if(indegree[i] == 0)
                que.addLast(i);
        }
        
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                int rv = que.removeFirst();
                ans.add(rv);
                for(int ele : graph[rv])
                {
                    if(--indegree[ele] == 0)
                        que.addLast(ele);
                }
            }
        }
        Collections.reverse(ans);
        
        if(ans.size()!=n)
            return new int[]{};
        
        return ans.stream().mapToInt(i -> i).toArray();     /// very IMPORTANT
    }
}
//329. Longest Increasing Path in a Matrix
class Solution {
    public int longestIncreasingPath(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
        LinkedList<Integer> que = new LinkedList<>();
        int[][] arr = new int[n][m];
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                for(int d=0;d<dir.length;d++)
                {
                    int nr = i + dir[d][0];
                    int nc = j + dir[d][1];
                    
                    if(nr>=0 && nc>=0 && nr<n && nc<m && mat[nr][nc] < mat[i][j])
                        arr[nr][nc]++;
                }
            }
        }
        
         for(int i=0;i<n;i++)
         {
            for(int j=0;j<m;j++)
            {
                if(arr[i][j] == 0)
                    que.addLast(i*m+j);
            }
         }
        
        int level = 0;
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                int rv = que.removeFirst();
                int r = rv/m;
                int c = rv%m;
                
               for(int[] d : dir) {
                    int dr = r + d[0];
                    int dc = c + d[1];
                    if(dr >= 0 && dc >= 0 && dr < mat.length && dc < mat[0].length && mat[dr][dc] < mat[r][c]) {
                        
                        if(--arr[dr][dc] == 0) {
                            que.addLast(dr * mat[0].length + dc);
                        }
                    }
                }
            }
            level++;
        }
        
        return level;
    }
}