

/*
Your Trie object will be instantiated and called as such:
Trie trie;
trie.insert("lintcode");
trie.search("lint"); will return false
trie.startsWith("lint"); will return true
*/
class TrieNode {
public:
    // Initialize your data structure here.
    TrieNode() {
        for(int i = 0; i < 26; i++)
            next[i] = NULL;
        isString = false;
    }
    TrieNode *next[26];
    bool isString;
};

class Trie {
public:
    Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    void insert(string word) {
        TrieNode *p = root;
        for(int i = 0; i < word.size(); i++){
            if(p->next[word[i]-'a'] == NULL){
                p->next[word[i]-'a'] = new TrieNode();
            }
            p = p->next[word[i]-'a'];
        }
        p->isString = true;
    }

    // Returns if the word is in the trie.
    bool search(string word) {
        TrieNode *p = root;
        for(int i = 0; i < word.size(); i++){
            if(p == NULL) return false;
            p = p->next[word[i]-'a'];
        }
        if(p == NULL || p->isString == false) return false;
        return true;

    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    bool startsWith(string prefix) {
        TrieNode *p = root;
        for(int i = 0; i < prefix.size(); i++) {
            p = p->next[prefix[i]-'a'];
            if(p == NULL) return false;
        }
        return true;
    }

private:
    TrieNode* root;
};