package _4_Tree.dfs_bfs;
import java.util.*;
import lib.*;


/*
LeetCode – Verify Preorder Serialization of a Binary Tree (Java)

One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

      9
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
 */


public class Verify_Preorder_Serialization_of_a_Binary_Tree {

    /*
    Java Solution - Stack

We can keep removing the leaf node until there is no one to remove. If a sequence is like "4 # #", change it to "#" and continue. We need a stack so that we can record previous removed nodes.
     */

    public boolean isValidSerialization(String preorder) {
        LinkedList<String> stack = new LinkedList<String>();
        String[] arr = preorder.split(",");

        for(int i=0; i<arr.length; i++){
            stack.add(arr[i]);

            while(stack.size()>=3
                    && stack.get(stack.size()-1).equals("#")
                    && stack.get(stack.size()-2).equals("#")
                    && !stack.get(stack.size()-3).equals("#")){

                stack.remove(stack.size()-1);
                stack.remove(stack.size()-1);
                stack.remove(stack.size()-1);

                stack.add("#");
            }

        }

        if(stack.size()==1 && stack.get(0).equals("#"))
            return true;
        else
            return false;
    }



/////////////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////




/////////////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////




/////////////////////////////////////////////////////////////////////




}
