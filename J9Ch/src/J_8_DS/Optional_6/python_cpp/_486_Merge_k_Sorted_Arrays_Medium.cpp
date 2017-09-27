/** 486 Merge k Sorted Arrays */


class Node {
public:
    int row, col, val;
    Node (int r, int c, int v) : row(r), col(c), val(v) {};
    bool operator < (const Node &obj) const {
        return val > obj.val;
    }
};

class Solution {
public:
    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    vector<int> mergekSortedArrays(vector<vector<int>>& arrays) {
        vector<int> result;
        if (arrays.empty())
            return result;

        priority_queue<Node> queue;
        for (int i = 0; i < arrays.size(); ++i) {
            if (!arrays[i].empty())
                queue.push(Node(i, 0, arrays[i][0]));
        }

        while (!queue.empty()) {
            Node curr = queue.top();
            queue.pop();
            result.push_back(curr.val);
            if (curr.col + 1 < arrays[curr.row].size())
                queue.push(Node(curr.row, curr.col + 1,
                            arrays[curr.row][curr.col + 1]));
        }

        return result;
    }
};