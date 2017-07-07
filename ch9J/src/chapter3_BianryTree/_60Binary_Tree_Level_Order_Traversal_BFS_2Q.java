package chapter3_BianryTree;

import java.util.ArrayList;

//version 3: BFS. two queues
public class _60Binary_Tree_Level_Order_Traversal_BFS_2Q {
/**
* @param root: The root of binary tree.
* @return: Level order a list of lists of integer
*/
public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
   ArrayList<ArrayList<Integer>> result = new ArrayList<>();
   if (root == null) {
       return result;
   }
   
   ArrayList<TreeNode> Q1 = new ArrayList<TreeNode>();
   ArrayList<TreeNode> Q2 = new ArrayList<TreeNode>();

   Q1.add(root);
   while (Q1.size() != 0) {
       ArrayList<Integer> level = new ArrayList<Integer>();
       Q2.clear();
       for (int i = 0; i < Q1.size(); i++) {
           TreeNode node = Q1.get(i);
           level.add(node.val);
           if (node.left != null) {
               Q2.add(node.left);
           }
           if (node.right != null) {
               Q2.add(node.right);
           }
       }
       
       // swap q1 and q2
       ArrayList<TreeNode> temp = Q1;
       Q1 = Q2;
       Q2 = temp;
       
       // add to result
       result.add(level);
   }
   
   return result;
}
}