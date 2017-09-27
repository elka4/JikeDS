

class Solution {
public:
    /**
     * @param expression: A string array
     * @return: The Polish notation of this expression
     */
    int getLevel(string opt) {
        if (opt == "+" || opt == "-")
            return 1;
        if (opt == "*" || opt == "/")
            return 2;

        return 0;
    }
    vector<string> convertToPN(vector<string> &expression) {
        // write your code here
        stack<string> stk;
        stack<string> cal;
        vector<string> PN;
    	int len = expression.size();
	    for(int i = len-1; i >= 0; i--)
	    {
		    if(expression[i] == ")")
                cal.push(expression[i]);
            else if (expression[i] == "(") {
                    while(cal.top() != ")") {
				        stk.push(cal.top());
				        cal.pop();
			        }
                    cal.pop();
            } else if (expression[i] == "*" || expression[i] == "/" ||
                            expression[i] == "+" || expression[i] == "-") {
		        if(!cal.empty()) {
			            if( cal.top() != ")" ) {
				            while (getLevel(cal.top()) > getLevel(expression[i])) {
					            stk.push(cal.top());
					            cal.pop();
					            if(cal.empty())
						            break;
				            }
			            }
		        }
			    cal.push(expression[i]);
            } else
                stk.push(expression[i]);
	    }

	    while (!cal.empty()) {
		    stk.push(cal.top());
		    cal.pop();
	    }
	    while (!stk.empty()) {
            PN.push_back(stk.top());
		    stk.pop();
	    }
        return PN;
    }
};