
class Solution {
public:
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    int largestRectangleArea(vector<int> &height) {
        // write your code here
        if(height.size() == 0) return 0;
        int res = 0;
        vector<int> tmp = height;
        tmp.push_back(0);  // Important

        stack<int> s;
        for(int i = 0; i < tmp.size(); i++)
        {
            if(s.empty() || (!s.empty() && tmp[i] >= tmp[s.top()])) s.push(i);
            else{
                while(!s.empty() && tmp[s.top()] > tmp[i])
                {
                    int idx = s.top(); s.pop();
                    int width = s.empty() ? i : (i-s.top()-1);
                    res = max(res, tmp[idx] * width);
                }
                s.push(i);  // Important
            }
        }
        return res;
    }
};
