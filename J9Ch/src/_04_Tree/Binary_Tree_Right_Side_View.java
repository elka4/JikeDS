package _04_Tree;

import lib.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
LeetCode – Binary Tree Right Side View (Java)

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom. For example, given the following binary tree,

   1            <---
 /   \
2     3         <---
 \
  5             <---
You can see [1, 3, 5].

Analysis

This problem can be solve by using a queue. On each level of the tree, we add the right-most element to the results.
 */

public class Binary_Tree_Right_Side_View {

    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        if(root == null) return result;

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(queue.size() > 0){
            //get size here
            int size = queue.size();

            for(int i=0; i<size; i++){
                TreeNode top = queue.remove();

                //the first element in the queue (right-most of the tree)
                if(i==0){
                    result.add(top.val);
                }
                //add right first
                if(top.right != null){
                    queue.add(top.right);
                }
                //add left
                if(top.left != null){
                    queue.add(top.left);
                }
            }
        }

        return result;
    }

    @Test
    public void test01() {
        int[] arr = {1, 2, 3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.left.setRightChild(new TreeNode(5));
        root.left.setLeftChild(new TreeNode(4));
        System.out.println("root: ");
        root.print();
        System.out.println(rightSideView(root));
    }



//-----------------------------------------------------------------------------

/*    My simple accepted solution(JAVA)
    The core idea of this algorithm:

            1.Each depth of the tree only select one node.

    View depth is current size of result list.
    Here is the code:*/

    public class Solution2 {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            rightView(root, result, 0);
            return result;
        }

        public void rightView(TreeNode curr, List<Integer> result, int currDepth){
            if(curr == null){
                return;
            }
            if(currDepth == result.size()){
                result.add(curr.val);
            }

            rightView(curr.right, result, currDepth + 1);
            rightView(curr.left, result, currDepth + 1);

        }
    }


//-----------------------------------------------------------------------------

    //Reverse Level Order Traversal, java
    public class Solution3 {
        public List<Integer> rightSideView(TreeNode root) {
            // reverse level traversal
            List<Integer> result = new ArrayList();
            Queue<TreeNode> queue = new LinkedList();
            if (root == null) return result;

            queue.offer(root);
            while (queue.size() != 0) {
                int size = queue.size();
                for (int i=0; i<size; i++) {
                    TreeNode cur = queue.poll();
                    if (i == 0) result.add(cur.val);
                    if (cur.right != null) queue.offer(cur.right);
                    if (cur.left != null) queue.offer(cur.left);
                }

            }
            return result;
        }
    }


//-----------------------------------------------------------------------------

    //Java Solution using Divide and Conquer

    class Solution4{
        public List<Integer> rightSideView(TreeNode root) {
            if(root==null)
                return new ArrayList<Integer>();
            List<Integer> left = rightSideView(root.left);
            List<Integer> right = rightSideView(root.right);
            List<Integer> re = new ArrayList<Integer>();
            re.add(root.val);
            for(int i=0;i<Math.max(left.size(), right.size());i++){
                if(i>=right.size())
                    re.add(left.get(i));
                else
                    re.add(right.get(i));
            }
            return re;
        }
    }



//-----------------------------------------------------------------------------

    //Simple Java solution w/ recursion (2ms)

    public class Solution5 {
        public List<Integer> rightSideView(TreeNode root) {
            ArrayList list = new ArrayList();
            rightSideView(root, 0, list);

            return list;
        }

        public void rightSideView(TreeNode root, int level, ArrayList list) {
            if(root == null) return;

            if(list.size() == level)
                list.add(root.val);

            rightSideView(root.right, level + 1, list);
            rightSideView(root.left, level + 1, list);
        }
    }




//-----------------------------------------------------------------------------

    //Share my Java iterative solution, based on level order traversal

    public class Solution6 {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> ret = new ArrayList<Integer>();
            if(root == null) return ret;
            Queue<TreeNode> q = new LinkedList<TreeNode>();
            q.offer(root);
            while(!q.isEmpty()){
                int cnt = q.size();
                for(int i = 0;i < cnt;i++){
                    TreeNode cur = q.poll();
                    if(i == cnt-1){
                        ret.add(cur.val);
                    }
                    if(cur.left != null){
                        q.offer(cur.left);
                    }
                    if(cur.right != null){
                        q.offer(cur.right);
                    }
                }
            }
            return ret;
        }
    }



//-----------------------------------------------------------------------------

    //DFS solution better than 90% solutions!

    public class Solution7 {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            if (root == null){
                return res;
            }
            dfs (root, res, 0);
            return res;
        }

        public void dfs (TreeNode root, List<Integer> res, int level){
            if (root == null){
                return;
            }
            if (res.size() == level){
                res.add (root.val);
            }
            if (root.right != null){
                dfs (root.right, res, level + 1);
            }
            if (root.left != null){
                dfs (root.left, res, level + 1);
            }
        }
    }






//-----------------------------------------------------------------------------

        //O(n) Java Solution, I myself was asked this question by Amazon in phone interview

    class Solution8{
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new LinkedList<Integer>();
            if(root == null) return res;
            List<TreeNode> candidates = new LinkedList<TreeNode>();
            candidates.add(root);
            while(!candidates.isEmpty()) {
                List<TreeNode> temp = new LinkedList<TreeNode>();
                res.add(candidates.get(0).val);
                for(TreeNode curr : candidates) {
                    if(curr.right != null)
                        temp.add(curr.right);
                    if(curr.left != null)
                        temp.add(curr.left);
                }
                candidates = temp;
            }
            return res;
        }

    }

    //A variation of this question is to print a tree one level at a time.



//-----------------------------------------------------------------------------

    //JAVA solution using recursion


    public class Solution9 {


        public List<Integer> rightSideView(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            if (root == null) return result;
            return helper(root, result, 0);
        }

        public List<Integer> helper(TreeNode root, ArrayList<Integer> result,
                                    int height) {
            if (height == result.size()) {
                result.add(root.val);
            }

            if (root.right != null) {
                helper(root.right, result, height + 1);

            }
            if (root.left != null) {
                helper(root.left, result, height + 1);
            }
            return result;
        }
    }

   /* Comments: height == result.size() is the core part in this recursion, it limits the amount of Node add to the result in each level(height) of the Tree.

    Some thoughts: If the questions is asking for a left view of the binary tree, just swap the order of

if (root.right != null) {
        helper(root.right, result, height + 1);

    }
    and

if (root.left != null) {
        helper(root.left, result, height + 1);
    }
    Moreover, if it's asking of the "x-ray view" of the binary tree, for example, display the second element from the right view(given a valid tree). The solution could be adding a counter inside

            if (height == result.size()) {
        result.add(root.val);
    }
    and keep track of the counter.


*/


//-----------------------------------------------------------------------------

    //Simple Java solution with BFS

    public class Solution10 {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            if(root==null) return result;
            Queue<TreeNode> q = new LinkedList<TreeNode>();
            q.add(root);
            while(q.size()>0){
                int size = q.size();
                for(int i=0;i<size;i++){
                    TreeNode node= q.poll();
                    if(i==size-1)
                        result.add(node.val);
                    if(node.left!=null) q.add(node.left);
                    if(node.right!=null) q.add(node.right);
                }
            }
            return result;
        }
    }




//-----------------------------------------------------------------------------




//-----------------------------------------------------------------------------




//-----------------------------------------------------------------------------




//-----------------------------------------------------------------------------




//-----------------------------------------------------------------------------





}
