
class NestedIterator {
public:
    stack<NestedInteger> st;
    NestedIterator(vector<NestedInteger> &nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            st.push(nestedList[i]);
        }
    }

    int next() {
        int ans =  st.top().getInteger();
        st.pop();
        return ans;
    }

    bool hasNext() {
        while (!st.empty()) {
            if (st.top().isInteger()) {
                return true;
            }
            vector<NestedInteger>  nestedList = st.top().getList();
            st.pop();
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                st.push(nestedList[i]);
            }
        }
        return false;
    }
};