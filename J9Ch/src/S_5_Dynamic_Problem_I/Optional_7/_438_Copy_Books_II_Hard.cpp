
class Solution {
public:
    /**
     * @param n: an integer
     * @param times: a vector of integers
     * @return: an integer
     */
    int copyBooksII(int n, vector<int> &times) {
        // write your code here
        int k = times.size(), ans;
        vector<int> eachTime, totalTime;
        for (int i=0; i<k; ++i) heapAdd(eachTime, totalTime, times[i], 0);
        for (int i=0; i<n; ++i) {
            ans = totalTime[0];
            int x = eachTime[0];
            heapDelete(eachTime, totalTime);
            heapAdd(eachTime, totalTime, x, ans+x);
        }
        ans = 0;
        for (int i=0; i<totalTime.size(); ++i) ans = max(ans, totalTime[i]);
        return ans;
    }
private:
    void heapAdd(vector<int> &eachTime, vector<int> &totalTime, int et, int tt) {
        eachTime.push_back(et);
        totalTime.push_back(tt);
        int t = eachTime.size()-1;
        while (t>0) {
            int temp = (t-1)/2;
            if (eachTime[t]+totalTime[t]>=eachTime[temp]+totalTime[temp]) break;
            swap(eachTime[t], eachTime[temp]);
            swap(totalTime[t], totalTime[temp]);
            t = temp;
        }
    }
    void heapDelete(vector<int> &eachTime, vector<int> &totalTime) {
        int t = eachTime.size()-1;
        eachTime[0] = eachTime[t];
        eachTime.pop_back();
        totalTime[0] = totalTime[t];
        totalTime.pop_back();
        t = 0;
        while (t*2+1<eachTime.size()) {
            int temp = t*2+1;
            if (temp+1<eachTime.size() && eachTime[temp]+totalTime[temp]>eachTime[temp+1]+totalTime[temp+1]) ++temp;
            if (eachTime[t]+totalTime[t]<=eachTime[temp]+totalTime[temp]) break;
            swap(eachTime[t], eachTime[temp]);
            swap(totalTime[t], totalTime[temp]);
            t = temp;
        }
    }
};