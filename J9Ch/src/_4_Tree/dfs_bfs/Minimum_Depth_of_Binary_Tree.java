package _4_Tree.dfs_bfs;

import lib.*;

import java.util.LinkedList;

/*
LeetCode – Minimum Depth of Binary Tree (Java)

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Thoughts

LinkedList is a queue in Java. The add() and remove() methods are used to manipulate the queue.
 */

public class Minimum_Depth_of_Binary_Tree {

    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        LinkedList<Integer> counts = new LinkedList<Integer>();

        nodes.add(root);
        counts.add(1);

        while(!nodes.isEmpty()){
            TreeNode curr = nodes.remove();
            int count = counts.remove();

            if(curr.left == null && curr.right == null){
                return count;
            }

            if(curr.left != null){
                nodes.add(curr.left);
                counts.add(count+1);
            }

            if(curr.right != null){
                nodes.add(curr.right);
                counts.add(count+1);
            }
        }

        return 0;
    }




//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////

}
