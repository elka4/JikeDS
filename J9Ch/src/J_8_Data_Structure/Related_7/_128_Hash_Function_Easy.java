package J_8_Data_Structure.Related_7;
import java.util.*;
/** 128 Hash Function


 * Created by tianhuizhu on 6/28/17.
 */
public class _128_Hash_Function_Easy {
    class Solution {
        public int hashCode(char[] key,int HASH_SIZE) {
            long ans = 0;
            for(int i = 0; i < key.length;i++) {
                ans = (ans * 33 + (int)(key[i])) % HASH_SIZE;
            }
            return (int)ans;
        }
    }
}
