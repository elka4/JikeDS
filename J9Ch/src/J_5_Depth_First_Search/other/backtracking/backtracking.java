package J_5_Depth_First_Search.other.backtracking;
import org.junit.Test;
import java.util.*;

public class backtracking {

    // subset
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list ,
                           List<Integer> tempList, int [] nums, int start){
        //
        list.add(new ArrayList<>(tempList));

        for(int i = start; i < nums.length; i++){

            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);

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

///////////////////////////////////////////////////////////////////////////////

    //_2_Subsets_II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack2(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack2(List<List<Integer>> list,
                           List<Integer> tempList, int [] nums, int start){
        //
        list.add(new ArrayList<>(tempList));

        for(int i = start; i < nums.length; i++){

            // skip duplicates
            if(i > start && nums[i] == nums[i-1]) continue;

            tempList.add(nums[i]);
            backtrack2(list, tempList, nums, i + 1);
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

///////////////////////////////////////////////////////////////////////////////

    //_3_Permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list,
                           List<Integer> tempList, int [] nums){

        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){

                // element already exists, skip
                if(tempList.contains(nums[i])) continue;

                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    @Test
    public void test_permute(){
        int[] nums = {1,2,3};
        for (List<Integer> list:permute(nums)){
            System.out.println(list);
        }
    }


///////////////////////////////////////////////////////////////////////////////

    //_4_Permutations_II

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList,
                           int [] nums, boolean [] used){

        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){

                if(used[i] || i > 0
                        && nums[i] == nums[i-1]
                        && !used[i - 1]){
                    continue;
                }

                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    @Test
    public void test_permuteUnique(){
        int[] nums = {1,2,2,3};
        for (List<Integer> list:permuteUnique(nums)){
            System.out.println(list);
        }
    }

///////////////////////////////////////////////////////////////////////////////

    //_5_Combination_Sum  Combination Sum
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList,
                           int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < nums.length; i++){

                tempList.add(nums[i]);
                // not i + 1 because we can reuse same elements
                backtrack(list, tempList, nums, remain - nums[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    @Test
    public void test_combinationSum(){
        int[] nums = {2, 3, 6, 7};
        System.out.println(combinationSum(nums, 7));
    }

///////////////////////////////////////////////////////////////////////////////

    //_6_Combination_Sum_II  Combination Sum II

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack6(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void backtrack6(List<List<Integer>> list, List<Integer> tempList,
                           int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < nums.length; i++){
                if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrack6(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    @Test
    public void test_combinationSumII(){
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSum2(nums, 8));
    }

///////////////////////////////////////////////////////////////////////////////

    //_7_Palindrome_Partitioning  Palindrome Partitioning
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), s, 0);
        return list;
    }

    public void backtrack(List<List<String>> list,
                          List<String> tempList, String s, int start){

        if(start == s.length())
            list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < s.length(); i++){
                if(isPalindrome(s, start, i)){
                    tempList.add(s.substring(start, i + 1));
                    backtrack(list, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
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

///////////////////////////////////////////////////////////////////////////////


}
