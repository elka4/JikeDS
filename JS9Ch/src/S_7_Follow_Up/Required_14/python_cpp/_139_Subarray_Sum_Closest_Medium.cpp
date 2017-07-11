/** 139 Subarray Sum Closest
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */


class Solution {
public:
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    struct node {
        node(int _value, int _pos):value(_value), pos(_pos) {}
        int value, pos;
        bool operator<(const node &o) const{
            return (value < o.value || value == o.value && pos < o.pos);
        }
    };
    vector<int> subarraySumClosest(vector<int> nums){
        // write your code here
        vector<node> s;
        vector<int> results(2);
        s.push_back(node(0,-1));
        int sum = 0, len = nums.size();
        for (int i = 0; i < len ; ++i) {
            sum += nums[i];
            s.push_back(node(sum, i));
        }
        sort(s.begin(), s.end());
        len = s.size();
        int ans = 0x7fffffff;
        for (int i = 0; i < len-1; ++i)
            if (abs(s[i+1].value - s[i].value) < ans) {
                ans = abs(s[i+1].value - s[i].value);
                results[0] = min(s[i].pos, s[i+1].pos)+1;
                results[1] = max(s[i].pos, s[i+1].pos);
            }
        return results;
    }
};
