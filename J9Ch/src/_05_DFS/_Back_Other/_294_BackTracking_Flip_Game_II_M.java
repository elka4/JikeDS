package _05_DFS._Back_Other;
import org.junit.Test;
import java.util.*;

//  294. Flip Game II
//  https://leetcode.com/problems/flip-game-ii/description/

public class _294_BackTracking_Flip_Game_II_M {
    /*
    Share my Java backtracking solution
    The idea is try to replace every "++" in the current string s to "--" and see if the opponent can win or not, if the opponent cannot win, great, we win!

    For the time complexity, here is what I thought, let's say the length of the input string s is n, there are at most n - 1 ways to replace "++" to "--" (imagine s is all "+++..."), once we replace one "++", there are at most (n - 2) - 1 ways to do the replacement, it's a little bit like solving the N-Queens problem, the time complexity is (n - 1) x (n - 3) x (n - 5) x ..., so it's O(n!!), double factorial.

    That's what I thought, but I could be wrong :)
     */
    //DFS
    //这题要求熟练掌握对于String的substring方法，index的了解和操作
    public boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        //要掌握 boolean startsWith(String prefix, int toffset) 这个方法
        //String substring(int beginIndex): toffset   where to begin looking in this string.
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) {
                //beginIndex   the beginning index, inclusive.
                String t = s.substring(0, i) + "--" + s.substring(i + 2);

                if (!canWin(t)) {
                    return true;
                }
            }
        }
        return false;
    }
    @Test
    public void test01(){
        System.out.println(canWin("++++"));
    }//true

//-------------------------------------------------------------------------/?
    //Simple backtracking inspired by Flip Game I

    //貌似目前这个最好理解，就是把本人可以进行的所有操作依次带入本方法作为对手的操作
    //要记住这个做法
    public boolean canWin2(String s) {
        //首先是进行Flip Game I的操作，得到所有++可以替换成--的结果。只替换一次。
        List<String> list = new ArrayList<>();
        for(int i = 0; i < s.length() - 1; i++){
            if(s.charAt(i) == '+' && s.charAt(i + 1) == '+')
                // generate all possible sequence after every attempt
                list.add(s.substring(0, i) + "--" + s.substring(i + 2, s.length()));
        }
        /*if(list.isEmpty())
            return false;*/
        //将这些替换了一次的结果再次带入本方法，就是对手的操作。只要有一个方法对手false，那就是本选手true。
        for(String str : list){
            // if there is any one way the next player can't win, take it and you'll win
            if(!canWin2(str))
                return true;
        }
        //能走到这儿就是对手怎么都会赢，那本选手就是输
        return false;
    }
    @Test
    public void test02(){
        System.out.println(canWin2("++++"));
    }

//-------------------------------------------------------------------------/?
    // 9Ch
    // 方法一 搜索
    public boolean canWin3(String s) {
        boolean[] state = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                state[i] = true;
            } else {
                state[i] = false;
            }
        }
        return search(state);
    }
    public boolean search(boolean[] state) {
        for (int i = 0; i < state.length - 1; i++) {
            if (state[i] && state[i + 1]) {
                state[i] = false;
                state[i + 1] = false;
                if (!search(state)) {
                    state[i] = true;
                    state[i + 1] = true;
                    return true;
                } else {
                    state[i] = true;
                    state[i + 1] = true;
                }
            }
        }
        return false;
    }
    @Test
    public void test03(){
        System.out.println(canWin3("++++"));
    }


//-------------------------------------------------------------------------/?
    // 9Ch
    // 方法二 nim 博弈
    public boolean canWin4(String s) {
        int[] nim = new int[s.length() + 1];
        boolean[] happen = new boolean[s.length() + 1];
        for (int i = 2; i <= s.length(); i++) {
            for (int j = 0; j < i - j - 1; j++) {
                happen[nim[j] ^ nim[i - j - 2]] = true;
            }
            boolean nimEmpty = true;
            for (int j = 0; j <= s.length(); j++) {
                if (!happen[j] && nimEmpty) {
                    nimEmpty = false;
                    nim[i] = j;
                } else {
                    happen[j] = false;
                }
            }
        }
        int ans = 0;
        int len = 0;
        s = s + "-";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                len++;
            } else {
                ans = ans ^ nim[len];
                len = 0;
            }
        }
        if (ans == 0) {
            return false;
        } else {
            return true;
        }
    }
    @Test
    public void test04(){
        System.out.println(canWin4("++++"));
    }


//-------------------------------------------------------------------------/?
}
/*
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
 */