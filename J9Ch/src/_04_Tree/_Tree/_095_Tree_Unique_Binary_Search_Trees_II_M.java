package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
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

}
