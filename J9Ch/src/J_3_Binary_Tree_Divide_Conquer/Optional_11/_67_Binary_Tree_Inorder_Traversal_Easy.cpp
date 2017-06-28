/**
67
 Binary Tree Inorder Traversal
 */


#include <vector>
#include "lintcode.h"

using namespace std;

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
    /**
     * @param root: The root of binary tree.
     * @return: Inorder in vector which contains node values.
     */
public:
    vector<int> inorder;

    void traverse(TreeNode *root) {
        if (root == NULL) {
            return;
        }
        traverse(root->left);
        inorder.push_back(root->val);
        traverse(root->right);
    }

    vector<int> inorderTraversal(TreeNode *root) {
        inorder.clear();
        traverse(root);
        return inorder;
        // write your code here
    }
};