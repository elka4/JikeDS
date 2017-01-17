package top100.Enhance._2Tree_Class;

public class _1_SortedArrayToBST {

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
}
