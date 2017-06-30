package leetcode.Binary_Search_38;

/**
 * Created by tianhuizhu on 6/19/17.
 */
public class a_167_Two_Sum_II_Input_array_is_sorted {
    public int[] twoSum(int[] num, int target) {
        int[] indice = new int[2];
        if (num == null || num.length < 2) return indice;
        int left = 0, right = num.length - 1;
        while (left < right) {
            int v = num[left] + num[right];
            if (v == target) {
                indice[0] = left + 1;
                indice[1] = right + 1;
                break;
            } else if (v > target) {
                right --;
            } else {
                left ++;
            }
        }
        return indice;
    }


    public int[] twoSum_2 (int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        while(start < end){
            if(numbers[start] + numbers[end] == target) break;
            if(numbers[start] + numbers[end] < target) start++;
            else end--;
        }
        return new int[]{start + 1, end + 1};
    }

    public int[] twoSum_mine (int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        int[] result = new int[2];
        while(start < end){
            int v = numbers[start] + numbers[end];
            if(v == target){
                result[0] = start + 1;
                result[1] = end + 1;
                break;
            } else if (v > target){
                end--;
            } else {
                start++;
            }
        }
        return result;
    }


}
