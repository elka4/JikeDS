package J_5_Depth_First_Search.all;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 10 String Permutation II
 * Permutation String Recursion
 * Given a string, find all permutations of it without duplicates.
 */
public class _10_String_Permutation_II {
    /**
     * @param str a string
     * @return all permutations
     */
    //iterative
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

    public String reverse2(char[] num, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
        return String.valueOf(num);
    }

    /*
    Given "abb", return ["abb", "bab", "bba"].

    Given "aabb", return ["aabb", "abab", "baba", "bbaa", "abba", "baab"].
     */
    @Test
    public void test01(){
        System.out.println(stringPermutation2("abb"));
        System.out.println(stringPermutation2("aabb"));
        System.out.println(stringPermutation2("ab"));
        System.out.println(stringPermutation2("abc"));
    }

    @Test
    public void test02(){
        char[] num = "abcdef".toCharArray();//adcbef
        System.out.println(reverse2(num, 1,3));
    }

/////////////////////////////////////////////////////////////////////////////
    //recursive
    public List<String> stringPermutation2_2(String str) {
        // Write your code here
        char[] string = str.toCharArray();
        boolean[] isUsed = new boolean[string.length];
        Arrays.sort(string);
        List<String> result = new ArrayList<String>();
        String temp = new String();
        stringPermutation2Helper(result, temp, string, isUsed);
        return result;
    }

    private void stringPermutation2Helper(List<String> result,
                                          String temp,
                                          char[] string,
                                          boolean[] isUsed) {
        if (temp.length() == string.length) {
            result.add(temp);
            return;
        }
        for (int i = 0; i < string.length; i++) {
            if (isUsed[i] == true || i != 0 &&
                    isUsed[i - 1] == false &&
                    string[i] == string[i - 1]) {
                continue;
            }
            isUsed[i] = true;
            stringPermutation2Helper(result, temp + string[i], string, isUsed);
            // backtracking
            isUsed[i] = false;
        }
    }

    @Test
    public void test03(){
        System.out.println(stringPermutation2("abb"));
        System.out.println(stringPermutation2("aabb"));
        System.out.println(stringPermutation2("ab"));
        System.out.println(stringPermutation2("abc"));
    }

}
