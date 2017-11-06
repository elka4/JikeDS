package _04_Tree._Other;
import java.util.*;


//
//
//
public class _582_Tree_Kill_Process_M {

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {

        // Store process tree as an adjacency list
        Map<Integer, List<Integer>> adjacencyLists = new HashMap<>();
        for (int i=0;i<ppid.size();i++) {
            adjacencyLists.putIfAbsent(ppid.get(i), new LinkedList<>());
            adjacencyLists.get(ppid.get(i)).add(pid.get(i));
        }

        // Kill all processes in the subtree rooted at process "kill"
        List<Integer> res = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        stack.add(kill);
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            res.add(cur);
            List<Integer> adjacencyList = adjacencyLists.get(cur);
            if (adjacencyList == null) continue;
            stack.addAll(adjacencyList);
        }
        return res;

    }

    public class Solution {
        public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
            if (kill == 0) return pid;

            int n = pid.size();
            Map<Integer, Set<Integer>> tree = new HashMap<>();
            for (int i = 0; i < n; i++) {
                tree.put(pid.get(i), new HashSet<Integer>());
            }
            for (int i = 0; i < n; i++) {
                if (tree.containsKey(ppid.get(i))) {
                    Set<Integer> children = tree.get(ppid.get(i));
                    children.add(pid.get(i));
                    tree.put(ppid.get(i), children);
                }
            }

            List<Integer> result = new ArrayList<>();
            traverse(tree, result, kill);

            return result;
        }

        private void traverse(Map<Integer, Set<Integer>> tree, List<Integer> result, int pid) {
            result.add(pid);

            Set<Integer> children = tree.get(pid);
            for (Integer child : children) {
                traverse(tree, result, child);
            }
        }
    }
}
/*

 */
/*

 */
