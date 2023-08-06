import java.util.*;
public class l003{

    public static void main(String[] args)
    {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        List<List<Integer>> ans = printSubsets(arr);
        System.out.println(ans.size());
        for(List<Integer> sans : ans)
            System.out.println(sans);
    }

    public static List<List<Integer>> printSubsets(ArrayList<Integer> arr)
    {
        List<List<Integer>> bans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        // printSubsets1(arr, 0, sans, bans);
        return printSubsets2(arr, 0);
    }

    public static void printSubsets1(ArrayList<Integer> arr, int idx, List<Integer> sans, List<List<Integer>> bans)
    {
        if(idx == arr.size())
        {
            bans.add(new ArrayList<>(sans));
            return;
        }
        sans.add(arr.get(idx));
        printSubsets1(arr, idx + 1, sans, bans);   //added
        sans.remove(sans.size() - 1);
        printSubsets1(arr, idx + 1, sans, bans);   //not added
    }

    public static List<List<Integer>> printSubsets2(ArrayList<Integer> arr, int idx)
    {
        if(idx == arr.size())
        {
            List<List<Integer>> bans = new ArrayList<>();
            List<Integer> sans = new ArrayList<>();
            bans.add(new ArrayList<>(sans));
            return bans;
        }

        List<List<Integer>> rres = printSubsets2(arr, idx + 1);
        List<List<Integer>> mans = new ArrayList<>();
        for(List<Integer> list : rres)
        {
            mans.add(new ArrayList<>(list)); // not added
            list.add(arr.get(idx)); // added
            mans.add(new ArrayList<>(list)); 
        }
        return mans;
    }

}