// import java.util.ArrayList;
import java.util.*;
public class l001{

    //CLASS 1
    public static void pppppp(int a, int b) {
        if (a > b)
            return;
    }

    public static void ppppp(int a, int b) {
        System.out.println(a);
        pppppp(a + 1, b);
    }

    public static void pppp(int a, int b) {
        System.out.println(a);
        ppppp(a + 1, b);
    }

    public static void ppp(int a, int b) {
        System.out.println(a);
        pppp(a + 1, b);
    }

    public static void pp(int a, int b) {
        System.out.println(a);
        ppp(a + 1, b);
    }

    public static void p(int a, int b) {
        System.out.println(a);
        pp(a + 1, b);
    }

    public static void printIncreasing(int a,int b)
    {
        if(a == b)
        {
            System.out.println(a);
            return;
        }
        System.out.println(a);
        printIncreasing(a+1,b);
    }

    public static void printDecreasing(int a,int b)
    {
        if(a == b)
        {
            System.out.println(b);
            return;
        }
        System.out.println(b);
        printDecreasing(a,b-1);
    }

    public static void printIncreasingDecreasing(int a,int b)
    {
        if(a==b)
        {
            System.out.println(a);
            System.out.println(a);
            return;
        }
        System.out.println(a);
        printIncreasingDecreasing(a+1,b);
        System.out.println(a);
    }

    public static void printOddEven(int a,int b)
    {
        if(a==b)
        {
            System.out.println(a);
            return;
        }  
        if(a%2!=0)
            System.out.println(a);
        printOddEven(a+1,b);
            if(a%2==0)
            System.out.println(a);
    }

    public static int printTreePath(int n)
    {
        if(n==1 || n==0)
        {
            System.out.println("base " + n);
            return n;
        }
        int ans = 0;
        System.out.println("pre " + n);
        ans += printTreePath(n-1);
        System.out.println("In " + n);
        ans+= printTreePath(n-2);
        System.out.println("post " + n);
        return ans + 3;
    }

    public static int factorial(int n)
    {
        return n==0? 1 : factorial(n-1)*n;
    }

    public static int power(int a,int b)
    {
        return b==0? 1: a*power(a,b-1);
    }

    public static int powerBetter(int a,int b)
    {
        if(b==0)
            return 1;
        if(b%2!=0)
            return powerBetter(a,b/2)*powerBetter(a,b/2)*a;
        else
            return powerBetter(a,b/2)*powerBetter(a,b/2);
    }

    //Recursion With Array
    public static void display(int[] arr,int idx)
    {
        if(idx == arr.length)
            return;
        System.out.println(arr[idx]);
        display(arr,idx+1);
    }

    public static void displayReverse(int[] arr,int idx)
    {
        if(idx == arr.length)
            return;
        display(arr,idx+1);
        System.out.println(arr[idx]);
    }

    public static int maximum(int[] arr,int idx)
    {
        if(idx == arr.length)
            return -(int)1e9;
        return Math.max(arr[idx],maximum(arr,idx+1));
    }

    public static int minimum(int[] arr,int idx)
    {
        if(idx == arr.length)
            return (int)1e9;
        return Math.min(arr[idx],minimum(arr,idx+1));
    }

    public static int find(int []arr,int idx,int data)
    {
        if(idx == arr.length)
            return -1;
        if(data == arr[idx])
            return idx;
        return find(arr,idx+1,data);
    }

    public static int firstIndex(int[] arr,int idx,int data)
    {
        if(idx == arr.length)
            return -1;
        int rans = firstIndex(arr,idx+1,data);
        if(arr[idx] == data)
            return idx;
        return rans;
    }

    public static int lastIndex(int[] arr,int idx,int data)
    {
         if(idx == arr.length)
            return -1;
        int rans = lastIndex(arr,idx+1,data);
        if(arr[idx] == data && rans==-1)
            return idx;
        return rans;
    }

    public static int[] allIndex(int[]arr,int idx,int data,int count)
    {
        if(idx == arr.length)
            return new int[count];
        if(arr[idx] == data)
            count++;
        int[] ans = allIndex(arr,idx+1,data,count);
        if(arr[idx] == data)
            ans[count-1] = idx;
        return ans;
    }

    // CLASS 2
    // ye top to bottom approch hai 
    // isme upar se ans mangvate hai
    public static ArrayList<String> getSubSequence(String str,int idx)
    {
        if(idx == str.length())
        {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        ArrayList<String> rres = getSubSequence(str,idx+1);
        ArrayList<String> mres = new ArrayList<>();
        for(String st : rres)
        {
            mres.add(str.charAt(idx) + st);
        }
        for(String st : rres)
        {
            mres.add(st);
        }
        return mres;
    }

    //bottom to top approach hai ye :
    // iska stack diagram bna kar dekh lo:
    public static int getSubSequence(String str,int idx,String ans,ArrayList<String> res)
    {
        if(idx == str.length())
        {
            res.add(ans);
            return 1;
        }
        int count = 0;
        count += getSubSequence(str,idx+1,ans+str.charAt(idx),res);
        count += getSubSequence(str,idx+1,ans,res);
        return count;
    }

    //https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    public static List<String> letterCombinations(String str) {
        
        if(str.length() == 0)
            return new ArrayList<>();
     
        String[] map = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        return letterCombinations(str,0,map);
    }

    public static List<String> letterCombinations(String str,int idx,String[]map) {
     
        if(idx == str.length())
        {
            List<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        List<String> rres = letterCombinations(str,idx+1,map);
        List<String> mres = new ArrayList<>();
        String val = map[str.charAt(idx) - '0'];
        
        for(int i=0;i<val.length();i++)
        {
            for(String s : rres)
            {
                mres.add(val.charAt(i) + s);
            }
        }
        return mres;
    }

    public static ArrayList<String> getStairPaths(int n) {

        if(n<0)
        {
            return new ArrayList<>();
        }
        
        if(n==0)
        {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        ArrayList<String> rres1 = getStairPaths(n-1);
        ArrayList<String> rres2 = getStairPaths(n-2);
        ArrayList<String> rres3 = getStairPaths(n-3);

        ArrayList<String> mres = new ArrayList<>();
        
        for(String s : rres1)
            mres.add('1'+s);
        for(String s : rres2)
            mres.add('2'+s);
        for(String s : rres3)
            mres.add('3'+s);

        return mres;
    } 

    public static ArrayList<String> mazePath(int sr,int sc,int er,int ec)
    {
        if(sr == er && sc == ec)
        {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        ArrayList<String> mres = new ArrayList<>();
        if(sr+1<=er)
        {
            ArrayList<String> hres = mazePath(sr+1,sc,er,ec);
            for(String s : hres)
                mres.add('h' + s);
        }
        if(sc+1<=ec)
        {
            ArrayList<String> vres = mazePath(sr,sc+1,er,ec);
            for(String s : vres)
                mres.add('v' + s);
        }
        return mres;
    }

    public static int mazePath(int sr,int sc,int er,int ec,String ans,ArrayList<String> res)
    {
        if(sr == er && sc == ec)
        {
            res.add(ans);
            return 1;
        }
        int count = 0;
        if(sc+1<=ec)
        count += mazePath(sr,sc+1,er,ec,ans+'h',res);
        if(sr+1<=er)
        count += mazePath(sr+1,sc,er,ec,ans+'v',res);
        return count;
    }

    public static ArrayList<String> mazePath_multi(int sr,int sc,int er,int ec)
    {
        if(sr == er && sc == ec)
        {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        ArrayList<String> mres = new ArrayList<>();
        for(int jump=1;sr+jump<=er;jump++)
        {
            ArrayList<String> hres = mazePath_multi(sr+jump,sc,er,ec);
            for(String s : hres)
                mres.add("h"+ jump + s);
        }
        for(int jump=1;sc+jump<=ec;jump++)
        {
            ArrayList<String> vres = mazePath_multi(sr,sc+jump,er,ec);
            for(String s : vres)
                mres.add("v" + jump + s);
        }
        return mres;
    }

    public static int mazePath_multi(int sr,int sc,int er,int ec,String ans,ArrayList<String> res)
    {
        if(sr == er && sc == ec)
        {
            res.add(ans);
            return 1;
        }
        int count = 0;
        for(int jump=1;sc+jump<=ec;jump++)
        count += mazePath_multi(sr,sc+jump,er,ec,ans+'h'+jump,res);
        for(int jump=1;sr+jump<=er;jump++)
        count += mazePath_multi(sr+jump,sc,er,ec,ans+'v'+jump,res);
        return count;
    }

    public static int mazePath_2(int sr,int sc,int er,int ec,String ans,ArrayList<String> res,int[][]dir,String[]dirs)
    {
        if(sr == er && sc == ec)
        {
            res.add(ans);
            return 1;
        }
        int count = 0;
        for(int d=0;d<dir.length;d++)
        {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];
            if(x>=0 && x<=er && y>=0 && y<=ec)
                count+= mazePath_2(sr,sc,er,ec,ans + dirs[d],res,dir,dirs);
        }
        return count;
    }

    public static int floodfill(int sr,int sc,int er,int ec,String ans,boolean[][] vis,ArrayList<String> res,int[][]dir,String[]dirs)
    {
        if(sr == er && sc == ec)
        {
            res.add(ans);
            return 1;
        }
        int count = 0;
        vis[sr][sc] = true;
        for(int d=0;d<dir.length;d++)
        {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];
            if(x>=0 && x<=er && y>=0 && y<=ec && !vis[x][y])
                count+= floodfill(sr,sc,er,ec,ans + dirs[d],vis,res,dir,dirs);
        }
        vis[sr][sc] = false;
        return count;
    }

    public static int floodFill_multi(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS,
        ArrayList<String> ans, String psf) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        vis[sr][sc] = true;
        for (int d = 0; d < dir.length; d++)
            for (int rad = 1; rad <= Math.max(n, m); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (!vis[r][c])
                        count += floodFill_multi(r, c, vis, dir, dirS, ans, psf + dirS[d] + rad);
                } else
                    break;
            }
        vis[sr][sc] = false;
        return count;
    }
    
    //CLASS3
    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
    // Rat in a Maze Problem - I 
    public static ArrayList<String> findPath(int[][] m, int n) {
        
        ArrayList<String> ans = new ArrayList<>();
        if(m.length==0 || m[0].length ==0 || m[n-1][n-1] ==0 || m[0][0]==0)
        {
            ans.add("-1");
            return ans;
        }
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
        String[] dirs = {"U","D","L","R"};
        solver(0,0,n-1,n-1,m,dir,dirs,ans,"");
        Collections.sort(ans);
        return ans;
    }
    
    public static int solver(int sr,int sc,int er,int ec,int[][]m,int[][] dir,String[] dirs,ArrayList<String> ans,String psf)
    {
        if(sr==er && sc==ec)
        {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        m[sr][sc] = 2;
        for(int d=0;d<dir.length;d++)
        {
            int nr = sr + dir[d][0];
            int nc = sc + dir[d][1];
            if(nr>=0 && nr<=er && nc>=0 && nc<=ec && m[nr][nc]==1)
                count+= solver(nr,nc,er,ec,m,dir,dirs,ans,psf+dirs[d]);
        }
        m[sr][sc] = 1;
        return count;
    }
    //https://practice.geeksforgeeks.org/problems/special-matrix4201/1
    // Special Matrix 
    //static int mod = (int)1e9 + 7;
    public int FindWays(int n, int m, int[][] blocked_cells)
    {
        if(n==0 || m==0)
            return 0;
        
        int[][] mat = new int[n][m];
        for(int i=0;i<blocked_cells.length;i++)
        {
            mat[blocked_cells[i][0]-1][blocked_cells[i][1]-1] = 1;
        }
        if(mat[0][0] == 1)
            return 0;
        int[][] dir = {{1,0},{0,1}};
        int[][] dp = new int[n][m];
        for(int []arr: dp)
            Arrays.fill(arr,-1);
        return solver(0,0,n-1,m-1,mat,dir,dp);
    }
    
    public static int solver(int sr,int sc,int er,int ec,int[][]mat,int[][] dir,int[][]dp)
    {
        if(sr==er && sc==ec)
        {
            return dp[sr][sc] = 1;
        }
        if(dp[sr][sc]!=-1)
            return dp[sr][sc];
        int count = 0;
        for(int d=0;d<dir.length;d++)
        {
            int nr = sr + dir[d][0];
            int nc = sc + dir[d][1];
            if(nr>=0 && nr<=er && nc>=0 && nc<=ec && mat[nr][nc]==0)
                count = (count % mod +  solver(nr,nc,er,ec,mat,dir,dp) % mod)%mod;
        }
        return dp[sr][sc] = count;
    }

    //Rat in a Maze Multiple Jumps allowed
    //Shortest Longest Path galt ans de deta hai tabhi 0 -1 return karaya hai
    public static class pair {
        String psf = "";
        int len = 0;

        pair(String psf, int len) {
            this.len = len;
            this.psf = psf;
        }
    }
    public static pair longestPath(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return new pair("", 0);
        }
        vis[sr][sc] = true; // blocked
        pair myAns = new pair("", -1);
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < m) {
                if (!vis[r][c]) {
                    pair recAns = longestPath(r, c, vis, dir, dirS);
                    if (recAns.len != -1 && recAns.len + 1 > myAns.len) {
                        myAns.len = recAns.len + 1;
                        myAns.psf = dirS[d] + recAns.psf;
                    }
                }
            }
        }
        vis[sr][sc] = false; // unblocked
        return myAns;
    }

    public static pair shortestPath(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return new pair("", 0);
        }
        vis[sr][sc] = true; // blocked
        pair myAns = new pair("", (int)1e9);
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < m) {
                if (!vis[r][c]) {
                    pair recAns = shortestPath(r, c, vis, dir, dirS);
                    if (recAns.len != (int)1e9 && recAns.len + 1 < myAns.len) {
                        myAns.len = recAns.len + 1;
                        myAns.psf = dirS[d] + recAns.psf;
                    }
                }
            }
        }
        vis[sr][sc] = false; // unblocked
        return myAns;
    }
    public static void longestShortestPath() {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        String[] dirS = { "D", "R", "U", "L" };
        boolean[][] vis = new boolean[3][3];
        // vis[1][1] = vis[1][2] = vis[2][1] = true;
        pair ans = longestPath(0, 0, vis, dir, dirS);
        System.out.println(ans.psf + " @ " + ans.len);
    }

    public static void main(String[] args)
    {
        // CLASS 1
        // System.out.println("ICREASING");
        // printIncreasing(5,12);
        // System.out.println("DECREASING");
        // printDecreasing(5,12);
        // System.out.println("INCREASINGDECREASING");
        // printIncreasingDecreasing(5,12);
        // System.out.println("ODDEVEN");
        // printOddEven(5,12);
        // System.out.println(printTreePath(5));
        // System.out.println(powerBetter(2,5));
        // int[] arr = {64,6,4,6,6,7,8,90,6,89,9};
        // System.out.println(maximum(arr,0));
        // System.out.println(minimum(arr,0));
        // System.out.println(find(arr,0,90));
        // System.out.println(firstIndex(arr,0,6));
        // System.out.println(lastIndex(arr,0,6));
        // System.out.println(getSubSequence("abc",0));
        // ArrayList<String> res = new ArrayList<>();
        // System.out.println(getSubSequence("abcd",0,"",res));
        // System.out.println(res);
        // System.out.println(letterCombinations("234"));
        // System.out.println(getStairPaths(8));
        // CLASS 2
        // System.out.println(mazePath(0,0,3,3));
        // ArrayList<String> res = new ArrayList<>();
        // System.out.println(mazePath(0,0,3,3,"",res));
        // System.out.println(res);
        // System.out.println(mazePath_multi(0,0,2,2).size());
        // ArrayList<String> res = new ArrayList<>();
        // System.out.println(mazePath_multi(0,0,2,2,"",res));
        // System.out.println(res);
        // System.out.println(mazePath(0,0,3,3));
    }
}