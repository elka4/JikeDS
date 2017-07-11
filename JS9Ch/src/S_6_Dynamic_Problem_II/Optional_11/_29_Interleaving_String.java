package S_6_Dynamic_Problem_II.Optional_11;
import java.util.*;
/** 29 Interleaving String


 * Created by tianhuizhu on 6/28/17.
 */
public class _29_Interleaving_String {

    public class Solution {
        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }

            boolean [][] interleaved = new boolean[s1.length() + 1][s2.length() + 1];
            interleaved[0][0] = true;

            for (int i = 1; i <= s1.length(); i++) {
                if(s3.charAt(i - 1) == s1.charAt(i - 1) && interleaved[i - 1][0])
                    interleaved[i][0] = true;
            }

            for (int j = 1; j <= s2.length(); j++) {
                if(s3.charAt(j - 1) == s2.charAt(j - 1) && interleaved[0][j - 1])
                    interleaved[0][j] = true;
            }

            for (int i = 1; i <= s1.length(); i++) {
                for (int j = 1; j <= s2.length(); j++) {
                    if(((s3.charAt(i + j - 1) == s1.charAt(i - 1) && interleaved[i - 1][j]))
                            || ((s3.charAt(i + j - 1)) == s2.charAt(j - 1) && interleaved[i][j - 1]))
                        interleaved[i][j] = true;
                }
            }

            return interleaved[s1.length()][s2.length()];
        }
    }
}
