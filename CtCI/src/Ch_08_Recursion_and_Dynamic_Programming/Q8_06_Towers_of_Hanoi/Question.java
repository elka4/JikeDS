package Ch_08_Recursion_and_Dynamic_Programming.Q8_06_Towers_of_Hanoi;

public class Question {
	public static void main(String[] args) {
		// Set up code.
		int n = 2;
		Tower[] towers = new Tower[3];
		for (int i = 0; i < 3; i++) {
			towers[i] = new Tower(i);
		}
		for (int i = n - 1; i >= 0; i--) {
			towers[0].add(i);
		}
		
		// Copy and paste output into a .XML file and open it with internet explorer.
		//towers[0].print();
		towers[0].moveDisks(n, towers[2], towers[1]);
		//towers[2].print();
	}
	/*
                <move_2_disks_from_0_to_2_with_buffer_1>
                <move_1_disks_from_0_to_1_with_buffer_2>
                <move_top_from_0_to_1>
                <before>
                <source_print>
                Contents of Tower 0: [1, 0]
                </source_print>
                <destination_print>
                Contents of Tower 1: []
                </destination_print>
                </before>
                <after>
                <source_print>
                Contents of Tower 0: [1]
                </source_print>
                <destination_print>
                Contents of Tower 1: [0]
                </destination_print>
                </after>
                </move_top_from_0_to_1>
                </move_1_disks_from_0_to_1_with_buffer_2>
                <move_top_from_0_to_2>
                <before>
                <source_print>
                Contents of Tower 0: [1]
                </source_print>
                <destination_print>
                Contents of Tower 2: []
                </destination_print>
                </before>
                <after>
                <source_print>
                Contents of Tower 0: []
                </source_print>
                <destination_print>
                Contents of Tower 2: [1]
                </destination_print>
                </after>
                </move_top_from_0_to_2>
                <move_1_disks_from_1_to_2_with_buffer_0>
                <move_top_from_1_to_2>
                <before>
                <source_print>
                Contents of Tower 1: [0]
                </source_print>
                <destination_print>
                Contents of Tower 2: [1]
                </destination_print>
                </before>
                <after>
                <source_print>
                Contents of Tower 1: []
                </source_print>
                <destination_print>
                Contents of Tower 2: [1, 0]
                </destination_print>
                </after>
                </move_top_from_1_to_2>
                </move_1_disks_from_1_to_2_with_buffer_0>
                </move_2_disks_from_0_to_2_with_buffer_1>
	 */
}
