package J_9_DP.all;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/** 602 Russian Doll Envelopes


 * Created by tianhuizhu on 6/28/17.
 */

//Given envelopes = [[5,4],[6,4],[6,7],[2,3]], 
// the maximum number of envelopes you can Russian doll is
// 3 ([2,3] => [5,4] => [6,7]).
public class _602_Russian_Doll_Envelopes {
    /**
     * @param envelopes a number of envelopes with widths and heights
     * @return the maximum number of envelopes
     */
    public int maxEnvelopes(int[][] envelopes) {
        // Write your code here
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;

        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });

        int dp[] = new int[envelopes.length];
        int len = 0;

        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if(index < 0)
                index = -index - 1;

            dp[index] = envelope[1];

            if (index == len)
                len++;
        }

        return len;
    }

//------------------------------------------------------------------------------/////////
    //Java NLogN Solution with Explanation

    /*
    Sort the array. Ascend on width and descend on height if width are same.
Find the longest increasing subsequence based on height.
Since the width is increasing, we only need to consider height.
[3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3]
when sorting otherwise it will be counted as an increasing number if the order
 is [3, 3], [3, 4]
     */

    public int maxEnvelopes2(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        //w升序，w相同则h降序
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });


        for (int[] envelope:envelopes
             ) {

            System.out.println("w: " + envelope[0]);
            System.out.println("h: " + envelope[1]);
        }

        int dp[] = new int[envelopes.length];
        int len = 0;

        for(int[] envelope : envelopes){
            //envelope[1] 指的是当前envelope的高度
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            
            //如果找不到这个高度，index为负值
            //为了把这个转为正数好存储：
            if(index < 0){
                System.out.println("index = [" + index + "]");
                index = -(index + 1);
                System.out.println("index = [" + index + "]");
            }

            
            dp[index] = envelope[1];
            if(index == len)
                len++;
        }
        for ( int i : dp  ) {
            System.out.println("i = " + i);
        }        
        return len;
    }
    
    @Test
    public void test01(){
        int[][] envelopes = {{5,4}, {6,4}, {6,7}, {2,3}};
        System.out.println(maxEnvelopes2(envelopes));
    }
//------------------------------------------------------------------------------/////////
//Simple DP solution

    public int maxEnvelopes3(int[][] envelopes) {
        if (   envelopes           == null
                || envelopes.length    == 0
                || envelopes[0]        == null
                || envelopes[0].length == 0){
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] e1, int[] e2){
                return Integer.compare(e1[0], e2[0]);
            }
        });

        int   n  = envelopes.length;
        int[] dp = new int[n];

        int ret = 0;
        for (int i = 0; i < n; i++){
            dp[i] = 1;

            for (int j = 0; j < i; j++){
                if (   envelopes[i][0] > envelopes[j][0]
                        && envelopes[i][1] > envelopes[j][1]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }

            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
//------------------------------------------------------------------------------/////////
//Short and simple Java solution (15 lines)
    public int maxEnvelopes4(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
        int max = 0;
        int dp [] = new int [envelopes.length];

        for(int i = 0; i < envelopes.length; i++){
            dp[i] = 1;

            for(int j = 0; j < i; j++){

                if(envelopes[j][0] < envelopes[i][0] &&
                   envelopes[j][1] < envelopes[i][1])

                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            max = Math.max(dp[i], max);
        }
        return max;
    }
//------------------------------------------------------------------------------/////////

//------------------------------------------------------------------------------/////////

//------------------------------------------------------------------------------/////////

//------------------------------------------------------------------------------/////////


}
/*

You have a number of envelopes with widths and heights given as a pair of
integers (w, h). One envelope can fit into another if and only if both the
 width and height of one envelope is greater than the width and height of
 the other envelope.

What is the maximum number of envelopes can you Russian doll?
(put one inside other)

Have you met this question in a real interview? Yes
Example
Given envelopes = [[5,4],[6,4],[6,7],[2,3]],
the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */