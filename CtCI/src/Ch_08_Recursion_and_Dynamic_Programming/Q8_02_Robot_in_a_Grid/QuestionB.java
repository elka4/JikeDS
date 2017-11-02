package Ch_08_Recursion_and_Dynamic_Programming.Q8_02_Robot_in_a_Grid;

import CtCILibrary.AssortedMethods;

import java.util.ArrayList;
import java.util.HashSet;

public class QuestionB {
	public static ArrayList<Point> getPath(boolean[][] maze) {
		if (maze == null || maze.length == 0) return null;
		ArrayList<Point> path = new ArrayList<Point>();
		HashSet<Point> failedPoints = new HashSet<Point>();
		if (getPath(maze, maze.length - 1,
                maze[0].length - 1, path, failedPoints)) {
			return path;
		}
		return null;
	}
	
	public static boolean getPath(boolean[][] maze, int row,
      int col, ArrayList<Point> path, HashSet<Point> failedPoints) {
		/* If out of bounds or not available, return.*/
		if (col < 0 || row < 0 || !maze[row][col]) {
			return false;
		}
		
		Point p = new Point(row, col);
		
		/* If we've already visited this cell, return. */
		if (failedPoints.contains(p)) { 
			return false;
		}	
		
		boolean isAtOrigin = (row == 0) && (col == 0);
		
		/* If there's a path from the start to my current location,
		 add my location.*/
		if (isAtOrigin || getPath(maze, row, col - 1, path, failedPoints)
                || getPath(maze, row - 1, col, path, failedPoints)) {
			path.add(p);
			return true;
		}
		
		failedPoints.add(p); // Cache result
		return false;
	}
	
	public static void main(String[] args) {
		int size = 20;
		boolean[][] maze = AssortedMethods.randomBooleanMatrix(size,
                size, 80);
		
		AssortedMethods.printMatrix(maze);
		
		ArrayList<Point> path = getPath(maze);
		if (path != null) {
			System.out.println(path.toString());
		} else {
			System.out.println("No path found.");
		}
	}
	/*
                    11110111101111111100
                    11111110111011111110
                    11101111110010001101
                    11110110110111111111
                    11111111111111111111
                    11011101111111010111
                    11101111110110111111
                    11111110110111111010
                    11111110010001111111
                    11010101011011111111
                    10110111111111111011
                    11111100111111111111
                    11010111010111011110
                    10101111110101110101
                    01100111110111110111
                    11110101111110110100
                    11110110101011101001
                    11110101001111111111
                    11001101111111111111
                    01010011011011011111
                    [(0, 0), (1, 0), (2, 0), (3, 0), (4, 0), (5, 0), (6, 0), (7, 0), (8, 0), (9, 0), (10, 0), (11, 0), (11, 1), (11, 2), (11, 3), (11, 4), (11, 5), (12, 5), (13, 5), (14, 5), (14, 6), (14, 7), (15, 7), (15, 8), (15, 9), (15, 10), (16, 10), (17, 10), (18, 10), (18, 11), (18, 12), (18, 13), (18, 14), (18, 15), (19, 15), (19, 16), (19, 17), (19, 18), (19, 19)]
	 */

}
