package _04_Tree._List;
import lib.*;
import java.util.*;


//  449. Serialize and Deserialize BST
//  https://leetcode.com/problems/serialize-and-deserialize-bst/
//  Tree
//  _297_Tree_Serialize_and_Deserialize_Binary_Tree_M
//  _652_Tree_Find_Duplicate_Subtrees_M
public class _449_Serialize_and_Deserialize_BST {
//------------------------------------------------------------------------
    //1
    //Java PreOrder + Queue solution
    public class Codec {
        private final String SEP = ",";
        private final String NULL = "null";
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            if (root == null) return NULL;
            //traverse it recursively if you want to, I am doing it iteratively here
            Stack<TreeNode> st = new Stack<>();
            st.push(root);
            while (!st.empty()) {
                root = st.pop();
                sb.append(root.val).append(SEP);
                if (root.right != null) st.push(root.right);
                if (root.left != null) st.push(root.left);
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        // pre-order traversal
        public TreeNode deserialize(String data) {
            if (data.equals(NULL)) return null;
            String[] strs = data.split(SEP);
            Queue<Integer> q = new LinkedList<>();
            for (String e : strs) {
                q.offer(Integer.parseInt(e));
            }
            return getNode(q);
        }

        // some notes:
        //   5
        //  3 6
        // 2   7
        private TreeNode getNode(Queue<Integer> q) { //q: 5,3,2,6,7
            if (q.isEmpty()) return null;
            TreeNode root = new TreeNode(q.poll());//root (5)
            Queue<Integer> samllerQueue = new LinkedList<>();
            while (!q.isEmpty() && q.peek() < root.val) {
                samllerQueue.offer(q.poll());
            }
            //smallerQueue : 3,2   storing elements smaller than 5 (root)
            root.left = getNode(samllerQueue);
            //q: 6,7   storing elements bigger than 5 (root)
            root.right = getNode(q);
            return root;
        }
    }


//------------------------------------------------------------------------
//2
//Deserialize from preorder and computed inorder, reusing old solution
/*
Java version based on the same idea:

Serialization: generate space separated pre-order traversal string
Deserialization: get in-order traversal string by sorting & generate the tree based on in-order and pre-order sequences
 */
class Solution2{
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return null;
        // pre-order traversal
        StringBuilder sb = new StringBuilder();
        preOrderRecurs(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void preOrderRecurs(TreeNode node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.val + " ");

            preOrderRecurs(node.left, sb);
            preOrderRecurs(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) return null;
        // get in order traversal by sorting
        String[] array = data.split("\\s");
        int[] preOrder = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            preOrder[i] = Integer.parseInt(array[i]);
        }
        int[] inOrder = Arrays.copyOf(preOrder, preOrder.length);
        Arrays.sort(inOrder);

        return recurs(inOrder, preOrder, 0, inOrder.length - 1, 0, preOrder.length - 1);
    }

    private TreeNode recurs(int[] inorder, int[] preorder, int inStart, int inEnd, int preStart, int preEnd) {
        if (inStart < 0 || inEnd < 0 || inEnd >= inorder.length || inStart > inEnd || preStart < 0 || preEnd < 0
                || preEnd >= preorder.length || preStart > preEnd) {
            return null;
        }
        int topValue = preorder[preStart];
        TreeNode topNode = new TreeNode(topValue);
        int topIndex = findTopIndex(inorder, inStart, inEnd, topValue);
        int leftItems = topIndex - inStart;
        int rightItems = inEnd - topIndex;
        TreeNode leftNode = recurs(inorder, preorder, inStart, topIndex - 1, preStart + 1, preStart + leftItems);
        TreeNode rightNode = recurs(inorder, preorder, topIndex + 1, inEnd, preEnd - rightItems + 1, preEnd);
        topNode.left = leftNode;
        topNode.right = rightNode;
        return topNode;
    }

    private int findTopIndex(int[] array, int start, int end, int value) {
        for (int i = start; i <= end; i++) {
            if (value == array[i]) {
                return i;
            }
        }
        return -1;
    }
}


//------------------------------------------------------------------------




//------------------------------------------------------------------------




//------------------------------------------------------------------------




//------------------------------------------------------------------------
}
/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.


 */