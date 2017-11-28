package _String._String;
import java.util.*;
import java.util.stream.*;
import org.junit.Test;

//  293. Flip Game
//  https://leetcode.com/problems/flip-game/description/
//
public class _293_String_Flip_Game_E {
//------------------------------------------------------------------------------
    //4 lines in Java
    public List<String> generatePossibleNextMoves(String s) {
        List list = new ArrayList();
        for (int i=-1; (i = s.indexOf("++", i+1)) >= 0; )
            list.add(s.substring(0, i) + "--" + s.substring(i+2));
        return list;
    }
//------------------------------------------------------------------------------

/*    Simple solution in Java
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

//------------------------------------------------------------------------------

    //AC simple O(n) JAVA solution
    public List<String> generatePossibleNextMoves3(String s) {
        List<String> res = new ArrayList<String>();

        char chs[] = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (chs[i] == chs[i+1] && chs[i] == '+') {
                chs[i] = chs[i+1] = '-';
                res.add(String.valueOf(chs));
                chs[i] = chs[i+1] = '+';
            }
        }
        return res;
    }
//------------------------------------------------------------------------------
    //Simple Java 8 functional (declarative) solution
//I noticed that Java 8 streams are much slower than loops. Streams make the code declarative but are a huge hit to the performance, unfortunately.

    public List<String> generatePossibleNextMoves4(String s) {
        List<String> res = new ArrayList<>();
        IntStream.range(1,s.length()).forEach(i ->
        {if (s.charAt(i-1) == '+' && s.charAt(i) == '+')
            res.add(s.substring(0,i-1) + "--" + s.substring(i+1,s.length()));});
        return res;
    }

//------------------------------------------------------------------------------
    //Simple java solution, no fancy

    public class Solution5 {
        public List<String> generatePossibleNextMoves(String s) {
            List<String> result = new ArrayList<String>();
            char[] chars =s.toCharArray();
            for(int i=0;i<s.length()-1;i++){
                if(s.charAt(i)=='+' && s.charAt(i+1)=='+'){
                    chars[i]=chars[i+1]='-';
                    result.add(String.valueOf(chars));
                    chars[i]=chars[i+1]='+';
                }
            }
            return result;
        }
    }
//------------------------------------------------------------------------------
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

Seen this question in a real interview before?   Yes  No
Companies
Google
Related Topics
String
Similar Questions
Flip Game II
Java
 */