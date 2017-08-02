package _4_Tree.dfs_bfs;

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
}
