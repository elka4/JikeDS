package DP_Classified._2Sequence;
import org.junit.Test;
import java.util.*;


//  Paint House
public class _1PaintHouse {

    /**
     * @param costs n x 3 cost matrix
     * @return an integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }
        int[][] f = new int[2][3];
        int old, now = 0;
//        f[now][0] = f[now][1] = f[now][2] = 0;

        int i, j, k;


        for (i = 1; i <= n; ++i) {
            old = now;
            now = 1 - now;
            for (j = 0; j < 3; ++j) {
                f[now][j] = Integer.MAX_VALUE;
                for (k = 0; k < 3; ++k) {
                    if (k != j && f[old][k] + costs[i-1][j] < f[now][j]) {
                        f[now][j] = f[old][k] + costs[i-1][j];
                    }
                }
            }
        }

        return Math.min(f[now][0], Math.min(f[now][1], f[now][2]));
    }


    //Given costs = [[14,2,11],[11,14,5],[14,3,10]] return 10
    @Test
    public void test01(){
        int[][] costs = {{14,2,11}, {11,14,5}, {14,3,10}};
        System.out.println(minCost(costs));

    }


//------------------------------------------------------------------------------
    //leetcode

    /*
    The 1st row is the prices for the 1st house, we can change the matrix to present sum of prices from the 2nd row. i.e, the costs[1][0] represent minimum price to paint the second house red plus the 1st house.
     */

    public int minCost2(int[][] costs) {
        if(costs==null||costs.length==0){
            return 0;
        }
        for(int i=1; i<costs.length; i++){
            costs[i][0] += Math.min(costs[i-1][1],costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0],costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][1],costs[i-1][0]);
        }
        int n = costs.length-1;
        return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
    }

    //Given costs = [[14,2,11],[11,14,5],[14,3,10]] return 10
    @Test
    public void test02(){
        int[][] costs = {{14,2,11}, {11,14,5}, {14,3,10}};
        System.out.println(minCost2(costs));

    }

//------------------------------------------------------------------------------

    //Share my very simple Java solution with explanation.
    //paintCurrentRed = min(paintPreviousGreen,paintPreviousBlue) + costs[i+1][0]

    public int minCost3(int[][] costs) {
        if(costs.length==0) return 0;
        int lastR = costs[0][0];
        int lastG = costs[0][1];
        int lastB = costs[0][2];
        for(int i=1; i<costs.length; i++){
            int curR = Math.min(lastG,lastB)+costs[i][0];
            int curG = Math.min(lastR,lastB)+costs[i][1];
            int curB = Math.min(lastR,lastG)+costs[i][2];
            lastR = curR;
            lastG = curG;
            lastB = curB;
        }
        return Math.min(Math.min(lastR,lastG),lastB);
    }
//------------------------------------------------------------------------------

}
/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

 Notice

All costs are positive integers.

Have you met this question in a real interview? Yes
Example
Given costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

house 0 is blue, house 1 is green, house 2 is blue, 2 + 5 + 3 = 10


 */