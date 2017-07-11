/** 616. Course Schedule II
 * Medium*/


class Solution {
public:
    /**
     * @param numCourses a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return the course order
     */
    vector<int> findOrder(int numCourses, vector<pair<int, int>>& prerequisites) {
        // Write your code here
        vector<unordered_multiset<int>> edges(numCourses);
        vector<int> d(numCourses, 0);
        for(int i = 0; i < prerequisites.size(); ++ i) {
            edges[prerequisites[i].second].insert(prerequisites[i].first);
            d[prerequisites[i].first] ++;
        }

        queue<int> q;
        for (int i = 0; i < numCourses; ++i)
            if (d[i] == 0)
                q.push(i);

        vector<int> order;
        while (!q.empty()) {
            int x = q.front(); q.pop();
            order.push_back(x);
            for(auto it = edges[x].begin(); it != edges[x].end(); ++ it) {
                -- d[*it];
                if (d[*it] == 0) {
                    q.push(*it);
                }
            }
        }
        if (order.size() == numCourses)
            return order;
        return vector<int>();
    }
};