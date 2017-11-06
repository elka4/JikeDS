package _04_Tree._BST;


//  96. Unique Binary Search Trees
//  https://leetcode.com/problems/unique-binary-search-trees/description/
//  http://www.lintcode.com/zh-cn/problem/unique-binary-search-trees/
public class _096_Tree_Unique_Binary_Search_Trees_M {


/*
    DP Solution in 6 lines with explanation. F(i, n) = G(i-1) * G(n-i)
    The problem can be solved in a dynamic programming way. I’ll explain the intuition and formulas in the following.

    Given a sequence 1…n, to construct a Binary Search Tree (BST) out of the sequence, we could enumerate each number i in the sequence, and use the number as the root, naturally, the subsequence 1…(i-1) on its left side would lay on the left branch of the root, and similarly the right subsequence (i+1)…n lay on the right branch of the root. We then can construct the subtree from the subsequence recursively. Through the above approach, we could ensure that the BST that we construct are all unique, since they have unique roots.

    The problem is to calculate the number of unique BST. To do so, we need to define two functions:

    G(n): the number of unique BST for a sequence of length n.

            F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST, and the sequence ranges from 1 to n.

    As one can see, G(n) is the actual function we need to calculate in order to solve the problem. And G(n) can be derived from F(i, n), which at the end, would recursively refer to G(n).

    First of all, given the above definitions, we can see that the total number of unique BST G(n), is the sum of BST F(i) using each number i as a root.
            i.e.

            G(n) = F(1, n) + F(2, n) + ... + F(n, n).
    Particularly, the bottom cases, there is only one combination to construct a BST out of a sequence of length 1 (only a root) or 0 (empty tree).
            i.e.

                    G(0)=1, G(1)=1.
    Given a sequence 1…n, we pick a number i out of the sequence as the root, then the number of unique BST with the specified root F(i), is the cartesian product of the number of BST for its left and right subtrees. For example, F(3, 7): the number of unique BST tree with number 3 as its root. To construct an unique BST out of the entire sequence [1, 2, 3, 4, 5, 6, 7] with 3 as the root, which is to say, we need to construct an unique BST out of its left subsequence [1, 2] and another BST out of the right subsequence [4, 5, 6, 7], and then combine them together (i.e. cartesian product). The tricky part is that we could consider the number of unique BST out of sequence [1,2] as G(2), and the number of of unique BST out of sequence [4, 5, 6, 7] as G(4). Therefore, F(3,7) = G(2) * G(4).

            i.e.

                    F(i, n) = G(i-1) * G(n-i)	1 <= i <= n
    Combining the above two formulas, we obtain the recursive formula for G(n). i.e.

            G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0)
    In terms of calculation, we need to start with the lower number, since the value of G(n) depends on the values of G(0) … G(n-1).

    With the above explanation and formulas, here is the implementation in Java.
*/

    public int numTrees(int n) {
        int [] G = new int[n+1];
        G[0] = G[1] = 1;

        for(int i=2; i<=n; ++i) {
            for(int j=1; j<=i; ++j) {
                G[i] += G[j-1] * G[i-j];
            }
        }

        return G[n];
    }




/*    Fantastic Clean Java DP Solution with Detail Explaination
    First note that dp[k] represents the number of BST trees built from 1....k;

    Then assume we have the number of the first 4 trees: dp[1] = 1 ,dp[2] =2 ,dp[3] = 5, dp[4] =14 , how do we get dp[5] based on these four numbers is the core problem here.

    The essential process is: to build a tree, we need to pick a root node, then we need to know how many possible left sub trees and right sub trees can be held under that node, finally multiply them.

    To build a tree contains {1,2,3,4,5}. First we pick 1 as root, for the left sub tree, there are none; for the right sub tree, we need count how many possible trees are there constructed from {2,3,4,5}, apparently it's the same number as {1,2,3,4}. So the total number of trees under "1" picked as root is dp[0] * dp[4] = 14. (assume dp[0] =1). Similarly, root 2 has dp[1]*dp[3] = 5 trees. root 3 has dp[2]*dp[2] = 4, root 4 has dp[3]*dp[1]= 5 and root 5 has dp[0]*dp[4] = 14. Finally sum the up and it's done.

    Now, we may have a better understanding of the dp[k], which essentially represents the number of BST trees with k consecutive nodes. It is used as database when we need to know how many left sub trees are possible for k nodes when picking (k+1) as root.*/

    public int numTrees1(int n) {
        int [] dp = new int[n+1];
        dp[0]= 1;
        dp[1] = 1;
        for(int level = 2; level <=n; level++)
            for(int root = 1; root<=level; root++)
                dp[level] += dp[level-root]*dp[root-1];
        return dp[n];
    }



    public int numTrees2(int n) {
        int [] G = new int[n+1]; G[0] = G[1] = 1;
        for(int i=2; i<=n; ++i) { for(int j=1; j<=i; ++j) {
            G[i] += G[j-1] * G[i-j]; }
        }
        return G[n];
    }
    public int number(int n){ if(n==0)return 1;
        if(n==1)return 1;
        int result[]=new int [n+1]; result[0]=1;
        result[1]=1;
        result[2]=2;
        if(n<3){
            return result[n];
        }
        for(int i=3;i<=n;i++){
            for(int k=1;k<=i;k++){ result[i]=result[i]+result[k-1]*result[i-k];
            } }
        return result[n];
    }

////////////////////////////////////////////////////////////////////////////
    //jiuzhang
public class Jiuzhang {
    /*
    The case for 3 elements example
    Count[3] = Count[0]*Count[2]  (1 as root)
                  + Count[1]*Count[1]  (2 as root)
                  + Count[2]*Count[0]  (3 as root)

    Therefore, we can get the equation:
    Count[i] = ∑ Count[0...k] * [ k+1....i]     0<=k<i-1

    */
    public int numTrees(int n) {
        int[] count = new int[n + 2];
        count[0] = 1;
        count[1] = 1;

        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }
}


////////////////////////////////////////////////////////////////////////////


}
/*
不同的二叉查找树

 描述
 笔记
 数据
 评测
给出 n，问由 1...n 为节点组成的不同的二叉查找树有多少种？

样例
给出n = 3，有5种不同形态的二叉查找树：

1           3    3       2      1
 \         /    /       / \      \
  3      2     1       1   3      2
 /      /       \                  \
2     1          2                  3
标签
动态规划 卡特兰数
 */

/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */
