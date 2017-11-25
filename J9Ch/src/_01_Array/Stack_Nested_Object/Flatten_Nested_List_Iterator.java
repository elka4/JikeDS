package _01_Array.Stack_Nested_Object;

/*
LeetCode – Flatten Nested List Iterator (Java)

Given a nested list of integers, implement an iterator to flatten it. Each element is either an integer, or a list -- whose elements may also be integers or other lists.

For example, given the list [[1,1],2,[1,1]], by calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 */
public class Flatten_Nested_List_Iterator {
    //Java Solution 1
/*
    public class NestedIterator implements Iterator<Integer> {
        Stack<NestedInteger> stack = new Stack<NestedInteger>();

        public NestedIterator(List<NestedInteger> nestedList) {
            if(nestedList==null)
                return;

            for(int i=nestedList.size()-1; i>=0; i--){
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while(!stack.isEmpty()){
                NestedInteger top = stack.peek();
                if(top.isInteger()){
                    return true;
                }else{
                    stack.pop();
                    for(int i=top.getList().size()-1; i>=0; i--){
                        stack.push(top.getList().get(i));
                    }
                }
            }

            return false;
        }
    }
    //Java Solution 2

    public class NestedIterator implements Iterator<Integer> {
        Stack<Iterator<NestedInteger>> stack = new Stack<Iterator<NestedInteger>>();
        Integer current;

        public NestedIterator(List<NestedInteger> nestedList) {
            if(nestedList==null)
                return;

            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            Integer result = current;
            current = null;
            return result;
        }

        @Override
        public boolean hasNext() {
            while(!stack.isEmpty() && current==null){
                Iterator<NestedInteger> top = stack.peek();
                if(!top.hasNext()){
                    stack.pop();
                    continue;
                }

                NestedInteger n = top.next();
                if(n.isInteger()){
                    current = n.getInteger();
                    return true;
                }else{
                    stack.push(n.getList().iterator());
                }
            }

            return false;
        }
    }*/



//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






}
