package _05_DFS._DFS_Other;
import java.util.*;
//332. Reconstruct Itinerary

public class _332_DFS_Reconstruct_Itinerary_M {

    class Solution{

        public List<String> findItinerary(String[][] tickets) {
            for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1
                    ]);
            visit("JFK");
            return route; }
        Map<String, PriorityQueue<String>> targets = new HashMap<>(); List<String> route = new LinkedList();
        void visit(String airport) {
            while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
                visit(targets.get(airport).poll()); route.add(0, airport);
        }
    }

    class Solution2{
        public List<String> findItinerary(String[][] tickets) {
            Map<String, PriorityQueue<String>> targets = new HashMap<>();
            for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1
                    ]);
            List<String> route = new LinkedList(); Stack<String> stack = new Stack<>(); stack.push("JFK");
            while (!stack.empty()) {
                while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty())
                stack.push(targets.get(stack.peek()).poll()); route.add(0, stack.pop());
            }
            return route; }
    }

    public List<String> findItinerary(String[][] tickets) {
        List<String> ans = new ArrayList<String>();
        if(tickets == null || tickets.length == 0) return ans;
        Map<String, PriorityQueue<String>> ticketsMap = new HashMap<>();
        for(int i = 0; i < tickets.length; i++) {
            if(!ticketsMap.containsKey(tickets[i][0])) ticketsMap.put(tickets[i][0], new PriorityQueue<String>());
            ticketsMap.get(tickets[i][0]).add(tickets[i][1]);
        }

        String curr = "JFK";
        Stack<String> drawBack = new Stack<String>();
        for(int i = 0; i < tickets.length; i++) {
            while(!ticketsMap.containsKey(curr) || ticketsMap.get(curr).isEmpty()) {
                drawBack.push(curr);
                curr = ans.remove(ans.size()-1);
            }
            ans.add(curr);
            curr = ticketsMap.get(curr).poll();
        }
        ans.add(curr);
        while(!drawBack.isEmpty()) ans.add(drawBack.pop());
        return ans;
    }


    class Solution4{

        public List<String> findItinerary1(String[][] tickets) {
            for (String[] ticket : tickets)
                targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
            visit("JFK");
            return route;
        }

        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        List<String> route = new LinkedList();

        void visit(String airport) {
            while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
                visit(targets.get(airport).poll());
            route.add(0, airport);
        }
//        Iterative version:

        public List<String> findItinerary2(String[][] tickets) {
            Map<String, PriorityQueue<String>> targets = new HashMap<>();
            for (String[] ticket : tickets)
                targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
            List<String> route = new LinkedList();
            Stack<String> stack = new Stack<>();
            stack.push("JFK");
            while (!stack.empty()) {
                while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty())
                    stack.push(targets.get(stack.peek()).poll());
                route.add(0, stack.pop());
            }
            return route;
        }

    }
//-------------------------------------------------------------------------///
    // 9Ch
    public class Jiuzhang {
        public List<String> findItinerary(String[][] tickets) {
            Map<String, PriorityQueue<String>> hashmap = new HashMap<String, PriorityQueue<String>>();
            List<String> list = new ArrayList<String>();
            String cur = "JFK";
            int length = tickets.length;
            for (int i = 0; i < length; i++) {
                if (!hashmap.containsKey(tickets[i][0])) {
                    hashmap.put(tickets[i][0], new PriorityQueue<String>());
                }
                hashmap.get(tickets[i][0]).add(tickets[i][1]);
            }
            dfs(cur, hashmap, list);
            Collections.reverse(list);
            return list;
        }
        public void dfs(String cur, Map<String, PriorityQueue<String>> hashmap, List<String> list) {
            while (hashmap.containsKey(cur) && !hashmap.get(cur).isEmpty()) {
                dfs(hashmap.get(cur).poll(), hashmap, list);
            }
            list.add(cur);
        }
    }



//-------------------------------------------------------------------------///






}
/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 */