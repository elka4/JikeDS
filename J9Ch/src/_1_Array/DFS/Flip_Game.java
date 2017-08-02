package _1_Array.DFS;
import java.util.*;
/*
LeetCode – Flip Game (Java)

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.
 */

public class Flip_Game {

    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<String>();

        if(s==null)
            return result;

        char[] arr = s.toCharArray();
        for(int i=0; i<arr.length-1; i++){
            if(arr[i]==arr[i+1] && arr[i]=='+'){
                arr[i]='-';
                arr[i+1]='-';
                result.add(new String(arr));
                arr[i]='+';
                arr[i+1]='+';
            }
        }

        return result;
    }
}
