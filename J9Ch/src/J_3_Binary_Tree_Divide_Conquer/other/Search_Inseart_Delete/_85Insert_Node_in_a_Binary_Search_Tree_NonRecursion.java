package J_3_Binary_Tree_Divide_Conquer.other.Search_Inseart_Delete;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

public class _85Insert_Node_in_a_Binary_Search_Tree_NonRecursion {
	 /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) {
            root = node;
            return root;
        }
        TreeNode tmp = root;
        TreeNode last = null;
        while (tmp != null) {
            last = tmp;
            if (tmp.val > node.val) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        if (last != null) {
            if (last.val > node.val) {
                last.left = node;
            } else {
                last.right = node;
            }
        }
        return root;
    }

    @Test
    public void test03() {
        int[] arr = {3, 2, 5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        insertNode(root, new TreeNode(4));
        root.print();
    }
}
