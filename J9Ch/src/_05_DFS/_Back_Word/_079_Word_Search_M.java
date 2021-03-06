package _05_DFS._Back_Word;

import org.junit.Test;

//  79. Word Search
//  https://leetcode.com/problems/word-search/description/
//  http://www.lintcode.com/zh-cn/problem/word-search/
//   Array, Backtracking
public class _079_Word_Search_M {
    //    Accepted very short Java solution. No additional space.
    //    Here accepted solution based on recursion. To save memory I decuded to apply bit mask for every visited cell. Please check board[y][x] ^= 256;
    public boolean exist1(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[y].length; x++) {
                if (exist1(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist1(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        board[y][x] ^= 256;
        boolean exist = exist1(board, y, x+1, word, i+1)
                || exist1(board, y, x-1, word, i+1)
                || exist1(board, y+1, x, word, i+1)
                || exist1(board, y-1, x, word, i+1);
        board[y][x] ^= 256;
        return exist;
    }
    /*[

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

*/
    @Test
    public void test01(){
//        int[] arr = new int[]{1,2,3};
//        String[] strs = new String[]{"22"};
        char[][] board = new char[][] {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
//        char[][] board1 = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
//        char[] board2 = new char[4]{'A','B','C','E'};
//        char[][] board = new char[3][4];
//        char[] values2 = { 'a', 'b', 'c' };
//        board[0][1] = 'A';
        System.out.println(exist1(board, "ABCCED"));
        System.out.println(exist1(board, "SEE"));
        System.out.println(exist1(board, "ABCB"));

    }


    //------------------------------------------------------------------------------
    //    My Java solution
    boolean[][] visited;
    public boolean exist2(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if((word.charAt(0) == board[i][j]) && search(board, word, i, j, 0)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean search(char[][]board, String word, int i, int j, int index){
        if(index == word.length()){
            return true;
        }

        if(i >= board.length || i < 0 || j >= board[i].length || j < 0 ||
                board[i][j] != word.charAt(index) || visited[i][j]){
            return false;
        }

        visited[i][j] = true;
        if(search(board, word, i-1, j, index+1) ||
                search(board, word, i+1, j, index+1) ||
                search(board, word, i, j-1, index+1) ||
                search(board, word, i, j+1, index+1)){
            return true;
        }

        visited[i][j] = false;
        return false;
    }

//------------------------------------------------------------------------------
    //Simple solution
    public boolean exist3(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(exist3(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist3(char[][] board, int x, int y, String word, int start) {
        if(start >= word.length()) return true;
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length) return false;
        if (board[x][y] == word.charAt(start++)) {
            char c = board[x][y];
            board[x][y] = '#';
            boolean res = exist3(board, x + 1, y, word, start) ||
                    exist3(board, x - 1, y, word, start) ||
                    exist3(board, x, y + 1, word, start) ||
                    exist3(board, x, y - 1, word, start);
            board[x][y] = c;
            return res;
        }
        return false;
    }

//--------------------------------------------------------------------------------
    // 9Ch
    // recursion
    public boolean exist4(char[][] board, String word) {
        if(board == null || board.length == 0)
            return false;
        if(word.length() == 0)
            return true;

        for(int i = 0; i< board.length; i++){
            for(int j=0; j< board[0].length; j++){
                boolean rst = find(board, i, j, word, 0);
                if(rst) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean find(char[][] board, int i, int j, String word, int start){
        if(start == word.length())
            return true;

        if (i < 0 || i>= board.length ||
                j < 0 || j >= board[0].length ||
                board[i][j] != word.charAt(start)){
            return false;
        }

        board[i][j] = '#'; // should remember to mark it。这之前board[i][j]  word.charAt(start)
        boolean rst = find(board, i-1, j, word, start+1)
                || find(board, i, j-1, word, start+1)
                || find(board, i+1, j, word, start+1)
                || find(board, i, j+1, word, start+1);
        board[i][j] = word.charAt(start); // 变回来
        return rst;
    }

    @Test
    public void test04(){
        char[][] board = new char[][] {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        System.out.println(exist4(board, "ABCCED"));
        System.out.println(exist4(board, "SEE"));
        System.out.println(exist4(board, "ABCB"));

    }

//--------------------------------------------------------------------------------
}

/*
lint
给出一个二维的字母板和一个单词，寻找字母板网格中是否存在这个单词。

单词可以由按顺序的相邻单元的字母组成，其中相邻单元指的是水平或者垂直方向相邻。每个单元中的字母最多只能使用一次。
您在真实的面试中是否遇到过这个题？
样例

给出board =

[

  "ABCE",
  "SFCS",
  "ADEE"

]

word = "ABCCED"， ->返回 true,

word = "SEE"，-> 返回 true,

word = "ABCB"， -> 返回 false.

 */


/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
 */