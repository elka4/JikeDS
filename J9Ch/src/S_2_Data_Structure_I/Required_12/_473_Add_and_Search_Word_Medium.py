

class TrieNode:
    def __init__(self):
        self.leaf = False
        self.children = [None] * 26

class Trie:
    def __init__(self):
        self.root = TrieNode()
        self.size = 0

    def insert(self, s):
        if len(s) == 0:
            return
        p = self.root
        i = 0
        while i < len(s):
            if p.children[ord(s[i])-ord('a')] is None:
                new_node = TrieNode()
                p.children[ord(s[i])-ord('a')] = new_node
            p = p.children[ord(s[i])-ord('a')]
            i += 1
        p.leaf = True
        self.size += 1

    def search(self, s):
        if len(s) == 0:
            return False

        return self.searchRe(s, self.root, 0)

    def searchRe(self, s, p, i):
        if len(s) == i:
            if p.leaf:
                return True
            return False

        result = False
        if s[i] == '.':
            for j in range(0, 26):
                if p.children[j] != None:
                    if self.searchRe(s, p.children[j], i+1):
                        result = True
        else:
            if p.children[ord(s[i]) - ord('a')] != None:
                if self.searchRe(s, p.children[ord(s[i]) - ord('a')], i+1):
                    result = True
        return result

class WordDictionary:
    # initialize your data structure here.
    def __init__(self):
        # Write your code here
        self.trie = Trie()

    # @param {string} word
    # @return {void}
    # Adds a word into the data structure.
    def addWord(self, word):
        # Write your code here
        self.trie.insert(word)

    # @param {string} word
    # @return {boolean}
    # Returns if the word is in the data structure. A word could
    # contain the dot character '.' to represent any one letter.
    def search(self, word):
        # Write your code here
        return self.trie.search(word)

        # Your WordDictionary object will be instantiated and called as such:
        # wordDictionary = WordDictionary()
        # wordDictionary.addWord("word")
        # wordDictionary.search("pattern")