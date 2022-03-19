public class l001
{

    public static void main(String[]args)
    {
        //
    }
    //
    public static void binarySearch(int[]arr,int data)
    {
        int si = 0;
        int ei = arr.length - 1;

        while(si<=ei)
        {
            int mid = si + (ei-si)/2;
            if(arr[mid] == data)
                return mid;
            else if(arr[mid]>data)
                si = mid+1;
            else
                ei = mid-1;
        }
        return -1;
    }
    //
    public static int firstIndex(int[]arr,int data)
    {
        int si = 0;
        int ei = arr.length - 1;

        while(si<=ei)
        {
            int mid = (si + ei)/2;
            if(arr[mid]== data)
            {
                if(mid-1>=0 && arr[mid-1] == data)
                    ei = mid-1;
                else
                    return mid;
            }
            else if(arr[mid]<data)
                si = mid + 1;
            else
                ei = mid - 1;
        } 
        return -1;
    }
    //
    public static int lastIndex(int[]arr,int data)
    {
        int si = 0;
        int ei = arr.length - 1;

        while(si<=ei)
        {
            int mid = (si + ei)/2;
            if(arr[mid]== data)
            {
                if(mid+1 < arr.length && arr[mid+1] == data)
                    si = mid+1;
                else
                    return mid;
            }
            else if(arr[mid]<data)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return -1;
    }
    //
    public static int closestElement(int[]arr,int data)
    {
        if(data<arr[si])
            return si;
        if(data>arr[si])
            return ei;
        
        while(si<=ei)
        {
            int mid = (si + ei)/2;
            if(arr[mid] == data)
                return mid;
            else if(arr[mid]<data)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return arr[ei] - data < arr[si] - data ? ei : si;
    }
    //
    public int perfectLocation(int[]arr,int data)
    {
        int si = 0;
        int ei = arr.length();
        while(si<=ei)
        {
            if(arr[mid]<=data)
                si = mid+1;
            else
                ei = mid;
        }
        return si;
    }
    // Count Inversions
    class Solution {
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        int local = localInversions(nums);
        int global = globalInversions(nums,new int[n],0,n-1);
        return local == global;
    }
    
    public int totalInversions(int[]nums,int[]sortedArr,int si,int ei,int mid)
    {
        int i = si;
        int j = mid+1;
        int k = si;
        int count = 0;
        
        while(i<=mid && j<=ei)
        {
            if(nums[i]<=nums[j])
            {
                sortedArr[k++] = nums[i++];
            }
            else
            {
                sortedArr[k++] = nums[j++];
                count+= mid-i+1;
            }
        }
            while (i <= mid || j <= ei)
                sortedArr[k++] = nums[i <= mid ? i++ : j++];

            while (si <= ei)
                nums[(int) si] = sortedArr[(int) si++];
            
            return count;
    }
    
    public int globalInversions(int[]nums,int[]sortedArr,int si,int ei)
    {
        if(si>=ei)
            return 0;
        int count = 0;
        int mid = si + (ei - si)/2;
        count+= globalInversions(nums,sortedArr,si,mid);
        count+= globalInversions(nums,sortedArr,mid+1,ei);  
        count+= totalInversions(nums,sortedArr,si,ei,mid);
        return count;
    }
    
    public int localInversions(int[] nums)
    {
        int n = nums.length;
        int count = 0;
        for(int i=0;i<n-1;i++)
        {
            if(nums[i] > nums[i+1])
                count++;
        }
        return count;
    }
}

    //33. Search in Rotated Sorted Array
    class Solution {
    public int search(int[] arr, int val) {
        
        int n = arr.length;
        int si = 0;
        int ei = n-1;
        
        while(si<=ei)
        {
            int mid = si + (ei-si)/2;
            if(arr[mid] == val)
                return mid;
            if(si == ei)
                return arr[mid] == val ? mid : -1;
            
            if(arr[si] <= arr[mid])  
            {
                if(val >= arr[si] && val <= arr[mid])
                    ei = mid - 1;
                else
                    si = mid + 1;
            }
            else if(arr[mid] <= arr[ei])
            {
                if(val >= arr[mid] && val <= arr[ei])
                    si = mid + 1;
                else
                    ei = mid - 1;
            }            
        }
        return -1;
    }
}

    // 658. Find K Closest Elements
    class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        int si = 0;
        int ei = n - 1;
        while ((ei - si + 1) > k)
        {
            if ((x - arr[si]) > (arr[ei] - x))
                si++;
            else
                ei--;
        }
        
        List<Integer> ans = new ArrayList<>();
        for(int i=si;i<=ei;i++)
            ans.add(arr[i]);
        return ans;
    }
}

    //

    
}