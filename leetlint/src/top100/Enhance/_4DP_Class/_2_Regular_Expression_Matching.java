package top100.Enhance._4DP_Class;

public class _2_Regular_Expression_Matching {
	public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;
        int[][] match = new int[s.length() + 1][p.length() + 1];
        helper(s, p, 0, 0, match);
        return match[s.length()][p.length()] == 1;
    }

    private boolean helper(String s, String p, int i, int j, int[][] match) {
        //Base Case:
        if (j == p.length()) {
            match[i][j] = (i == s.length()) ? 1 : -1;
            return match[i][j] == 1;
        }   

        if (match[i][j] != 0) {
            return match[i][j] == 1;
        }


        //Case 1: p[i + 1] != "*"
        if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
            match[i][j] = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && helper(s, p, i + 1, j, match) || helper(s, p, i, j + 2, match)) ? 1 : -1;
        } else {//Case 2: p[i + 1] == '*'
            match[i][j] = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && helper(s, p, i + 1, j + 1, match)) ? 1 : -1;
        }
        return match[i][j] == 1;
    }



    private boolean helper2(String s, String p, int i, int j) {
        //Base Case:
        if (j == p.length())
            return i == s.length();

        //Case 1: p[i + 1] != "*"
        if (j == p.length() - 1 || p.charAt(j + 1) != '*') {
            if (i == s.length() || (s.charAt(i) != p.charAt(j) && p.charAt(j) != '.'))
                return false;
            else
                return helper2(s, p, i + 1, j + 1);
        }



        //Case 2: p[i + 1] == "*"
        while (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
            if(helper2(s, p, i, j + 2))//* match preceding i chars
                return true;
            i++;
        }
        return helper2(s, p, i, j + 2);//* match preceding 0 chars
    }
}
