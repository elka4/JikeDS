/** 129 Rehashing
 * Medium*/


class Solution {
public:
    void addlistnode(ListNode* node, int number) {
        if (node->next != NULL)
            addlistnode(node->next, number);
        else
            node->next = new ListNode(number);
    }
    void addnode(vector<ListNode*> &anshashTable, int number) {
        int p = (number + anshashTable.size()) % anshashTable.size();
        if (anshashTable[p] == NULL)
            anshashTable[p] = new ListNode(number);
        else
            addlistnode(anshashTable[p], number);
    }
    vector<ListNode*> rehashing(vector<ListNode*> hashTable){
        vector<ListNode*> anshashTable;
        for(int i = 0; i < hashTable.size() * 2; i++)
            anshashTable.push_back(NULL);
        int HASH_SIZE = anshashTable.size();
        for(int i = 0; i < hashTable.size(); i++) {
            ListNode* p = hashTable[i];
            while (p != NULL) {
                addnode(anshashTable,p->val);
                p = p->next;
            }
        }
        return anshashTable;
    }
};