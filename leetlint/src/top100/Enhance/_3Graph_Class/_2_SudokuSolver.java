package top100.Enhance._3Graph_Class;


public class _2_SudokuSolver {
	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0) {
			return;
		}
		if (board[0] == null || board[0].length == 0) {
			return;
		}
		dfsHelper(board, 0, 0);
		
	}
	
	private boolean dfsHelper(char[][] board, int i, int j) {
		//base case
		//change to next line when j ==9
		//Terminate when i == 8
		while (board[i][j] != '.') {
			j++;
			if (j == 9) {
				if (i == 8) {
					return true;
				}
				i++;
				j = 0;
			}
		}
		boolean[] rowChecker = new boolean[9];
		boolean[] colChecker = new boolean[9];
		boolean[] subChecker = new boolean[9];
		fillCheckers(board, rowChecker, colChecker, subChecker, i, j);
		for (int cur = 1; cur <= 9; cur++) {
			board[i][j] = (char)(cur + '0');
			if (check(board, rowChecker, colChecker, subChecker, i, j, cur) 
					&& dfsHelper(board, i, j)) {
				return true;
			}
			board[i][j] = '.';
		}
		return false;
		
	}
	
	private void fillCheckers(char[][] board, boolean[] rowChecker, 
			boolean[] colChecker, boolean[] subChecker, int row, int col) {
		//Fill row
		for (int j = 0; j < 9; j++) {
			if (board[row][j] != '.') {
				rowChecker[board[row][j] - '1'] = true;
			}
		}
		
		//Fill col
		for (int i = 0; i < 9; i++) {
			if (board[i][col] != '.') {
				colChecker[board[i][col] - '1'] = true;
			}
		}
		
		//File subM
		int startX = row / 3 * 3;
		int startY = col/ 3 * 3;
		for (int i = startX; i < startX + 3; i++) {
			for (int j = startY; j < startY + 3; j++) {
				if (board[i][j] != '.') {
					subChecker[board[i][j] - '1'] = true;
				}
			}
		}
	}
	
	
	private boolean check(char[][] board, boolean[] rowChecker, 
			boolean[] colChecker, boolean[] subChecker, int row, int col, int toFill) {
		if (rowChecker[toFill - 1] || colChecker[toFill - 1] || subChecker[toFill - 1]){
			return false;
		} else {
			return true;
		}
		
		
	}
}
