package HF.HF1_SimulationAlgorithms_StringManipulationSkills._4_Intelegent;

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

    boolean knows(int a, int b){
        return a > b;
    }
}
