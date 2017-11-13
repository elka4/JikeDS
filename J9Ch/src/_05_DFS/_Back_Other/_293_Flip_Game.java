package _05_DFS._Back_Other;
import org.junit.Test;

import java.util.*;

//  293. Flip Game
//  https://leetcode.com/problems/flip-game/description/
//  String
public class _293_Flip_Game {
    //4 lines in Java
    public List<String> generatePossibleNextMoves1(String s) {
        List list = new ArrayList();
        //总觉得这个应该用while写，不过要记住这种在for中操作的方法
        for (int i=-1; (i = s.indexOf("++", i+1)) >= 0; )
            list.add(s.substring(0, i) + "--" + s.substring(i+2));
        return list;
    }
    @Test
    public void test01(){
        System.out.println(generatePossibleNextMoves1("++++"));
    }//[--++, +--+, ++--]


//////////////////////////////////////////////////////////////////////
    /*Simple solution in Java
        We start from i = 1 and check whether current and previous characters of the input string equals to +. If true, then add substring to a list: characters before previous one (concatenating with --) and characters after the current character.*/
    public List<String> generatePossibleNextMoves2(String s) {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {

                list.add(s.substring(0, i - 1) + "--" + s.substring(i + 1, s.length()));
            }
        }
        return list;
    }
    @Test
    public void test02(){
        System.out.println(generatePossibleNextMoves2("++++"));
    }//[--++, +--+, ++--]


//////////////////////////////////////////////////////////////////////
    //    AC simple O(n) JAVA solution
    public List<String> generatePossibleNextMoves3(String s) {
        List<String> result = new ArrayList<String>();

        char chs[] = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (chs[i] == chs[i+1] && chs[i] == '+') {
                //这个先变化，处理，再变回来的方法，是不是和经典recursion的add和remove很像啊
                chs[i] = chs[i+1] = '-';
                result.add(String.valueOf(chs));
                chs[i] = chs[i+1] = '+';
            }
        }
        return result;
    }
    @Test
    public void test03(){
        System.out.println(generatePossibleNextMoves3("++++"));
    }//[--++, +--+, ++--]

//////////////////////////////////////////////////////////////////////
}
/*
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
If there is no valid move, return an empty list [].
 */