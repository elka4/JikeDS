/**
 * 474
 * Lowest Common Ancestor II*/


/**
 * Definition of ParentTreeNode:
 * class ParentTreeNode {
 * public:
 *     int val;
 *     ParentTreeNode *parent, *left, *right;
 * }
 */
class Solution {
public:
    /**
     * @param root: The root of the tree
     * @param A, B: Two node in the tree
     * @return: The lowest common ancestor of A and B
     */
    int Atop, Btop, top;
    ParentTreeNode *a[100000], *b[100000], *ans[100000];
    bool find;
    void inorder(ParentTreeNode *node, ParentTreeNode *A, int flag) {
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

    ParentTreeNode *lowestCommonAncestorII(ParentTreeNode *root,
                                           ParentTreeNode *A,
                                           ParentTreeNode *B) {
        // Write your code here
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