package _04_Tree._Traverse;

import lib.TreeNode;

import java.util.Stack;


//
//
//
public class _173_Tree_Binary_Search_Tree_Iterator_M {


    public class BSTIterator {
        private Stack<TreeNode> stack = new Stack<TreeNode>();
        public BSTIterator(TreeNode root) { pushAll(root);
        }
        /** @return whether we have a next smallest number */
        public boolean hasNext() { return !stack.isEmpty();
        }
        /** @return the next smallest number */
        public int next() {
            TreeNode tmpNode = stack.pop(); pushAll(tmpNode.right);
            return tmpNode.val;
        }
        private void pushAll(TreeNode node) {
            for (; node != null; stack.push(node), node = node.left);
        }
    }


    public class BSTIterator2 {

        private Stack<TreeNode> stack;
        public BSTIterator2(TreeNode root) {
            stack = new Stack<>();
            TreeNode cur = root;
            while(cur != null){
                stack.push(cur);
                if(cur.left != null)
                    cur = cur.left;
                else
                    break;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node = stack.pop();
            TreeNode cur = node;
            // traversal right branch
            if(cur.right != null){
                cur = cur.right;
                while(cur != null){
                    stack.push(cur);
                    if(cur.left != null)
                        cur = cur.left;
                    else
                        break;
                }
            }
            return node.val;
        }
    }

}
/*

 */
/*

 */
