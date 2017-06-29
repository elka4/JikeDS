



class Node {
public:
    int value, x, y;
    Node(int _v, int _x, int _y) {
        value = _v;
        x = _x;
        y = _y;
    }
    bool operator < (const Node& obj) const {
        return value > obj.value;
    }
};

class Solution {
public:
    /**
     * @param A an integer arrays sorted in ascending order
     * @param B an integer arrays sorted in ascending order
     * @param k an integer
     * @return an integer
     */
    int kthSmallestSum(vector<int>& A, vector<int>& B, int k) {
        // Write your code here
        int n = A.size();
        int m = B.size();
        priority_queue<Node> q;
        q.push(Node(A[0] + B[0], 0, 0));
        map<int, bool> mp;
        int ans = -1;
        mp[0] = true;
        for (int i = 0; i < k; ++i) {
            Node tmp = q.top(); q.pop();
            ans = tmp.value;
            int x = tmp.x, y = tmp.y;
            if (x + 1 < n) {
                int data = (x + 1) * m + y;
                if (mp.find(data) == mp.end()) {
                    mp[data] = true;
                    q.push(Node(A[x + 1] + B[y], x + 1, y));
                }
            }
            if (y + 1 < m) {
                int data = x * m + y + 1;
                if (mp.find(data) == mp.end()) {
                    mp[data] = true;
                    q.push(Node(A[x] + B[y + 1], x, y + 1));
                }
            }
        }
        return ans;
    }
};