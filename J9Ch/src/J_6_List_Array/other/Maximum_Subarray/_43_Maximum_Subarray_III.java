package J_6_List_Array.other.Maximum_Subarray;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 43 Maximum Subarray III


 * Created by tianhuizhu on 6/28/17.
 */

    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */

    /*
    Given an array of integers and a number k,
    find k non-overlapping subarrays which have the largest sum.
    The number in each subarray should be contiguous.
    Return the largest sum.
    The subarray should contain at least one number
     */

    // Given [-1,4,-2,3,-2,3], k=2, return 8



    //找k个不overlap的subarray，sum最大

public class _43_Maximum_Subarray_III {

    // 方法一 划分类DP
    public int maxSubArray(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        int len = nums.length;


        int[][] globalMax = new int[k + 1][len + 1];
        int[][] localMax = new int[k + 1][len + 1];

        for (int i = 1; i <= k; i++) {
            localMax[i][i-1] = Integer.MIN_VALUE;

            //小于 i 的数组不能够partition
            for (int j = i; j <= len; j++) {
                localMax[i][j] = Math.max(localMax[i][j-1],
                        globalMax[i - 1][j-1]) + nums[j-1];

                if (j == i)
                    globalMax[i][j] = localMax[i][j];
                else
                    globalMax[i][j] = Math.max(globalMax[i][j-1],
                                                localMax[i][j]);
            }
        }
        return globalMax[k][len];
    }

    @Test
    public void test01(){
        int[] nums = {-1,4,-2,3,-2,3};
        System.out.println(maxSubArray(nums, 2));
    }
    @Test
    public void test02(){
        int[] nums = {-1,4,-2,3,-2,3};
        System.out.println(maxSubArray(nums, 3));
    }
    @Test
    public void test03(){
        int[] nums = {-1,4,-2,3,-2,3};
        System.out.println(maxSubArray(nums, 4));
    }


    /*
    这是一题划分类型的题目, 划分类型的题目只关心划分位,而不关心区间里面的内容.针对这题来说,
    我们只划分数组,并不是划分的区间内的所有数字形成的子数组就是我们在这个划分中要找的,
    可能是子子数组, 和区间型DP(Scramble String,Burst Balloons)形成了比较大的区别.这点需要注意.
     也是划分类DP这种特点, 导致我们直接用类似区间型DP的定义方式,复杂度会很高,子子数组怎么复杂度很高.

    所以此类题目一个比较好的解法是采用local, global双状态. local保存当前位置为结尾的局部最优状态,
     global保存全局最优状态.

    如果采用传统解法:

    state: f[i][j]表示前i个元素选了j次子数组，能够取得的最大值

    function: f[i][j] = max{f[x][j-1] + subarray(x+1, i)} {x=0->i-1}

    answer: f[n][k]

    intialize: f[i][0] = 0, f[0][i] = -MAXINT (i>0)
    其中转换状态中的subarray(x+1,i)需要求 x+1至i位置中的和最大的子数组.这个复杂度比较高.
    需要提前求出来,本身又是一个O(n^2)的DP问题,总体的复杂度为O(kn^2).

    如果采用local, global的方法:
    state: local[i][j]定义为前i个元素中取j个子数组, 其中第i个元素一定会被取到的最大和.
             global[i][j]定义为前i个元素取j个子数组,第i个元素不管取不取的最大值,
             显然 global 是一个全局最优值.
     */
//------------------------------------------------------------------------------/////////////


    //方法二
    public int maxSubArray2(ArrayList<Integer> nums, int k) {
        // write your code
        int len = nums.size();
        int[][] f = new int[k+1][len];

        for (int i = 1; i < k+1; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += nums.get(j);
            }
            f[i][i-1] = sum;
        }

        for (int i = 1; i < len; i++) {
            f[1][i] = Math.max(f[1][i-1]+nums.get(i), nums.get(i));
        }

        for (int i = 2; i < k+1; i++) {
            for (int n = i;  n< len; n++) {

                int curMax = f[i][n-1] + nums.get(n);
                for (int j = i-2; j < n; j++) {

                    if ((f[i-1][j] + nums.get(n)) > curMax) {
                        curMax = f[i-1][j] + nums.get(n);
                    }
                }
                f[i][n] = curMax;
            }
        }

        int res = Integer.MIN_VALUE;
        for (int i = k-1; i < len; i++){
            if (f[k][i] > res) {
                res = f[k][i];
            }
        }
        return res;
    }

    @Test
    public void test04(){
        Integer[] num = {-1,4,-2,3,-2,3};
        //System.out.println(maxSubArray2(nums, 2));

        List<Integer> listofOptions = (List<Integer>) Arrays.asList(num);
        //then you can user constructoru of an arraylist to instantiate with predefined values.

        ArrayList<Integer> nums = new ArrayList<Integer>(listofOptions);

        //ArrayList<Integer>  nums = (ArrayList<Integer>) Arrays.asList(num);


        //List<Integer> list21 =  Arrays.asList(num); // Cannot modify returned list
        //List<Integer> list22 = new ArrayList<>(Arrays.asList(integers)); // good
        //List<Integer> list23 = Arrays.stream(integers).collect(Collectors.toList()); //Java 8

        System.out.println(maxSubArray2(nums, 2));
    }
    @Test
    public void test05(){
        Integer[] num = {-1,4,-2,3,-2,3};
        List<Integer> listofOptions = (List<Integer>) Arrays.asList(num);
        ArrayList<Integer> nums = new ArrayList<Integer>(listofOptions);
        System.out.println(maxSubArray2(nums, 3));
    }

    @Test
    public void test06(){
        Integer[] num = {-1,4,-2,3,-2,3};
        List<Integer> listofOptions = (List<Integer>) Arrays.asList(num);
        ArrayList<Integer> nums = new ArrayList<Integer>(listofOptions);
        System.out.println(maxSubArray2(nums, 4));
    }

//------------------------------------------------------------------------------/////////////
/*
我帮你加了一个初始化，通过了测试，我稍微解释一下，可能跟老师上课讲的有点一丢丢不一样。
首先，你的程序没有初始化，那么数组的初始值就是0，因为nums可能都是负数，所以答案会小与0，那么你这个初始值都是0，
最终的答案肯定是大于等于0的（因为操作都是Max.max,初始化都是0，结果当然不可能是小于0了）
所以我的初始化时 local[i][j] = global[i][j] = Integer.MIN_VALUE / 2;（这里除以2是因为
Integer.MIN_VALUE 如果与负数做加法可能会溢出）。因为我们是求最大值，且最大值可能是负数，所以全部初始化为最小值。
然后我们来设置初始状态，local[i][0] = global[i][0] = 0; 因为1~k我们都是要通过转移方程去求出来的
，那么把0时候的状态设置一下就好，显然是0就可以了。
 */
    public int maxSubArray3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return 0;
        }

        int n = nums.length;
        //int[][] dp = new int[n + 1][k + 1];
        int[][] local = new int[n + 1][k + 1];
        int[][] global = new int[n + 1][k + 1];

        for (int i = 0; i < n + 1; ++i) {
            local[i][0] = global[i][0] = 0;

            for (int j = 1; j < k + 1; ++j)
                local[i][j] = global[i][j] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                local[i][j] = Math.max(local[i - 1][j] + nums[i - 1],
                        global[i - 1][j - 1] + nums[i - 1]);
                global[i][j] = Math.max(local[i][j], global[i - 1][j]);
            }
        }

        return global[n][k];
    }
    @Test
    public void test07(){
        int[] nums = {-1,4,-2,3,-2,3};
        System.out.println(maxSubArray3(nums, 2));
    }
    @Test
    public void test08(){
        int[] nums = {-1,4,-2,3,-2,3};
        System.out.println(maxSubArray3(nums, 3));
    }
    @Test
    public void test09(){
        int[] nums = {-1,4,-2,3,-2,3};
        System.out.println(maxSubArray3(nums, 4));
    }



//------------------------------------------------------------------------------/////////////





}
