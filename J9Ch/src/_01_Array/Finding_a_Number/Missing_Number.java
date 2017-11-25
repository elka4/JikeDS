package _01_Array.Finding_a_Number;

import java.util.Arrays;

/*
public String removeKdigits(String num, int k) {
    if(num.length()==k)
        return "0";

    StringBuilder sb = new StringBuilder(num);
    for(int j=0; j<k; j++){
        int i=0;
        while(i<sb.length()-1&&sb.charAt(i)<=sb.charAt(i+1)){
            i++;
        }
        sb.delete(i, i+1);
    }

    //remove leading 0's
    while (sb.length() > 1 && sb.charAt(0)=='0')
        sb.delete(0,1);

    if(sb.length()==0){
        return "0";
    }

    return sb.toString();
}

 */

public class Missing_Number {

    //Java Solution 1 - Math
    public int missingNumber(int[] nums) {
        int sum=0;
        for(int i=0; i<nums.length; i++){
            sum+=nums[i];
        }

        int n=nums.length;
        return n*(n+1)/2-sum;
    }


    //Java Solution 2 - Bit
    public int missingNumber2(int[] nums) {

        int miss=0;
        for(int i=0; i<nums.length; i++){
            miss ^= (i+1) ^nums[i];
        }

        return miss;
    }


    //Java Solution 3 - Binary Search
    public int missingNumber3(int[] nums) {
        Arrays.sort(nums);
        int l=0, r=nums.length;
        while(l<r){
            int m = (l+r)/2;
            if(nums[m]>m){
                r=m;
            }else{
                l=m+1;
            }
        }

        return r;
    }


//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//



//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//


}
