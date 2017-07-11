package J_5_Depth_First_Search.all;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 10 String Permutation II
 * Created by tianhuizhu on 6/28/17.
 */
public class _10_String_Permutation_II {
    /**
     * @param str a string
     * @return all permutations
     */
    public List<String> stringPermutation2(String str) {
        // Write your code here
        List<String> result = new ArrayList<String>();
        char[] s = str.toCharArray();
        Arrays.sort(s);
        result.add(String.valueOf(s));
        while ((s = nextPermutation(s)) != null) {
            result.add(String.valueOf(s));
        }
        return result;
    }

    public char[] nextPermutation(char[] nums) {
        int index = -1;
        for(int i = nums.length -1; i > 0; i--){
            if(nums[i] > nums[i-1]){
                index = i-1;
                break;
            }
        }
        if(index == -1){
            return null;
        }
        for(int i = nums.length -1; i > index; i--){
            if(nums[i] > nums[index]){
                char temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                break;
            }
        }
        reverse(nums,index+1,nums.length-1);
        return nums;

    }

    public void reverse(char[] num, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }
/*
Given "abb", return ["abb", "bab", "bba"].

Given "aabb", return ["aabb", "abab", "baba", "bbaa", "abba", "baab"].
 */
    @Test
    public void test(){
        System.out.println(stringPermutation2("abb"));
        System.out.println(stringPermutation2("aabb"));
    }

}
