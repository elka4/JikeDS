/**
88
 Lowest Common Ancestor *

/**
 * Definition of TreeNode:
 * class TreeNode {
 * public:
 *     int val;
 *     TreeNode *left, *right;
 *     TreeNode(int val) {
 *         this->val = val;
 *         this->left = this->right = NULL;
 *     }
 * }
 */
class Solution {
public:
    /**
     * @param root: The root of the binary search tree.
     * @param A and B: two nodes in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    int Atop, Btop, top;
    TreeNode *a[100000], *b[100000], *ans[100000];
    bool find;
    void inorder(TreeNode *node, TreeNode *A, int flag) {
        if (find==true)
            return;
        if (node == NULL)
            return;
        ans[++top] = node;
        if (A == node) {
            find = true;
            if (flag == 0) {
                Atop = top;
                for (int i = 1; i <= top; ++i)
                    a[i] = ans[i];
            } else {
                Btop = top;
                for (int i = 1; i <= top; ++i)
                    b[i] = ans[i];
            }
            return;
        }

        inorder(node->left, A, flag);
        if (find) return;

        inorder(node->right, A, flag);
        if (find) return;

        top --;

    }
    TreeNode *leastCommonAncestor(TreeNode *root, TreeNode *A, TreeNode *B) {
        // write your code here
        top = 0; find = false;
        inorder(root, A, 0);

        top = 0; find = false;
        inorder(root, B, 1);

        Atop = min(Atop, Btop);
        Btop = Atop;

        while (a[Atop] != b[Btop]) {
            Atop --;
            Btop --;
        }
        return a[Atop];
    }
};