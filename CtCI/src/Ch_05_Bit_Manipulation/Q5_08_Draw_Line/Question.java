package Ch_05_Bit_Manipulation.Q5_08_Draw_Line;

public class Question {
	public static int computeByteNum(int width, int x, int y) {
		return (width * y + x) / 8;
	}
	
	public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		int start_offset = x1 % 8;
		int first_full_byte = x1 / 8;
		if (start_offset != 0) {
			first_full_byte++;
		}
		
		int end_offset = x2 % 8;
		int last_full_byte = x2 / 8;
		if (end_offset != 7) {
			last_full_byte--;
		}
		
		// Set full bytes
		for (int b = first_full_byte; b <= last_full_byte; b++) {
			screen[(width / 8) * y + b] = (byte) 0xFF;
		}
		
		byte start_mask = (byte) (0xFF >> start_offset);
		byte end_mask = (byte) ~(0xFF >> (end_offset + 1));
		
		// Set start and end of line
		if ((x1 / 8) == (x2 / 8)) { // If x1 and x2 are in the same byte
			byte mask = (byte) (start_mask & end_mask);
			screen[(width / 8) * y + (x1 / 8)] |= mask;
		} else {
			if (start_offset != 0) {
				int byte_number = (width / 8) * y + first_full_byte - 1;
				screen[byte_number] |= start_mask;
			}
			if (end_offset != 7) {
				int byte_number = (width / 8) * y + last_full_byte + 1;
				screen[byte_number] |= end_mask;
			} 
		}
	}
	
	public static void printByte(byte b) {
		for (int i = 7; i >= 0; i--) {
			char c = ((b >> i) & 1) == 1 ? '1' : '_';
			System.out.print(c);
		}
	}
	
	public static void printScreen(byte[] screen, int width) {
		int height = screen.length * 8 / width;
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c+=8) {
				byte b = screen[computeByteNum(width, c, r)];
				printByte(b);
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		int width = 8 * 1;
		int height = 1;
		for (int r = 0; r < height; r++) {
			for (int c1 = 0; c1 < width; c1++) {
				for (int c2 = c1; c2 < width; c2++) {
					byte[] screen = new byte[width * height / 8];

					System.out.println("row: " + r + ": " + c1 + " -> " + c2);
					drawLine(screen, width, c1, c2, r);
					printScreen(screen, width);
					System.out.println("\n\n");
				}
			}
		}
	}

	/*
row: 0: 0 -> 0
1_______



row: 0: 0 -> 1
11______



row: 0: 0 -> 2
111_____



row: 0: 0 -> 3
1111____



row: 0: 0 -> 4
11111___



row: 0: 0 -> 5
111111__



row: 0: 0 -> 6
1111111_



row: 0: 0 -> 7
11111111



row: 0: 1 -> 1
_1______



row: 0: 1 -> 2
_11_____



row: 0: 1 -> 3
_111____



row: 0: 1 -> 4
_1111___



row: 0: 1 -> 5
_11111__



row: 0: 1 -> 6
_111111_



row: 0: 1 -> 7
_1111111



row: 0: 2 -> 2
__1_____



row: 0: 2 -> 3
__11____



row: 0: 2 -> 4
__111___



row: 0: 2 -> 5
__1111__



row: 0: 2 -> 6
__11111_



row: 0: 2 -> 7
__111111



row: 0: 3 -> 3
___1____



row: 0: 3 -> 4
___11___



row: 0: 3 -> 5
___111__



row: 0: 3 -> 6
___1111_



row: 0: 3 -> 7
___11111



row: 0: 4 -> 4
____1___



row: 0: 4 -> 5
____11__



row: 0: 4 -> 6
____111_



row: 0: 4 -> 7
____1111



row: 0: 5 -> 5
_____1__



row: 0: 5 -> 6
_____11_



row: 0: 5 -> 7
_____111



row: 0: 6 -> 6
______1_



row: 0: 6 -> 7
______11



row: 0: 7 -> 7
_______1
	 */

}

/*
Draw Line: A monochrome screen is stored as a single array of bytes,
allowing eight consecutive pixels to be stored in one byte.
The screen has width w, where wis divisible by 8 (that is, no byte will be split across rows).
 The height of the screen, of course, can be derived from the length of
 the array andthewidth.Implementafunctionthatdrawsahorizontallinefrom(xl, y)to(x2J y).
The method signature should look something like:
drawLine(byte[] screen, int width, int xl, int x2, int y)
 */
