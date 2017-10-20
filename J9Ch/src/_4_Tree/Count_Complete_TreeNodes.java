package _4_Tree;
import lib.TreeNode;

//
// Count Complete Tree Nodes
public class Count_Complete_TreeNodes {
    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
    public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 :
                height(root.right) == h-1 ? (1 << h) + countNodes(root.right)
                        : (1 << h-1) + countNodes(root.left);
    }


/////////////////////////////////////////////////////////////////////////

    int height2(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
    public int countNodes2(TreeNode root) {
        int nodes = 0, h = height2(root);
        while (root != null) {
            if (height2(root.right) == h - 1) {
                nodes += 1 << h;
                root = root.right;
            } else {
                nodes += 1 << h-1;
                root = root.left;
            }
            h--;
        }
        return nodes;
    }

/////////////////////////////////////////////////////////////////////////


    public int countNodes3(TreeNode root) {
        if (root == null)
            return 0;
        TreeNode left = root, right = root;
        int height = 0;
        while (right != null) {
            left = left.left;
            right = right.right;
            height++;
        }
        if (left == null)
            return (1 << height) - 1;
        return 1 + countNodes3(root.left) + countNodes3(root.right);
    }

/////////////////////////////////////////////////////////////////////////


    public int countNodes4(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + countNodes4(root.left) + countNodes4(root.right);
    }

/////////////////////////////////////////////////////////////////////////


    public int countNodes5(TreeNode root) {

        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);

        if (leftDepth == rightDepth)
            return (1 << leftDepth) - 1;
        else
            return 1+countNodes5(root.left) + countNodes5(root.right);

    }

    private int rightDepth(TreeNode root) {
        // TODO Auto-generated method stub
        int dep = 0;
        while (root != null) {
            root = root.right;
            dep++;
        }
        return dep;
    }

    private int leftDepth(TreeNode root) {
        // TODO Auto-generated method stub
        int dep = 0;
        while (root != null) {
            root = root.left;
            dep++;
        }
        return dep;
    }

//////////////////////////////////////////////////////////////////

    public int countNodes6(TreeNode root) {
        if (root==null) return 0;
        if (root.left==null) return 1;
        int height = 0;
        int nodesSum = 0;
        TreeNode curr = root;
        while(curr.left!=null) {
            nodesSum += (1<<height);
            height++;
            curr = curr.left;
        }
        return nodesSum + countLastLevel(root, height);
    }

    private int countLastLevel(TreeNode root, int height) {
        if(height==1)
            if (root.right!=null) return 2;
            else if (root.left!=null) return 1;
            else return 0;
        TreeNode midNode = root.left;
        int currHeight = 1;
        while(currHeight<height) {
            currHeight++;
            midNode = midNode.right;
        }
        if (midNode==null) return countLastLevel(root.left, height-1);
        else return (1<<(height-1)) + countLastLevel(root.right, height-1);
    }

//////////////////////////////////////////////////////////////////


}

/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

 */
