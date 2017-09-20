package J_5_Depth_First_Search.Required_7;

import org.junit.Test;

import java.util.*;
/** 16. Permutations II
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _16_Permutations_II {
    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public List<List<Integer>> permuteUnique(int[] nums) {

        ArrayList<List<Integer>> results = new ArrayList<List<Integer>>();

        if (nums == null) {
            return results;
        }

        if(nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }

        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] visited = new int[nums.length];
        for ( int i = 0; i < visited.length; i++){
            visited[i] = 0;
        }

        helper(results, list, visited, nums);
        return results;
    }


    public void helper(ArrayList<List<Integer>> results,
                       ArrayList<Integer> list, int[] visited, int[] nums) {

        if(list.size() == nums.length) {
            results.add(new ArrayList<Integer>(list));
            System.out.println("list.size() == nums.length " + list);
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if ( visited[i] == 1 ||
                            ( i != 0
                            && nums[i] == nums[i - 1]
                            && visited[i-1] == 0)){
                continue;
            }
            /*
            上面的判断主要是为了去除重复元素影响。
            比如，给出一个排好序的数组，[1,2,2]，那么第一个2和第二2如果在结果中互换位置，
            我们也认为是同一种方案，所以我们强制要求相同的数字，原来排在前面的，在结果
            当中也应该排在前面，这样就保证了唯一性。所以当前面的2还没有使用的时候，就
            不应该让后面的2使用。
            */
            System.out.println("nums[i] " + nums[i]);
            System.out.println("list " + list);
            System.out.println("results " + results);
            System.out.println("========================");

            visited[i] = 1;
            list.add(nums[i]);
            helper(results, list, visited, nums);
            list.remove(list.size() - 1);
            visited[i] = 0;
        }
    }

    @Test
    public void test01(){
        int[] nums = {1,2,2,3};
        System.out.println(permuteUnique(nums));
    }
    @Test
    public void test02(){
        int[] nums = {1,2,2};
        System.out.println(permuteUnique(nums));
    }
    /*
nums[i] 1
list []
results []
========================
nums[i] 2
list [1]
results []
========================
nums[i] 2
list [1, 2]
results []
========================
list.size() == nums.length [1, 2, 2]
nums[i] 2
list []
results [[1, 2, 2]]
========================
nums[i] 1
list [2]
results [[1, 2, 2]]
========================
nums[i] 2
list [2, 1]
results [[1, 2, 2]]
========================
list.size() == nums.length [2, 1, 2]
nums[i] 2
list [2]
results [[1, 2, 2], [2, 1, 2]]
========================
nums[i] 1
list [2, 2]
results [[1, 2, 2], [2, 1, 2]]
========================
list.size() == nums.length [2, 2, 1]
[[1, 2, 2], [2, 1, 2], [2, 2, 1]]
     */


    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums==null || nums.length==0) { return ans; }
        permute(ans, nums, 0);
        return ans;
    }

    private void permute(List<List<Integer>> ans, int[] nums, int index) {
        if (index == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int num: nums) { temp.add(num); }
            ans.add(temp);
            return;
        }
        Set<Integer> appeared = new HashSet<>();
        for (int i=index; i<nums.length; ++i) {
            if (appeared.add(nums[i])) {
                swap(nums, index, i);
                permute(ans, nums, index+1);
                swap(nums, index, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int save = nums[i];
        nums[i] = nums[j];
        nums[j] = save;
    }
    @Test
    public void test03(){
        int[] nums = {1,2,2,3};
        System.out.println(permuteUnique2(nums));
    }
    @Test
    public void test04(){
        int[] nums = {1,2,2};
        System.out.println(permuteUnique2(nums));
    }
}
