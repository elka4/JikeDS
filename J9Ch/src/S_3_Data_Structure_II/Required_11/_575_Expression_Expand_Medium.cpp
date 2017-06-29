
class Solution {
public:
    /**
     * @param s  an expression includes numbers, letters and brackets
     * @return a string
     */
    string expressionExpand(string& s) {
        // Write your code here
        if (s.find('[') == string::npos)
            return s;

        int n = s.size(), times;
        string left_str, right_str;

        if (isLetter(s[0])) {
            int index = 0;
            while (index < n && isLetter(s[index]))
                index ++;
            times = 1;
            left_str = s.substr(0, index);
            right_str = s.substr(index);
        } else {
            int left = s.find('[');
            int right = 0;
            string times_str = s.substr(0, left);
            times = atoi(times_str.c_str());
            int pair = 0;
            for (int index = left; index < n; ++index) {
                if (s[index] == '[')
                    pair += 1;
                else if (s[index] == ']')
                    pair -= 1;

                if (pair == 0) {
                    right = index;
                    break;
                }
            }
            left_str = s.substr(left + 1, right - left - 1);
            right_str = s.substr(right + 1, n - right - 1);
        }

        string expand_left_str = expressionExpand(left_str);
        string expand_right_str = expressionExpand(right_str);
        string result = "";
        for (int i = 0; i < times; ++i)
            result += expand_left_str;
        return result + expand_right_str;
    }

    bool isLetter(char s) {
        return s >= 'a' && s <= 'z' ||
               s >= 'A' && s <= 'Z';
    }
};