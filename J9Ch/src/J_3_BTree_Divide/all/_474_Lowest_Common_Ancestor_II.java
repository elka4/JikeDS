package J_3_BTree_Divide.all;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 474
 * Lowest Common Ancestor II
 *
 * Created by tianhuizhu on 6/28/17.
 */
public class _474_Lowest_Common_Ancestor_II {

    /**
     * @param root: The root of the tree
     * @param A, B: Two node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public TreeNode lowestCommonAncestorII(TreeNode root,
                                                 TreeNode A,
                                                 TreeNode B) {
        ArrayList<TreeNode> pathA = getPath2Root(A);
        ArrayList<TreeNode> pathB = getPath2Root(B);

        int indexA = pathA.size() - 1;
        int indexB = pathB.size() - 1;

        TreeNode lowestAncestor = null;
        while (indexA >= 0 && indexB >= 0) {
            if (pathA.get(indexA) != pathB.get(indexB)) {
                break;
            }
            lowestAncestor = pathA.get(indexA);
            indexA--;
            indexB--;
        }

        return lowestAncestor;
    }

    private ArrayList<TreeNode> getPath2Root(TreeNode node) {
        ArrayList<TreeNode> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        return path;
    }

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));

        root.left.parent = root;
        root.right.parent = root;
        root.right.left.parent = root.right;
        root.right.right.parent = root.right;


        System.out.println("root: ");
        root.print();


        lowestCommonAncestorII(root, root.right.right, root.right.left).print();
    }

}
