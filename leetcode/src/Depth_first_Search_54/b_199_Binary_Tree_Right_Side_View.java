package Depth_first_Search_54;
import java.util.*;
/**
 * Created by tianhuizhu on 6/19/17.
 */
public class b_199_Binary_Tree_Right_Side_View {

//1. My simple accepted solution(JAVA)
public class Solution1 {
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
//2. My C++ solution, modified preorder traversal
//3. Reverse Level Order Traversal, java
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
//4. Simple C++ solution (BTW: I like clean codes)
//5. Java Solution using Divide and Conquer
    class solution5{
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
}
