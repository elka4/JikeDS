package _05_DFS.Permutation_Top;
import org.junit.Test;
import java.util.*;

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

///////////////////////////////////////////////////////

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


///////////////////////////////////////////////////////

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

//////////////////////////////////////////////////////
    private void swap(int[] nums, int pos, int i) {
        int temp = nums[pos];
        nums[pos] = nums[i];
        nums[i] = temp;
    }

//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////

    // permutationII, recursion, hashset
    public List <List <Integer>> permuteUnique1(int[] nums) {
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

    public List<List<Integer>> permuteUnique11(int[] nums) {
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



///////////////////////////////////////////////////////
    public List<List<Integer>> permuteUnique2(int[] nums) {

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
//            System.out.println("list.size() == nums.length " + list);
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if ( visited[i] == 1 ||
            ( i != 0 && nums[i] == nums[i - 1] && visited[i-1] == 0)){ continue; }
            /*
            上面的判断主要是为了去除重复元素影响。
            比如，给出一个排好序的数组，[1,2,2]，那么第一个2和第二2如果在结果中互换位置，
            我们也认为是同一种方案，所以我们强制要求相同的数字，原来排在前面的，在结果
            当中也应该排在前面，这样就保证了唯一性。所以当前面的2还没有使用的时候，就
            不应该让后面的2使用。
            */
            visited[i] = 1;
            list.add(nums[i]);
            helper(results, list, visited, nums);
            list.remove(list.size() - 1);
            visited[i] = 0;
        }
    }

///////////////////////////////////////////////////////
    public List<List<Integer>> permuteUnique3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        //use cache, nused to record one position is used or not
        backtrack(nums, result, new ArrayList<>(),  new boolean[nums.length]);
        return result;
    }

    private void backtrack(int[] nums, List<List<Integer>> result,
                           List<Integer> tempList, boolean [] used) {

        //退出条件就是 tempList已经达到nums.length
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            //避免重复已使用元素
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
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
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////


//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////

}
