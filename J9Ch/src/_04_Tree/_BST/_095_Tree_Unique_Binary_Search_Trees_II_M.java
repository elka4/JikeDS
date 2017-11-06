package _04_Tree._BST;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


//  95. Unique Binary Search Trees II
//  https://leetcode.com/problems/unique-binary-search-trees-ii/description/
//  http://www.lintcode.com/zh-cn/problem/unique-binary-search-trees-ii/
public class _095_Tree_Unique_Binary_Search_Trees_II_M {


    public class Solution {
        public List<TreeNode> generateTrees(int n) {
            return genTrees(1,n); }
        public List<TreeNode> genTrees (int start, int end) {
            List<TreeNode> list = new ArrayList<TreeNode>();
            if(start>end) {
                list.add(null);
                return list; }
            if(start == end){
                list.add(new TreeNode(start)); return list;
            }
            List<TreeNode> left,right; for(int i=start;i<=end;i++) {
                left = genTrees(start, i-1); right = genTrees(i+1,end);
                for(TreeNode lnode: left) {
                    for(TreeNode rnode: right) {
                        TreeNode root = new TreeNode(i); root.left = lnode;
                        root.right = rnode; list.add(root);
                    } }
            }
            return list; }
    }

    //Here is my java solution with DP:

    public class Solution2 {
        public  List<TreeNode> generateTrees(int n) {
            List<TreeNode>[] result = new List[n+1];
            result[0] = new ArrayList<TreeNode>(); result[0].add(null);
            for(int len = 1; len <= n; len++){
                result[len] = new ArrayList<TreeNode>();
                for(int j=0; j<len; j++){
                    for(TreeNode nodeL : result[j]){
                        for(TreeNode nodeR : result[len-j-1]){
                            TreeNode node = new TreeNode(j+1); node.left = nodeL;
                            node.right = clone(nodeR, j+1); result[len].add(node);
                        }
                    }
                }
            }
            return result[n];
        }

        private  TreeNode clone(TreeNode n, int offset){ if(n == null)
            return null;
            TreeNode node = new TreeNode(n.val + offset); node.left = clone(n.left, offset);
            node.right = clone(n.right, offset);
            return node;
        }
    }
    /*
    result[i] stores the result until length i. For the result for length i+1, select the root node j from 0 to i, combine the result from left side and right side. Note for the right side we have to clone the nodes as the value will be offsetted by j.
written by jianwu original link here
     */



    /*Solution 3
This problem is a variant of the problem of Unique Binary Search Trees.
I provided a solution along with explanation for the above problem, in the question
"DP solution in 6 lines with explanation"
It is intuitive to solve this problem by following the same algorithm. Here is the code in a divide-and-conquer style.

     */
    public class Solution3 {
        public List<TreeNode> generateTrees(int n) { return generateSubtrees(1, n);
        }
        private List<TreeNode> generateSubtrees(int s, int e) { List<TreeNode> res = new LinkedList<TreeNode>(); if (s > e) {
            res.add(null); // empty tree
            return res; }
            for (int i = s; i <= e; ++i) {
                List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1); List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);
                for (TreeNode left : leftSubtrees) {
                    for (TreeNode right : rightSubtrees) {
                        TreeNode root = new TreeNode(i); root.left = left;
                        root.right = right; res.add(root);
                    } }
            }
            return res; }
    }
/////////////////////////////////////////////////////////////////////////

    //jiuzhang
    public class Jiuzhang {
        public ArrayList<TreeNode> generateTrees(int n) {
            return generate(1, n);
        }

        private ArrayList<TreeNode> generate(int start, int end){
            ArrayList<TreeNode> rst = new ArrayList<TreeNode>();

            if(start > end){
                rst.add(null);
                return rst;
            }

            for(int i=start; i<=end; i++){
                ArrayList<TreeNode> left = generate(start, i-1);
                ArrayList<TreeNode> right = generate(i+1, end);
                for(TreeNode l: left){
                    for(TreeNode r: right){
                        // should new a root here because it need to
                        // be different for each tree
                        TreeNode root = new TreeNode(i);
                        root.left = l;
                        root.right = r;
                        rst.add(root);
                    }
                }
            }
            return rst;
        }
    }
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////

}
/*
不同的二叉查找树 II

 描述
 笔记
 数据
 评测
给出n，生成所有由1...n为节点组成的不同的二叉查找树

样例
给出n = 3，生成所有5种不同形态的二叉查找树：

1         3     3       2    1
 \       /     /       / \    \
  3     2     1       1   3    2
 /     /       \                \
2     1         2                3
标签
动态规划 深度优先搜索
 */

/*
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */
