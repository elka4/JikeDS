package S_1_Follow_up_in_CodeInterview.Optional_6;
import java.util.*;
/** 32 Minimum Window Substring
 * Created by tianhuizhu on 6/28/17.
 */
public class _32_Minimum_Window_Substring_Medium {

    public class Solution1 {
        //方法一:
        int initTargetHash(int[] targethash, String Target) {
            int targetnum = 0;
            for (char ch : Target.toCharArray()) {
                targetnum++;
                targethash[ch]++;
            }
            return targetnum;
        }

        boolean valid(int[] sourcehash, int[] targethash) {

            for (int i = 0; i < 256; i++) {
                if (targethash[i] > sourcehash[i])
                    return false;
            }
            return true;
        }

        public String minWindow(String Source, String Target) {
            // queueing the position that matches the char in T
            int ans = Integer.MAX_VALUE;
            String minStr = "";

            int[] sourcehash = new int[256];
            int[] targethash = new int[256];

            initTargetHash(targethash, Target);
            int j = 0, i = 0;
            for (i = 0; i < Source.length(); i++) {
                while (!valid(sourcehash, targethash) && j < Source.length()) {
                    sourcehash[Source.charAt(j)]++;
                    if (j < Source.length())
                        j++;
                    else
                        break;
                }
                if (valid(sourcehash, targethash)) {
                    if (ans > j - i) {
                        ans = Math.min(ans, j - i);
                        minStr = Source.substring(i, j);
                    }
                }
                sourcehash[Source.charAt(i)]--;
            }
            return minStr;
        }

        //方法二:
        public class Solution2 {
            int initTargetHash(int[] targethash, String Target) {
                int targetnum = 0;
                for (char ch : Target.toCharArray()) {
                    targetnum++;
                    targethash[ch]++;
                }
                return targetnum;
            }

            public String minWindow(String Source, String Target) {
                // queueing the position that matches the char in T
                int ans = Integer.MAX_VALUE;
                String minStr = "";

                int[] targethash = new int[256];

                int targetnum = initTargetHash(targethash, Target);
                int sourcenum = 0;
                int j = 0, i = 0;
                for (i = 0; i < Source.length(); i++) {
                    if (targethash[Source.charAt(i)] > 0)
                        sourcenum++;

                    targethash[Source.charAt(i)]--;
                    while (sourcenum >= targetnum) {
                        if (ans > i - j + 1) {
                            ans = Math.min(ans, i - j + 1);
                            minStr = Source.substring(j, i + 1);
                        }
                        targethash[Source.charAt(j)]++;
                        if (targethash[Source.charAt(j)] > 0)
                            sourcenum--;
                        j++;
                    }
                }
                return minStr;
            }

        }
    }
}
