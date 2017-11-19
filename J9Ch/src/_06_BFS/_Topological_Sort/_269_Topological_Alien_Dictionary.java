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

//-------------------------------------------------------------------------////////////
    //http://blog.csdn.net/a921122/article/details/60407972
/*
首先，能想到拓扑排序（不要问我怎么想到的），这一题基本也就解决了一大半了。详细来说 ，我们需要在脑海中构建一个图，图的节点就是字母，然后图的边是什么呢？？？这是一个很关键的地方，我一开始就搞错了 ，我感觉也是题目故意不交代清楚。
我一开始以为单词内的 字母要按顺序排，因此图的边就是一个单词内的字母之间的顺序，后来发现example1，就不符合这个规律。（真的是写完才发现的，特别麻烦。）

实际上，边是按照相邻单词之间不同字母的顺序来生成的，比如“abc”，“abd”，两个单词相邻，依次比较单词的各个字母，找到第一个不同的，也就是cd，那么c->d就形成一个边。以此类推比较完所有相邻的单词即可。（在本题中，遍历两个字符串中较小的长度，如果没有不同的字符，则忽略）

具体方法：
生成三个数组，1.int[26]存储不同节点的入度（实际上这里应该使用最小堆，但是由于只有26个字母，用数组效率更高），2.一个存储边对边数量int[26][26]（实际上这里就是存储图的，用的是邻接矩阵方式存储图，而不是邻接链表，因为只有26个字母，所以很有限）,3.int[26]第三个数组用来存储字母的出现顺序，用来应对在出现多种拓扑排序的结果时，用来确定哪个字母放在前面的；
初始化数组1，所有的值为-1，将出现了的字符置为0；并记录字符出现的顺序到数组3；获取相邻字符串的第一个不同字符，作为边，记录到数组2，当出现一个边时，还要对入度数组1对应的节点的数量++；（这一步实际上就是拓扑排序的准备工作，图结构和入度最小堆的构造）
拓扑排序。（将入度为0的节点取出以后，要将数组1中当前位置的值置为-1。假如出现多个入度为0的节点，则使用数组3进行排序）
检查入度数组，是否所有的值都为-1，如果不是则说明图中有环，输出为“”，否则输出结果。（这一步实际上是拓扑排序的收尾工作）


这里简单介绍一下拓扑排序的基本原理：选取一个入度为0的节点（入度数组1），将它加入结果队列list，并在邻接矩阵中去掉以该点为出度的所有边，并且在入度数组1中对应位置--。再选取入度为0的节点，直到所有节点都入度为0，输出list即可。（假如最后发现存在节点入度不为0的节点，说明图中有环，返回空。）
 */
    public class Solution5 {
        private final static int NUM = 26;

        int[] nArrInDegreeNum;
        int[][] nArrEdges;
        int[] nArrOrders;

        public String alienOrder(String[] words) {
            nArrInDegreeNum = new int[NUM];// store number of indegree, like
            // min heap
            // init all to -1, which means there is no such element
            for (int i = 0; i < NUM; i++) {
                nArrInDegreeNum[i] = -1;
            }
            nArrEdges = new int[26][26];// store edge
            nArrOrders = new int[26];// for the order of alphabet
            int nOrder = 1;

            // init all character appeared to 0, and decide the order of alph
            for (String string : words) {
                // if it appears and =-2, set it = -1
                for (char c : string.toCharArray()) {
                    if (nArrInDegreeNum[c - 'a'] == -1) {
                        nArrInDegreeNum[c - 'a'] = 0;
                    }
                    if (nArrOrders[c - 'a'] == 0) {
                        nArrOrders[c - 'a'] = nOrder;
                        nOrder++;
                    }
                }
            }

            // compare the neighbor strings, and find the first different char
            for (int index = 0; index < words.length; index++) {
                if (index < words.length - 1) {
                    findFirstDifferentChar(words[index], words[index + 1]);
                }
            }

            // top sort
            StringBuilder sb = new StringBuilder();
            topologicalSorting(sb);

            // judge whether all degree is -1, if not return ""
            for (int i = 0; i < NUM; i++) {
                if (nArrInDegreeNum[i] != -1) {
                    return "";
                }
            }
            return sb.toString();
        }

        private void topologicalSorting(StringBuilder sb) {
            boolean bFind = true;
            LinkedList<Integer> llFind = new LinkedList<>();

            while (bFind) {
                bFind = false;
                for (int i = 0; i < NUM; i++) {
                    if (nArrInDegreeNum[i] == 0) {
                        bFind = true;
                        llFind.add(i);
                        nArrInDegreeNum[i] = -1;
                    }
                }
                // if find the indegree==0
                if (bFind) {
                    // for two same chars, sort as its appeared order
                    Collections.sort(llFind, new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return nArrOrders[o1] - nArrOrders[o2];
                        }
                    });
                    for (Integer integer : llFind) {
                        char c = (char) ('a' + integer);
                        sb.append(c);
                        for (int i = 0; i < NUM; i++) {
                            if (nArrEdges[integer][i] > 0) {
                                nArrInDegreeNum[i] -= nArrEdges[integer][i];
                                nArrEdges[integer][i] = 0;
                            }
                        }
                    }
                    llFind.clear();
                }
            }
        }

        private void findFirstDifferentChar(String str1, String str2) {
            char[] c1 = str1.toCharArray();
            char[] c2 = str2.toCharArray();
            int nLength = Math.min(c1.length, c2.length);
            for (int i = 0; i < nLength; i++) {
                if (c1[i] != c2[i]) {
                    nArrInDegreeNum[c2[i] - 'a']++;// indegree num ++
                    nArrEdges[c1[i] - 'a'][c2[i] - 'a']++;// edge ++
                    break;
                }

            }
        }
    }



//-------------------------------------------------------------------------////////////
    //https://segmentfault.com/a/1190000003795463
/*
拓扑排序
复杂度
时间 O(N) 空间 O(N)

思路
首先简单介绍一下拓扑排序，这是一个能够找出有向无环图顺序的一个方法

假设我们有3条边：A->C, B->C, C->D，先将每个节点的计数器初始化为0。然后我们对遍历边时，每遇到一个边，把目的节点的计数器都加1。然后，我们再遍历一遍，找出所有计数器值还是0的节点，这些节点就是有向无环图的“根”。然后我们从根开始广度优先搜索。具体来说，搜索到某个节点时，将该节点加入结果中，然后所有被该节点指向的节点的计数器减1，在减1之后，如果某个被指向节点的计数器变成0了，那这个被指向的节点就是该节点下轮搜索的子节点。在实现的角度来看，我们可以用一个队列，这样每次从队列头拿出来一个加入结果中，同时把被这个节点指向的节点中计数器值减到0的节点也都加入队列尾中。需要注意的是，如果图是有环的，则计数器会产生断层，即某个节点的计数器永远无法清零（有环意味着有的节点被多加了1，然而遍历的时候一次只减一个1，所以导致无法归零），这样该节点也无法加入到结果中。所以我们只要判断这个结果的节点数和实际图中节点数相等，就代表无环，不相等，则代表有环。

对于这题来说，我们首先要初始化所有节点（即字母），一个是该字母指向的字母的集合（被指向的字母在字母表中处于较后的位置），一个是该字母的计数器。然后我们根据字典开始建图，但是字典中并没有显示给出边的情况，如何根据字典建图呢？其实边都暗藏在相邻两个词之间，比如abc和abd，我们比较两个词的每一位，直到第一个不一样的字母c和d，因为abd这个词在后面，所以d在字母表中应该是在c的后面。所以每两个相邻的词都能蕴含一条边的信息。在建图的同时，实际上我们也可以计数了，对于每条边，将较后的字母的计数器加1。计数时需要注意的是，我们不能将同样一条边计数两次，所以要用一个集合来排除已经计数过的边。最后，我们开始拓扑排序，从计数器为0的字母开始广度优先搜索。为了找到这些计数器为0的字母，我们还需要先遍历一遍所有的计数器。

最后，根据结果的字母个数和图中所有字母的个数，判断时候有环即可。无环直接返回结果。

注意
因为这题代码很冗长，面试的时候最好都把几个大步骤都写成子函数，先完成主函数，再实现各个子函数，比如初始化图，建图，加边，排序，都可以分开
要先对字典里所有存在的字母初始化入度为0，否则之后建图可能会漏掉一些没有入度的字母
'a'+'b'+""和'a'+""+'b'是不一样的，前者先算数字和，后者则是字符串拼接
因为字典里有重复的边，所有要先判断，已经添加过的边不要重复添加

 */

    public class Solution6 {
        public String alienOrder(String[] words) {
            // 节点构成的图
            Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
            // 节点的计数器
            Map<Character, Integer> indegree = new HashMap<Character, Integer>();
            // 结果存在这个里面
            StringBuilder order = new StringBuilder();
            // 初始化图和计数器
            initialize(words, graph, indegree);
            // 建图并计数
            buildGraphAndGetIndegree(words, graph, indegree);
            // 拓扑排序的最后一步，根据计数器值广度优先搜索
            topologicalSort(order, graph, indegree);
            // 如果大小相等说明无环
            return order.length() == indegree.size() ? order.toString() : "";
        }

        private void initialize(String[] words, Map<Character, Set<Character>> graph,
                                Map<Character, Integer> indegree){
            for(String word : words){
                for(int i = 0; i < word.length(); i++){
                    char curr = word.charAt(i);
                    // 对每个单词的每个字母初始化计数器和图节点
                    if(graph.get(curr) == null){
                        graph.put(curr, new HashSet<Character>());
                    }
                    if(indegree.get(curr) == null){
                        indegree.put(curr, 0);
                    }
                }
            }
        }

        private void buildGraphAndGetIndegree(String[] words, Map<Character, Set<Character>> graph,
                                              Map<Character, Integer> indegree){
            Set<String> edges = new HashSet<String>();
            for(int i = 0; i < words.length - 1; i++){
                // 每两个相邻的词进行比较
                String word1 = words[i];
                String word2 = words[i + 1];
                for(int j = 0; j < word1.length() && j < word2.length(); j++){
                    char from = word1.charAt(j);
                    char to = word2.charAt(j);
                    // 如果相同则继续，找到两个单词第一个不相同的字母
                    if(from == to) continue;
                    // 如果这两个字母构成的边还没有使用过，则
                    if(!edges.contains(from+""+to)){
                        Set<Character> set = graph.get(from);
                        set.add(to);
                        // 将后面的字母加入前面字母的Set中
                        graph.put(from, set);
                        Integer toin = indegree.get(to);
                        toin++;
                        // 更新后面字母的计数器，+1
                        indegree.put(to, toin);
                        // 记录这条边已经处理过了
                        edges.add(from+""+to);
                        break;
                    }
                }
            }
        }

        private void topologicalSort(StringBuilder order, Map<Character, Set<Character>> graph,
                                     Map<Character, Integer> indegree){
            // 广度优先搜索的队列
            Queue<Character> queue = new LinkedList<Character>();
            // 将有向图的根，即计数器为0的节点加入队列中
            for(Character key : indegree.keySet()){
                if(indegree.get(key) == 0){
                    queue.offer(key);
                }
            }
            // 搜索
            while(!queue.isEmpty()){
                Character curr = queue.poll();
                // 将队头节点加入结果中
                order.append(curr);
                Set<Character> set = graph.get(curr);
                if(set != null){
                    // 对所有该节点指向的节点，更新其计数器，-1
                    for(Character c : set){
                        Integer val = indegree.get(c);
                        val--;
                        // 如果计数器归零，则加入队列中待处理
                        if(val == 0){
                            queue.offer(c);
                        }
                        indegree.put(c, val);
                    }
                }
            }
        }
    }

//新建一个AlienChar数据结构重写，只用一个Map作为Graph自身
public class Solution7 {
    public String alienOrder(String[] words) {
        Map<Character, AlienChar> graph = new HashMap<Character, AlienChar>();
        // 如果建图失败，比如有a->b和b->a这样的边，就返回false
        boolean isBuildSucceed = buildGraph(words, graph);
        if(!isBuildSucceed){
            return "";
        }
        // 在建好的图中根据拓扑排序遍历
        String order = findOrder(graph);
        return order.length() == graph.size() ? order : "";
    }

    private boolean buildGraph(String[] words, Map<Character, AlienChar> graph){
        HashSet<String> visited = new HashSet<String>();
        // 初始化图，每个字母都初始化入度为0
        initializeGraph(words, graph);
        for(int wordIdx = 0; wordIdx < words.length - 1; wordIdx++){
            String before = words[wordIdx];
            String after = words[wordIdx + 1];
            Character prev = null, next = null;
            // 找到相邻两个单词第一个不一样的字母
            for(int letterIdx = 0; letterIdx < before.length() && letterIdx < after.length(); letterIdx++){
                if(before.charAt(letterIdx) != after.charAt(letterIdx)){
                    prev = before.charAt(letterIdx);
                    next = after.charAt(letterIdx);
                    break;
                }
            }
            // 如果有环，则建图失败
            if(prev != null && visited.contains(next + "" + prev)){
                return false;
            }
            // 如果这条边没有添加过，则在图中加入这条边
            if(prev != null && !visited.contains(prev + "" + next)){
                addEdge(prev, next, graph);
                visited.add(prev + "" + next);
            }
        }
        return true;
    }

    private void initializeGraph(String[] words, Map<Character, AlienChar> graph){
        for(String word : words){
            for(int idx = 0; idx < word.length(); idx++){
                if(!graph.containsKey(word.charAt(idx))){
                    graph.put(word.charAt(idx), new AlienChar(word.charAt(idx)));
                }
            }
        }
    }

    private void addEdge(char prev, char next, Map<Character, AlienChar> graph){
        AlienChar prevAlienChar = graph.get(prev);
        AlienChar nextAlienChar = graph.get(next);
        nextAlienChar.indegree += 1;
        prevAlienChar.later.add(nextAlienChar);
        graph.put(prev, prevAlienChar);
        graph.put(next, nextAlienChar);
    }

    private String findOrder(Map<Character, AlienChar> graph){
        StringBuilder order = new StringBuilder();
        Queue<AlienChar> queue = new LinkedList<AlienChar>();
        for(Character c : graph.keySet()){
            if(graph.get(c).indegree == 0){
                queue.offer(graph.get(c));
            }
        }
        while(!queue.isEmpty()){
            AlienChar curr = queue.poll();
            order.append(curr.val);
            for(AlienChar next : curr.later){
                if(--next.indegree == 0){
                    queue.offer(next);
                }
            }
        }
        return order.toString();
    }
}

    class AlienChar {
        char val;
        ArrayList<AlienChar> later;
        int indegree;
        public AlienChar(char c){
            this.val = c;
            this.later = new ArrayList<AlienChar>();
            this.indegree = 0;
        }
    }

//-------------------------------------------------------------------------////////////
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
There is a new alien language which uses the latin alphabet.
However, the order among letters are unknown to you.
You receive a list of non-empty words from the dictionary,
where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.

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
思路：
使用拓扑排序是这一道题目的正解。我们可以利用这道题目总结一下拓扑排序的实现框架：
1）定义两个哈希表，一个用来表示每次出现过的字符的入度，另一个来存一个字符指向的字符集合。
2）每次找出一个入度为0的字符，并且更新这个字符指向的字符集入度减1。如果在没有遍历完所有字符的情况下找不到入度为0的字符，那么说明没有合法的拓扑排序，返回“”。如果在一次遍历过程中出现了多个入度为0的字符，说明拓扑排序的解不唯一，任意选择其中一个就可以了。
代码：
 */

/*

 */