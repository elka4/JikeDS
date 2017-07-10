


class ConnectingGraph {
private:
    vector<int> father;
public:
    int find(int x) {
        if (father[x] == 0)
            return x;

        return father[x] = find(father[x]);
    }

    ConnectingGraph(int n) {
        // initialize your data structure here.
        father.resize(n + 1);
        for (int i = 1; i <= n; ++i)
            father[i] = 0;
    }

    void  connect(int a, int b) {
        // Write your code here
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b)
            father[root_a] = root_b;
    }

    bool query(int a, int b) {
        // Write your code here
        int root_a = find(a);
        int root_b = find(b);
        return root_a == root_b;
    }
};