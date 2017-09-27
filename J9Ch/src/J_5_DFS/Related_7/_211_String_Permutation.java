package J_5_DFS.Related_7;

import org.junit.Test;
/** 211 String Permutation
 * Created by tianhuizhu on 6/28/17.
 */
public class _211_String_Permutation {
    /**
     * @param A a string
     * @param B a string
     * @return a boolean
     */
    public boolean stringPermutation(String A, String B) {
        // Write your code here
        int[] cnt = new int[1000];
        for (int i = 0; i < A.length(); ++i)
            cnt[(int)A.charAt(i)] += 1;
        for (int i = 0; i < B.length(); ++i)
            cnt[(int)B.charAt(i)] -= 1;
        for (int i = 0; i < 1000; ++i)
            if (cnt[i] != 0)
                return false;
        return true;
    }

//abcd is a permutation of bcad, but abbe is not a permutation of abe
    @Test
    public void test(){
        System.out.println(stringPermutation("abcd", "bcad"));
        System.out.println(stringPermutation("abbe", "abe"));
    }
}
