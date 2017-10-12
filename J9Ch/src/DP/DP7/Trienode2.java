package DP.DP7;

public class Trienode2 {
    Trienode2[] sons;
    boolean hasWord;
    String word;

    public Trienode2() {
        int i;
        sons = new Trienode2[26];
        for (i = 0; i < 26; i++) {
            sons[i] = null;
        }
        hasWord = false;
    }
    // insert a word called wordStr from root
    public static void Insert(Trienode2 root, String wordStr) {
        char[] word = wordStr.toCharArray();
        Trienode2 p = root;
        int i;
        for (i = 0; i < word.length; i++) {
            int c = word[i] - 'a'; // 0 ~ 26
            if (p.sons[c] == null) {
                p.sons[c] = new Trienode2();
            }
            p = p.sons[c];
        }
        p.hasWord = true;
        p.word = wordStr;
    }
}
