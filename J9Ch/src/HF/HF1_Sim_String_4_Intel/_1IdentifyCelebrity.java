package HF.HF1_Sim_String_4_Intel;

/*
思路一:
• 一个简单的做法，对每个做一次名人检验，看是不是所有人都认识他， 但他不认识所有人
• 时间复杂度O(n2)
• 怎样降低时间复杂度? 冗余在哪里?
 */

/*

• Company Tags:LinkedIn Facebook
能力维度:
4. 逻辑思维/算法优化能力
6. 算法分析(时间/空间复杂度)
 */

/*
 小技巧总结:
• 降时间复杂度 - > 找冗余
• 思维上双向:true时候，false的时候?
 */

//Identify Celebrity
public class _1IdentifyCelebrity {
    /**
     * @param n a party with n people
     * @return the celebrity's label or -1
     */

    public int findCelebrity(int n) {
        // Write your code here
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (knows(ans, 1)) {
                ans = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (ans != i && knows(ans, i)) {
                return -1;
            }
            if (ans != i && !knows(i, ans)) {
                return -1;
            }
        }
        return ans;
    }

////////////////////////////////////////////////////////////
    boolean knows(int a, int b){
        return a > b;
    }
////////////////////////////////////////////////////////////

    //jiuzhang
    /**
     * @param n a party with n people
     * @return the celebrity's label or -1
     */
    public int findCelebrity2(int n) {
        // Write your code here
        int candidate = 0;
        for(int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        for(int i = 0; i < candidate; i++) {
            if(knows(candidate, i) || !knows(i, candidate)) {
                return -1;
            }
        }
        for(int i = candidate + 1; i < n; i++) {
            if(!knows(i, candidate)) {
                return -1;
            }
        }
        return candidate;
    }
////////////////////////////////////////////////////////////

}
