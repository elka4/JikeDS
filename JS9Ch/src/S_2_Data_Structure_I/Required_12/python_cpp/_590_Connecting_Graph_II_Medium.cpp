


class ConnectingGraph2 {
private:
    vector<int> father;
    vector<int> size;
public:
    int find(int x) {
        if (father[x] == 0)
            return x;

        return father[x] = find(father[x]);
    }

    ConnectingGraph2(int n) {
        // initialize your data structure here.
        father.resize(n + 1);
        size.resize(n + 1);
        for (int i = 1; i <= n; ++i) {
            father[i] = 0;
            size[i] = 1;
        }
    }

    void  connect(int a, int b) {
        // Write your code here
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            father[root_a] = root_b;
            size[root_b] += size[root_a];
        }
    }

    int query(int a) {
        // Write your code here
        int root_a = find(a);
        return size[root_a];
    }
};