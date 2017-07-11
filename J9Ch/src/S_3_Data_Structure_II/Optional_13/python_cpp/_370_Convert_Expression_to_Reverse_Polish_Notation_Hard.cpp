

class Solution {
public:
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    int getLevel(string opt) {
        if (opt == "(")
            return 0;
        if (opt == "+" || opt == "-")
            return 1;
        if (opt == "*" || opt == "/")
            return 2;

        return 3;
    }

    bool isOperator(string c) {
        return (c == "+" || c == "-" || c == "*" || c == "/");
    }

    vector<string> convertToRPN(vector<string> &expression) {
        // write your code here
        stack<string> st;
        vector<string> RPN;
        int len = expression.size();
        for (int i = 0; i < len; ++i) {
            string c = expression[i];
            if (c == "(")
                st.push(c);
            else if (c == ")") {
                while (st.top() != "(") {
                    RPN.push_back(st.top());
                    st.pop();
                }
                st.pop();
            } else {
                if (!isOperator(c))
                    st.push(c);
                else {
                    while (!st.empty() && getLevel(st.top()) >= getLevel(c)) {
                            RPN.push_back(st.top());
                            st.pop();
                    }
                    st.push(c);
                }
            }
        }

        while (! st.empty()) {
            RPN.push_back(st.top());
            st.pop();
        }

        return RPN;
    }
};