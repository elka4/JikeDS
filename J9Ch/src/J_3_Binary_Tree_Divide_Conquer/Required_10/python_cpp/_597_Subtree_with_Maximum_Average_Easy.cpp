/** 597. Subtree with Maximum Average
 * Easy*/


/**
 * Definition of TreeNode:
 * class TreeNode {
 * public:
 *     int val;
 *     TreeNode *left, *right>;
 *     TreeNode(int val) {
 *         this->val = val;
 *         this->left = this->right> = NULL;
 *     }
 * }
 */
class ResultType {
public:
    int sum, size;
    ResultType():sum(0), size(0) {}
    ResultType(int _sum, int _size): sum(_sum), size(_size) {}
};

class Solution {
public:
    /**
     * @param root the root of binary tree
     * @return the root of the maximum average of subtree
     */
    TreeNode* findSubtree2(TreeNode* root) {
        // Write your code here
        helper(root);
        return node;
    }

    ResultType helper(TreeNode* root) {
        if (root == NULL) {
            return ResultType();
        }
        ResultType left = helper(root->left);
        ResultType right = helper(root->right);

        ResultType result = ResultType(left.sum + right.sum + root->val,
                                       left.size + right.size + 1);

        if (node == NULL || result.sum * data.size >= data.sum * result.size) {
            data = result;
            node = root;
        }

        return result;
    }

private:
    TreeNode* node = NULL;
    ResultType data;
};