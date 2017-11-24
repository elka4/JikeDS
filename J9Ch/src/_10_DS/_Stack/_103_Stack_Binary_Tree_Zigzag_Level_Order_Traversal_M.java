package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
import lib.*;

public class _103_Stack_Binary_Tree_Zigzag_Level_Order_Traversal_M {
//    My accepted JAVA solution
    public class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root)
        {
            List<List<Integer>> sol = new ArrayList<>();
            travel(root, sol, 0);
            return sol;
        }

        private void travel(TreeNode curr, List<List<Integer>> sol, int level)
        {
            if(curr == null) return;

            if(sol.size() <= level)
            {
                List<Integer> newLevel = new LinkedList<>();
                sol.add(newLevel);
            }

            List<Integer> collection  = sol.get(level);
            if(level % 2 == 0) collection.add(curr.val);
            else collection.add(0, curr.val);

            travel(curr.left, sol, level + 1);
            travel(curr.right, sol, level + 1);
        }
    }
//    O(n) solution by using LinkedList along with ArrayList. So insertion in the inner list and outer list are both O(1),
//    Using DFS and creating new lists when needed.



//    A concise and easy understanding Java solution
    public class Solution2 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if(root == null) return res;

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            boolean order = true;
            int size = 1;

            while(!q.isEmpty()) {
                List<Integer> tmp = new ArrayList<>();
                for(int i = 0; i < size; ++i) {
                    TreeNode n = q.poll();
                    if(order) {
                        tmp.add(n.val);
                    } else {
                        tmp.add(0, n.val);
                    }
                    if(n.left != null) q.add(n.left);
                    if(n.right != null) q.add(n.right);
                }
                res.add(tmp);
                size = q.size();
                order = order ? false : true;
            }
            return res;
        }
    }



    //JAVA Double Stack Solution
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        TreeNode c=root;
        List<List<Integer>> ans =new ArrayList<List<Integer>>();
        if(c==null) return ans;
        Stack<TreeNode> s1=new Stack<TreeNode>();
        Stack<TreeNode> s2=new Stack<TreeNode>();
        s1.push(root);
        while(!s1.isEmpty()||!s2.isEmpty())
        {
            List<Integer> tmp=new ArrayList<Integer>();
            while(!s1.isEmpty())
            {
                c=s1.pop();
                tmp.add(c.val);
                if(c.left!=null) s2.push(c.left);
                if(c.right!=null) s2.push(c.right);
            }
            ans.add(tmp);
            tmp=new ArrayList<Integer>();
            while(!s2.isEmpty())
            {
                c=s2.pop();
                tmp.add(c.val);
                if(c.right!=null)s1.push(c.right);
                if(c.left!=null)s1.push(c.left);
            }
            if(!tmp.isEmpty()) ans.add(tmp);
        }
        return ans;
    }
///////////////////////////////////
public class Jiuzhang {
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (root == null) {
            return result;
        }

        Stack<TreeNode> currLevel = new Stack<TreeNode>();
        Stack<TreeNode> nextLevel = new Stack<TreeNode>();
        Stack<TreeNode> tmp;

        currLevel.push(root);
        boolean normalOrder = true;

        while (!currLevel.isEmpty()) {
            ArrayList<Integer> currLevelResult = new ArrayList<Integer>();

            while (!currLevel.isEmpty()) {
                TreeNode node = currLevel.pop();
                currLevelResult.add(node.val);

                if (normalOrder) {
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                }
            }

            result.add(currLevelResult);
            tmp = currLevel;
            currLevel = nextLevel;
            nextLevel = tmp;
            normalOrder = !normalOrder;
        }

        return result;

    }
}
//---------------------------------//////////
}
/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */