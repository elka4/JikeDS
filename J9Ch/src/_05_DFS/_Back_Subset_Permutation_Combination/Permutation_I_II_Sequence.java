package _05_DFS._Back_Subset_Permutation_Combination;
import org.junit.Test;

import java.util.*;

// top100
public class Permutation_I_II_Sequence {
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
    @Test
    public void test01(){
        System.out.println(permute(new int[]{1,2,3}));
    }//[[3, 2, 1], [2, 3, 1], [2, 1, 3], [3, 1, 2], [1, 3, 2], [1, 2, 3]]


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
    @Test
    public void test02(){
        System.out.println(permute2(new int[]{1,2,3}));
    }//[[3, 2, 1], [2, 3, 1], [2, 1, 3], [3, 1, 2], [1, 3, 2], [1, 2, 3]]

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
    @Test
    public void test03(){
        System.out.println(permute3(new int[]{1,2,3}));
    }//[[3, 2, 1], [2, 3, 1], [2, 1, 3], [3, 1, 2], [1, 3, 2], [1, 2, 3]]

//---------------------------------/////////////////
    private void swap(int[] nums, int pos, int i) {
        int temp = nums[pos];
        nums[pos] = nums[i];
        nums[i] = temp;
    }
//---------------------------------/////////////////
//---------------------------------/////////////////
//---------------------------------/////////////////
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
    @Test
    public void test21(){
        System.out.println(permuteUnique1(new int[]{1,2,2}));
    }//[[1, 2, 2], [2, 1, 2], [2, 2, 1]]



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
    @Test
    public void test211(){
        System.out.println(permuteUnique11(new int[]{1,2,2}));
    }//[[1, 2, 2], [2, 1, 2], [2, 2, 1]]


//---------------------------------//////////////////
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
    @Test
    public void test22(){
        System.out.println(permuteUnique2(new int[]{1,2,2}));
    }//[[1, 2, 2], [2, 1, 2], [2, 2, 1]]

//---------------------------------//////////////////

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
    @Test
    public void test23(){
        System.out.println(permuteUnique3(new int[]{1,2,2}));
    }//[[1, 2, 2], [2, 1, 2], [2, 2, 1]]

//---------------------------------//////////////////
//---------------------------------//////////////////
//---------------------------------//////////////////

    public String getPermutation01(int n, int k) {
        int pos = 0;
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n+1];
        StringBuilder sb = new StringBuilder();

        // create an array of factorial lookup
        int sum = 1;
        factorial[0] = 1;
        for(int i=1; i<=n; i++){
            sum *= i;
            factorial[i] = sum;
        }
        // factorial[] = {1, 1, 2, 6, 24, ... n!}

        // create a list of numbers to get indices
        for(int i=1; i<=n; i++){
            numbers.add(i);
        }
        // numbers = {1, 2, 3, 4}

        k--;

        for(int i = 1; i <= n; i++){
            int index = k/factorial[n-i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            k-=index*factorial[n-i];
        }

        return String.valueOf(sb);
    }

    //第k个排列    给定 n 和 k，求123..n组成的排列中的第 k 个排列。
    //[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    @Test
    public void test31(){
        System.out.println(getPermutation01(3,1));//123
        System.out.println(getPermutation01(3,2));//132
        System.out.println(getPermutation01(3,3));//213
        System.out.println(getPermutation01(3,4));//231
        System.out.println(getPermutation01(3,5));//312
        System.out.println(getPermutation01(3,6));//321
    }
//---------------------------------/////////////////
    public String getPermutation02(int n, int k) {
        List<Integer> num = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) num.add(i);
        int[] fact = new int[n];  // factorial
        fact[0] = 1;
        for (int i = 1; i < n; i++) fact[i] = i*fact[i-1];
        k = k-1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--){
            int ind = k/fact[i-1];
            k = k%fact[i-1];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }
    //第k个排列    给定 n 和 k，求123..n组成的排列中的第 k 个排列。
    //[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    @Test
    public void test32(){
        System.out.println(getPermutation02(3,1));//123
        System.out.println(getPermutation02(3,2));//132
        System.out.println(getPermutation02(3,3));//213
        System.out.println(getPermutation02(3,4));//231
        System.out.println(getPermutation02(3,5));//312
        System.out.println(getPermutation02(3,6));//321
    }


//---------------------------------/////////////////
    public String getPermutation03(int n, int k) {
        int pos = 0;
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n+1];
        StringBuilder sb = new StringBuilder();

        // create an array of factorial lookup
        int sum = 1;
        factorial[0] = 1;
        for(int i=1; i<=n; i++){
            sum *= i;
            factorial[i] = sum;
        }
        // factorial[] = {1, 1, 2, 6, 24, ... n!}

        // create a list of numbers to get indices
        for(int i=1; i<=n; i++){
            numbers.add(i);
        }
        // numbers = {1, 2, 3, 4}

        k--;

        for(int i = 1; i <= n; i++){
            int index = k/factorial[n-i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            k-=index*factorial[n-i];
        }

        return String.valueOf(sb);
    }
    //第k个排列    给定 n 和 k，求123..n组成的排列中的第 k 个排列。
    //[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    @Test
    public void test33(){
        System.out.println(getPermutation03(3,1));//123
        System.out.println(getPermutation03(3,2));//132
        System.out.println(getPermutation03(3,3));//213
        System.out.println(getPermutation03(3,4));//231
        System.out.println(getPermutation03(3,5));//312
        System.out.println(getPermutation03(3,6));//321
    }

//---------------------------------/////////////////
    public String getPermutation04(int n, int k) {
        List<Integer> num = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) num.add(i);
        int[] fact = new int[n];  // factorial
        fact[0] = 1;
        for (int i = 1; i < n; i++) fact[i] = i*fact[i-1];
        k = k-1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--){
            int ind = k/fact[i-1];
            k = k%fact[i-1];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }
    //第k个排列    给定 n 和 k，求123..n组成的排列中的第 k 个排列。
    //[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    @Test
    public void test34(){
        System.out.println(getPermutation04(3,1));//123
        System.out.println(getPermutation04(3,2));//132
        System.out.println(getPermutation04(3,3));//213
        System.out.println(getPermutation04(3,4));//231
        System.out.println(getPermutation04(3,5));//312
        System.out.println(getPermutation04(3,6));//321
    }
//---------------------------------/////////////////
    // 9Ch
    public String getPermutation_J(int n, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[n];

        k = k - 1;
        int factor = 1;
        for (int i = 1; i < n; i++) {
            factor *= i;
        }

        for (int i = 0; i < n; i++) {
            int index = k / factor;
            k = k % factor;
            for (int j = 0; j < n; j++) {
                if (used[j] == false) {
                    if (index == 0) {
                        used[j] = true;
                        sb.append((char) ('0' + j + 1));
                        break;
                    } else {
                        index--;
                    }
                }
            }
            if (i < n - 1) {
                factor = factor / (n - 1 - i);
            }
        }

        return sb.toString();
    }
    //第k个排列    给定 n 和 k，求123..n组成的排列中的第 k 个排列。
    //[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    @Test
    public void test35(){
        System.out.println(getPermutation_J(3,1));//123
        System.out.println(getPermutation_J(3,2));//132
        System.out.println(getPermutation_J(3,3));//213
        System.out.println(getPermutation_J(3,4));//231
        System.out.println(getPermutation_J(3,5));//312
        System.out.println(getPermutation_J(3,6));//321
    }
//---------------------------------/////////////////
//---------------------------------/////////////////
//---------------------------------/////////////////

}
