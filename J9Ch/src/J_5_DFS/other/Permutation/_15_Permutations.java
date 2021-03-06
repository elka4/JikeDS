package J_5_DFS.other.Permutation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 15. Permutations
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _15_Permutations {
    public List<List<Integer>> permute4(int[] nums) {
        ArrayList<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null) {
            return rst;
        }

        if (nums.length == 0) {
            rst.add(new ArrayList<Integer>());
            return rst;
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(rst, list, nums);
        return rst;
    }

    private int count = 0;

    public void helper(ArrayList<List<Integer>> rst,
                       ArrayList<Integer> list, int[] nums){
        if(list.size() == nums.length) {
            rst.add(new ArrayList<Integer>(list));
            System.out.println("list.size() == nums.length " + list);
            return;
        }

        System.out.println("count " + count++);

        for(int i = 0; i < nums.length; i++){
            if(list.contains(nums[i])){
                System.out.println("list " + list + " contains " + nums[i]);
                continue; //避免重复将同一个数字加入list
            }
             System.out.println("nums[i] " + nums[i]);
             System.out.println("list " + list);
             System.out.println("rst " + rst);
             System.out.println("========================");
            list.add(nums[i]);
            helper(rst, list, nums);
            list.remove(list.size() - 1);
        }

    }
    @Test
    public void test01(){
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }
    @Test
    public void test02(){
        int[] nums = {1,2};
        System.out.println(permute(nums));
    }
    @Test
    public void test03(){
        int[] nums = {3,1,2};
        System.out.println(permute(nums));
    }


    /*
count 0
nums[i] 1
list []
rst []
========================
count 1
list [1] contains 1
nums[i] 2
list [1]
rst []
========================
count 2
list [1, 2] contains 1
list [1, 2] contains 2
nums[i] 3
list [1, 2]
rst []
========================
list.size() == nums.length [1, 2, 3]
nums[i] 3
list [1]
rst [[1, 2, 3]]
========================
count 3
list [1, 3] contains 1
nums[i] 2
list [1, 3]
rst [[1, 2, 3]]
========================
list.size() == nums.length [1, 3, 2]
list [1, 3] contains 3
nums[i] 2
list []
rst [[1, 2, 3], [1, 3, 2]]
========================
count 4
nums[i] 1
list [2]
rst [[1, 2, 3], [1, 3, 2]]
========================
count 5
list [2, 1] contains 1
list [2, 1] contains 2
nums[i] 3
list [2, 1]
rst [[1, 2, 3], [1, 3, 2]]
========================
list.size() == nums.length [2, 1, 3]
list [2] contains 2
nums[i] 3
list [2]
rst [[1, 2, 3], [1, 3, 2], [2, 1, 3]]
========================
count 6
nums[i] 1
list [2, 3]
rst [[1, 2, 3], [1, 3, 2], [2, 1, 3]]
========================
list.size() == nums.length [2, 3, 1]
list [2, 3] contains 2
list [2, 3] contains 3
nums[i] 3
list []
rst [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1]]
========================
count 7
nums[i] 1
list [3]
rst [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1]]
========================
count 8
list [3, 1] contains 1
nums[i] 2
list [3, 1]
rst [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1]]
========================
list.size() == nums.length [3, 1, 2]
list [3, 1] contains 3
nums[i] 2
list [3]
rst [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2]]
========================
count 9
nums[i] 1
list [3, 2]
rst [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2]]
========================
list.size() == nums.length [3, 2, 1]
list [3, 2] contains 2
list [3, 2] contains 3
list [3] contains 3
[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
     */


//------------------------------------------------------------------------------

    // Non-Recursion, stack
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute2(int[] nums) {
        ArrayList<List<Integer>> permutations
                = new ArrayList<List<Integer>>();

        if (nums == null) {
            return permutations;
        }

        if (nums.length == 0) {
            permutations.add(new ArrayList<Integer>());
            return permutations;
        }

        int n = nums.length;
        ArrayList<Integer> stack = new ArrayList<Integer>();

        stack.add(-1);
        while (stack.size() != 0) {
            Integer last = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);

            // increase the last number
            int next = -1;
            for (int i = last + 1; i < n; i++) {
                if (!stack.contains(i)) {
                    next = i;
                    break;
                }
            }
            if (next == -1) {
                continue;
            }

            // generate the next permutation
            stack.add(next);
            for (int i = 0; i < n; i++) {
                if (!stack.contains(i)) {
                    stack.add(i);
                }
            }

            // copy to permutations set
            ArrayList<Integer> permutation = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                permutation.add(nums[stack.get(i)]);
            }

            permutations.add(permutation);
        }

        return permutations;
    }


//------------------------------------------------------------------------------
    // recursion, user cache
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        //use cache, nused to record one position is used or not
        backtrack(nums, result, new ArrayList<>(),  new boolean[nums.length]);
        return result;
    }

    private void backtrack(int[] nums, List<List<Integer>> result,
                           List<Integer> tempList, boolean [] used){

        //退出条件就是 tempList已经达到nums.length
        if(tempList.size() == nums.length){
            result.add(new ArrayList<>(tempList));
            return;
        }

        for(int i = 0; i < nums.length; i++){

            //避免重复已使用元素
            if(used[i]  ){
                //不是第一个i > 0，而且和前一个相同nums[i] == nums[i-1]
                continue;
            }


            // 1.把当前元素加入templist
            tempList.add(nums[i]);

            //进入下层recursion之前标记当前元素已被使用
            used[i] = true;

            // 2.进入下层的recursion，从下一个元素开始
            backtrack(nums, result, tempList, used);

            //走出下层recursion之后标记当前元素已未被使用
            used[i] = false;

            // 3.把当前元素从templist移除
            tempList.remove(tempList.size() - 1);
        }

    }
}
