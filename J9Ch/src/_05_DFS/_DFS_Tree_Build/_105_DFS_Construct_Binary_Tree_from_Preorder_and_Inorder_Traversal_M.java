package _05_DFS._DFS_Tree_Build;

import lib.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _105_DFS_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_M {

    class Solution{
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return helper(0, 0, inorder.length - 1, preorder, inorder);
        }

        public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
            if (preStart > preorder.length - 1 || inStart > inEnd) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[preStart]);
            int inIndex = 0; // Index of current root in inorder
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == root.val) {
                    inIndex = i;
                }
            }
            root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
            root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
            return root;
        }
    }

    //5ms Java Clean Solution with Caching
    class Solution2{
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

            for(int i = 0; i < inorder.length; i++) {
                inMap.put(inorder[i], i);
            }

            TreeNode root = buildTree(preorder, 0, preorder.length - 1,
                    inorder, 0, inorder.length - 1, inMap);
            return root;
        }

        public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder,
                                  int inStart, int inEnd, Map<Integer, Integer> inMap) {

            if(preStart > preEnd || inStart > inEnd) return null;

            TreeNode root = new TreeNode(preorder[preStart]);
            int inRoot = inMap.get(root.val);
            int numsLeft = inRoot - inStart;

            root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
                    inorder, inStart, inRoot - 1, inMap);

            root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
                    inorder, inRoot + 1, inEnd, inMap);

            return root;
        }
    }

//////////////////////////////////////////////////////////////////////////////////////
    //jiuzhang
public class Jiuzhang {
    private int findPosition(int[] arr, int start, int end, int key) {
        int i;
        for (i = start; i <= end; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    private TreeNode myBuildTree(int[] inorder, int instart, int inend,
                                 int[] preorder, int prestart, int preend) {
        if (instart > inend) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[prestart]);

        int position = findPosition(inorder, instart, inend, preorder[prestart]);

        root.left = myBuildTree(inorder, instart, position - 1,
                preorder, prestart + 1, prestart + position - instart);

        root.right = myBuildTree(inorder, position + 1, inend,
                preorder, position - inend + preend + 1, preend);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length != preorder.length) {
            return null;
        }
        return myBuildTree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }
}



//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */

/*
根据中序遍历和后序遍历树构造二叉树

 注意事项

你可以假设树中不存在相同数值的节点

您在真实的面试中是否遇到过这个题？ Yes
样例
给出树的中序遍历： [1,2,3] 和后序遍历： [1,3,2]

返回如下的树：

  2

 /  \

1    3
 */