package _04_Tree._Other;
import java.util.*;

//  582. Kill Process
//  https://leetcode.com/problems/kill-process/description/
//
public class _582_Tree_Kill_Process_M {

    //https://leetcode.com/problems/kill-process/solution/

    //Approach #1 Depth First Search [Time Limit Exceeded]

//    Approach #2 Tree Simulation [Accepted]


    //Approach #3 HashMap + Depth First Search [Accepted]

    //Approach #4 HashMap + Breadth First Search [Accepted]:


//-----------------------------------------------------------------------------
    //BFS
    public List<Integer> killProcess5(List<Integer> pid, List<Integer> ppid, int kill) {

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

//-----------------------------------------------------------------------------
    //DFS
    public List<Integer> killProcess6(List<Integer> pid, List<Integer> ppid, int kill) {
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


//-----------------------------------------------------------------------------
}
/*

Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

Example 1:
Input:
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation:
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.
Note:
The given kill id is guaranteed to be one of the given PIDs.
n >= 1.

 */
