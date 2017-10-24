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

    //jiuzhang
    public class Jiuzhang {
        /**
         * @param numCourses a total of n courses
         * @param prerequisites a list of prerequisite pairs
         * @return the course order
         */
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // Write your code here
            List[] edges = new ArrayList[numCourses];
            int[] degree = new int[numCourses];

            for (int i = 0;i < numCourses; i++)
                edges[i] = new ArrayList<Integer>();

            for (int i = 0; i < prerequisites.length; i++) {
                degree[prerequisites[i][0]] ++ ;
                edges[prerequisites[i][1]].add(prerequisites[i][0]);
            }

            Queue queue = new LinkedList();
            for(int i = 0; i < degree.length; i++){
                if (degree[i] == 0) {
                    queue.add(i);
                }
            }

            int count = 0;
            int[] order = new int[numCourses];
            while(!queue.isEmpty()){
                int course = (int)queue.poll();
                order[count] = course;
                count ++;
                int n = edges[course].size();
                for(int i = n - 1; i >= 0 ; i--){
                    int pointer = (int)edges[course].get(i);
                    degree[pointer]--;
                    if (degree[pointer] == 0) {
                        queue.add(pointer);
                    }
                }
            }

            if (count == numCourses)
                return order;

            return new int[0];
        }
    }

//////////////////////////////////////////////////////////////////////////////////////






}
/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
click to show more hints.

Hints:
This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
 */