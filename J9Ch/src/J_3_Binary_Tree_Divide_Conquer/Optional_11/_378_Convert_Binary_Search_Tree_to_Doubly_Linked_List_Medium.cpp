/**
 * 378
 * Convert Binary Search Tree to Doubly Linked List
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
 * Definition of Doubly-ListNode
 * class DoublyListNode {
 * public:
 *     int val;
 *     DoublyListNode *next, *prev;
 *     DoublyListNode(int val) {
 *         this->val = val;
           this->prev = this->next = NULL;
 *     }
 * }
 */
class Solution {
public:
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    DoublyListNode* bstToDoublyList(TreeNode* root) {
        // Write your code here
        if (root == NULL)
            return NULL;
        DoublyListNode* node = getDoublyList(root, NULL, NULL);
        while (node->prev != NULL)
            node = node->prev;
        return node;
    }
    DoublyListNode* getDoublyList(TreeNode* root,
                                  DoublyListNode* prev,
                                  DoublyListNode* next) {
        if (root == NULL)
            return NULL;
        DoublyListNode* node = new DoublyListNode(root->val);
        if (root->left == NULL) {
            node->prev = prev;
            if (prev != NULL)
                prev->next = node;
        } else
            getDoublyList(root->left, prev, node);
        if (root->right == NULL) {
            node->next = next;
            if (next != NULL)
                next->prev = node;
        } else
            getDoublyList(root->right, node, next);

        return node;
    }
};


