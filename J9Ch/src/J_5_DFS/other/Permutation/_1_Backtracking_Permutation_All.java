package J_5_DFS.other.Permutation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// top100
public class _1_Backtracking_Permutation_All {
    // permutation, interative
    //time O(n!)
    //space O(n!)
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0){
            return res;
        }

        res.add(new ArrayList<Integer>());

        for(int i = 0; i < nums.length; i++){
            List<List<Integer>> nextRes = new ArrayList<List<Integer>>();

            //for each list in res
            for (List<Integer> list : res){
                for (int j = 0; j < list.size() + 1; j++) {

                    //copy a list to nextList
                    List<Integer> nextList = new ArrayList<Integer>(list);
                    nextList.add(j, nums[i]);
                    nextRes.add(nextList);
                }
            }
            // move to next level
            res = nextRes;
        }
        return res;
    }

    //---------------------------------//////////////////

    // permutation, recursion
    //time O(n!*n)
    //space O(n)
    private List<List <Integer>> permute2(int[] num) {
        //Corner Case Checked
        List <List<Integer>> result = new ArrayList <List<Integer>>();
        List <Integer> list = new ArrayList <Integer>();
        helper(num, list, result);
        return result;
    }
    public void helper(int num[], List<Integer> list, List<List<Integer>> result){
        if(list.size() == num.length){
            result.add(new ArrayList <Integer>(list));
            return;
        }
        //For each elem
        for(int i = 0; i  < num.length; i++){
            //Position left == nums left(No duplicates)
            if(!list.contains(num[i])){
                list.add(num[i]);
                //Next Position
                helper(num, list, result);
                //Empty last position for next iteration
                list.remove(list.size()-1);
            }
        }
    }


    //---------------------------------//////////////////

    // permutation, recursion, swap
    //因为用了swap，所以省了空间
    //
    public List <List <Integer>> permute3(int[] nums) {
        List <List <Integer>> res = new ArrayList <List <Integer>>();
        if(nums == null || nums.length == 0)
            return res;
        List <Integer> list = new ArrayList <Integer>();
        helper2(nums, res, list, 0);
        return res;
    }

    private void helper2(int[] nums, List <List <Integer>> res,
                         List <Integer> list, int pos){
        //Base Case:
        if(pos == nums.length){
            res.add(new ArrayList <Integer>(list));
            return;
        }

        //Main Cases:
        for(int i = pos; i  < nums.length; i++){
            list.add(nums[i]);

            swap(nums, pos, i);
            //helper(nums, res, list, level + 1);
            helper2(nums, res, list, pos + 1);//这样对不对？
            swap(nums, pos, i);

            list.remove(list.size() - 1);
        }

        return;
    }

    private void swap(int[] nums, int pos, int i) {
        int temp = nums[pos];
        nums[pos] = nums[i];
        nums[i] = temp;
    }

//---------------------------------//////////////////
    // permutationII, recursion, hashset
    public List <List <Integer>> permuteUnique(int[] nums) {
        //Corn Cases Checked

        List <List <Integer>> res = new ArrayList<>();
        //Method: DFS, and using a HashSet <Integer> in each
        // pos to remove duplicates
        dfsHelper(res, nums, 0);
        return res;
    }

    private void dfsHelper(List <List <Integer>> res, int[] nums, int pos) {
        if (pos == nums.length) {
            List <Integer> list = new ArrayList <Integer>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }

        //用这个hashset来去重
        Set<Integer> used = new HashSet<Integer>();
        for (int i = pos; i  < nums.length; i++) {
            if (used.add(nums[i])) {

                swap(nums, i, pos);
                dfsHelper(res, nums, pos + 1);
                swap(nums, i, pos);

            }
        }
    }
    //---------------------------------//////////////////


    @Test
    public void test01(){
        int[] nums = {1,2,3,4,5,6,7,8,9,0};

        long start = System.currentTimeMillis();
        List<List<Integer>> list = permute(nums);
        System.out.println("There are " + list.size() + " permutations.");
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(  ": " + time + "ms");


        start = System.currentTimeMillis();
        list = permute2(nums);
        System.out.println("There are " + list.size() + " permutations.");
        end = System.currentTimeMillis();
        time = end - start;
        System.out.println(  ": " + time + "ms");


        start = System.currentTimeMillis();
        list = permute3(nums);
        System.out.println("There are " + list.size() + " permutations.");
        end = System.currentTimeMillis();
        time = end - start;
        System.out.println(  ": " + time + "ms");

    }


    @Test
    public void test02 (){
        int[] input = {1, 2 ,2,  3};
        System.out.println(permuteUnique(input));
    }


}
