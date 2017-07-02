package Depth_first_Search_54;
import java.util.*;
/**
 * Created by tianhuizhu on 6/19/17.
 */
public class b_207_Course_Schedule {
    //Easy BFS Topological sort, Java
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

    //Java DFS and BFS solution

    //BFS
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

    //DFS
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

    //OO easy to read java solution

    public class Solution4 {
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

}
