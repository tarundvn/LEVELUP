public class l001{
  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int i = scn.nextInt();
    int j = scn.nextInt();
    int k = scn.nextInt();
    int m = scn.nextInt();
    
    int onmask = (1<<i);
    System.out.println((n|onmask)); 
    int offmask = ~(1<<j);
    System.out.println((n&offmask));
    int xormask = (1<<k);
    System.out.println((n^xormask));
    int checkmask = (1<<m);
    System.out.println((n&checkmask) == 0 ? false : true);
  }
}

//Print Value Of Rsb Mask   :: pheli bit jo 1 ho  <--- iss direction se (-n is 2s complement of n)
import java.io.*;
import java.util.*;
public class Main {
  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int ans = (n&-n);
    System.out.println(Integer.toBinaryString(ans));
  }
}

//Kernighans Algorithm
public class Main {
  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int count = 0;
    while(n!=0)
    {
      int rsmb = n&-n;
      n-=rsmb;
      count++;
    }
    System.out.println(count);
  }
}
//Counting Bits LEETCODE:
class Solution {
    public int[] countBits(int n) {
        int[]arr = new int[n+1];
        for(int i=0;i<arr.length;i++)
        {
            arr[i] = count(i);
        }
        return arr;
    }
    
    public int count (int n)
    {
        int count = 0;
        while(n!=0)
        {
            int rsmb = n&-n;
            n-=rsmb;
            count++;
        }
        return count;
    }
}
//1125. Smallest Sufficient Team
class Solution {
    List<Integer> ans;
    int[][] dp;
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        HashMap<String,Integer> map = new HashMap<>();
        int n = req_skills.length;
        for(int i=0;i<n;i++)
        {
            map.put(req_skills[i],n-1-i);
        }
      
        ans = new ArrayList<>();
        int[] peopleMask = new int[people.size()];
        for(int i=0;i<people.size();i++)
        {
            for(String str : people.get(i))
            {
                int mask = map.get(str);
                peopleMask[i] = (peopleMask[i] | (1<<mask));
                if(peopleMask[i] == ((1<<n)-1))
                {
                    ans.add(i);
                    return ans.stream().mapToInt(j->j).toArray();
                }
            }
        }
            
        int k = n+1;
        dp = new int[peopleMask.length+1][(1<<k)];
        for(int[] d : dp)
            Arrays.fill(d,-1);
        solution(peopleMask,0,n,0,new ArrayList<>());
        return ans.stream().mapToInt(i->i).toArray();
    }
    
    public int solution(int[] peopleMask,int val,int n,int idx,List<Integer>sans)
    {
        if(idx == peopleMask.length)
        {
            System.out.println(sans);
            if(val == ((1<<n) -1))
            {
                if(sans.size() < ans.size() || ans.size()==0)
                    ans = new ArrayList<>(sans);
                return dp[idx][val] = 1;
            }
            return dp[idx][val] = 0;
        }
        
        if(dp[idx][val] !=-1)
            return dp[idx][val];
        
        int count = 0;
            count+= solution(peopleMask,val,n,idx+1,sans);
        sans.add(idx);
            count+= solution(peopleMask,(val|peopleMask[idx]),n,idx+1,sans);
        sans.remove(sans.size()-1);
        return dp[idx][val] = count;
    }
}
//1178. Number of Valid Words for Each Puzzle
class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
    
        HashMap<Character,ArrayList<Integer>> map = new HashMap<>();
		for (int i = 0; i <26; i++) {
			map.put((char)('a'+i),new ArrayList<>());
		}
    
		for(String word:words){
			int mask=0;
			for (char ch:word.toCharArray()){
				int bit=ch-'a';
				mask = mask|(1<<bit);
			}

			HashSet<Character> unique =  new HashSet<>();
			for (char ch:word.toCharArray()){
				if (unique.contains(ch)){
					continue;
				}
				unique.add(ch);
				map.get(ch).add(mask);
			}
		}
    
        ArrayList<Integer> res = new ArrayList<>();
		for(String puzzle:puzzles){
			
            int pmask=0;
			for (char ch:puzzle.toCharArray()){
				int bit=ch-'a';
				pmask = pmask|(1<<bit);
			}
            
			char fch = puzzle.charAt(0);
			ArrayList<Integer> wordsToCheck = map.get(fch);
			int count=0;
			for (int wmask:wordsToCheck){
				if ((wmask & pmask)==wmask){
					count++;
				}
			}
			res.add(count);
		}
		return res;


	}

}
//136. Single Number
class Solution {
    public int singleNumber(int[] nums) {
        int uni = 0;
        for(int ele : nums)
            uni = (uni^ele);
        return uni;
    }
}
//260. Single Number III
class Solution {
    public int[] singleNumber(int[] nums) {
        
        int xor = 0;
        for(int ele : nums)
            xor^=ele;
        int rsmb = xor&-xor;
        int x = 0;
        int y = 0; 
        
        for(int ele : nums)
        {
            if((ele&rsmb) == rsmb)
            {
                x^=ele;
            }
            else
            {
                y^=ele;
            }
        }
        return new int[]{x,y};
    }
}
//268. Missing Number
class Solution {
    public int missingNumber(int[] nums) {
        int xor = 0;
        for(int ele : nums)
            xor^=ele;
        for(int i=0;i<=nums.length;i++)
            xor^=i;
       return xor;
    }
}
//

class Solution {
    int[][] dp;
    public int maxSatisfaction(int[] s) {
        Arrays.sort(s);
        dp = new int[s.length + 1][s.length + 1];
        for(int[] d : dp)
            Arrays.fill(d,-(int)1e8);
        return maxSatisfaction(s,0,0);
    }
    
    public int maxSatisfaction(int[] satisfaction,int idx,int time) {
        if(idx == satisfaction.length)
            return dp[idx][time] = 0;
        if(dp[idx][time] != -(int)1e8)
            return dp[idx][time];        
        int max = -(int)1e9;
        if(idx+1<=satisfaction.length)
            max = Math.max(max,maxSatisfaction(satisfaction,idx+1,time + 1) + (time + 1)*satisfaction[idx]);
        if(idx+1<=satisfaction.length)
            max = Math.max(max,maxSatisfaction(satisfaction,idx+1,time));
        return dp[idx][time] = max;
    }
}