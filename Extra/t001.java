import java.util.*;
public class t001{

    static long[][][][] dp;
    public static void main(String[] args)
    {
        Scanner scn = new Scanner(System.in);
        int A = scn.nextInt();
        int[][] B = new int[A][A];
        for(int i=0;i<A;i++)
        {
            for(int j=0;j<A;j++)
            {
                B[i][j] = scn.nextInt();
            }
        }

        dp =  new long[14][((1<<14) - 1)][14][2];
        for(long[][][] x : dp)
        {
            for(long[][] y : x)
            {
                for(long[]z : y)
                {
                    Arrays.fill(z,-1);
                }
            }
        }

        return solution(A,B,0,0,0,0);
    }

    public static long solution(int A,int[][]B,int idx,int mask,int twos,int threes)
    {
        if(idx == A)
        {
            if(twos == A || threes > 0)
                return 1;
            return 0;
        }

        if(dp[idx][mask][twos][threes]!=-1)
            return dp[idx][mask][twos][threes];

        long moves = 0;

        for(int i=0;i<A;i++)
        {
            int check = (1<<i);
            if((check & mask) == 0)
            {
                int y = (B[idx][i] == 2) ? 1 : 0;
                int z = (B[idx][i] == 3) ? 1 : 0;
                if(threes == 1)
                    z = 1;
                moves += solution(A,B,idx+1,(mask | check),twos + y,z);
            }
        }

        return dp[idx][mask][twos][threes] = moves;
    }
}