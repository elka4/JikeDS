package _10_DS.UF;
import java.util.*;

//  721. Accounts Merge
//  https://leetcode.com/problems/accounts-merge/description/
//  Depth-first Search Union Find
public class _721_Accounts_Merge_M {

//https://leetcode.com/problems/accounts-merge/solution/
//Approach #1: Depth-First Search [Accepted]
    /*
    Intuition

Draw an edge between two emails if they occur in the same account. The problem comes down to finding connected components of this graph.

Algorithm

For each account, draw the edge from the first email to all other emails. Additionally, we'll remember a map from emails to names on the side. After finding each connected component using a depth-first search, we'll add that to our answer.
     */
class Solution1 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap();
        Map<String, ArrayList<String>> graph = new HashMap();
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                graph.computeIfAbsent(email, x-> new ArrayList<String>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x-> new ArrayList<String>()).add(email);
                emailToName.put(email, name);
            }
        }

        Set<String> seen = new HashSet();
        List<List<String>> ans = new ArrayList();
        for (String email: graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack();
                stack.push(email);
                List<String> component = new ArrayList();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei: graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }
        return ans;
    }
}
///////////////////////////////////////////////////////////////////////////////////////
//Approach #2: Union-Find [Accepted]
/*
As in Approach #1, our problem comes down to finding the connected components of a graph. This is a natural fit for a Disjoint Set Union (DSU) structure.

Algorithm

As in Approach #1, draw edges between emails if they occur in the same account. For easier interoperability between our DSU template, we will map each email to some integer index by using emailToID. Then, dsu.find(email) will tell us a unique id representing what component that email is in.

For more information on DSU, please look at Approach #2 in the article here. For brevity, the solutions showcased below do not use union-by-rank.

Python
 */
    class Solution2 {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            DSU dsu = new DSU();
            Map<String, String> emailToName = new HashMap();
            Map<String, Integer> emailToID = new HashMap();
            int id = 0;
            for (List<String> account: accounts) {
                String name = "";
                for (String email: account) {
                    if (name == "") {
                        name = email;
                        continue;
                    }
                    emailToName.put(email, name);
                    if (!emailToID.containsKey(email)) {
                        emailToID.put(email, id++);
                    }
                    dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
                }
            }

            Map<Integer, List<String>> ans = new HashMap();
            for (String email: emailToName.keySet()) {
                int index = dsu.find(emailToID.get(email));
                ans.computeIfAbsent(index, x-> new ArrayList()).add(email);
            }
            for (List<String> component: ans.values()) {
                Collections.sort(component);
                component.add(0, emailToName.get(component.get(0)));
            }
            return new ArrayList(ans.values());
        }
    }
    class DSU {
        int[] parent;
        public DSU() {
            parent = new int[10001];
            for (int i = 0; i <= 10000; ++i)
                parent[i] = i;
        }
        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

///////////////////////////////////////////////////////////////////////////////////////
//3
    /*
    [Java/C++] Union Find
The key task here is to connect those emails, and this is a perfect use case for union find.
to group these emails, each group need to have a representative, or parent.
At the beginning, set each email as its own representative.
Emails in each account naturally belong to a same group, and should be joined by assigning to the same parent (let's use the parent of first email in that list);
Simple Example:

a b c // now b, c have parent a
d e f // now e, f have parent d
g a d // now abc, def all merged to group g

parents populated after parsing 1st account: a b c
a->a
b->a
c->a

parents populated after parsing 2nd account: d e f
d->d
e->d
f->d

parents populated after parsing 3rd account: g a d
g->g
a->g
d->g

     */
//https://discuss.leetcode.com/topic/109642/java-c-union-find/2
    class Solution3 {
        public List<List<String>> accountsMerge(List<List<String>> acts) {
            Map<String, String> owner = new HashMap<>();
            Map<String, String> parents = new HashMap<>();
            Map<String, TreeSet<String>> unions = new HashMap<>();
            for (List<String> a : acts) {
                for (int i = 1; i < a.size(); i++) {
                    parents.put(a.get(i), a.get(i));
                    owner.put(a.get(i), a.get(0));
                }
            }
            for (List<String> a : acts) {
                String p = find(a.get(1), parents);
                for (int i = 2; i < a.size(); i++)
                    parents.put(find(a.get(i), parents), p);
            }
            for (List<String> a : acts) {
                for (int i = 1; i < a.size(); i++) {
                    String p = find(a.get(i), parents);
                    if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
                    unions.get(p).add(a.get(i));
                }
            }
            List<List<String>> res = new ArrayList<>();
            for (String p : unions.keySet()) {
                List<String> emails = new ArrayList(unions.get(p));
                emails.add(0, owner.get(p));
                res.add(emails);
            }
            return res;
        }
        private String find(String s, Map<String, String> p) {
            return p.get(s) == s ? s : find(p.get(s), p);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////
//4
//HashMap plus union found solution using Java programming!

    class Solution4 {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            if(accounts==null || accounts.size()<1){
                return new ArrayList<>();
            }
            int[] parent = new int[accounts.size()];
            for(int i=0;i<parent.length;i++){
                parent[i] = i;
            }

            Map<String,Integer> map = new HashMap<>();
            for(int i=0;i<accounts.size();i++){
                List<String> eleStrs = accounts.get(i);
                for(int j=1;j<eleStrs.size();j++){
                    String email = eleStrs.get(j);
                    if(map.containsKey(email)){
                        int pre_id = map.get(email);
                        int cur_id = i;
                        int parent_pre_id = findParent(parent,pre_id);
                        int parent_cur_id = findParent(parent,cur_id);
                        if(parent_pre_id!=parent_cur_id){
                            parent[parent_cur_id] = parent_pre_id;
                        }
                    }else{
                        map.put(email,i);
                    }
                }
            }

            Map<Integer,Set<String>> hm = new HashMap<>();
            for(int i=0;i<parent.length;i++){
                int index = findParent(parent,i);
                if(!hm.containsKey(index)){
                    hm.put(index,new HashSet<>());
                }

                Set<String> temp = new HashSet<>();
                for(int j=1;j<accounts.get(i).size();j++){
                    temp.add(accounts.get(i).get(j));
                }
                hm.get(index).addAll(temp);
            }

            List<List<String>> ans = new ArrayList<>();
            for(Integer id : hm.keySet()){
                ans.add(new ArrayList<>());
                ans.get(ans.size()-1).add(accounts.get(id).get(0));

                List<String> addemails = new ArrayList<>(hm.get(id));
                Collections.sort(addemails);
                ans.get(ans.size()-1).addAll(addemails);
            }

            return ans;

        }

        public int findParent(int[] parent,int index){
            while(index!=parent[index]){
                parent[index] = parent[parent[index]];
                index = parent[index];
            }

            return index;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////
//5
/*Java Union-Find solution with explanation

    Intuitively, we could think of using HashMap to solve this problem.
    Since User name could be duplicated, it couldn't be used as key. But we still need a way to record how potentially different user names associate with the same account.
    So need to be able to:

    Given an email address,we need to know if it occurred before;if it did,we need to know the user associate with it.
    Given a user name,we know which account it belongs to
    For (2), we could assume every account is unique and use the index as the account number.So the user name could be easily found with accounts.get(index).get(0); Then we build Map<Integer,Set<String>> m2 to map the account index with all its emails
    For (1), we build Map<String,Integer> m1 to record email and its account number.And use Union-Find to build account connection: When we find current email address is already in m1, we union current account number.*/
    class Solution5 {
        int[] id;
        int[] size;
        public int find(int p){
            while(p != id[p]){
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }
        public void union(int p ,int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootP == rootQ) return;
            if(size[rootP] < size[rootQ]){
                id[rootP] = id[rootQ];
                size[rootQ] += size[rootP];
            }
            else{
                id[rootQ] = id[rootP];
                size[rootP] += size[rootQ];
            }
        }
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            List<List<String>> res = new LinkedList<>();
            if(accounts == null||accounts.size() == 0) return res;
            int n = accounts.size();
            //m1 records "email,accountIndex" pairs
            Map<String,Integer> m1 = new HashMap<>();
            //m2 records "accountIndex, emails associated with it" pairs
            Map<Integer,Set<String>> m2 = new HashMap<>();
            //Initialize the id for union-find
            id = new int[n];
            size = new int[n];
            for(int i = 0;i < n;i++){
                id[i] = i;
            }
            for(int i = 0;i < n;i++){
                if(!m2.containsKey(i)){
                    m2.put(i,new HashSet<>());
                }
                for(int j = 1;j < accounts.get(i).size();j++){
                    String email = accounts.get(i).get(j);
                    // if email occurred before, we know the (m1.containsKey(email))th account is the same one as the ith account
                    //We could simply merge the two accounts
                    if(m1.containsKey(email)){
                        union(m1.get(email),i);
                    }
                    m1.put(email,i);
                    m2.get(i).add(email);
                }
            }
            //We use map to record the merging result
            Map<Integer,Set<String>> map = new HashMap<>();
            for(int i = 0;i < n;i++){
                int idx = i;
                //find current idx's parent p
                while(idx != id[idx]){
                    id[idx] = id[id[idx]];
                    idx = id[idx];
                }
                int p = id[idx];
                //merge all emails associate with i to p
                if(!map.containsKey(p)) map.put(p,new HashSet<>());
                if(m2.containsKey(i) && m2.get(i).size() > 0){
                    map.get(p).addAll(m2.get(i));
                }
            }
            //Adding entries to the list
            for(int key : map.keySet()){
                List<String> oneres = new LinkedList<>();
                //adding emails
                oneres.addAll(map.get(key));
                //Sort the list
                Collections.sort(oneres);
                //get "name" using index
                oneres.add(0,accounts.get(key).get(0));
                res.add(oneres);
            }
            return res;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////
}
/*

 */

