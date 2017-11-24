package _05_DFS._Back_Subset_Permutation_Combination;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class backtracking_Comment {

    // subset
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        //input, result, templist, start position
        backtrack(nums, result, new ArrayList<>(), 0);
        return result;
    }

    private void backtrack(int [] nums, List<List<Integer>> result,
                           List<Integer> tempList, int start){

        //subset 就是一进入就把templist加入result
        result.add(new ArrayList<>(tempList));

        //循环处理input的每一个元素，从start开始。
        //退出条件就是for循环完成
        for(int i = start; i < nums.length; i++){

            // 1.把当前元素加入templist
            tempList.add(nums[i]);

            // 2.进入下层的recursion，从下一个元素开始
            backtrack(nums, result, tempList, i + 1);

            // 3.把当前元素从templist移除
            tempList.remove(tempList.size() - 1);
        }
    }

    @Test
    public void test_subsets(){
        int[] nums = {1,2,3};
        for (List<Integer> list:subsets(nums)){
            System.out.println(list);
        }
    }

//------------------------------------------------------------------------------//////////
    //_2_Subsets_II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        //input, result, templist, start position
        backtrack2(nums, result, new ArrayList<>(), 0);
        return result;
    }

    private void backtrack2(int [] nums, List<List<Integer>> result,
                           List<Integer> tempList, int start){

        //subset 就是一进入就把templist加入result
        result.add(new ArrayList<>(tempList));
        System.out.println("tempList " + tempList);

        System.out.println("start " + start);
        //循环处理input的每一个元素，从start开始。
        //退出条件就是for循环完成
        for(int i = start; i < nums.length; i++){

            // skip duplicates。
            //不是第一个i > start，而且和前一个相同nums[i] == nums[i-1]

            if(i > start && nums[i] == nums[i-1]) {
                System.out.println(i + " continue " + nums[i]);
                continue;
            }

            // 1.把当前元素加入templist
            tempList.add(nums[i]);

            // 2.进入下层的recursion，从下一个元素i+1开始
            backtrack2(nums, result, tempList, i + 1);

            System.out.println("remove " + tempList.get(tempList.size() - 1));
            // 3.把当前元素从templist移除
            tempList.remove(tempList.size() - 1);
        }
    }

    @Test
    public void test_subsetsWithDup(){
        int[] nums = {1,2, 2, 3};
        for (List<Integer> list:subsetsWithDup(nums)){
            System.out.println(list);
        }
    }

//------------------------------------------------------------------------------/
    //_3_Permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // not necessary
        backtrack(nums, result, new ArrayList<>());
        return result;
    }

    private void backtrack(int [] nums, List<List<Integer>> result,
                           List<Integer> tempList){

        //退出条件就是 tempList已经达到nums.length
        if(tempList.size() == nums.length){
            result.add(new ArrayList<>(tempList));
            return;
        }

        for(int i = 0; i < nums.length; i++){

            // element already exists, skip
            //因为每次recursion进入下层循环，都会从头开始for loop，为了避免重复加入相同元素:
            if(tempList.contains(nums[i])) continue;

            // 1.把当前元素加入templist
            tempList.add(nums[i]);

            // 2.进入下层的recursion，从下一个元素开始
            backtrack(nums, result, tempList);

            // 3.把当前元素从templist移除
            tempList.remove(tempList.size() - 1);
        }

    }

    @Test
    public void test_permute(){
        int[] nums = {2,1,3};
        for (List<Integer> list:permute(nums)){
            System.out.println(list);
        }
    }


//------------------------------------------------------------------------------//////////
    //_4_Permutations_II
    public List<List<Integer>> permuteUnique(int[] nums) {
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
            if(used[i] ||
                i > 0 && nums[i] == nums[i-1] && !used[i - 1]){
                //不是第一个i > 0，而且和前一个相同nums[i] == nums[i-1]
                continue;
            }
            /*
            if ( visited[i] == 1 ||
                            ( i != 0
                            && nums[i] == nums[i - 1]
                            && visited[i-1] == 0)){
                continue;
            }
             */

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

    @Test
    public void test_permuteUnique(){
        int[] nums = {1,2,2,3};
        for (List<Integer> list:permuteUnique(nums)){
            System.out.println(list);
        }
    }

//------------------------------------------------------------------------------//////////
    //_5_Combination_Sum  Combination Sum
    //num无重复元素，每个元素可以重复使用
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        backtrack(nums, result, new ArrayList<>(),  target, 0);
        return result;
    }

    private void backtrack(int [] nums, List<List<Integer>> result,
                           List<Integer> tempList, int remain, int start){

        //退出条件：remain < 0. 这时候不满足加入条件
        if(remain < 0) {
            return;
        }
        //退出条件：remain == 0. 这时候满足加入条件。将tempList加入result
        if(remain == 0){
            result.add(new ArrayList<>(tempList));
            return;
        }

        for(int i = start; i < nums.length; i++){

            // 1.把当前元素加入templist
            tempList.add(nums[i]);

            // 2.进入下层的recursion，用remain - nums[i]计算，从i开始
            //为什么从i开始不是从i+1开始？因为可以重复使用元素，所再次使用i。
            // not i + 1 because we can reuse same elements
            backtrack(nums, result, tempList, remain - nums[i], i);

            // 3.把当前元素从templist移除
            tempList.remove(tempList.size() - 1);
        }

    }

    @Test
    public void test_combinationSum(){
        int[] nums = { 1, 2, 3, 6, 7};
        System.out.println(combinationSum(nums, 7));
    }

//------------------------------------------------------------------------------//////////
    //_6_Combination_Sum_II  Combination Sum II
    //nums有重复元素，不重复使用同一元素
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        backtrack6(nums, result, new ArrayList<>(), target, 0);
        return result;

    }

    private void backtrack6(int [] nums, List<List<Integer>> list,
                List<Integer> tempList, int remain, int start){

        if(remain < 0){
            return;
        }
        if(remain == 0){
            list.add(new ArrayList<>(tempList));
            return;
        }

        for(int i = start; i < nums.length; i++){
            // skip duplicates
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }

            tempList.add(nums[i]);

            //下一层从i + 1开始， 所以避开重复使用同一元素
            backtrack6(nums, list, tempList, remain - nums[i], i + 1);


            tempList.remove(tempList.size() - 1);
        }

    }

    @Test
    public void test_combinationSumII(){
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSum2(nums, 8));
    }

//------------------------------------------------------------------------------//////////
    //_7_Palindrome_Partitioning  Palindrome Partitioning
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        backtrack(s,result, new ArrayList<>(),  0);
        return result;
    }

    public void backtrack(String s, List<List<String>> result,
                          List<String> tempList, int start){

        if(start == s.length()){
            result.add(new ArrayList<>(tempList));
            return;
        }
        for(int i = start; i < s.length(); i++){

            if(isPalindrome(s, start, i)){

                tempList.add(s.substring(start, i + 1));

                backtrack(s, result, tempList, i + 1);

                tempList.remove(tempList.size() - 1);
            }
        }

    }

    public boolean isPalindrome(String s, int low, int high){
        while(low < high)
            if(s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }

    /*
    For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
     */
    @Test
    public void test_partition(){

        System.out.println(partition("aab"));
    }

//------------------------------------------------------------------------------//////////


}
