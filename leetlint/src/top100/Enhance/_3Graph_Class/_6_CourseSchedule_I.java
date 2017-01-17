package top100.Enhance._3Graph_Class;

import java.util.*;

public class _6_CourseSchedule_I {
    public boolean canFinish_DFS (int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0
              || prerequisites[0] == null || prerequisites[0].length == 0)
            return true;

        //Represent the graph
        List<List<Integer>> neighborList = new ArrayList<List<Integer>>();
        initLists(prerequisites, neighborList, numCourses);
        //Using status lable
        int[] status = new int[numCourses];
        //Depth First Search
        for (int i = 0; i < numCourses; i++) {
            //Find an entrance
            if (status[i] == 0 && !dfsHelper(neighborList, status, i)) {
                return false;
            }
        }
        return true;
}
private void initLists(int[][] prerequisites,
                    List<List<Integer>> neighborList, int numCourses) {
      //Create Lists
      for (int i = 0; i < numCourses; i++) {
          List<Integer> neighbor = new ArrayList<Integer>();
          neighborList.add(neighbor);
      }
      //Add Neighbors
      for (int[] vector: prerequisites) {
          neighborList.get(vector[1]).add(vector[0]);
      }
}
private boolean dfsHelper(List<List<Integer>> neighborList,
                                      int[] status, int cur) {
    status[cur] = -1;
    for (int i : neighborList.get(cur)) {
        if(status[i] == -1 || !dfsHelper(neighborList, status, i))
            return false;
    }
    status[cur] = 1;
    return true;
}
public boolean canFinish_BFS (int numCourses, int[][] prerequisites) {
    if (prerequisites == null || prerequisites.length == 0 ||
            prerequisites[0] == null || prerequisites[0].length != 2)
        return true;
    //Represent the graph
    List<List<Integer>> neighborList = new ArrayList<List<Integer>>();
    //Using inCount
    int[] inCount = new int[numCourses];
    initListsAndInCount(prerequisites, neighborList, numCourses, inCount);
    //Breadth First Search
    int[] finished = {0};
    Deque<Integer> queue = new LinkedList<Integer>();
    for (int i = 0; i < numCourses; i++) {
        //Find an entrance and put into queue
        if (inCount[i] == 0) {
            queue.offerLast(i);
        }
    }
    bfsHelper(neighborList, inCount, queue, finished);
    return finished[0] == numCourses;
}

private void bfsHelper(List<List<Integer>> neighborList, int[] inCount,
                              Deque<Integer> queue, int[] finished) {
    while (!queue.isEmpty()) {
        //queue.pollFirst() represents this node is finished
        int cur = queue.pollFirst();
        finished[0]++;
        for (int next : neighborList.get(cur)) {
            if (--inCount[next] == 0) {
                //Queue maintains all nodes with inCount == 0
                queue.offerLast(next);
            }
        }
    }
}
private void initListsAndInCount (int[][] prerequisites,
      List<List<Integer>> neighborList, int numCourses, int[] inCount) {
    //Create Lists
    for (int i = 0; i < numCourses; i++) {
        List<Integer> neighbor = new ArrayList<Integer>();
        neighborList.add(neighbor);
    }
    //Add Neighbors
    for (int[] vector: prerequisites) {
        inCount[vector[0]]++;
        neighborList.get(vector[1]).add(vector[0]);
    }
}
}

