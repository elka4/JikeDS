package _04_Tree._Build;
import lib.TreeNode;
import org.junit.Test;
import java.util.*;

//  105. Construct Binary Tree from Preorder and Inorder Traversal
//  https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
//  http://www.lintcode.com/zh-cn/problem/construct-binary-tree-from-preorder-and-inorder-traversal/
//  Array Tree Depth-first Search
//  _106_Tree_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_M
//  10

public class _105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_M {
//--------------------------------------------------------------------------------
/*
思路：

Binary tree一共3种访问顺序：preorder, inorder, postorder。这道题目的推广就是给定一个binary tree的两种访问顺序，重构该binary tree。这类题目给定的两个排序中，如果包括了inorder，且没有重复元素，就非常好解了。

解这类题有两个关键点：

1. 在inorder中寻找root的位置，从而从序列中分割出左右子树。

Inorder:     left subtree | root | right subtree
Preorder:   root | left subtree | right subtree
Postorder: left subtree | right subtree | root

可见root是preorder序列的第一个节点，也是postorder的最后一个节点。所以给定这两个序列的任意一个我们即知道了root->val。通过搜索inorder序列，可以定位root所在的位置，从而也得到了left subtree和right subtree的节点数。

2. 递归构建

当root，left/right subtree都确定后
root->left = construct(inorder(left subtree), preorder(left subtree))
root->right = construct(inorder(right subtree), preorder(right subtree))

*/

/*
这题跟上面那题类似，通过前序遍历和中序遍历的结果构造二叉树，我们只需要知道前序遍历的第一个值就是根节点，那么仍然可以采用上面提到的方式处理：

通过前序遍历找到根节点
通过根节点将中序遍历数据拆分成两部分
对于各个部分重复上述操作

可以看到，这两道题目，只要能清楚了树的几种遍历方式，以及找到如何找到根节点，并通过中序遍历拆分成两个子树，就能很容易的搞定了，唯一需要注意的是写代码的时候拆分索引位置要弄对。

*/


    /*      http://fisherlei.blogspot.com/2013/01/leetcode-construct-binary-tree-from.html
    [Thoughts]

There is an example.
        _______7______
       /              \
    __10__          ___2
   /      \        /
   4       3      _8
            \    /
             1  11
The preorder and inorder traversals for the binary tree above is:
preorder = {7,10,4,3,1,2,8,11}
inorder = {4,10,3,1,7,11,8,2}

The first node in preorder alwasy the root of the tree. We can break the tree like:
1st round:
preorder:  {7}, {10,4,3,1}, {2,8,11}
inorder:     {4,10,3,1}, {7}, {11, 8,2}

        _______7______
       /              \
    {4,10,3,1}       {11,8,2}
Since we alreay find that {7} will be the root, and in "inorder" sert, all the data in the left of {7} will construct the left sub-tree. And the right part will construct a right sub-tree. We can the left and right part agin based on the preorder.
2nd round
left part                                                                            right part
preorder: {10}, {4}, {3,1}                                              {2}, {8,11}
inorder:  {4}, {10}, {3,1}                                                {11,8}, {2}


        _______7______
       /              \
    __10__          ___2
   /      \        /
   4      {3,1}   {11,8}
see that, {10} will be the root of left-sub-tree and {2} will be the root of right-sub-tree.

Same way to split {3,1} and {11,8}, yo will get the complete tree now.

        _______7______
       /              \
    __10__          ___2
   /      \        /
   4       3      _8
            \    /
             1  11
So, simulate this process from bottom to top with recursion as following code.
     */
//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
    //1
    // jiuzhang

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (inorder.length != preorder.length) {
            return null;
        }
        return myBuildTree(inorder, 0, inorder.length - 1,
                preorder, 0, preorder.length - 1);
    }
    //inorder = {4,10,3,1,7,11,8,2}
    //preorder = {7,10,4,3,1,2,8,11}
    private TreeNode myBuildTree(int[] inorder, int instart, int inend,
                                 int[] preorder, int prestart, int preend) {
        if (instart > inend) {
            return null;
        }
        //preorder的第一个就是root
        TreeNode root = new TreeNode(preorder[prestart]);

        //找到root在inorder array中的位置
        int position = findPosition(inorder, instart, inend, preorder[prestart]);

        //inorder:   {4,10,3,1}, {7}, {11,8,2}
        //preorder:  {7}, {10,4,3,1}, {2,8,11}

        root.left = myBuildTree(inorder, instart, position - 1, //{4,10,3,1}
        preorder, prestart + 1, prestart + position - instart);//{10,4,3,1}

        root.right = myBuildTree(inorder, position + 1, inend,  //{11,8,2}
        preorder, preend + position - inend + 1, preend);//{2,8,11}

        return root;
    }

    private int findPosition(int[] arr, int start, int end, int key) {
	    int i;
	    for (i = start; i <= end; i++) {
	        if (arr[i] == key) {
	            return i;
	        }
	    }
	    return -1;
	}

	@Test
    public void test01(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree1(preorder, inorder).print();
    }

//----------------------------------------------------------------------------
    //2
    //My Accepted Java Solution
    //这个比上面的还简单一些，关键就是其实是不需要preorder的end index。
    //因为看上面的代码，唯一的最关键的就是要找到preorder[prestart]，因为这个就是root。
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder

        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper(preStart + 1, inStart, inIndex - 1,
                preorder, inorder);

        root.right = helper(preStart + 1 + inIndex - inStart, inIndex + 1, inEnd,
                preorder, inorder);

        return root;
    }

    @Test
    public void test02(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree2(preorder, inorder).print();
    }
//----------------------------------------------------------------------------
    //3
    // 5ms Java Clean Solution with Caching
    //只是改用HashMap存储value和index，不用for loop。这样会更快啊！！！

        /*
        5ms Java Clean Solution with Caching
        In this questions, most of people just loop through inorder[] to find the root.
        However, by caching positions of inorder[] indices using a HashMap,
        the run time can drop from 20ms to 5ms.

        Here is my 5ms AC solution:
     */

    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        TreeNode root = buildTree3(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1, inMap);
        return root;
    }

    public TreeNode buildTree3(int[] preorder, int preStart, int preEnd,
                               int[] inorder,  int inStart,  int inEnd,
                               Map<Integer, Integer> inMap) {
        if(preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);

        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildTree3(preorder, preStart + 1, preStart + numsLeft,
                                inorder, inStart, inRoot - 1, inMap);

        root.right = buildTree3(preorder, preStart + numsLeft + 1, preEnd,
                        inorder, inRoot + 1, inEnd, inMap);

        return root;
    }

    @Test
    public void test03(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree2(preorder, inorder).print();
    }
//-------------------------------------------------------------------------
    //4
    public TreeNode buildTree4(int[] preorder, int[] inorder) {
        int preStart = 0;
        int preEnd = preorder.length-1;
        int inStart = 0;
        int inEnd = inorder.length-1;

        return construct(preorder, preStart, preEnd, inorder, inStart, inEnd);
    }

    public TreeNode construct(int[] preorder, int preStart, int preEnd,
                              int[] inorder, int inStart, int inEnd){

        if(preStart>preEnd||inStart>inEnd){
            return null;
        }

        int val = preorder[preStart];
        TreeNode p = new TreeNode(val);

        //find parent element index from inorder
        int k=0;
        for(int i=0; i<inorder.length; i++){
            if(val == inorder[i]){
                k=i;
                break;
            }
        }

        p.left = construct(preorder, preStart+1,
                preStart+(k-inStart), inorder, inStart, k-1);

        p.right= construct(preorder, preStart+(k-inStart)+1,
                preEnd, inorder, k+1 , inEnd);

        return p;
    }

    @Test
    public void test04(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree4(preorder, inorder).print();
    }


//-------------------------------------------------------------------------
    //5
    //Concise recursive Java code by making slight modification to the previous problem.
    //这个也是省略了preend
    public  TreeNode buildTree5(int[] preorder, int[] inorder) {
        return helper(preorder, 0, inorder, 0, inorder.length - 1);
    }

    public TreeNode helper(int[] preorder, int preStart, int[] inorder, int inStart, int inEnd) {
        if (preStart > preorder.length - 1 || inStart > inEnd)
            return null;

        TreeNode root = new TreeNode(preorder[preStart]);

        int target = inStart;
        while (inorder[target] != preorder[preStart]) target++;//这种方法还挺特别啊

        root.left = helper(preorder, preStart + 1, inorder, inStart, target - 1);

        root.right = helper(preorder, preStart + target - inStart + 1,
                        inorder, target + 1, inEnd);

        return root;
    }

    @Test
    public void test05(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree5(preorder, inorder).print();
    }
//-------------------------------------------------------------------------
    //6
    /*
        My Accepted Java Solution
        Hi guys, this is my Java solution. I read this post, which is very helpful.

        The basic idea is here:
        Say we have 2 arrays, PRE and IN.
        Preorder traversing implies that PRE[0] is the root node.
        Then we can find this PRE[0] in IN, say it's IN[5].
        Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side,
        IN[6] to the end is on the right side.
        Recursively doing this on subarrays, we can build a tree out of it :)

        Hope this helps.
     */
    public TreeNode buildTree6(int[] preorder, int[] inorder) {
        return helper6(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper6(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper6(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper6(preStart + inIndex - inStart + 1, inIndex + 1,
                inEnd, preorder, inorder);
        return root;
    }

    @Test
    public void test06(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree6(preorder, inorder).print();
    }

//-------------------------------------------------------------------------
    //7
    //Here is the iterative solution in Java
    //唯一一个iterative的做法，有点意思啊
    public TreeNode buildTree8(int[] preorder, int[] inorder) {
        if (inorder.length==0) return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        stack.push(root);
        int i=0, j=0;
        TreeNode node = null;
        TreeNode cur = root;

        while (j<inorder.length){
            if (stack.peek().val == inorder[j]){
                node = stack.pop();
                j++;
            }
            else if (node!=null){
                cur = new TreeNode(preorder[i]);
                node.right = cur;
                node=null;
                stack.push(cur);
                i++;
            }
            else {
                cur = new TreeNode(preorder[i]);
                stack.peek().left = cur;
                stack.push(cur);
                i++;
            }
        }
        return root.left;
    }

    @Test
    public void test08(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree8(preorder, inorder).print();
    }

//-------------------------------------------------------------------------
    //9
    //Concise Java Recursive Solution
    //真牛逼啊真牛逼， 不过本质和上面的还是一样，只不过不使用index作为参数
    public TreeNode buildTree9(int[] preorder, int[] inorder) {
        if(preorder==null || inorder==null || inorder.length==0 || preorder.length==0)
            return null;
        TreeNode root = new TreeNode(preorder[0]);
        if(preorder.length==1) return root;
        int breakindex = -1;
        for(int i=0;i<inorder.length;i++) {
            if(inorder[i]==preorder[0]) {
                breakindex=i;
                break;
            }
        }
        int[] subleftpre  = Arrays.copyOfRange(preorder,1,breakindex+1);
        int[] subleftin   = Arrays.copyOfRange(inorder,0,breakindex);
        int[] subrightpre = Arrays.copyOfRange(preorder,breakindex+1,preorder.length);
        int[] subrightin  = Arrays.copyOfRange(inorder,breakindex+1,inorder.length);

        root.left  = buildTree9(subleftpre,subleftin);
        root.right = buildTree9(subrightpre,subrightin);
        return root;
    }

    @Test
    public void test09(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree9(preorder, inorder).print();
    }

//-------------------------------------------------------------------------
    //10
    //Recursive solution in Java
    //This question is similar to the one using postorder and inorder arrays.
    // Once one notices that the first element of preorder is the root node,
    // the rest is fairly straightforward.
    public TreeNode buildTree10(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        return buildTree10(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode buildTree10(int[] preorder, int[] inorder,
                                 int preorderIndex, int start, int end) {
        if (start > end) return null;
        TreeNode node = new TreeNode(preorder[preorderIndex]);

        int inorderIndex = findInorderIndex(inorder, start, end, preorder[preorderIndex]);
        int leftTreeSize = inorderIndex - start;
        int rightTreeSize = end - inorderIndex;

        node.left = buildTree10(preorder, inorder,
                preorderIndex + 1, start, inorderIndex - 1);

        node.right = buildTree10(preorder, inorder,
                preorderIndex + leftTreeSize + 1, inorderIndex + 1, end);

        return node;
    }

    private int findInorderIndex(int[] inorder, int start, int end, int key) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == key) return i;
        }
        return -1;
    }

    @Test
    public void test10(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree10(preorder, inorder).print();
    }

//-------------------------------------------------------------------------
}
/*
前序遍历和中序遍历树构造二叉树

 描述
 笔记
 数据
 评测
根据前序遍历和中序遍历树构造二叉树.

 注意事项

你可以假设树中不存在相同数值的节点

样例
给出中序遍历：[1,2,3]和前序遍历：[2,1,3]. 返回如下的树:

  2
 / \
1   3
标签
二叉树
 */

/*Given preorder and inorder traversal of a tree, construct the binary tree.

 Notice

You may assume that duplicates do not exist in the tree.

Have you met this question in a real interview? Yes
Example
Given in-order [1,2,3] and pre-order [2,1,3], return a tree:

  2
 / \
1   3
Tags 
Binary Tree*/

/*
LeetCode – Construct Binary Tree from Preorder and Inorder Traversal (Java)

Given preorder and inorder traversal of a tree, construct the binary tree.

Analysis

Consider the following example:

in-order:   4 2 5 (1) 6 7 3 8
pre-order: (1) 2 4 5  3 7 6 8
From the pre-order array, we know that first element is the root. We can find the root in in-order array. Then we can identify the left and right sub-trees of the root from in-order array.

Using the length of left sub-tree, we can identify left and right sub-trees in pre-order array. Recursively, we can build up the tree.

For this example, the constructed tree is:
 */