package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _636_Stack_Design_Log_Storage_System_M {
//  https://leetcode.com/problems/exclusive-time-of-functions/solution/


//    Java Stack Solution O(n) Time O(n) Space
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prevTime = 0;
        for (String log : logs) {
            String[] parts = log.split(":");
            if (!stack.isEmpty()) res[stack.peek()] +=  Integer.parseInt(parts[2]) - prevTime;
            prevTime = Integer.parseInt(parts[2]);
            if (parts[1].equals("start")) stack.push(Integer.parseInt(parts[0]));
            else {
                res[stack.pop()]++;
                prevTime++;
            }
        }
        return res;
    }


/*
    Java clean solution with normalized time explained
    The sample input is very confusing when time t has mixed meaning of beginning of time t for start and end of time t for end

            logs =
["0:start:0",
        "1:start:2",
        "1:end:5",
        "0:end:6"]
    We can increase all end time by 1 to normalize the meaning of time t, so time talways means "beginning of time t"

    logs =
            ["0:start:0",
            "1:start:2",
            "1:end:6",
            "0:end:7"]
    Now it is clear to see that function 0 spent (2 - 0) + (7 - 6) and function 1 spent (6 - 2)*/

    public int[] exclusiveTime2(int n, List<String> logs) {
        int[] res = new int[n];
        int ptime = 0, running = 0;
        Stack<Integer> stack = new Stack<>();

        for (String log : logs) {
            String[] split = log.split(":");
            int func = Integer.parseInt(split[0]);
            boolean start = split[1].equals("start");
            int time = Integer.parseInt(split[2]);
            if (!start)
                time++;

            res[running] += (time - ptime);
            if (start) {
                stack.push(running);
                running = func;
            } else {
                running = stack.pop();
            }
            ptime = time;
        }
        return res;
    }

}

/*
Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

Example 1:
Input:
n = 2
logs =
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
Output:[3, 4]
Explanation:
Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1.
Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time.
So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
Note:
Input logs will be sorted by timestamp, NOT log id.
Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
Two functions won't start or end at the same time.
Functions could be called recursively, and will always end.
1 <= n <= 100
 */