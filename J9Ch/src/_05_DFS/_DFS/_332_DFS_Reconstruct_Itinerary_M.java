package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _332_DFS_Reconstruct_Itinerary_M {

    class Solution{

        public List<String> findItinerary(String[][] tickets) { for (String[] ticket : tickets)
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
        public List<String> findItinerary(String[][] tickets) { Map<String, PriorityQueue<String>> targets = new HashMap<>(); for (String[] ticket : tickets)
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
}
