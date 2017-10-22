package _04_Tree._2Tree_Class;

import lib.*;
import org.junit.Test;

public class _1_SortedArrayToBST {
    //time O(n) space O(logn)
    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null || num.length == 0) {
            return null;
        }
        return buildtree(num, 0, num.length - 1);
    }
    private TreeNode buildtree(int[] num, int start, int end){
        if(start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;

        TreeNode root = new TreeNode(num[mid]);
        root.left = buildtree(num, start, mid - 1);
        root.right = buildtree(num, mid + 1, end);

        return root;
    }

    @Test
    public void test01(){
        int[] arr = {1,3,5,6,7,8};
        TreeNode root = sortedArrayToBST(arr);
        System.out.println("root: ");
        root.print();
    }
}
