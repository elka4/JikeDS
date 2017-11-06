package _04_Tree._Validate;
/*
LeetCode – Unique Binary Search Trees (Java)

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example, Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
Analysis

Let count[i] be the number of unique binary search trees for i. The number of trees are determined by the number of subtrees which have different root node. For example,
 */


public class Unique_Binary_Search_Trees {

    //DP
    public int numTrees(int n) {
        int[] count = new int[n + 1];

        count[0] = 1;
        count[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= i - 1; j++) {
                count[i] = count[i] + count[j] * count[i - j - 1];
            }
        }

        return count[n];
    }





////////////////////////////////////////////////////////////////////

    /*
    F(i, n) = G(i-1) * G(n-i)	1 <= i <= n
    G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0)

     */

    public int numTrees2(int n) {
        int [] G = new int[n+1];
        G[0] = G[1] = 1;

        for(int i=2; i<=n; ++i) {
            for(int j=1; j<=i; ++j) {
                G[i] += G[j-1] * G[i-j];
            }
        }

        return G[n];
    }





////////////////////////////////////////////////////////////////////

    //Dp problem. 10+ lines with comments
    /**
     * Taking 1~n as root respectively:
     *      1 as root: # of trees = F(0) * F(n-1)  // F(0) == 1
     *      2 as root: # of trees = F(1) * F(n-2)
     *      3 as root: # of trees = F(2) * F(n-3)
     *      ...
     *      n-1 as root: # of trees = F(n-2) * F(1)
     *      n as root:   # of trees = F(n-1) * F(0)
     *
     * So, the formulation is:
     *      F(n) = F(0) * F(n-1) + F(1) * F(n-2) + F(2) * F(n-3) + ... + F(n-2) * F(1) + F(n-1) * F(0)
     */

    int numTrees3(int n) {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for (int i=2; i<=n; i++) {
            dp[i] = 0;
            for (int j=1; j<=i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }





////////////////////////////////////////////////////////////////////

/*    Fantastic Clean Java DP Solution with Detail Explaination
    First note that dp[k] represents the number of BST trees built from 1....k;

    Then assume we have the number of the first 4 trees: dp[1] = 1 ,dp[2] =2 ,dp[3] = 5, dp[4] =14 , how do we get dp[5] based on these four numbers is the core problem here.

    The essential process is: to build a tree, we need to pick a root node, then we need to know how many possible left sub trees and right sub trees can be held under that node, finally multiply them.

    To build a tree contains {1,2,3,4,5}. First we pick 1 as root, for the left sub tree, there are none; for the right sub tree, we need count how many possible trees are there constructed from {2,3,4,5}, apparently it's the same number as {1,2,3,4}. So the total number of trees under "1" picked as root is dp[0] * dp[4] = 14. (assume dp[0] =1). Similarly, root 2 has dp[1]*dp[3] = 5 trees. root 3 has dp[2]*dp[2] = 4, root 4 has dp[3]*dp[1]= 5 and root 5 has dp[0]*dp[4] = 14. Finally sum the up and it's done.

    Now, we may have a better understanding of the dp[k], which essentially represents the number of BST trees with k consecutive nodes. It is used as database when we need to know how many left sub trees are possible for k nodes when picking (k+1) as root.*/

    public int numTrees4(int n) {
        int [] dp = new int[n+1];
        dp[0]= 1;
        dp[1] = 1;
        for(int level = 2; level <=n; level++)
            for(int root = 1; root<=level; root++)
                dp[level] += dp[level-root]*dp[root-1];
        return dp[n];
    }


////////////////////////////////////////////////////////////////////

/*    Simple solution with easy explaination
    WE can know that by zero we can have 1 bst of null
    by 1 we have 1 bst of 1
    and for 2 we can arrange using two ways
    Now idea is simple for rest numbers. for n=3 make 1 as root node so there will be 0 nodes in left subtree and 2 nodes in right subtree. we know the solution for 2 nodes that they can be used to make 2 bsts.
    Now making 2 as the root node , there will be 1 in left subtree and 1 node in right subtree. ! node results in 1 way for making a BST.
    Now making 3 as root node. There will be 2 nodes in left subtree and 0 nodes in right subtree. We know 2 will give 2 BST and zero will give 1 BST.
    Totalling the result of all the 3 nodes as root will give 5. Same process can be applied for more numbers.*/

    public int number(int n){
        if(n==0)return 1;
        if(n==1)return 1;

        int result[]=new int [n+1];
        result[0]=1;
        result[1]=1;
        result[2]=2;
        if(n<3){
            return result[n];
        }

        for(int i=3;i<=n;i++){
            for(int k=1;k<=i;k++){

                result[i]=result[i]+result[k-1]*result[i-k];
            }
        }


        return result[n];
    }





////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////

}
