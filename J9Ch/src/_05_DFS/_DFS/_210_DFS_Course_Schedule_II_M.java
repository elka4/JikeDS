package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _210_DFS_Course_Schedule_II_M {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] incLinkCounts = new int[numCourses];
        List<List<Integer>> adjs = new ArrayList<>(numCourses);
        initialiseGraph(incLinkCounts, adjs, prerequisites);
        //return solveByBFS(incLinkCounts, adjs);
        return solveByDFS(adjs);
    }

    private void initialiseGraph(int[] incLinkCounts, List<List<Integer>> adjs, int[][] prerequisites){
        int n = incLinkCounts.length;
        while (n-- > 0) adjs.add(new ArrayList<>());
        for (int[] edge : prerequisites) {
            incLinkCounts[edge[0]]++;
            adjs.get(edge[1]).add(edge[0]);
        }
    }

    private int[] solveByBFS(int[] incLinkCounts, List<List<Integer>> adjs){
        int[] order = new int[incLinkCounts.length];
        Queue<Integer> toVisit = new ArrayDeque<>();
        for (int i = 0; i < incLinkCounts.length; i++) {
            if (incLinkCounts[i] == 0) toVisit.offer(i);
        }
        int visited = 0;
        while (!toVisit.isEmpty()) {
            int from = toVisit.poll();
            order[visited++] = from;
            for (int to : adjs.get(from)) {
                incLinkCounts[to]--;
                if (incLinkCounts[to] == 0) toVisit.offer(to);
            }
        }
        return visited == incLinkCounts.length ? order : new int[0];
    }
    private int[] solveByDFS(List<List<Integer>> adjs) {
        BitSet hasCycle = new BitSet(1);
        BitSet visited = new BitSet(adjs.size());
        BitSet onStack = new BitSet(adjs.size());
        Deque<Integer> order = new ArrayDeque<>();
        for (int i = adjs.size() - 1; i >= 0; i--) {
            if (visited.get(i) == false && hasOrder(i, adjs, visited, onStack, order) == false) return new int[0];
        }
        int[] orderArray = new int[adjs.size()];
        for (int i = 0; !order.isEmpty(); i++) orderArray[i] = order.pop();
        return orderArray;
    }

    private boolean hasOrder(int from, List<List<Integer>> adjs, BitSet visited, BitSet onStack, Deque<Integer> order) {
        visited.set(from);
        onStack.set(from);
        for (int to : adjs.get(from)) {
            if (visited.get(to) == false) {
                if (hasOrder(to, adjs, visited, onStack, order) == false) return false;
            } else if (onStack.get(to) == true) {
                return false;
            }
        }
        onStack.clear(from);
        order.push(from);
        return true;
    }




    public class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<List<Integer>> adj = new ArrayList<>(numCourses);
            for (int i = 0; i < numCourses; i++) adj.add(i, new ArrayList<>());
            for (int i = 0; i < prerequisites.length; i++) adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
            boolean[] visited = new boolean[numCourses];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < numCourses; i++) {
                if (!topologicalSort(adj, i, stack, visited, new boolean[numCourses])) return new int[0];
            }
            int i = 0;
            int[] result = new int[numCourses];
            while (!stack.isEmpty()) {
                result[i++] = stack.pop();
            }
            return result;
        }

        private boolean topologicalSort(List<List<Integer>> adj, int v, Stack<Integer> stack, boolean[] visited, boolean[] isLoop) {
            if (visited[v]) return true;
            if (isLoop[v]) return false;
            isLoop[v] = true;
            for (Integer u : adj.get(v)) {
                if (!topologicalSort(adj, u, stack, visited, isLoop)) return false;
            }
            visited[v] = true;
            stack.push(v);
            return true;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */