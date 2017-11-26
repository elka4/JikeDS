package _06_BFS._Topological_Sort;
import java.util.*;
import org.junit.Test;
public class _207_Topological_Course_Schedule {
//    Easy BFS Topological sort, Java
    class Solution1{
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
    }


/*
    Java DFS and BFS solution
    According to my code test, BFS is much faster than DFS.
    From my perspective DFS searches more branches.
    EX: 1->3->4 //1->5->3 the first branch we need search 3's children, in second we still need to do so.

    BFS:
*/

    public class Solution2 {
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


//    JAVA---------Easy Version To UnderStand!!!!!!!!!!!!!!!!!
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0)
            return false;
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][1]]++;
        }
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int i = 0; i < prerequisites.length; i++) {
                if (x == prerequisites[i][0]) {
                    inDegree[prerequisites[i][1]]--;
                    if (inDegree[prerequisites[i][1]] == 0)
                        queue.offer(prerequisites[i][1]);
                }
            }
        }
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] != 0)
                return false;
        }
        return true;
    }


//Concise JAVA solutions based on BFS and DFS with explanation


/*    BFS Solution: (Topological sorting)

    The basic idea is to use Topological algorithm: put NODE with 0 indgree into the queue,
    then make indegree of NODE's successor dereasing 1. Keep the above steps with BFS.

    Finally, if each node' indgree equals 0, then it is validated DAG (Directed Acyclic Graph),
    which means the course schedule can be finished.*/
    class Solution5{
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if (numCourses == 0 || prerequisites.length == 0) return true;

            // Convert graph presentation from edges to indegree of adjacent list.
            int indegree[] = new int[numCourses];
            for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
                indegree[prerequisites[i][0]]++;

            Queue<Integer> queue = new LinkedList<Integer>();
            for (int i = 0; i < numCourses; i++)
                if (indegree[i] == 0)
                    queue.add(i);

            // How many courses don't need prerequisites.
            int canFinishCount = queue.size();
            while (!queue.isEmpty()) {
                int prerequisite = queue.remove(); // Already finished this prerequisite course.
                for (int i = 0; i < prerequisites.length; i++)  {
                    if (prerequisites[i][1] == prerequisite) {
                        indegree[prerequisites[i][0]]--;
                        if (indegree[prerequisites[i][0]] == 0) {
                            canFinishCount++;
                            queue.add(prerequisites[i][0]);
                        }
                    }
                }
            }

            return (canFinishCount == numCourses);
        }
    }
/*    DFS Solution:

    The basic idea is using DFS to check if the current node was already included in the traveling path.
    In this case, we need to convert graph presentation from edge list to adjacency list first,
    and then check if there's cycle existing in any subset. Because tree is a connected graph,
    we can start from any node.

    The graph is possibly not connected, so need to check every node.

    To do memorization and pruning, a HashMap is used to save the previous results for the specific node.*/
    class Slution6{
        HashMap<Integer, Boolean>memo = new HashMap<Integer, Boolean>();//Memorization HashMap for DFS pruning
        public boolean canFinish(int n, int[][] edges) {
            if (edges.length != 0) {
                // Neighbors of each node
                HashMap<Integer, HashSet<Integer>> neighbors = new HashMap<Integer, HashSet<Integer>>();
                // Nodes on the current path
                HashSet<Integer>curPath = new HashSet<Integer>();
                // Convert graph presentation from edge list to adjacency list
                for (int i = 0; i < edges.length; i++) {
                    if (!neighbors.containsKey(edges[i][1]))
                        neighbors.put(edges[i][1], new HashSet<Integer>());
                    neighbors.get(edges[i][1]).add(edges[i][0]);
                }
                // The graph is possibly not connected, so need to check every node.
                for (int a[] : edges)
                    // Use DFS method to check if there's cycle in any curPath
                    if (hasCycle(neighbors, a[0], -1, curPath))
                        return false;
            }
            return true;
        }

        boolean hasCycle(HashMap<Integer, HashSet<Integer>> neighbors,
                         int kid, int parent, HashSet<Integer>curPath) {
            if (memo.containsKey(kid)) return memo.get(kid);
            // The current node is already in the set of the current path
            if (curPath.contains(kid)) return true;
            if (!neighbors.containsKey(kid)) return false;

            curPath.add(kid);
            for (Integer neighbor : neighbors.get(kid)) {
                // DFS
                boolean hasCycle = hasCycle(neighbors, neighbor, kid, curPath);
                memo.put(kid, hasCycle);
                if (hasCycle) return true;
            }
            curPath.remove(kid);
            return false;
        }
    }


    //OO easy to read java solution
    public class Solution7 {
        class Course {
            private boolean vis;
            private boolean done;
            private ArrayList<Course> pre = new ArrayList<Course>();

            void addPre(Course preCourse) {
                pre.add(preCourse);
            }

            boolean isCyclic() {
                if( done ) {
                    return false;
                }
                if( vis ) {
                    return true;
                }
                vis = true;

                for(Course preCourse: pre ) {
                    if( preCourse.isCyclic() ) {
                        return true;
                    }
                }

                vis = false;
                done = true;
                return false;
            }
        }


        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Course clist[] = new Course[numCourses];

            for(int i=0; i<numCourses; i++) {
                clist[i] = new Course();
            }

            for(int[] couple: prerequisites ) {
                Course c1 = clist[couple[0]];
                Course c2 = clist[couple[1]];
                c1.addPre(c2);
            }

            for(int i=0; i<numCourses; i++) {
                if( clist[i].isCyclic() ) {
                    return false;
                }
            }

            return true;
        }
    }

    //We had basically the same idea but I moved the isCyclic() out to look more OO.
    class Solution8{
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Course[] courses = new Course[numCourses];
            for (int i = 0; i < numCourses; i++) {
                courses[i] = new Course();
            }
            for (int i = 0; i < prerequisites.length; i++) {
                courses[prerequisites[i][0]].add(courses[prerequisites[i][1]]);
            }
            for (int i = 0; i < numCourses; i++) {
                if(isCyclic(courses[i])) return false;
            }
            return true;
        }

        private boolean isCyclic(Course cur) {
            if (cur.tested == true) return false;
            if (cur.visited == true) return true;
            cur.visited = true;
            for (Course c : cur.pre) {
                if (isCyclic(c)) {
                    return true;
                }
            }
            cur.tested = true;
            return false;
        }

        class Course {
            boolean visited = false;
            boolean tested = false;
            List<Course> pre = new ArrayList<Course>();
            public void add(Course c) {
                pre.add(c);
            }
        }
    }

    // My Java BFS solution
    public class Solution9 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
            int[] indegree = new int[numCourses];
            Queue<Integer> queue = new LinkedList<Integer>();
            int count = numCourses;
            for (int i = 0; i < numCourses; i++) {
                map.put(i, new ArrayList<Integer>());
            }
            for (int i = 0; i < prerequisites.length; i++) {
                map.get(prerequisites[i][0]).add(prerequisites[i][1]);
                indegree[prerequisites[i][1]]++;
            }
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }
            while (!queue.isEmpty()) {
                int current = queue.poll();
                for (int i : map.get(current)) {
                    if (--indegree[i] == 0) {
                        queue.offer(i);
                    }
                }
                count--;
            }
            return count == 0;
        }
    }

//----------------------------------------------------------------------------
    // 9Ch
public class Jiuzhang {

    /**
     * @param numCourses a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Write your code here
        List[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];

        Arrays.fill(edges, new ArrayList<Integer>());
//        Arrays.fill(edges, new ArrayList<>()); 不行
        for (int i = 0;i < numCourses; i++)
            edges[i] = new ArrayList<Integer>();

        /*
        如果没有出现在prerequisites里，就是不需要任何先修课程，
        那么degree[prerequisites[i][0]]就保持为0，就是不需要任何先修课程
         */
        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]] ++ ;
            edges[prerequisites[i][1]].add(prerequisites[i][0]); // 1 决定 0
        }

        //上一步忽略的，也就是没有先修课程的课程（degree为0）入Queue
        Queue queue = new LinkedList();
        for(int i = 0; i < degree.length; i++){
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()){
            int course = (int)queue.poll(); //没有先修课程的出列
            count ++;
            int n = edges[course].size(); // course这门课能决定的课程数量
            //对于课程course能决定的每一门课，degree--，如果degree为0就入列
            for(int i = 0; i < n; i++){
                int pointer = (int)edges[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    queue.add(pointer);
                }
            }
        }

        return count == numCourses;
    }
}
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