/**625 Course Schedule*/



class Solution {
public:
    /**
     * @param numCourses a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return true if can finish all courses or false
     */
    bool canFinish(int numCourses, vector<pair<int, int>>& prerequisites) {
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

        int node = 0;
        while (!q.empty()) {
            int x = q.front(); q.pop();
            node ++;
            for(auto it = edges[x].begin(); it != edges[x].end(); ++ it) {
                -- d[*it];
                if (d[*it] == 0) {
                    q.push(*it);
                }
            }
        }
        return node == numCourses;
    }
};