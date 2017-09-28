package Bit106.L04;

/**
 * Implement BST
 */
public class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int x) {
        this.val = x;
    }

    public void insert(int value) {
        if (value <= val) { //Go to left
            // 如果left node本来就没有, 直接添加left node,
            // 如果有left node, 就把left node当做root, 对这个subtree再做一遍
            // recursion
            if (left == null) {
                left = new TreeNode(value);
            } else {
                left.insert(value);
            }
        } else { //Go to right
            if (right == null) {
                right = new TreeNode(value);
            } else {
                right.insert(value);
            }
        }
    }

    public boolean contains(int value) {
        if (value == val) {
            return true;
        } else if (value < val) { //Go to left
            if (left == null) {
                return false;
            } else {
                return left.contains(value); // recursion
            }
        } else { //Go to right
            if (right == null) {
                return false;
            } else {
                return right.contains(value);
            }
        }
    }

    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.println(val);
        if (right != null) {
            right.printInOrder();
        }

    }
}
