package lib;


public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public int size;
    public TreeNode(int x) { val = x;  size = 1;}

    public void setLeftChild(TreeNode left) {
        this.left = left;
//        if (left != null) {
//            left.parent = this;
//        }
    }

    public void setRightChild(TreeNode right) {
        this.right = right;
//        if (right != null) {
//            right.parent = this;
//        }
    }
    public static TreeNode createMinimalBST(int arr[], int start, int end){
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode n = new TreeNode(arr[mid]);
        n.setLeftChild(createMinimalBST(arr, start, mid - 1));
        n.setRightChild(createMinimalBST(arr, mid + 1, end));
        return n;
    }

    public void insertInOrder(int d) {
        if (d <= val) {
            if (left == null) {
                setLeftChild(new TreeNode(d));
            } else {
                left.insertInOrder(d);
            }
        } else {
            if (right == null) {
                setRightChild(new TreeNode(d));
            } else {
                right.insertInOrder(d);
            }
        }
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isBST() {
        if (left != null) {
            if (val < left.val || !left.isBST()) {
                return false;
            }
        }

        if (right != null) {
            if (val >= right.val || !right.isBST()) {
                return false;
            }
        }

        return true;
    }

    public int height() {
        int leftHeight = left != null ? left.height() : 0;
        int rightHeight = right != null ? right.height() : 0;
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public TreeNode find(int d) {
        if (d == val) {
            return this;
        } else if (d <= val) {
            return left != null ? left.find(d) : null;
        } else if (d > val) {
            return right != null ? right.find(d) : null;
        }
        return null;
    }
    public static TreeNode createMinimalBST(int array[]) {

        return createMinimalBST(array, 0, array.length - 1);
    }
    public void print() {
        BTreePrinter.printNode(this);
    }

}
