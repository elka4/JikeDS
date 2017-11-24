package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _199_DFS_Binary_Tree_Right_Side_View_M {
    /*
    The core idea of this algorithm:

1.Each depth of the tree only select one node.

View depth is current size of result list.
     */
    public class Solution {
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


    class Solution2{
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            helper(res, root, 0);
            return res;
        }

        public void helper(List<Integer> res, TreeNode root, int level) {
            if(root == null) return;
            if(res.size() == level)
                res.add(level, root.val);
            helper(res, root.right, level + 1);
            helper(res, root.left, level + 1);
        }
    }


    public class Solution3 {
        public List<Integer> rightSideView(TreeNode root) {
            if(root == null){
                return new ArrayList<Integer>();
            }

            List<Integer> result = new ArrayList<Integer>();
            Stack<NodeWithDep> stack = new Stack<NodeWithDep>();

            NodeWithDep nwdRoot = new NodeWithDep(root, 0);
            stack.push(nwdRoot);
            while(!stack.isEmpty()){
                NodeWithDep nwd = stack.pop();
                TreeNode curr = nwd.node;
                if(nwd.depth == result.size()){
                    result.add(curr.val);
                }
                if(curr.left != null){
                    stack.push(new NodeWithDep(curr.left, nwd.depth + 1));
                }
                if(curr.right != null){
                    stack.push(new NodeWithDep(curr.right, nwd.depth + 1));
                }
            }
            return result;
        }

        class NodeWithDep{
            TreeNode node;
            int depth;
            NodeWithDep(TreeNode node, int depth){
                this.node = node;
                this.depth = depth;
            }
        }

    }
//-------------------------------------------------------------------------///

// 9Ch
public class Jiuzhang {
    public List<Integer> rightSideView(TreeNode root) {
        HashMap<Integer, Integer> depthToValue = new HashMap<Integer, Integer>();

        dfs(depthToValue, root, 1);

        int depth = 1;
        List<Integer> result = new ArrayList<Integer>();

        while (depthToValue.containsKey(depth)) {
            result.add(depthToValue.get(depth));
            depth++;
        }
        return result;
    }

    private void dfs(HashMap<Integer, Integer> depthToValue, TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        depthToValue.put(depth, node.val);

        dfs(depthToValue, node.left, depth + 1);
        dfs(depthToValue, node.right, depth + 1);
    }
}



//-------------------------------------------------------------------------///

}
/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].


 */