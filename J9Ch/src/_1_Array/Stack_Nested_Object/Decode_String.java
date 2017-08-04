package _1_Array.Stack_Nested_Object;
import java.util.*;

/*
LeetCode – Decode String (Java)

Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the
square brackets is being repeated exactly k times. Note that k is guaranteed
to be a positive integer.

You may assume that the input string is always valid; No extra white spaces,
square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits
and that digits are only for those repeat numbers, k. For example,
there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */


public class Decode_String {

    class Node{
        int num;
        ArrayList<Node> list;
        char symbol;
        boolean isList;

        public Node(char s){
            symbol=s;
        }

        public Node(int n){
            list = new ArrayList<Node>();
            isList=true;
            num=n;
        }

        public String toString(){
            String s = "";
            if(isList){
                s += num + ":" + list.toString();
            }else{
                s += symbol;
            }
            return s;
        }
    }

    public class Solution {
        public String decodeString(String s) {
            int i = 0;
            Stack<Node> stack = new Stack<Node>();

            stack.push(new Node(1));

            String t = "";
            while (i < s.length()) {
                char c = s.charAt(i);

                // new Node
                if (c >= '0' && c <= '9') {
                    t += c;

                } else if (c == '[') {
                    if (t.length() > 0) {
                        int num = Integer.parseInt(t);
                        stack.push(new Node(num));
                        t = "";
                    }
                } else if (c == ']') {
                    Node top = stack.pop();

                    if (stack.isEmpty()) {

                    } else {
                        stack.peek().list.add(top);
                    }

                } else {
                    stack.peek().list.add(new Node(c));
                }

                i++;
            }

            return getString(stack.peek());
        }

        public String getString(Node node){
            String s="";
            if(node.isList){
                for(int i=0; i<node.num; i++){
                    for(Node t: node.list)
                        s+= getString(t);
                }
            }else{
                s+=node.symbol;
            }

            return s;
        }
    }



////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////







}
