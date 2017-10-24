package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _294_BackTracking_Flip_Game_II_M {

    public boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) {
                String t = s.substring(0, i) + "--" + s.substring(i + 2);

                if (!canWin(t)) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean canWin2(String s) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < s.length() - 1; i++){
            if(s.charAt(i) == '+' && s.charAt(i + 1) == '+')
                list.add(s.substring(0, i) + "--" + s.substring(i + 2, s.length()));         // generate all possible sequence after every attempt
        }
    /*if(list.isEmpty())
        return false;*/
        for(String str : list){
            if(!canWin2(str))             // if there is any one way the next player can't win, take it and you'll win
                return true;
        }
        return false;
    }
}
