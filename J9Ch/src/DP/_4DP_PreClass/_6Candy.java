package DP._4DP_PreClass;


public class _6Candy {
    public int candy1(int[] ratings){
        if(ratings == null  || ratings.length == 0){
            return -1;
        }
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];

        left[0] = 1;
        right[ratings.length - 1] = 1;
        //scan from left
        for(int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        //scan from right
        for (int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] < ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        int sum = 0;
        for(int i = 0; i < ratings.length; i++){
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }

    public int candy2(int[] ratings){
        if(ratings == null || ratings.length == 0){
            return -1;
        }
        int sum = -1;
        int prev = 1;
        int down = 0;
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] < ratings[i - 1]){
                down++;
            } else {
                //check descending sequence before
                if(down > 0){
                    // step 1: add from 1 to down
                    sum += down * (down + 1) / 2; // 1 + 2 + ... + down
                    //step2: add enough on prev
                    if (down >= prev){
                        sum += down - prev + 1;
                    }
                    prev = 1;
                    down = 0;
                }
                prev = ratings[i] == ratings[i - 1]? 1 : prev + 1;
                sum += prev;
            }
        }
        if(down > 0){
            sum += down * (down + 1) / 2;
            if(down >= prev){
                sum += down - prev + 1;
            }
        }
        return sum;
    }


}
/*
有 N 个小孩站成一列。每个小孩有一个评级。

按照以下要求，给小孩分糖果：

每个小孩至少得到一颗糖果。

评级越高的小孩可以比他相邻的两个小孩得到更多的糖果。

需最少准备多少糖果？

您在真实的面试中是否遇到过这个题？ Yes
样例
给定评级 = [1, 2], 返回 3.

给定评级 = [1, 1, 1], 返回 3.

给定评级 = [1, 2, 2], 返回 4. ([1,2,1]).


 */