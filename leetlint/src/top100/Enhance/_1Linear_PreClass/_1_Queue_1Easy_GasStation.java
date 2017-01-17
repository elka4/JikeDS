package top100.Enhance._1Linear_PreClass;

public class _1_Queue_1Easy_GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		if (gas == null || cost == null) {
			return -1;
		}
		if (gas.length != cost.length) {
			return -1;
		}
		int start = gas.length - 1;
		int end = 0;
		int sum = gas[start] - cost[start];
		while(end < start) {
			//Case 1: sum < 0 --> a new start needed
			if (sum < 0) {
				sum += gas[--start] - cost[start];
			} else {// Case 2: sum >= 0 --> try to move more
				sum += gas[end] - cost[end++];
			}
		}
		return sum >= 0 ? start : -1;
	}
}
