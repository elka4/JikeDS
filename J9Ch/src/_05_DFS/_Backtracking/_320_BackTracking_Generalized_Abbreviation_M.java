package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _320_BackTracking_Generalized_Abbreviation_M {

    class Solution{
        public List<String> generateAbbreviations(String word){
            List<String> ret = new ArrayList<String>();
            backtrack(ret, word, 0, "", 0);

            return ret;
        }

        private void backtrack(List<String> ret, String word, int pos, String cur, int count){
            if(pos==word.length()){
                if(count > 0) cur += count;
                ret.add(cur);
            }
            else{
                backtrack(ret, word, pos + 1, cur, count + 1);
                backtrack(ret, word, pos+1, cur + (count>0 ? count : "") + word.charAt(pos), 0);
            }
        }
    }

    public class Solution2 {
        public List<String> generateAbbreviations(String word) {
            List<String> res = new ArrayList<String>();
            int len = word.length();
            res.add(len==0 ? "" : String.valueOf(len));
            for(int i = 0 ; i < len ; i++)
                for(String right : generateAbbreviations(word.substring(i+1))){
                    String leftNum = i > 0 ? String.valueOf(i) : "";
                    res.add( leftNum + word.substring(i,i + 1) + right );
                }
            return res;
        }
    }


    class Solution3{
        public List<String> generateAbbreviations(String word) {
            List<String> res = new ArrayList<>();
            DFS(res, new StringBuilder(), word.toCharArray(), 0, 0);
            return res;
        }

        public void DFS(List<String> res, StringBuilder sb, char[] c, int i, int num) {
            int len = sb.length();
            if(i == c.length) {
                if(num != 0) sb.append(num);
                res.add(sb.toString());
            } else {
                DFS(res, sb, c, i + 1, num + 1);               // abbr c[i]

                if(num != 0) sb.append(num);                   // not abbr c[i]
                DFS(res, sb.append(c[i]), c, i + 1, 0);
            }
            sb.setLength(len);
        }
    }
}
