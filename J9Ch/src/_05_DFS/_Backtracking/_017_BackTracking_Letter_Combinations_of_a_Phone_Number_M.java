package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _017_BackTracking_Letter_Combinations_of_a_Phone_Number_M {

    class Solution{
        public List<String> letterCombinations(String digits) {
            LinkedList<String> ans = new LinkedList<String>();
            String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            ans.add("");
            for(int i =0; i<digits.length();i++){
                int x = Character.getNumericValue(digits.charAt(i));
                while(ans.peek().length()==i){
                    String t = ans.remove();
                    for(char s : mapping[x].toCharArray())
                        ans.add(t+s);
                }
            }
            return ans;
        }
    }



}
