package _04_Tree._BST;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
import java.util.*;

//  99. Recover Binary Search Tree
//  https://leetcode.com/problems/recover-binary-search-tree/description/
//  Tree, DFS
//  6:
public class _099_Recover_Binary_Search_Tree_H {

//-------------------------------------------------------------------------------
    //1
/*
No Fancy Algorithm, just Simple and Powerful In-Order Traversal
This question appeared difficult to me but it is really just a simple in-order traversal! I got really frustrated when other people are showing off Morris Traversal which is totally not necessary here.

Let's start by writing the in order traversal:

private void traverse (TreeNode root) {
if (root == null)
return;
traverse(root.left);
// Do some business
traverse(root.right);
}
So when we need to print the node values in order, we insert System.out.println(root.val) in the place of "Do some business".

What is the business we are doing here?
We need to find the first and second elements that are not in order right?

How do we find these two elements? For example, we have the following tree that is printed as in order traversal:

6, 3, 4, 5, 2

We compare each node with its next one and we can find out that 6 is the first element to swap because 6 > 3 and 2 is the second element to swap because 2 < 5.

Really, what we are comparing is the current node and its previous node in the "in order traversal".

Let us define three variables, firstElement, secondElement, and prevElement. Now we just need to build the "do some business" logic as finding the two elements. See the code below:
*/
    public class Solution {
        TreeNode firstElement = null;
        TreeNode secondElement = null;
        // The reason for this initialization is to avoid null pointer exception
        // in the first comparison when prevElement has not been initialized
        TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

        public void recoverTree(TreeNode root) {
            traverse(root);// In order traversal to find the two elements
            // Swap the values of the two nodes
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }

        //InOrder
        private void traverse(TreeNode root) {
            if (root == null) return;
            traverse(root.left);

            /*if(pre!=null && pre.val > root.val){
                if(first==null){first = pre;second = root;}
                else{second = root;}
            }*/

            // Start of "do some business",
            // If first element has not been found, assign it to prevElement
            // (refer to 6 in the example above)
            if (firstElement == null && prevElement.val >= root.val) {
                firstElement = prevElement;
            }

            // If first element is found, assign the second element to the root
            // (refer to 2 in the example above)
            if (firstElement != null && prevElement.val >= root.val) {
                secondElement = root;
            }
            prevElement = root;
            // End of "do some business"

            /*if(pre!=null && pre.val > root.val){
                if(first==null){first = pre;second = root;}
                else{second = root;}
            }*/

            if(prevElement!=null && prevElement.val > root.val){
                if(firstElement==null){
                    firstElement = prevElement;
                    secondElement = root;}
                else{secondElement = root;}
            }

            traverse(root.right);
        }
    }

    @Test
    public void test01() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        new Solution().recoverTree(root);
        root.print();
    }


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution11 {
        TreeNode first = null;
        TreeNode secondElement = null;
        TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

        public void recoverTree(TreeNode root) {
            traverse(root);
            int temp = first.val;
            first.val = secondElement.val;
            secondElement.val = temp;
        }
        //InOrder
        private void traverse(TreeNode root) {
            if (root == null) return;
            traverse(root.left);
            if(prevElement!=null && prevElement.val > root.val){
                if(first==null){
                    first = prevElement;
                    secondElement = root;
                }
                else{
                    secondElement = root;
                }
            }
            prevElement = root;
            traverse(root.right);
        }
    }

//---------------------------------------------------------------------------------
    //2
    //morrisTraversal 是不用空间的遍历，不需要stack不需要recursion
    //因为morrisTraversal可以在一些地方进行操作从而成为inorder traversion，那么就可以用来inorder的找到错位的两个node
/*
Detail Explain about How Morris Traversal Finds two Incorrect Pointer
To understand this, you need to first understand Morris Traversal or Morris Threading Traversal.
It take use of leaf nodes' right/left pointer to achieve O(1) space Traversal on a Binary Tree.
Below is a standard Inorder Morris Traversal, referred from
http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html (a Chinese Blog, while the graphs are great for illustration)
*/

    public void morrisTraversal(TreeNode root){
        TreeNode temp = null;
        while(root!=null){
            if(root.left!=null){
                // connect threading for root
                temp = root.left;
                while(temp.right!=null && temp.right != root)
                    temp = temp.right;
                // the threading already exists
                if(temp.right!=null){
                    temp.right = null;
                    System.out.println(root.val);       //inorder
                    root = root.right;
                }else{
                    // construct the threading
                     temp.right = root;
                     root = root.left;
                }
            }else{
                System.out.println(root.val);       //inorder
                root = root.right;
            }
        }
    }

    @Test
    public void test02() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        morrisTraversal(root);
        root.print();
    }
    /*
    root:
   7
  / \
 /   \
 9   5
/ \
4 6
     */
    //



/*
In the above code, System.out.println(root.val);appear twice, which functions as outputing the Node in ascending order (BST). Since these places are in order, replace them with

if(pre!=null && pre.val > root.val){
    if(first==null){first = pre;second = root;}
    else{second = root;}
}
pre = root;

each time, the pre node and root are in order as System.out.println(root.val); outputs them in order.

Then, come to how to specify the first wrong node and second wrong node.

When they are not consecutive, the first time we meet pre.val > root.val ensure us the first node is the pre node, since root should be traversal ahead of pre, pre should be at least at small as root. The second time we meet pre.val > root.val ensure us the second node is the root node, since we are now looking for a node to replace with out first node, which is found before.

When they are consecutive, which means the case pre.val > cur.val will appear only once. We need to take case this case without destroy the previous analysis. So the first node will still be pre, and the second will be just set to root. Once we meet this case again, the first node will not be affected.

Below is the updated version on Morris Traversal.
*/

    public void morrisTraversal2(TreeNode root){
        TreeNode temp = null;

        while(root!=null){
            if(root.left!=null){
                // connect threading for root
                temp = root.left;
                while(temp.right!=null && temp.right != root)
                    temp = temp.right;
                // the threading already exists
                if(temp.right!=null){
                    temp.right = null;
                    System.out.println(root.val);       //inorder
                    root = root.right;
                }else{
                    // construct the threading
                    temp.right = root;
                    root = root.left;
                }
            }else{
                System.out.println(root.val);       //inorder
                root = root.right;
            }
        }
    }
    //这个就是建立在morrisTraversal基础上的inorder traverse，用来找错位的两个node
    public void recoverTree(TreeNode root) {
        TreeNode pre = null;                                    //TreeNode temp = null;
        TreeNode first = null, second = null, temp = null;

        while(root!=null){                                      //while(root!=null){
            if(root.left!=null){                                //if(root.left!=null){
                // connect threading for root
                temp = root.left;                               //temp = root.left;
                while(temp.right!=null && temp.right != root)
                    temp = temp.right;
                // the threading already exists
                if(temp.right!=null){
                    if(pre!=null && pre.val > root.val){
                        if(first==null){
                            first = pre;
                            second = root;
                        }
                        else{
                            second = root;
                        }
                    }
                    pre = root;

                    temp.right = null;
                    root = root.right;
                }else{// construct the threading
                    temp.right = root;
                    root = root.left;
                }
            }else{
                if(pre!=null && pre.val > root.val){
                    if(first==null){
                        first = pre;
                        second = root;
                    }
                    else{
                        second = root;
                    }
                }
                pre = root;
                root = root.right;
            }
        }

        // swap two node values;
        if(first!= null && second != null){
            int t = first.val;
            first.val = second.val; second.val = t;
        }
    }

/*    private void help(TreeNode pre, TreeNode root, TreeNode first, TreeNode second){
        if(pre!=null && pre.val > root.val){
            if(first==null){
                first = pre;
                second = root;
            }
            else{
                second = root;
            }
        }
        pre = root;
    }*/

    @Test
    public void test0() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        recoverTree(root);
        root.print();
    }
    /*
    root:
                   7
                  / \
                 /   \
                 9   5
                / \
                4 6

                   7
                  / \
                 /   \
                 5   9
                / \
                4 6
     */
//---------------------------------------------------------------------------------
    //3
    class Recover_BST {
        TreeNode first;
        TreeNode second;
        TreeNode pre;

        public void recoverTree(TreeNode root) {
            if(root==null) return;
            inorder(root);
            if(second!=null && first !=null){
                int val = second.val;
                second.val = first.val;
                first.val = val;
            }
        }

        public void inorder(TreeNode root){
            if(root==null) return;
            inorder(root.left);
            if(pre==null){
                pre=root;
            }else{
                if(root.val<pre.val){
                    if(first==null){
                        first=pre;
                    }
                    second=root;
                }
                pre=root;
            }
            inorder(root.right);
        }
    }

    @Test
    public void test04() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        new Recover_BST().recoverTree(root);
        root.print();
    }

//---------------------------------------------------------------------------------
    //4
    // 9Ch

    //http://www.cnblogs.com/yuzhangcmu/p/4208319.html
    //http://blog.sina.com.cn/s/blog_eb52001d0102v1z3.html
    public class Jiuzhang {
        private TreeNode firstElement = null;
        private TreeNode secondElement = null;
        private TreeNode lastElement = new TreeNode(Integer.MIN_VALUE);

        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            traverse(root.left);
            if (firstElement == null && root.val < lastElement.val) {
                firstElement = lastElement;
            }
            if (firstElement != null && root.val < lastElement.val) {
                secondElement = root;
            }
            lastElement = root;
            traverse(root.right);
        }

        public void recoverTree(TreeNode root) {
            // traverse and get two elements
            traverse(root);
            // swap
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }
    }

    @Test
    public void test05() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        new Jiuzhang().recoverTree(root);
        root.print();
    }
//---------------------------------------------------------------------------------
//5
/*
 SOLUTION 1:
采用递归+全局变量完成：

空间复杂度是O(logn)

REF: http://huntfor.iteye.com/blog/2077665
这一篇讲得蛮清楚：
http://yucoding.blogspot.com/2013/03/leetcode-question-75-recover-binary.html

具体的思路，还是通过中序遍历，只不过，不需要存储每个节点，只需要存一个前驱即可。

例如1,4,3,2,5,6

1.当我们读到4的时候，发现是正序的，不做处理

2.但是遇到3时，发现逆序，将4存为第一个错误节点，3存为第二个错误节点

3.继续往后，发现3，2又是逆序了，那么将第2个错误节点更新为2

如果是这样的序列：1,4,3,5,6同上，得到逆序的两个节点为4和3。

========================================

这里我们补充一下，为什么要替换第二个节点而不是第一个节点：
e.g. The correct BST is below:
【LeetCode】Recover <wbr>Binary <wbr>Search <wbr>Tree
The inorder traversal is :  1 3 4 6 7 8 10 13 14

Find the place which the order is wrong.
        Wrong order: 1 3 8 6 7 4 10 13 14
        FIND:                    8 6
        Then we find:             7 4
        8, 6 是错误的序列, 但是，7，4也是错误的序列。
        因为8，6前面的序列是正确的，所以8，6一定是后面的序列交换来的。
        而后面的是比较大的数字，也就是说8一定是被交换过来的。而7，4
        中也应该是小的数字4是前面交换过来的。

        用反证法来证明：
        假设：6是后面交换过来的
        推论: 那么8比6还大，那么8应该也是后面交换来的，
        这样起码有3个错误的数字了
        而题目是2个错误的数字，得证，只应该是8是交换过来的。
结论就是：我们需要交换的是：8， 4.
 */

    public class RecoverTree {
        TreeNode pre = null;
        TreeNode first = null;
        TreeNode second = null;


        public void recoverTree(TreeNode root) {
            inOrder(root);

            // swap the value of first and second node.
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }

        public void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            // inorder traverse.
            inOrder(root.left);

        /*
        Find the place which the order is wrong.
        For example: 1 3 4 6 7 8 10 13 14
        Wrong order: 1 3 8 6 7 4 10 13 14
        FIND:            ___
        Then we find:        ___
        8, 6 是错误的序列, 但是，7，4也是错误的序列。
        因为8，6前面的序列是正确的，所以8，6一定是后面的序列交换来的。
        而后面的是比较大的数字，也就是说8一定是被交换过来的。而7，4
        中也应该是小的数字4是前面交换过来的。

        用反证法来证明：
        假设：6是后面交换过来的
        推论: 那么8比6还大，那么8应该也是后面交换来的，
        这样起码有3个错误的数字了
        而题目是2个错误的数字，得证，只应该是8是交换过来的。
        */

            // 判断 pre 是否已经设置
            if (pre != null && pre.val > root.val) {
                if (first == null) {
                    // 首次找到反序.
                    first = pre;
                    second = root;
                } else {
                    // 第二次找到反序，更新Second.
                    second = root;
                }
            }

            pre = root;

            // inorder traverse.
            inOrder(root.right);
        }

    //---------------------------------------------------------------------------------
    //6
    /* 使用Stack的Inorder Traverse
    SOLUTION 2:
    也可以采用非递归方法，不需要加全局变量，空间复杂度是O(logn)：
     */
        public void recoverTree6(TreeNode root) {
            if (root == null) {
                return;
            }
            TreeNode node1 = null;
            TreeNode node2 = null;
            TreeNode pre = null;
            Stack<TreeNode> s = new Stack<TreeNode>();
            TreeNode cur = root;

            while (true) {
                while (cur != null) {
                    s.push(cur);
                    cur = cur.left;
                }
                if (s.isEmpty()) {
                    break;
                }
                TreeNode node = s.pop();

                if (pre != null) {
                    // invalid order
                    if (pre.val > node.val) {
                        if (node1 == null) {
                            node1 = pre;
                            node2 = node;
                        } else {
                            node2 = node;
                        }
                    }
                }
                pre = node;
                cur = node.right;
            }

            int tmp = node1.val;
            node1.val = node2.val;
            node2.val = tmp;

            return;
        }
    }

//---------------------------------------------------------------------------------

//7
/*
SOLUTION 3:
还有更厉害的作法，可以达到O(1)的空间复杂度。以后再补上。
 */


//---------------------------------------------------------------------------------




//---------------------------------------------------------------------------------
}
/*
LeetCode – Recover Binary Search Tree (Java)

Two elements of a binary search tree (BST) are swapped by mistake. Recover the tree without changing its structure.

Java Solution

Inorder traveral will return values in an increasing order. So if an element is less than its previous element,the previous element is a swapped node.
 */

/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

 */