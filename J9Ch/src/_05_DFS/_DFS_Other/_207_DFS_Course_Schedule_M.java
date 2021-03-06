package _05_DFS._DFS_Other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class _207_DFS_Course_Schedule_M {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses]; // i -> j
        int[] indegree = new int[numCourses];

        for (int i=0; i<prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (matrix[pre][ready] == 0)
                indegree[ready]++; //duplicate case
            matrix[pre][ready] = 1;
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList();
        for (int i=0; i<indegree.length; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i=0; i<numCourses; i++) {
                if (matrix[course][i] != 0) {
                    if (--indegree[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }


//    Java DFS and BFS solution
//    According to my code test, BFS is much faster than DFS. From my perspective DFS searches more branches. EX: 1->3->4 //1->5->3 the first branch we need search 3's children, in second we still need to do so.
//
//    BFS:

    public class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            ArrayList[] graph = new ArrayList[numCourses];
            int[] degree = new int[numCourses];
            Queue queue = new LinkedList();
            int count=0;

            for(int i=0;i<numCourses;i++)
                graph[i] = new ArrayList();

            for(int i=0; i<prerequisites.length;i++){
                degree[prerequisites[i][1]]++;
                graph[prerequisites[i][0]].add(prerequisites[i][1]);
            }
            for(int i=0; i<degree.length;i++){
                if(degree[i] == 0){
                    queue.add(i);
                    count++;
                }
            }

            while(queue.size() != 0){
                int course = (int)queue.poll();
                for(int i=0; i<graph[course].size();i++){
                    int pointer = (int)graph[course].get(i);
                    degree[pointer]--;
                    if(degree[pointer] == 0){
                        queue.add(pointer);
                        count++;
                    }
                }
            }
            if(count == numCourses)
                return true;
            else
                return false;
        }
    }
//    DFS:

    public class Solution3 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            ArrayList[] graph = new ArrayList[numCourses];
            for(int i=0;i<numCourses;i++)
                graph[i] = new ArrayList();

            boolean[] visited = new boolean[numCourses];
            for(int i=0; i<prerequisites.length;i++){
                graph[prerequisites[i][1]].add(prerequisites[i][0]);
            }

            for(int i=0; i<numCourses; i++){
                if(!dfs(graph,visited,i))
                    return false;
            }
            return true;
        }

        private boolean dfs(ArrayList[] graph, boolean[] visited, int course){
            if(visited[course])
                return false;
            else
                visited[course] = true;;

            for(int i=0; i<graph[course].size();i++){
                if(!dfs(graph,visited,(int)graph[course].get(i)))
                    return false;
            }
            visited[course] = false;
            return true;
        }
    }
//----------------------------------------------------------------------------




//----------------------------------------------------------------------------






}
/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
click to show more hints.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.

 */

/*
现在你总共有 n 门课需要选，记为 0 到 n - 1.
一些课程在修之前需要先修另外的一些课程，比如要学习课程 0 你需要先学习课程 1 ，表示为[0,1]
给定n门课以及他们的先决条件，判断是否可能完成所有课程？

您在真实的面试中是否遇到过这个题？ Yes
样例
给定 n = 2，先决条件为 [[1,0]] 返回 true
给定 n = 2，先决条件为 [[1,0],[0,1]] 返回 false
 */