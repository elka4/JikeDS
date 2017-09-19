package HF.OA9;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

//拓扑排序
//Course Schedule II
public class _6CourseScheduleII {
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

    @Test
    public void test01(){
        int[][] prerequisites = {{1,0}};
        int[] result = findOrder(2,prerequisites);

        List<Integer> list =  Arrays.stream(result).boxed().collect(Collectors.toList());

        list.forEach(System.out::println);
    }

    @Test
    public void test02(){
        int[][] prerequisites = {{1,0}, {2,0}, {3,1}, {3,2}};
        int[] result = findOrder(4,prerequisites);

        List<Integer> list =  Arrays.stream(result).boxed().collect(Collectors.toList());

        list.forEach(System.out::println);
    }

/////////////////////////////////////////////////////////////////////

}
/*
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Have you met this question in a real interview? Yes
Example
Given n = 2, prerequisites = [[1,0]]
Return [0,1]

Given n = 4, prerequisites = [1,0],[2,0],[3,1],[3,2]]
Return [0,1,2,3] or [0,2,1,3]
 */