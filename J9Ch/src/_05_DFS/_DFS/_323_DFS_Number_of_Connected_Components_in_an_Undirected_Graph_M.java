package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _323_DFS_Number_of_Connected_Components_in_an_Undirected_Graph_M {

    class Solution{
        private int[] father;
        public int countComponents(int n, int[][] edges) {

            Set<Integer> set = new HashSet<Integer>();
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
            for (int i = 0; i < edges.length; i++) {
                union(edges[i][0], edges[i][1]);
            }

            for (int i = 0; i < n; i++){
                set.add(find(i));
            }
            return set.size();
        }

        int find(int node) {
            if (father[node] == node) {
                return node;
            }
            father[node] = find(father[node]);
            return father[node];
        }

        void union(int node1, int node2) {
            father[find(node1)] = find(node2);
        }
    }


    class Solution2{
        public int countComponents(int n, int[][] edges) {
            int res = n;

            int[] root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
            for (int[] pair : edges) {
                int rootX = findRoot(root, pair[0]);
                int rootY = findRoot(root, pair[1]);
                if (rootX != rootY) {
                    root[rootY] = rootX;
                    res--;
                }
            }
            return res;
        }
        public int findRoot(int[] root, int i) {
            while (root[i] != i) i = root[i];
            return i;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */