/**
 448
 Inorder Successor in Binary Search Tree
 */


/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* inorderSuccessor(TreeNode* root, TreeNode* p) {
        TreeNode *successor = NULL;
        while (root != NULL && root->val != p->val) {
            if (root->val > p->val) {
                successor = root;
                root = root->left;
            } else {
                root = root->right;
            }
        }

        if (root == NULL) {
            return NULL;
        }

        if (root->right == NULL) {
            return successor;
        }

        root = root->right;
        while (root->left != NULL) {
            root = root->left;
        }

        return root;
     }
};

// 方法二

class Solution {
public:
  TreeNode* inorderSuccessor(TreeNode* root, TreeNode* p) {
    // write your code here
    if (!root || !p) {
      return NULL;
    }

    TreeNode *ans = NULL, *cur = root;
    while (cur != p) {
      if (p->val < cur->val) {
        ans = cur;
        cur = cur->left;
      }
      if (p->val > cur->val) {
        cur = cur->right;
      }
    }

    if (p->right) {
      cur = p->right;
      while (cur->left) {
        cur = cur->left;
      }
      ans = cur;
    }

    return ans;
  }
};