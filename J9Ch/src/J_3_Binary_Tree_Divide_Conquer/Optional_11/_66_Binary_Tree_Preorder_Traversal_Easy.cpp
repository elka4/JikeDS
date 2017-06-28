/**66 Binary Tree Preorder Traversal */


#include <vector>
#include "lintcode.h"

using namespace std;

class Solution {
public:
    vector<int> preorder;

    void traverse(TreeNode *root) {
        if (root == NULL) {
            return;
        }
        preorder.push_back(root->val);
        traverse(root->left);
        traverse(root->right);
    }

    vector<int> preorderTraversal(TreeNode *root) {
        preorder.clear();
        traverse(root);
        return preorder;
    }
};