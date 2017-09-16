package HF.HF1_SimulationAlgorithms_StringManipulationSkills._1_phone_interview;

public class _2EditDistance {

    public boolean isOneEditDistance(String s, String t){
        if (s.length() > t.length()) {
            return isOneEditDistance(t,s);
        }
        int diff = t.length() - s.length();

        if (diff == 0) {
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    cnt++;
                }
            }
            return (cnt == 1);
        }
        if (diff == 1) {
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    return (s.substring(i).equals(t.substring(i + 1)));
                }
            }
        }
        return true;
    }
}

/*
Given two strings S and T, determine if they are both one edit distance apart.

Have you met this question in a real interview? Yes
Example
Given s = "aDb", t = "adb"
return true
 */
