package _04_Tree.Change_Tree;

import lib.*;
import org.junit.Test;

import java.util.LinkedList;
/*
LeetCode – Invert Binary Tree (Java)

Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so fuck off.
 */
public class Invert_Binary_Tree {
    //Java Solution 1 - Recursive

    public TreeNode invertTree(TreeNode root) {
        if(root!=null){
            helper(root);
        }

        return root;
    }

    public void helper(TreeNode p){

        TreeNode temp = p.left;
        p.left = p.right;
        p.right = temp;

        if(p.left!=null)
            helper(p.left);

        if(p.right!=null)
            helper(p.right);
    }

    @Test
    public void test01() {
        int[] arr = {5, 3, 7,1,2,6};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        invertTree(root).print();

    }
/*
root:
   5
  / \
 /   \
 3   7
/ \ /
1 2 6

   5
  / \
 /   \
 7   3
  \ / \
  6 2 1

 */

//////////////////////////////////////////////////////////////////////////


    //Java Solution 2 - Iterative

    public TreeNode invertTree2(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

        if(root!=null){
            queue.add(root);
        }

        while(!queue.isEmpty()){
            TreeNode p = queue.poll();
            if(p.left!=null)
                queue.add(p.left);
            if(p.right!=null)
                queue.add(p.right);

            TreeNode temp = p.left;
            p.left = p.right;
            p.right = temp;
        }

        return root;
    }

//////////////////////////////////////////////////////////////////////////





//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////

}
