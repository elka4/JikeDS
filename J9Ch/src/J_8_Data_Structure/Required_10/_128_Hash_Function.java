package J_8_Data_Structure.Required_10;

/** 128 Hash Function
 * Easy

 * Created by tianhuizhu on 6/28/17.
 */
public class _128_Hash_Function {

    class Solution {
        public int hashCode(char[] key,int HASH_SIZE) {
            long ans = 0;
            for(int i = 0; i < key.length;i++) {
                ans = (ans * 33 + (int)(key[i])) % HASH_SIZE;
            }
            return (int)ans;
        }
    };
}
