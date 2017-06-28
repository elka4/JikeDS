package J_3_Binary_Tree_Divide_Conquer.Optional_11;
import java.util.*;
/**
 * 474
 * Lowest Common Ancestor II
 *
 * Created by tianhuizhu on 6/28/17.
 */
public class _474_Lowest_Common_Ancestor_II_Easy {

    /**
     * Definition of ParentTreeNode:
     *
     * class ParentTreeNode {
     *     public ParentTreeNode parent, left, right;
     * }
     */
    class ParentTreeNode {
        public ParentTreeNode parent, left, right;
    }
    public class Solution {
        /**
         * @param root: The root of the tree
         * @param A, B: Two node in the tree
         * @return: The lowest common ancestor of A and B
         */
        public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
                                                     ParentTreeNode A,
                                                     ParentTreeNode B) {
            ArrayList<ParentTreeNode> pathA = getPath2Root(A);
            ArrayList<ParentTreeNode> pathB = getPath2Root(B);

            int indexA = pathA.size() - 1;
            int indexB = pathB.size() - 1;

            ParentTreeNode lowestAncestor = null;
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

        private ArrayList<ParentTreeNode> getPath2Root(ParentTreeNode node) {
            ArrayList<ParentTreeNode> path = new ArrayList<>();
            while (node != null) {
                path.add(node);
                node = node.parent;
            }
            return path;
        }
    }
}
