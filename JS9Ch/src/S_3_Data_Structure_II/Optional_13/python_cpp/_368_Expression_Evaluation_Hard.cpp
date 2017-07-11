

class Solution {
public:
    /**
     * @param expression: a vector of strings;
     * @return: an integer
     */
    int evaluateExpression(vector<string> &expression) {
        // write your code here
        if (expression.size()==0) return 0;
        vector<string> stack;
        vector<int> suffixExpression;
        for (int i=0; i<expression.size(); ++i)
            if (isDigit(expression[i])) suffixExpression.push_back(alterDigit(expression[i]));
            else pushInto(suffixExpression, stack, expression[i]);
        while (stack.size()>0) {
            calc(suffixExpression, stack.back());
            stack.pop_back();
        }
        if (suffixExpression.size()>0) return suffixExpression[0];
        else return 0;
    }

private:
    bool isDigit(string &str) {
        if (str.length()>1) return true;
        if (str[0]>='0' && str[0]<='9') return true;
        else return false;
    }

    int alterDigit(string &str) {
        int a = 0;
        for (int i=0; i<str.length(); ++i) a = a*10+str[i]-48;
        return a;
    }

    void pushInto(vector<int> &suffixExpression, vector<string> &stack, string &str) {
        if (str==")") {
            while (stack.back()!="(") {
                calc(suffixExpression, stack.back());
                stack.pop_back();
            }
            stack.pop_back();
        }
        else {
            while (!stack.empty() && cmp(stack.back(), str)) {
                calc(suffixExpression, stack.back());
                stack.pop_back();
            }
            stack.push_back(str);
        }
    }

    void calc(vector<int> &suffixExpression, string &str) {
        int b = suffixExpression.back();
        suffixExpression.pop_back();
        int a = suffixExpression.back();
        suffixExpression.pop_back();
        if (str=="+") suffixExpression.push_back(a+b);
        else
            if (str=="-") suffixExpression.push_back(a-b);
            else
                if (str=="*") suffixExpression.push_back(a*b);
                else suffixExpression.push_back(a/b);
    }

    bool cmp(string &stra, string &strb) {
        int a, b;
        if (stra=="+" || stra=="-") a = 1;
        else
            if (stra=="(") a = 0;
            else a = 2;
        if (strb=="+" || strb=="-") b = 1;
        else
            if (strb=="(") b = 3;
            else b = 2;
        if (a<b) return false;
        else return true;
    }
};