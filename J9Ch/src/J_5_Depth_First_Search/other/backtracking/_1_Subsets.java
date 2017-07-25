package J_5_Depth_First_Search.other.backtracking;
import org.junit.Test;
import java.util.*;

//https://leetcode.com/problems/permutations/#/discuss

public class _1_Subsets {

    //Subsets : https://leetcode.com/problems/subsets/

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list ,
           List<Integer> tempList, int [] nums, int start){

        list.add(new ArrayList<>(tempList));

        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /*
    Given a set of distinct integers, nums, return all possible subsets.

    Note: The solution set must not contain duplicate subsets.

    For example,
    If nums = [1,2,3], a solution is:

    [
      [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]

     */

////////////////////////////////////////////////////////////////////
    //iteration解法

    // Simple Java Solution with For-Each loops
    // No messy indexing. Avoid the ConcurrentModificationException
    // by using a temp list.
    public List<List<Integer>> subsets2(int[] S) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());

        Arrays.sort(S);

        for(int i : S) {
            List<List<Integer>> tmp = new ArrayList<>();
            //对于res里的每一个list，加入当前元素i，然后将此list加回到temp
            for(List<Integer> sub : res) {
                List<Integer> a = new ArrayList<>(sub);
                a.add(i);
                tmp.add(a);
                System.out.println("a : " + a);
                System.out.println("tmp : " + tmp);
            }
            res.addAll(tmp);
            System.out.println("res : " + res);
            System.out.println("=================");
        }
        return res;
    }

    @Test
    public void test_subsets2(){
        int[] nums = {1,2,3};
        System.out.println(subsets2(nums));
    }

//////////////////////////////////////////////////////////////////

    public List<List<Integer>> subsets5(int[] S) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        Arrays.sort(S);

        for(int i = S.length - 1; i >= 0; i--){
            int size = res.size() - 1;

            for(int j = size; j >= 0; j--){
                List<Integer> newList1 = new ArrayList<>();
                newList1.add(S[i]);
                newList1.addAll(res.get(j));
                res.add(newList1);

                System.out.println("newList1 " + newList1);
                System.out.println("res " + res);
            }
            System.out.println("=================");
        }
        return res;
    }

    @Test
    public void test_subsets5(){
        int[] nums = {1,2,3};
        System.out.println(subsets5(nums));
    }




//////////////////////////////////////////////////////////////////

    // Simple Java Solution Using Bit Operations
    public List<List<Integer>> subsets3(int[] nums) {
        int n = nums.length;
        List<List<Integer>> subsets = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, n); i++)
        {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++)
            {
                if (((1 << j) & i) != 0)
                    subset.add(nums[j]);
            }
            Collections.sort(subset);
            subsets.add(subset);
        }
        return subsets;
    }

    public List<List<Integer>> subsets33(int[] S) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        Arrays.sort(S);
        for (int i=0;i<1<<S.length;i++)
        {
            List<Integer> result =  new ArrayList<Integer>();
            for(int j=0;j<S.length;j++)
            {
                if ((i&(1<<j))!=0) result.add(S[j]);
            }
            results.add(result);
        }
        return results;
    }

//////////////////////////////////////////////////////////////////

    /* Share my 12-line simple java code with brief explanations

    dfs. 每个位置都有选与不选两个选项, 也可以看成遍历一棵二叉树, 向左走选, 向右走不选
    */
    public List<List<Integer>> subsets4(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null) { return ans; }
        Arrays.sort(nums);  // non-descending order
        dfs(ans, nums, new ArrayList<Integer>(), 0);
        return ans;
    }

    private void dfs(List<List<Integer>> ans,
                     int[] nums, List<Integer> list, int index) {
        if (index == nums.length) {
            ans.add(new ArrayList<Integer>(list));
            return;
        }
        // not pick the number at this index
        dfs(ans, nums, list, index+1);

        list.add(nums[index]);
        // pick the number at this index
        dfs(ans, nums, list, index+1);

        list.remove(list.size()-1);
    }

    @Test
    public void test_subsets4(){
        int[] nums = {1,2,3};
        System.out.println(subsets4(nums));
    }

//////////////////////////////////////////////////////////////////



    //Divide and conquer
    private List<List<Integer>> rec_subsets(int []S, int begin){
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if(S.length == 0 || begin >= S.length){
            List<Integer> emptyList = new ArrayList<Integer>();
            result.add(emptyList);
            return result;
        }

        List<List<Integer>> rest = rec_subsets(S, begin+1);

        // Add the result from the rest of the problem.
        result.addAll(rest);

        for(List<Integer> elem : rest){
            ArrayList<Integer> newComb = new ArrayList<Integer>();
            newComb.addAll(elem);
            elem.add(0, S[begin]);

            result.add(newComb);
        }

        return result;
    }

    public List<List<Integer>> subsets6(int[] S) {
        Arrays.sort(S);
        return this.rec_subsets(S, 0);
    }

//////////////////////////////////////////////////////////////////

    // Java backtracing solution.
    public List<List<Integer>> subsets7(int[] nums) {
        int N=nums.length;
        Arrays.sort(nums);
        boolean[] seats=new boolean[N];

        List<List<Integer>> res=new ArrayList<>();


        search(res,nums,seats,N,0);

        return res;
    }

    public void search(List<List<Integer>> res, int[] nums,
                       boolean[] seats,int N,int index){
        if(index==N){

            List<Integer> list=new ArrayList<>();

            for (int i=0;i<N;i++){
                if(seats[i]){
                    list.add(nums[i]);
                }
            }
            res.add(list);
            return;
        }

        seats[index]=true;
        search(res,nums,seats,N,index+1);
        seats[index]=false;

        search(res,nums,seats,N,index+1);
    }

//////////////////////////////////////////////////////////////////







}
