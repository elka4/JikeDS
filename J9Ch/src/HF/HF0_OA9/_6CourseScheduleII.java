package HF.HF0_OA9;

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

//------------------------------------------------------------------------------

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

//------------------------------------------------------------------------------

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

/*
你需要去上n门九章的课才能获得offer，这些课被标号为 0 到 n-1 。
有一些课程需要“前置课程”，比如如果你要上课程0，你需要先学课程1，我们用一个匹配来表示他们： [0,1]

给你课程的总数量和一些前置课程的需求，返回你为了学完所有课程所安排的学习顺序。

可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

您在真实的面试中是否遇到过这个题？ Yes
样例
给定 n = 2, prerequisites = [[1,0]]
返回 [0,1]

给定 n = 4, prerequisites = [1,0],[2,0],[3,1],[3,2]]
返回 [0,1,2,3] or [0,2,1,3]
 */