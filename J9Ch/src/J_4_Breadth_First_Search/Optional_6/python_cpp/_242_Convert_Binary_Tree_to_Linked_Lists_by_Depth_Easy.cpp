/** 242 Convert Binary Tree to Linked Lists by Depth
*/


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
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    /**
     * @param root the root of binary tree
     * @return a lists of linked list
     */
    vector<ListNode*> binaryTreeToLists(TreeNode* root) {
        // Write your code here
        vector<ListNode*> result;
        dfs(root, 1, result);
        return result;
    }

    void dfs(TreeNode* root, int depth, vector<ListNode*> &result) {
        if (root == NULL)
            return;
        ListNode* node = new ListNode(root->val);
        if (result.size() < depth) {
            result.push_back(node);
        }
        else {
            node->next = result[depth-1];
            result[depth-1] = node;
        }
        dfs(root->right, depth + 1, result);
        dfs(root->left, depth + 1, result);
    }
};