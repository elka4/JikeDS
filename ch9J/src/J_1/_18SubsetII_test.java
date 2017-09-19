package chapter1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

//input有重复的数，result避免有重复结果。
//实际上是问去重的问题，选代表
//｛1，2'，2''，2'''｝取12'最顺眼，按顺序取，不能跳着取。

public class _18SubsetII_test {
/**
 * @param nums: A set of numbers.
 * @return: A subset of subsets. All valid subsets.
 */
public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
    // write your code here
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    ArrayList<Integer> subset = new ArrayList<>();
    if (nums == null || nums.length == 0){
        result.add(subset);
        return result;
    }
    Arrays.sort(nums);
    helper(result, nums, subset, 0);
    return result;

}

    private void helper(ArrayList<ArrayList<Integer>> result , int[] nums,
                        ArrayList<Integer> subset, int position){
        result.add(new ArrayList<>(subset));

        for (int i = position; i < nums.length; i++){
            if (i != position && nums[i] == nums[i - 1]){
                continue;
            }
            subset.add(nums[i]);
            helper(result, nums, subset, i + 1);
            result.remove(subset.size() - 1);

        }


    }

    @Test
    public void test01(){
        _18SubsetII_test sub = new _18SubsetII_test();
        int[] input = {0,1};

        ArrayList<ArrayList<Integer>> result = sub.subsetsWithDup(input);
        System.out.println(result);

    }
}

/*Given a subset of numbers that may has duplicate numbers, 
 * return all fromIndexsible subsets

 Notice： Each element in a subset must be in non-descending order.
The ordering between two subsets is free.
The solution set must not contain duplicate subsets.

Example： If S = [1,2,2], a solution is:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
Challenge：Can you do it in both recursively and iteratively?

Tags： Recursion
Related Problems：Medium Subsets
 */
