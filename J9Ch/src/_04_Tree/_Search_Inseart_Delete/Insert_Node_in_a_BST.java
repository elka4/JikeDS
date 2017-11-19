package _04_Tree._Search_Inseart_Delete;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

public class Insert_Node_in_a_BST {
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
    public void test0333() {
        int[] arr = {3, 2, 5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        insertNode(root, new TreeNode(4));
        root.print();
    }

//-------------------------------------------------------------------------///
    public TreeNode insertNode2(TreeNode root, TreeNode node) {
        if (root == null) {
            return node;
        }
        if (root.val > node.val) {
            root.left = insertNode2(root.left, node);
        } else {
            root.right = insertNode2(root.right, node);
        }
        return root;
    }
//-------------------------------------------------------------------------///

    /**
     * @param root: The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;

        TreeNode parent = findNode(dummy, root, value);
        TreeNode node;
        if (parent.left != null && parent.left.val == value) {
            node = parent.left;
        } else if (parent.right != null && parent.right.val == value) {
            node = parent.right;
        } else {
            return dummy.left;
        }

        deleteNode(parent, node);

        return dummy.left;
    }

    private TreeNode findNode(TreeNode parent, TreeNode node, int value) {
        if (node == null) {
            return parent;
        }

        if (node.val == value) {
            return parent;
        }
        if (value < node.val) {
            return findNode(node, node.left, value);
        } else {
            return findNode(node, node.right, value);
        }
    }

    private void deleteNode(TreeNode parent, TreeNode node) {
        if (node.right == null) {
            if (parent.left == node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        } else {
            TreeNode temp = node.right;
            TreeNode father = node;

            while (temp.left != null) {
                father = temp;
                temp = temp.left;
            }

            if (father.left == temp) {
                father.left = temp.right;
            } else {
                father.right = temp.right;
            }

            if (parent.left == node) {
                parent.left = temp;
            } else {
                parent.right = temp;
            }

            temp.left = node.left;
            temp.right = node.right;
        }
    }

    @Test
    public void test03() {
        int[] arr = {5, 3, 6};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        System.out.println("root: ");
        root.print();

        deleteNode(root, root.left);
        root.print();
    }
//-------------------------------------------------------------------------//////////////
//Iteration
public TreeNode search (TreeNode root, int val) {
    if(root == null || root.val == val)
        return root;
    TreeNode cur = root;
    while(cur != null) {
        if(cur.val == val)
            return cur;
        else if (cur.val < val)
            cur = cur.right;
        else
            cur = cur.left;
    }
    return null;

}
    public TreeNode search2 (TreeNode root, int val) {
        if(root == null || root.val == val){
            return root;
        } else if (root.val < val){
            return search2(root.right, val);
        } else {
            return search2(root.left, val);
        }
    }

    @Test
    public void test01() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        search(root, 3).print();
        search2(root, 6).print();

    }

    @Test
    public void test02() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        search(root, 3).print();
        search2(root, 6).print();
        search2(root, 7).print();

    }
//-------------------------------------------------------------------------/


    public TreeNode insert(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        TreeNode cur = root;
        TreeNode prev = null;
        //1.Search the position to insert
        while(cur != null && cur.val != val){
            prev = cur;
            if(cur.val < val){
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        //2.Insert the large node
        if(prev.val < val){
            prev.right = new TreeNode(val);
        } else {
            prev.left = new TreeNode(val);
        }
        return root;

    }

    //Recursion
    public TreeNode insert2(TreeNode root, int val) {
        //Base Case
        if(root == null) return new TreeNode(val);

        if(root.val > val) {
            //Assume all nodes are handled in left subtree
            root.left = insert2(root.left, val);
        } else if (root.val < val) {
            //assume all nodes are handled in right subtree
            root.right = insert2(root.right, val);
        }
        return root;
    }

    @Test
    public void test033() {
        int[] arr = {3, 2, 5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        insert(root, 4);
        insert2(root, 1);
        root.print();
    }

    @Test
    public void test04() {
        int[] arr = {5, 3, 7,1,2,6};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        insert(root, 4);
        insert2(root, 8);
        root.print();
    }
////////////////////////////////////////////////////////////

//-------------------------------------------------------------------------//////////////


//-------------------------------------------------------------------------//////////////



}
