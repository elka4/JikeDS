package top100.Enhance._4DP_Class;

import java.util.*;

public class _6_Unique_Binary_Search_Tree_II {
	public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        int[] count = new int[n + 1];
        count[0] = 1;
        for (int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                count[i] += count[j] * count[i - j - 1];
            }
        }
        TreeNode[] resarr =  helper(1, n, count);
        res = Arrays.asList(resarr);
        return res;
    }
    private TreeNode[] helper(int start, int end, int[] count){
        if(start > end){
            TreeNode[] roots = new TreeNode[1];
            roots[0] = null;
            return roots;
        }
        TreeNode[] roots = new TreeNode[count[end - start + 1]];
        int index = 0;
        for(int i = start; i <= end; i++){
            TreeNode[] lefts = helper(start, i - 1, count);
            TreeNode[] rights = helper(i + 1, end, count);
            for(TreeNode left : lefts){
                for(TreeNode right : rights){
                    roots[index] = new TreeNode(i);
                    roots[index].left = left;
                    roots[index++].right = right;
                }
            }
        }
        return roots;
    }
}
