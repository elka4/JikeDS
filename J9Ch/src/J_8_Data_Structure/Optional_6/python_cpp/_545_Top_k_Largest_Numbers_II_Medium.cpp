/** 545 Top k Largest Numbers II
*/


bool cmp(const int& a, const int& b) {
    return a > b;
}

class Solution {
private:
    priority_queue<int, vector<int>, greater<int>> queue;
    int k;

public:
    Solution(int k) {
        this->k = k;
    }

    void add(int num) {
        if (queue.size() < k) {
            queue.push(num);
        } else if (queue.top() < num) {
            queue.pop();
            queue.push(num);
        }
    }

    vector<int> topk() {
        vector<int> topk;
        int n = queue.size();
        for (int i = 0; i < k && i < n ; ++i) {
            topk.push_back(queue.top());
            queue.pop();
        }

        for (int i = 0; i < n; ++i)
            queue.push(topk[i]);

        sort(topk.begin(), topk.end(), cmp);
        return topk;
    }
};
.  京ICP备16004690号-1