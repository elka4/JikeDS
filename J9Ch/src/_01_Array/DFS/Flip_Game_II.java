package _01_Array.DFS;

import java.util.ArrayList;
import java.util.List;
/*
LeetCode – Flip Game II (Java)

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".


 */

//Backtracking

//
public class Flip_Game_II {
    public boolean canWin(String s) {
        if(s==null||s.length()==0){
            return false;
        }

        return canWinHelper(s.toCharArray());
    }

    public boolean canWinHelper(char[] arr){
        for(int i=0; i<arr.length-1;i++){
            if(arr[i]=='+'&&arr[i+1]=='+'){
                arr[i]='-';
                arr[i+1]='-';

                //翻转以后recursion，进入下一个对手的操作
                //实际程序的运行是在两个选手之间跳来跳去的recursion
                boolean win = canWinHelper(arr);

                arr[i]='+';
                arr[i+1]='+';

                //if there is a flip which makes the other player lose, the first play wins
                if(!win){
                    return true;
                }
            }
        }

        return false;
    }
    //Roughly, the time is n*n*...n, which is O(n^n).
    // The reason is each recursion takes O(n) and there are totally n recursions.


//-------------------------------------------------------------------------------
/*
    Share my Java backtracking solution
    The idea is try to replace every "++" in the current string s to "--" and see if the opponent can win or not, if the opponent cannot win, great, we win!

    For the time complexity, here is what I thought, let's say the length of the input string s is n, there are at most n - 1 ways to replace "++" to "--" (imagine s is all "+++..."), once we replace one "++", there are at most (n - 2) - 1 ways to do the replacement, it's a little bit like solving the N-Queens problem, the time complexity is (n - 1) x (n - 3) x (n - 5) x ..., so it's O(n!!), double factorial.

    That's what I thought, but I could be wrong :)*/


//我觉得这个想法很棒，每次都是在s的基础上创建t，然后进入对手的recursion
    public boolean canWin2(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) {
                String t = s.substring(0, i) + "--" + s.substring(i + 2);

                if (!canWin2(t)) {
                    return true;
                }
            }
        }

        return false;
    }




//-------------------------------------------------------------------------------

    //Simple backtracking inspired by Flip Game I
    public boolean canWin3(String s) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < s.length() - 1; i++){
            if(s.charAt(i) == '+' && s.charAt(i + 1) == '+')
                list.add(s.substring(0, i) + "--" + s.substring(i + 2, s.length()));                          // generate all possible sequence after every attempt
        }

        /*if(list.isEmpty())
            return false;*/
        for(String str : list){
            // if there is any one way the next player can't win, take it and you'll win
            if(!canWin3(str))
                return true;
        }
        return false;
    }




//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






}
