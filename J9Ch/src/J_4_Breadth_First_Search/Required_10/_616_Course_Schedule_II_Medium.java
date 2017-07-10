package J_4_Breadth_First_Search.Required_10;
import java.util.*;import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;
/** 616. Course Schedule II
 * Medium

 * Created by tianhuizhu on 6/27/17.
 */
public class _616_Course_Schedule_II_Medium {


    /**
     * @param numCourses    a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Write your code here
        List[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];

        for (int i = 0; i < numCourses; i++)
            edges[i] = new ArrayList<Integer>();

        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]]++;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        Queue queue = new LinkedList();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        int[] order = new int[numCourses];
        while (!queue.isEmpty()) {
            int course = (int) queue.poll();
            order[count] = course;
            count++;
            int n = edges[course].size();
            for (int i = n - 1; i >= 0; i--) {
                int pointer = (int) edges[course].get(i);
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
    /*
    Given n = 2, prerequisites = [[1,0]]
Return [0,1]

Given n = 4, prerequisites = [1,0],[2,0],[3,1],[3,2]]
Return [0,1,2,3] or [0,2,1,3]
     */
    @Test
    public void test01(){
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[] result = findOrder(numCourses, prerequisites);
        for (int i: result
             ) {
            System.out.println(i);
        }
    }
}
