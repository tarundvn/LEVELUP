//MotherVertexGFG
//1 TopoLogical Sort(Store in Stack)
//2 Copy Graph intpo New Graph with reversed edges
//3 DFS(Pop and Store
//LeetCode 1631
class Solution {
    public int minimumEffortPath(int[][] heights) {
        
        int n = heights.length;
        int m = heights[0].length;
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        boolean[][]vis = new boolean[n][m];
        
        PriorityQueue<int[]> pque = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });
        
        pque.add(new int[]{0,0,heights[0][0]});
        
        while(pque.size()!=0)
        {
            int size = pque.size();
            while(size-->0)
            {
                int[]rv = pque.remove();
                int r = rv[0]/m;
                int c = rv[0]%m;
                int diff = rv[1];
                int val = rv[2];
                
                if(vis[r][c])
                    continue;
                vis[r][c] = true;
                
                if(r == n-1 && c == m-1)
                    return diff;
                
                for(int d = 0;d<dir.length;d++)
                {
                    int nr = r + dir[d][0];
                    int nc = c + dir[d][1];
                    
                    if(nr>=0 && nr<n && nc>=0 && nc<m && vis[nr][nc] == false)
                    {
                        int ndiff = Math.max(diff,Math.abs(heights[nr][nc] - val));
                        pque.add(new int[]{nr*m + nc,ndiff,heights[nr][nc]});
                    }
                }
                
            }
        }
        return -1;
    }
}
//815. Bus Routes
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        
        int n = routes.length;
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<n;i++)
        {
            for(int ele : routes[i])
            {
                List<Integer> r = map.getOrDefault(ele,new ArrayList<>());
                r.add(i);
                map.put(ele,r);
            }
        }
       
        HashSet<Integer> stand = new HashSet<>();
        stand.add(source);
        HashSet<Integer> busNo = new HashSet<>();
        
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(source);
        int level = 0;
        while(que.size()!=0)
        {
            int size = que.size();
            while(size-->0)
            {
                int rv= que.removeFirst();
                
                if(rv == target)
                    return level;
                
                for(int bus : map.get(rv))
                {
                    if(!busNo.contains(bus))
                    {
                        busNo.add(bus);
                        for(int ele : routes[bus])
                        {
                            if(!stand.contains(ele))
                            {
                                stand.add(ele);
                                que.addLast(ele);
                            }
                        }
                    }
                }                
            }
            level++;
        }
        return -1;
    }
}