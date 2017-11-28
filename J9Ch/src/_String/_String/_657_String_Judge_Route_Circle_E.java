package _String._String;
import java.util.*;
import org.junit.Test;

//  657. Judge Route Circle
//  https://leetcode.com/problems/judge-route-circle/
//
//  Friend Circles
//  6:
//
public class _657_String_Judge_Route_Circle_E {
//------------------------------------------------------------------------------
    //1
    //[C++] [Java] Clean Code
    public class Solution1 {
        public boolean judgeCircle(String moves) {
            int x = 0;
            int y = 0;
            for (char ch : moves.toCharArray()) {
                if (ch == 'U') y++;
                else if (ch == 'D') y--;
                else if (ch == 'R') x++;
                else if (ch == 'L') x--;
            }
            return x == 0 && y == 0;
        }
    }
//------------------------------------------------------------------------------
    //2
//    Easy 2 lines Java
//    Easy solution using split.(It needs spaces from front and behind to be calculated correctly):

    public boolean judgeCircle2(String moves) {
        moves=" " + moves + " ";
        return moves.split("L").length==moves.split("R").length && moves.split("U").length == moves.split("D").length;
    }

//------------------------------------------------------------------------------
    //3
//Java solution with explanation - O(n)
/*We have to check if the net displacement is 0. This is true only if the distance traversed up equals that of down
    and traversed left equals that of right.*/

    public class Solution3 {
        public boolean judgeCircle(String moves) {
            int ls = 0, rs = 0, ups = 0, downs = 0;
            for(int i = 0; i < moves.length(); i++) {
                switch(moves.charAt(i)) {
                    case 'U':
                        ups++;
                        break;
                    case 'D':
                        downs++;
                        break;
                    case 'L':
                        ls++;
                        break;
                    case 'R':
                        rs++;
                }
            }

            return ls == rs && ups == downs;
        }
    }


//------------------------------------------------------------------------------
    //4
    //Java solution, if else...
    public class Solution4 {
        public boolean judgeCircle(String moves) {
            int x = 0, y = 0;
            for (char c : moves.toCharArray()) {
                if (c == 'R') x++;
                else if (c == 'L') x--;
                else if (c == 'U') y--;
                else if (c == 'D') y++;
            }
            return x == 0 && y == 0;
        }
    }


//------------------------------------------------------------------------------
    //5
    //[Java/C++] 4 lines solution
    public boolean judgeCircle4(String moves) {
        int x = 0, y = 0;
        for(char c: moves.toCharArray()){
            x += (c=='R'?1:0) + (c=='L'?-1:0); y += (c=='U'?1:0) + (c=='D'?-1:0);
        }
        return x == 0 && y == 0;
    }

//------------------------------------------------------------------------------
    //6
    //share my java solution!
    public class Solution5 {
        public boolean judgeCircle(String moves) {
            int[] current = new int[2];
            for(int i=0;i<moves.length();i++){
                if(moves.charAt(i)=='R'){
                    current[0]++;
                }else if(moves.charAt(i)=='L'){
                    current[0]--;
                }else if(moves.charAt(i)=='U'){
                    current[1]++;
                }else{
                    current[1]--;
                }

            }
            return current[0]==0&&current[1]==0;
        }
    }

//------------------------------------------------------------------------------


//------------------------------------------------------------------------------
}
/*
Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

Example 1:
Input: "UD"
Output: true
Example 2:
Input: "LL"
Output: false
Seen this question in a real interview before?   Yes  No
Companies
Google
Related Topics
String
Similar Questions
Friend Circles
 */