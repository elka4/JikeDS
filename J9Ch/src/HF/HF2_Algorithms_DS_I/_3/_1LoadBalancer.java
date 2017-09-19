package HF.HF2_Algorithms_DS_I._3;

import java.util.*;

// Load Balancer
public class _1LoadBalancer {

    public class LoadBalancer {

        private Map<Integer, Integer> dict = null;
        private List<Integer> servers = null;

        public LoadBalancer() {
            // Initialize your data structure here.
            dict = new HashMap<Integer, Integer>();
            servers = new ArrayList<Integer>();
        }

        // @param server_id add a new server to the cluster
        // @return void
        public void add(int server_id) {
            // Write your code here
            int m = servers.size();
            dict.put(server_id, m);
            servers.add(server_id);
        }

        // @param server_id server_id remove a bad server from the cluster
        // @return void
        public void remove(int server_id) {
            // Write your code here
            int index = dict.get(server_id);
            int m = servers.size();
            dict.put(servers.get(m - 1), index);
            servers.set(index, servers.get(m - 1));
            servers.remove(m - 1);
            dict.remove(server_id);
        }

        // @return pick a server in the cluster randomly with equal probability
        public int pick() {
            // Write your code here
            Random r = new Random();
            int m = servers.size();
            int index = Math.abs(r.nextInt()) % m;
            return servers.get(index);
        }
    }

/////////////////////////////////////////////////////////////

    // version: 高频题班
    public class LoadBalancer2 {
        int n = 0;
        Map<Integer, Integer> pos = new HashMap<>();
        List<Integer> array = new ArrayList<>();
        Random rand = new Random();

        public LoadBalancer2() {
            // Initialize your data structure here.
        }

        // @param server_id add a new server to the cluster
        // @return void
        public void add(int server_id) {
            // Write your code here
            if (!pos.containsKey(server_id)) {
                array.add(server_id);
                pos.put(server_id, n);
                n++;
            }
        }

        // @param server_id server_id remove a bad server from the cluster
        // @return void
        public void remove(int server_id) {
            // Write your code here
            if (pos.containsKey(server_id)) {
                int lastItem = array.get(n - 1);
                int removeIdx = pos.get(server_id);

                pos.put(lastItem, removeIdx);
                array.set(removeIdx, lastItem);

                pos.remove(server_id);
                array.remove(n - 1);
                n--;
            }
        }

        // @return pick a server in the cluster randomly with equal probability
        public int pick() {
            // Write your code here
            return array.get(rand.nextInt(n));
        }
    }

/////////////////////////////////////////////////////////////

}
/*

Implement a load balancer for web servers. It provide the following functionality:

Add a new server to the cluster => add(server_id).
Remove a bad server from the cluster => remove(server_id).
Pick a server in the cluster randomly with equal probability => pick().
Have you met this question in a real interview? Yes
Example
At beginning, the cluster is empty => {}.

add(1)
add(2)
add(3)
pick()
>> 1         // the return value is random, it can be either 1, 2, or 3.
pick()
>> 2
pick()
>> 1
pick()
>> 3
remove(1)
pick()
>> 2
pick()
>> 3
pick()
>> 3
 */


/*
为网站实现一个负载均衡器，提供如下的 3 个功能：

添加一台新的服务器到整个集群中 => add(server_id)。
从集群中删除一个服务器 => remove(server_id)。
在集群中随机（等概率）选择一个有效的服务器 => pick()。
您在真实的面试中是否遇到过这个题？ Yes
样例
最开始时，集群中一台服务器都没有。

add(1)
add(2)
add(3)
pick()
>> 1         // 返回值是随机的，这里是 1 或者 2 或者 3 都正确。
pick()
>> 2
pick()
>> 1
pick()
>> 3
remove(1)
pick()
>> 2
pick()
>> 3
pick()
>> 3
 */