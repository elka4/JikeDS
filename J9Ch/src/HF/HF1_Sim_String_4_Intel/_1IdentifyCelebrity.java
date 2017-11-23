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

// bool knows(a, b) which tells you whether A knows B.
//  https://leetcode.com/problems/find-the-celebrity/description/
//Identify Celebrity
public class _1IdentifyCelebrity {
    /**
     * @param n a party with n people
     * @return the celebrity's label or -1
     */

    public int findCelebrity(int n) {
        // Write your code here
        int ans = 0;
        /*
        A 认识 B， 就是A肯定不不是名人，而B有可能是名人
        所以排除A，并且把B设为candidate
         */
        for (int i = 1; i < n; i++) {
            if (knows(ans, i)) {
                ans = i;
            }
        }

        // 可能存在没有名人

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

//------------------------------------------------------------------------
    boolean knows(int a, int b){
        return a > b;
    }
//------------------------------------------------------------------------

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
//------------------------------------------------------------------------
    public  int findCelebrityTop(int n){
        if(n <= 0){
            return -1;
        }
        if(n == 1){
            return 0;
        }
        int candidate = 0;
        for(int i = 1; i < n; i++){
             //find a possible candidate who knows nobody else in his right hand
            if (knows(candidate, i)){
                candidate = i;
            }
        }

        for(int i = 0; i < n; i++){

            if (i < candidate && knows(candidate, i)) {
                //check the candidate: A. knows nobody in his left hand
                return  -1;
                //known by everyone else
            }
            if(!knows(i, candidate)) {
                return -1;
            }
        }
        return candidate;
    }

//------------------------------------------------------------------------

}
/*
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.


 */