package HF.HF0_OA9;

import org.junit.Test;

import java.util.*;

//基础最小生成树
//Minimum Spanning Tree
public class _7MinimumSpanningTree {

    public class Connection {
        public String city1, city2;
        public int cost;

        public Connection(String city1, String city2, int cost) {
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }

        @Override
        public String toString(){
            return city1 + " " + city2 + " " + cost;
        }
    }

//-----------------------------------------------------------------------------

    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    public List<Connection> lowestCost(List<Connection> connections) {
        // Write your code here
        Collections.sort(connections, comp);
        Map<String, Integer> hash = new HashMap<String, Integer>();
        int n = 0;
        for (Connection connection : connections) {
            if (!hash.containsKey(connection.city1)) {
                hash.put(connection.city1, ++n);
            }
            if (!hash.containsKey(connection.city2)) {
                hash.put(connection.city2, ++n);
            }
        }

        int[] father = new int[n + 1];

        List<Connection> results = new ArrayList<Connection>();
        for (Connection connection : connections) {
            int num1 = hash.get(connection.city1);
            int num2 = hash.get(connection.city2);

            int root1 = find(num1, father);
            int root2 = find(num2, father);
            if (root1 != root2) {
                father[root1] = root2;
                results.add(connection);
            }
        }

        if (results.size() != n - 1)
            return new ArrayList<Connection>();
        return results;
    }

    static Comparator<Connection> comp = new Comparator<Connection>() {
        public int compare(Connection a, Connection b) {
            if (a.cost != b.cost)
                return a.cost - b.cost;
            if (a.city1.equals(b.city1)) {
                return a.city2.compareTo(b.city2);
            }
            return a.city1.compareTo(b.city1);
        }
    };

    public int find(int num, int[] father) {
        if (father[num] == 0)
            return num;

        return father[num] = find(father[num], father);
    }


//-----------------------------------------------------------------------------/

    // version: 高频题班
    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    int n = 0;

    public List<Connection> lowestCost2(List<Connection> connections) {
        // Write your code here
        List<Connection> ans = new ArrayList<>();
        UFS ufs = new UFS(connections.size() * 2);

        Collections.sort(connections, new Comparator<Connection>() {
            public int compare(Connection a, Connection b) {
                if (a.cost != b.cost) {
                    return a.cost - b.cost;
                }
                if (a.city1.equals(b.city1)) {
                    return a.city2.compareTo(b.city2);
                }
                return a.city1.compareTo(b.city1);
            }
        });

        for (Connection item : connections) {
            int c1 = getID(item.city1);
            int c2 = getID(item.city2);
            if (ufs.find(c1) != ufs.find(c2)) {
                ans.add(item);
                ufs.union(c1, c2);
            }
        }
        if (ans.size() == n - 1) {
            return ans;
        } else {
            return new ArrayList<>();
        }
    }


    Map<String, Integer> name2ID = new HashMap<>();

    public int getID(String name) {
        if (name2ID.containsKey(name)) {
            return name2ID.get(name);
        } else {
            name2ID.put(name, n++);
            return n - 1;
        }
    }
//-------------------------------------------------------------------------///
    public class UFS {
        int[] f;          // father

        public UFS(int n) {
            f = new int[n];
            for (int i = 0; i < n; i++) {
                f[i] = -1;
            }
        }

        public void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (f[a] < f[b]) {
                f[a] += f[b];
                f[b] = a;
            } else {
                f[b] += f[a];
                f[a] = b;
            }
        }

        public int find(int x) {
            if (f[x] < 0) {
                return x;
            }
            f[x] = find(f[x]);
            return f[x];
        }
    }

    @Test
    public void test01(){
        List<Connection> connections = new ArrayList<>();
        connections.add(new Connection("Acity","Bcity",1));
        connections.add(new Connection("Acity","Ccity",2));
        connections.add(new Connection("Bcity","Ccity",3));

        List result = lowestCost2(connections);
        System.out.println(result);
        //[Acity Bcity 1, Acity Ccity 2]

    }
//-------------------------------------------------------------------------///


//-----------------------------------------------------------------------------/


}
/*
Given a list of Connections, which is the Connection class (the city name at both ends of the edge and a cost between them), find some edges, connect all the cities and spend the least amount.

Return the connects if can connect all the cities, otherwise return empty list.

 Notice

Return the connections sorted by the cost, or sorted city1 name if their cost is same, or sorted city2 if their city1 name is also same.

Have you met this question in a real interview? Yes
Example
Gievn the connections = ["Acity","Bcity",1], ["Acity","Ccity",2], ["Bcity","Ccity",3]

Return ["Acity","Bcity",1], ["Acity","Ccity",2]
 */

/*
给出一些Connections，即Connections类，找到一些能够将所有城市都连接起来并且花费最小的边。
如果说可以将所有城市都连接起来，则返回这个连接方法；不然的话返回一个空列表。

 注意事项

返回cost最小的连接方法，如果cost相同就按照city1进行排序，如果city1也相同那么就按照city2进行排序。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 connections = ["Acity","Bcity",1], ["Acity","Ccity",2], ["Bcity","Ccity",3]

返回 ["Acity","Bcity",1], ["Acity","Ccity",2]
 */

/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
 */