package _04_Tree._Validate;
import java.util.LinkedList;
import java.util.Stack;

//  331. Verify Preorder Serialization of a Binary Tree
//  https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/description/
//
public class _331_Verify_Preorder_Serialization_of_a_Binary_Tree {

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



//------------------------------------------------------------------------------

    //7 lines Easy Java Solution
    /*Some used stack. Some used the depth of a stack. Here I use a different perspective. In a binary tree, if we consider null as leaves, then

    all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
    all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
    Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree - indegree. When the next node comes, we then decrease diff by 1, because the node provides an in degree. If the node is not null, we increase diff by 2, because it provides two out degrees. If a serialization is correct, diff should never be negative and diff will be zero when finished.
*/
    public boolean isValidSerialization2(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for (String node: nodes) {
            if (--diff < 0) return false;
            if (!node.equals("#")) diff += 2;
        }
        return diff == 0;
    }


//------------------------------------------------------------------------------

    //Java intuitive 22ms solution with stack
    //See detailed comments below. Time complexity is O(n), space is also O(n) for the stack.
    public boolean isValidSerialization3(String preorder) {
        // using a stack, scan left to right
        // case 1: we see a number, just push it to the stack
        // case 2: we see #, check if the top of stack is also #
        // if so, pop #, pop the number in a while loop, until top of stack is not #
        // if not, push it to stack
        // in the end, check if stack size is 1, and stack top is #
        if (preorder == null) {
            return false;
        }
        Stack<String> st = new Stack<>();
        String[] strs = preorder.split(",");
        for (int pos = 0; pos < strs.length; pos++) {
            String curr = strs[pos];
            while (curr.equals("#") && !st.isEmpty() && st.peek().equals(curr)) {
                st.pop();
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
            st.push(curr);
        }
        return st.size() == 1 && st.peek().equals("#");
    }

//------------------------------------------------------------------------------

    //JAVA, Counting Indegree and Outdegree, SIMPLE & CLEAR!
    public boolean isValidSerialization4(String preorder) {
        String[] strs = preorder.split(",");
        int degree = -1;         // root has no indegree, for compensate init with -1
        for (String str: strs) {
            degree++;             // all nodes have 1 indegree (root compensated)
            if (degree > 0) {     // total degree should never exceeds 0
                return false;
            }
            if (!str.equals("#")) {// only non-leaf node has 2 outdegree
                degree -= 2;
            }
        }
        return degree == 0;
    }

//------------------------------------------------------------------------------

    public boolean isValidSerialization5(String preorder) {
        int need = 1;
        for (String val : preorder.split(",")) {
            if (need == 0)
                return false;
            need -= " #".indexOf(val);
        }
        return need == 0;
    }


//------------------------------------------------------------------------------

    //2 lines Java using Regex

    public boolean isValidSerialization6(String preorder) {
        String s = preorder.replaceAll("\\d+,#,#", "#");
        return s.equals("#") || !s.equals(preorder) && isValidSerialization(s);
    }


//------------------------------------------------------------------------------

    //Java recursion solution

    //Main idea is checking balance of nodes from bottom-up manner and bubble up failure condition.
    public boolean isValidSerialization7(String preorder) {
        String[] tree = preorder.split(",");
        return valid(tree, 0) == tree.length-1;
    }

    private int valid(String[] tree, int current) {
        if(current >= tree.length) return -1;
        if("#".equals(tree[current])) return current;

        // left
        int next = valid(tree, current + 1);
        if(next == -1) return -1;

        // right
        next = valid(tree, next + 1);
        return next == -1 ? -1 : next;
    }

//------------------------------------------------------------------------------

    //lean sample JAVA solution with explain

    public boolean isValidSerialization8(String preorder) {
        String[] chars = preorder.split(",");

        int sentinel = 0;
        int node = 0;
        for (int i = chars.length - 1; i >= 0; i--)
        {
            if (chars[i].equals("#"))
            {
                sentinel++;
            }
            else
            {
                node++;
            }
            if (sentinel - node < 1)
            {
                return false;
            }
        }
        return sentinel - node == 1;
    }

    /*
    Total quantity of sentinel is always nodes' quantity plus one.
Each time when adding a node to the tree, one more sentinel must be added some where after the node. (image a node replaced a sentinel, but it self has two sentinel)
browse the string backward follow rule 1 and 2.
     */
//------------------------------------------------------------------------------
    // 9Ch
    public class Jiuzhang {
        public boolean isValidSerialization(String preorder) {
            String s = preorder;
            boolean flag = true;
            while (s.length() > 1) {
                int index = s.indexOf(",#,#");
                if (index < 0) {
                    flag = false;
                    break;
                }
                int start = index;
                while (start > 0 && s.charAt(start - 1) != ',')
                {
                    start --;
                }
                if (s.charAt(start) == '#') {
                    flag = false;
                    break;
                }
                s = s.substring(0, start) + s.substring(index + 3);
            }
            if (s.equals("#") && flag)
                return true;
            else
                return false;
        }
    }

//------------------------------------------------------------------------------
}
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

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false


Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
 */

