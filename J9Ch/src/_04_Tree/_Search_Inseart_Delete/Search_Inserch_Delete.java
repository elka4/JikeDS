package _04_Tree._Search_Inseart_Delete;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

public class Search_Inserch_Delete {


//--------------------------------------------------------------------------------
    //Recursive Search
    public TreeNode search_r (TreeNode root, int val) {
        if(root == null || root.val == val){
            return root;
        } else if (root.val < val){
            return search_r(root.right, val);
        } else {
            return search_r(root.left, val);
        }
    }
    @Test
    public void t_search_r() {
        int[] arr = {4,2,5,1,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        search_r(root,2).print();
    }

//--------------------------------------------------------------------------------
    //Iterative Search
    public TreeNode search_i (TreeNode root, int val) {
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
    @Test
    public void t_search_i() {
        int[] arr = {4,2,5,1,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        search_i(root,2).print();
    }
//--------------------------------------------------------------------------------
    //Recursive Insert
    public TreeNode insert_r1(TreeNode root, TreeNode node) {
        if (root == null) {
            return node;
        }
        if (root.val > node.val) {
            root.left = insert_r1(root.left, node);
        } else {
            root.right = insert_r1(root.right, node);
        }
        return root;
    }
    @Test
    public void t_insert_r1() {
        int[] arr = {4,2,5,1,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        insert_r1(root,new TreeNode(6)).print();
    }

    //Recursion
    public TreeNode insert_r2(TreeNode root, int val) {
        //Base Case
        if(root == null) return new TreeNode(val);

        if(root.val > val) {
            //Assume all nodes are handled in left subtree
            root.left = insert_r2(root.left, val);
        } else if (root.val < val) {
            //assume all nodes are handled in right subtree
            root.right = insert_r2(root.right, val);
        }
        return root;
    }
    @Test
    public void t_insert_r2() {
        int[] arr = {4,2,5,1,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        insert_r2(root, 6).print();
    }
//--------------------------------------------------------------------------------
    //Iterative Insert
    public TreeNode insert_i1(TreeNode root, TreeNode node) {
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
    public void t_insert_i1() {
        int[] arr = {4,2,5,1,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        insert_i1(root, new TreeNode(6)).print();
    }

    //Iterative Insert
    public TreeNode insert_i2(TreeNode root, int val) {
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
    @Test
    public void t_insert_i2() {
        int[] arr = {4,2,5,1,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        insert_r2(root, 0).print();
    }

//--------------------------------------------------------------------------------
    //Delete
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
    public void t_removeNode() {
        int[] arr = {4,2,5,1,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        removeNode(root, 4).print();
    }

//--------------------------------------------------------------------------------
    //Iterative Delete
    public TreeNode delete (TreeNode root, int val) {
        if (root == null)
            return root;
        TreeNode dummy = new TreeNode (Integer.MIN_VALUE);
        dummy.left = root;
        TreeNode prev = dummy;
        TreeNode cur = root;

        //1. Find the node with val
        while (cur != null && cur.val != val) {
            prev = cur;
            if (cur.val < val) {
                cur = cur.right;
            }
            else {
                cur = cur.left;
            }
        }
        if (cur == null)
            return dummy.left;//Not found
        TreeNode target = cur;

        //2. Case 1: Two child
        if (cur.left != null && cur.right != null) {
            //2.1 Find the leftMost node in right subTree
            prev = cur;
            cur = cur.right;
            while (cur.left != null) {
                prev = cur;
                cur = cur.left;
            }
            //2.2 Change value
            target.val = cur.val;
        }

        //3. Case 2: Delete cur, which has one or no child
        if (cur.left == null) {
            if (prev.left == cur)
                prev.left = cur.right;
            else
                prev.right = cur.right;
        } else {
            if (prev.left == cur)
                prev.left = cur.left;
            else
                prev.right = cur.left;
        }
        return dummy.left;
    }
    @Test
    public void t_delete() {
        int[] arr = {4,2,5,1,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        delete(root, 4).print();
    }

//--------------------------------------------------------------------------------

}
