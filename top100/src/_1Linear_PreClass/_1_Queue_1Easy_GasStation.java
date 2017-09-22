package _1Linear_PreClass;

import org.junit.Test;

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

	@Test
    public void test01(){
        int[] gas = {1, 1, 3, 1};
        int[] cost = {2, 2, 1, 1};
        System.out.println(canCompleteCircuit(gas, cost));
    }

////////////////////////////////////////////////////////////////??????
    //My O(N) time, O(1) extra space solution.
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        for(int i = 0; i < gas.length; i++) {
            gas[i] -= cost[i];
        }
        int sum = 0;
        int result = 0;
        int n = gas.length;

        for(int i = 0; i < n * 2 - 1; i++) {
            sum += gas[i % n];
            if(sum < 0) {
                result = i + 1;
                if(result >= n) {
                    return -1;
                }
                sum = 0;
            }
        }
        return result;
        }
    }
/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.


 */

/*
在一条环路上有 N 个加油站，其中第 i 个加油站有汽油gas[i]，并且从第_i_个加油站前往第_i_+1个加油站需要消耗汽油cost[i]。

你有一辆油箱容量无限大的汽车，现在要从某一个加油站出发绕环路一周，一开始油箱为空。

求可环绕环路一周时出发的加油站的编号，若不存在环绕一周的方案，则返回-1。

 注意事项

数据保证答案唯一。

您在真实的面试中是否遇到过这个题？ Yes
样例
现在有4个加油站，汽油量gas[i]=[1, 1, 3, 1]，环路旅行时消耗的汽油量cost[i]=[2, 2, 1, 1]。则出发的加油站的编号为2。


 */