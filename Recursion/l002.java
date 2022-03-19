import java.util.HashSet;
public class l002{
    public static void main(String[]args)
    {
        //ans1
        //int [] arr = {10,20,30,40,45,54,1};
        //Repition can be handeled without hashmap also.. by doing this
        //targetSumEqualSubset(arr,1,0,arr[0],"" + arr[0],"",100);
    }
    public static int targetSumEqualSubset(int[] arr,int idx,int val1,int val2,String p1,String p2,int tar)
    {
        if(idx == arr.length)
        {
            if(val1 == val2 && val1 == tar){
                System.out.println(p1 + " == " +p2);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count+= targetSumEqualSubset(arr,idx+1,val1 + arr[idx],val2,p1.length()==0?p1+arr[idx]:p1+"+"+arr[idx],p2,tar);
        count+= targetSumEqualSubset(arr,idx+1,val1,val2+arr[idx],p1,p2.length()==0?p2+arr[idx]:p2+"+"+arr[idx],tar);
        return count;
    }
    //
     public static int permutation(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            count += permutation(ros, ans + ch);
        }

        return count;
    }
    //
    public static int permutationUnique(String str, String ans)
    {
        if (str.length() == 0)
        {
            System.out.println(ans);
            return 1;
        }
        boolean[]vis = new boolean[26];
        int count = 0;
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str[i];
            if (!vis[ch - 'a'])
            {
                vis[ch - 'a'] = true;
                String ros = str.substr(0, i) + str.substr(i + 1);
                count += permutationUnique(ros, ans + ch);
            }
        }
        return count;
    }
    //
    public static int permutationUnique2(String str, String ans)
    {
        if(str.length() == 0)
        {
            System.out.println(ans);
            return 1;
        }
        char prev = '$';
        int count = 0;
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str[i];
            if (prev != ch)
            {
                String ros = str.substr(0, i) + str.substr(i + 1);
                count += permutationUnique2(ros, ans + ch);
            }
            prev = ch;
        }
        return count;
    }
    //https://leetcode.com/problems/permutations/
    public List<List<Integer>> permute(int[] nums) {
        
        List<List<Integer>>bans  = new ArrayList<>();
        if(nums.length == 0)
            return bans;
        List<Integer>sans  = new ArrayList<>();
        permute(nums,bans,sans);
        return bans;
    }
    public int permute(int[] nums,List<List<Integer>>bans,List<Integer>sans) {
        
        if(sans.size()==nums.length)
        {
            bans.add(new ArrayList<>(sans));
            return 1;
        }
        int count = 0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>=-10)
            {
                sans.add(nums[i]);
                int temp = nums[i];
                nums[i] = -11;
                count+= permute(nums,bans,sans);
                sans.remove(sans.size()-1);
                nums[i] = temp;
            }
        }
        return count;
    }
}