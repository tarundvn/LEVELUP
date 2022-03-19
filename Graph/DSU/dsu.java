//684. Redundant Connection
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        
        int n = edges.length;
        int[]parr = new int[n];
        for(int i=0;i<n;i++)
            parr[i]=i;
        
        for(int i=0;i<n;i++)
        {
            int u = edges[i][0]-1;
            int v = edges[i][1]-1;
            
            int p1 = findPar(u,parr);
            int p2 = findPar(v,parr);
            
            if(p1!=p2)
            {
                parr[p1] = p2;
            }
            else
            {
                return new int[]{u+1,v+1};
            }
        }   
        return new int[]{};
    }
    public int findPar(int u,int[]parr)
    {
        return u==parr[u] ? u : (parr[u] = findPar(parr[u],parr));
    }
}
//Lexicographically smallest equivalent string
public class Solution {
	public static String smallestString(String s, String t, String str) {
		
        int n = s.length();
        int[] parr = new int[26];
        for(int i=0;i<26;i++)
            parr[i] = i;
        
        for(int i=0;i<n;i++)
        {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            int p1 = findPar(c1-'a',parr);
            int p2 = findPar(c2-'a',parr);
             parr[p1] = Math.min(p1, p2);
        	 parr[p2] = Math.min(p1, p2);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++)
        {
            char ch = str.charAt(i); 
            int val = parr[ch-'a'];
            sb.append(val + 'a');
        }
        return sb.toString();
	}
    public static int findPar(int u,int[]parr)
    {
        return u==parr[u] ? u : (parr[u] = findPar(parr[u],parr));
    }
}
//695. Max Area of Island
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        
        int n = grid.length;
        int m = grid[0].length;
        
        int[] parr = new int[n*m];
        int[] size = new int[n*m];
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        
        for(int i=0;i<n*m;i++){
            parr[i] = i;
            size[i] = 1;
        }
        
        for(int i=0;i<n*m;i++)
        {
            int r = i/m;
            int c = i%m;
        
            if(grid[r][c]==1)
            {
                for(int d=0;d<dir.length;d++)
                {
                    int nr = r + dir[d][0];
                    int nc = c + dir[d][1];
                
                    if(nr>=0 && nr<n && nc>=0 && nc<m && grid[nr][nc] == 1)
                    {
                        int p1 = findPar(r*m+c,parr);
                        int p2 = findPar(nr*m+nc,parr);
                    
                        if(p1!=p2)
                        {
                            parr[p1] = p2;
                            size[p2] += size[p1];
                        }
                    }
                }
            }
        }
        int ans = 0;
        for(int i=0;i<size.length;i++)
        {
            if(grid[i/m][i%m] == 1)
            ans = Math.max(ans,size[i]);
        }
        return ans;
    }
    
    public int findPar(int u,int[]parr)
    {
        return u == parr[u] ? u : (parr[u] = findPar(parr[u],parr));
    }
}
//839. Similar String Groups
class Solution {
    public int numSimilarGroups(String[] strs) {
        
        int[] parr = new int[strs.length];
        for(int i=0;i<parr.length;i++)
            parr[i] = i;
        
        for(int i=0;i<strs.length-1;i++)
        {
            
            for(int j=i+1;j<strs.length;j++)
            {
            
                String a = strs[i];
                String b = strs[j];
            
                if(isSimilar(a,b))
                {
                    int p1 = findPar(i,parr);
                    int p2 = findPar(j,parr);
                    
                    if(p1!=p2)
                    {
                        parr[p1] = p2;
                    }   
                }
            }
        }
        
            int count = 0;
            for(int i=0;i<strs.length;i++)
            {
                if(parr[i] == i)
                count++;
            }
            return count;
    }
    public boolean isSimilar(String a,String b)
    {
        int count = 0;
        for(int i=0;i<a.length();i++)
        {
            char ch1 = a.charAt(i);
            char ch2 = b.charAt(i);     
            if(ch1 != ch2)
                count++;
        }
        return count <= 2;
    }
    
    public static int findPar(int u,int[]parr)
    {
        return u==parr[u] ? u : (parr[u] = findPar(parr[u],parr));
    }
}
//434 · Number of Islands II
public class Solution {
    /**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        if(operators==null) return new ArrayList<>();
        int[][] grid = new int[n][m];
        int[] parr = new int[n*m];  
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int i=0;i<n*m;i++)
            parr[i] = i;
        
        List<Integer> ans = new ArrayList<>();
        int count = 0;
        for(int i=0;i<operators.length;i++)
        {
            int r = operators[i].x;
            int c = operators[i].y;

            if(grid[r][c] == 1)
            {
                ans.add(count);
                continue;
            }
            grid[r][c] = 1;
            count++;
            for(int d=0;d<dir.length;d++)
            {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1]; 
                
                if(nr>=0 && nr<n && nc>=0 && nc<m && grid[nr][nc] == 1)
                {
                    int p1 = findPar(nr*m + nc ,parr);
                    int p2 = findPar(r*m + c ,parr);

                    if(p1!=p2)
                    {
                        parr[p1] = p2;
                        count--;
                    }

                }
            }
            ans.add(count);
        }
        return ans;
    }
    public static int findPar(int u,int[]parr)
    {
        return u==parr[u] ? u : (parr[u] = findPar(parr[u],parr));
    }
}
//Very IMP see Picture as well
//Optimize Water Distribution
public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
      
      List<int[]> edges = new ArrayList<>();
      for(int i=0;i<wells.length;i++)
      {
          edges.add(new int[]{0,i+1,wells[i]});
      }
      
      for(int [] a : pipes)
      {
          edges.add(a);
      }
      
      int[] parr = new int[n+1];
      for(int i=0;i<n+1;i++)
      {
          parr[i] = i;
      }
      
      Collections.sort(edges,(a,b)->{
          return a[2] - b[2];
      });
      
      int wt = 0;
      for(int []arr : edges)
      {
          int u = arr[0];
          int v = arr[1];
          
          int p1 = findPar(u,parr);
          int p2 = findPar(v,parr);
          
          if(p1!=p2)
          {
              wt+= arr[2];
              parr[p1] = p2;
          }
      }
      
    return wt;
  }
//924. Minimize Malware Spread
class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        
        int n = graph.length;
        int[]parr = new int[n];
        for(int i=0;i<n;i++)
            parr[i] = i;

        int[] population = new int[n];
        for(int i=0;i<n;i++)
            population[i] = 1;
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i==j || graph[i][j] == 0)
                    continue;
                
                int p1 = findPar(i,parr);
                int p2 = findPar(j,parr);
                
                if(p1!=p2)
                {
                    parr[p1] = p2;
                    population[p2] += population[p1];
                }
            }
        }
        
        int[] malwares = new int[n];
        for(int i=0;i<initial.length;i++)
        {
            int par = findPar(initial[i],parr);
            malwares[par]++;
        }
        
        Arrays.sort(initial);
        
        int idx = initial[0];
        int currpopulation = population[findPar(initial[0],parr)];
        for(int i=1;i<initial.length;i++)
        {
            if(malwares[findPar(initial[i],parr)] == 1)
            {
                if(population[findPar(initial[i],parr)] > currpopulation)
                    idx = initial[i];
                if(malwares[findPar(idx,parr)] != 1)
                     idx = initial[i];
            }
        }
         return idx;
    }
    
    public int findPar(int u,int[]parr)
    {
        return u == parr[u] ? u : (parr[u] = findPar(parr[u],parr));
    }
}
//959. Regions Cut By Slashes
class Solution {
    public int regionsBySlashes(String[] grid) {
        
        int n = grid.length;
        int size = (n+1)*(n+1);
        int[] parr = new int[size];
        for(int i=0;i<size;i++)
            parr[i] = i;
        
        for(int i=0;i<n+1;i++)
        {
            parr[0*(n+1) + i] = parr[0];
            parr[(n)*(n+1) + i] = parr[0];
            parr[i*(n+1) + 0] = parr[0];
            parr[i*(n+1) + n] = parr[0];
        }
        
        int regions = 1;
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                char ch = grid[i].charAt(j);
                if(ch == '/')
                {
                    regions += union((i+1)*(n+1)+j,i*(n+1) + (j+1),parr);
                }
                
                if(ch == '\\')
                {
                    regions += union(i*(n+1)+j,(i+1)*(n+1) + (j+1),parr);
                }
            }
        }
        
        return regions;
    }
    
    public int findPar(int u,int[]parr)
    {
        return u == parr[u] ? u : (parr[u] = findPar(parr[u],parr)); 
    }
    
    public int union(int u,int v,int[] parr)
    {
        int p1 = findPar(u,parr);
        int p2 = findPar(v,parr);
        
        if(p1!=p2)
        {
            parr[p2] = p1;
            return 0;
        }
        return 1;
    }
}