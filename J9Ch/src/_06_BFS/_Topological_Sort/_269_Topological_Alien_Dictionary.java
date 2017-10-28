package _06_BFS._Topological_Sort;
import java.util.*;
import org.junit.Test;
public class _269_Topological_Alien_Dictionary {
//Java AC solution using BFS

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map=new HashMap<Character, Set<Character>>();
        Map<Character, Integer> degree=new HashMap<Character, Integer>();
        String result="";
        if(words==null || words.length==0) return result;
        for(String s: words){
            for(char c: s.toCharArray()){
                degree.put(c,0);
            }
        }
        for(int i=0; i<words.length-1; i++){
            String cur=words[i];
            String next=words[i+1];
            int length=Math.min(cur.length(), next.length());
            for(int j=0; j<length; j++){
                char c1=cur.charAt(j);
                char c2=next.charAt(j);
                if(c1!=c2){
                    Set<Character> set=new HashSet<Character>();
                    if(map.containsKey(c1)) set=map.get(c1);
                    if(!set.contains(c2)){
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2)+1);
                    }
                    break;
                }
            }
        }
        Queue<Character> q=new LinkedList<Character>();
        for(char c: degree.keySet()){
            if(degree.get(c)==0) q.add(c);
        }
        while(!q.isEmpty()){
            char c=q.remove();
            result+=c;
            if(map.containsKey(c)){
                for(char c2: map.get(c)){
                    degree.put(c2,degree.get(c2)-1);
                    if(degree.get(c2)==0) q.add(c2);
                }
            }
        }
        if(result.length()!=degree.size()) return "";
        return result;
    }


    //3ms Clean Java Solution (DFS)
/*
The key to this problem is:

A topological ordering is possible if and only if the graph has no directed cycles
Let's build a graph and perform a DFS. The following states made things easier.

visited[i] = -1. Not even exist.
visited[i] = 0. Exist. Non-visited.
visited[i] = 1. Visiting.
visited[i] = 2. Visited.
Similarly, there is another simple BFS version.
 */

    class Solution2{
        private final int N = 26;
        public String alienOrder(String[] words) {
            boolean[][] adj = new boolean[N][N];
            int[] visited = new int[N];
            buildGraph(words, adj, visited);

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++) {
                if(visited[i] == 0) {                 // unvisited
                    if(!dfs(adj, visited, sb, i)) return "";
                }
            }
            return sb.reverse().toString();
        }

        public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
            visited[i] = 1;                            // 1 = visiting
            for(int j = 0; j < N; j++) {
                if(adj[i][j]) {                        // connected
                    if(visited[j] == 1) return false;  // 1 => 1, cycle
                    if(visited[j] == 0) {              // 0 = unvisited
                        if(!dfs(adj, visited, sb, j)) return false;
                    }
                }
            }
            visited[i] = 2;                           // 2 = visited
            sb.append((char) (i + 'a'));
            return true;
        }

        public void buildGraph(String[] words, boolean[][] adj, int[] visited) {
            Arrays.fill(visited, -1);                 // -1 = not even existed
            for(int i = 0; i < words.length; i++) {
                for(char c : words[i].toCharArray()) visited[c - 'a'] = 0;
                if(i > 0) {
                    String w1 = words[i - 1], w2 = words[i];
                    int len = Math.min(w1.length(), w2.length());
                    for(int j = 0; j < len; j++) {
                        char c1 = w1.charAt(j), c2 = w2.charAt(j);
                        if(c1 != c2) {
                            adj[c1 - 'a'][c2 - 'a'] = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    //Solution
    class Solution4{
        public String alienOrder(String[] words) {
            List<Set<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                adj.add(new HashSet<Integer>());
            }
            int[] degree = new int[26];
            Arrays.fill(degree, -1);

            for (int i = 0; i < words.length; i++) {
                for (char c : words[i].toCharArray()) {
                    if (degree[c - 'a'] < 0) {
                        degree[c - 'a'] = 0;
                    }
                }
                if (i > 0) {
                    String w1 = words[i - 1], w2 = words[i];
                    int len = Math.min(w1.length(), w2.length());
                    for (int j = 0; j < len; j++) {
                        int c1 = w1.charAt(j) - 'a', c2 = w2.charAt(j) - 'a';
                        if (c1 != c2) {
                            if (!adj.get(c1).contains(c2)) {
                                adj.get(c1).add(c2);
                                degree[c2]++;
                            }
                            break;
                        }
                        if (j == w2.length() - 1 && w1.length() > w2.length()) { // "abcd" -> "ab"
                            return "";
                        }
                    }
                }
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < degree.length; i++) {
                if (degree[i] == 0) {
                    q.add(i);
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!q.isEmpty()) {
                int i = q.poll();
                sb.append((char) ('a'  + i));
                for (int j : adj.get(i)) {
                    degree[j]--;
                    if (degree[j] == 0) {
                        q.add(j);
                    }
                }
            }

            for (int d : degree) {
                if (d > 0) {
                    return "";
                }
            }

            return sb.toString();
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////
    //jiuzhang
public class Jiuzhang{
    public class Node {
        public int degree;
        public ArrayList<Integer> neighbour = new ArrayList<Integer>();
        void Node() {
            degree = 0;
        }
    }
    public String alienOrder(String[] words) {
        Node[] node = new Node[26];
        boolean[] happen = new boolean[26];
        for (int i = 0; i < 26; i++) {
            node[i] = new Node();
        }
        //Build the Graph
        for (int i = 0; i < words.length; i++) {
            int startPoint = 0, endPoint = 0;
            for (int j = 0; j < words[i].length(); j++) {
                happen[charToInt(words[i].charAt(j))] = true;
            }
            if (i != words.length - 1) {
                for (int j = 0; j < Math.min(words[i].length(), words[i + 1].length()); j++) {
                    if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                        startPoint = charToInt(words[i].charAt(j));
                        endPoint = charToInt(words[i + 1].charAt(j));
                        break;
                    }
                }
            }
            if (startPoint != endPoint) {
                node[startPoint].neighbour.add(endPoint);
                node[endPoint].degree++;
            }
        }
        //Topological Sort
        Queue<Integer> queue = new LinkedList<Integer>();
        String ans = "";
        for (int i = 0; i < 26; i++) {
            if (node[i].degree == 0 && happen[i]) {
                queue.offer(i);
                ans = ans + intToChar(i);
            }
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i : node[now].neighbour) {
                node[i].degree--;
                if (node[i].degree == 0) {
                    queue.offer(i);
                    ans = ans + intToChar(i);
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            if (node[i].degree != 0) {
                return "";
            }
        }
        return ans;
    }
    public char intToChar(int i) {
        return (char)('a' + i);
    }
    public int charToInt(char ch) {
        return ch - 'a';
    }
}
}
/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Example 2:
Given the following words in dictionary,

[
  "z",
  "x"
]
The correct order is: "zx".

Example 3:
Given the following words in dictionary,

[
  "z",
  "x",
  "z"
]
The order is invalid, so return "".

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

 */

/*

 */