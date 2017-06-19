package ch9J.chapter1;

import java.util.ArrayList;
import java.util.Collections;

//lintcode version
public class _16Permutations2_List {
public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    if(nums == null || nums.size() == 0)
        return result;
    ArrayList<Integer> list = new ArrayList<Integer>();
    int[] visited = new int[nums.size()];
    
    Collections.sort(nums);
    helper(result, list, visited, nums);
    return result;
}

public void helper(ArrayList<ArrayList<Integer>> result, 
		ArrayList<Integer> list, int[] visited, ArrayList<Integer> nums) {
	
    if(list.size() == nums.size()) {
        result.add(new ArrayList<Integer>(list));
        return;
    }
    
    for(int i = 0; i < nums.size(); i++) {
        if (visited[i] == 1 || 
        		(i != 0 && nums.get(i) == nums.get(i - 1) && visited[i - 1] == 0)){
            continue;
        }
        
        visited[i] = 1;
        list.add(nums.get(i));
        helper(result, list, visited, nums);
        list.remove(list.size() - 1);
        visited[i] = 0;
    }
}
}

