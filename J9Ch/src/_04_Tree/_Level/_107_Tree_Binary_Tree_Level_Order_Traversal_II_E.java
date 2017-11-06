package _04_Tree._Level;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.*;


//
//
//
public class _107_Tree_Binary_Tree_Level_Order_Traversal_II_E {

//    DFS solution:

    public class Solution1 {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

            if(root == null) return wrapList;

            queue.offer(root);
            while(!queue.isEmpty()){
                int levelNum = queue.size();
                List<Integer> subList = new LinkedList<Integer>();
                for(int i=0; i<levelNum; i++) {
                    if(queue.peek().left != null) queue.offer(queue.peek().left);
                    if(queue.peek().right != null) queue.offer(queue.peek().right);
                    subList.add(queue.poll().val);
                }
                wrapList.add(0, subList);
            }
            return wrapList;
        }
    }
//    BFS solution:

    public class Solution2 {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
            levelMaker(wrapList, root, 0);
            return wrapList;
        }

        public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
            if(root == null) return;
            if(level >= list.size()) {
                list.add(0, new LinkedList<Integer>());
            }
            levelMaker(list, root.left, level+1);
            levelMaker(list, root.right, level+1);
            list.get(list.size()-level-1).add(root.val);
        }
    }
////////////////////////////////////////////////////////////////////////
    /**
     * @param root: The root of binary tree.
     * @return: buttom-up level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }

        Collections.reverse(result);
        return result;
    }


    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(levelOrderBottom(root));
    }

//////////////////////////////////////////////////////////////

    public List<ArrayList<Integer>> levelOrderBottom2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new  ArrayList<ArrayList<Integer>>();

        if(root == null){
            return result;
        }

        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();
        current.offer(root);

        ArrayList<Integer> numberList = new ArrayList<Integer>();

        // need to track when each level starts
        while(!current.isEmpty()){
            TreeNode head = current.poll();

            numberList.add(head.val);

            if(head.left != null){
                next.offer(head.left);
            }
            if(head.right!= null){
                next.offer(head.right);
            }

            if(current.isEmpty()){
                current = next;
                next = new LinkedList<TreeNode>();
                result.add(numberList);
                numberList = new ArrayList<Integer>();
            }
        }

        //return Collections.reverse(result);
        ArrayList<ArrayList<Integer>> reversedResult = new  ArrayList<ArrayList<Integer>>();
        for(int i=result.size()-1; i>=0; i--){
            reversedResult.add(result.get(i));
        }

        return reversedResult;
    }
////////////////////////////////////////////////////////////////////////
}
/*

 */
/*
 * Given a binary tree, return the bottom-up level order
 *  traversal of its nodes' values. (ie, from left to right,
 *  level by level from leaf to root).

Example
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7


return its bottom-up level order traversal as:

[
  [15,7],
  [9,20],
  [3]
]

Tags
Queue Binary Tree Binary Tree Traversal Breadth First Search
 * */