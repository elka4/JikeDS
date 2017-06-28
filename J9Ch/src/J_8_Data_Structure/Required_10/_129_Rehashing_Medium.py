'''/** 129 Rehashing
 * Medium'''



class Solution:
    def addlistnode(self, node, number):
        if node.next != None:
            self.addlistnode(node.next, number)
        else:
            node.next = ListNode(number)

    def addnode(self, anshashTable, number):
        p = number % len(anshashTable)
        if anshashTable[p] == None:
            anshashTable[p] = ListNode(number)
        else:
            self.addlistnode(anshashTable[p], number)

    def rehashing(self,hashTable):
        anshashTable = [None for i in range(len(hashTable) * 2)]
        HASH_SIZE = 2 * len(hashTable)
        for item in hashTable:
            p = item
            while p != None:
                self.addnode(anshashTable,p.val)
                p = p.next
        return anshashTable