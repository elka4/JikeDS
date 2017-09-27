package S_2_DS_I.Required_12;

/** 590 Connecting Graph II


 * Created by tianhuizhu on 6/28/17.
 */
public class _590_Connecting_Graph_II {

    public class ConnectingGraph2 {

        private int[] father = null;
        private int[] size = null;

        private int find(int x) {
            if (father[x] == x) {
                return x;
            }
            return father[x] = find(father[x]);
        }

        public ConnectingGraph2(int n) {
            // initialize your data structure here.
            father = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                father[i] = i;
                size[i] = 1;
            }
        }

        public void connect(int a, int b) {
            // Write your code here
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                father[root_a] = root_b;
                size[root_b] += size[root_a];
            }
        }

        public int query(int a) {
            // Write your code here
            int root_a = find(a);
            return size[root_a];
        }
    }
}
