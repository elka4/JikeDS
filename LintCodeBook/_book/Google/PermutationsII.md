##Permutations II

	Given a list of numbers with duplicate number in it. Find all unique permutations.

	For numbers [1,2,2] the unique permutations are:

	[

	    [1,2,2],

	    [2,1,2],

	    [2,2,1]

	]

####Challenge
Do it without recursion.

####Tags: Recursion

####思路：
- visited[i-1]是为了顺序访问
- i!=0 是为了nums[i-1]不溢出
- nums[i]=nums[i-1]跟subset一样
- 九章这个思路有点难理解，看下面自己的解法
- 九章这个速度更快一些
- 用了这个条件判断,就可以避开了很多cases

```java
class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> S) {
		// write your code here
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>() ;
		if(S==null || S.size()==0){
			return result;
		}
		int[] nums = new int[S.size()];

		for (int i = 0; i < nums.length; i++) {
			nums[i] = S.get(i).intValue();
		}
		Arrays.sort(nums);
		int[] visited = new int[nums.length];


		subsetshelper(result, list,visited, nums);
		return result;
	}

	public void subsetshelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int[] visited, int[] nums) {
        if(list.size() == nums.length) {
            result.add(new ArrayList<Integer>(list));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
        	//现在这个数跟前面那个数一样，并且前面的数还没用过，这种情况就是跳过了，就不符合遍历需求
        	//一定要分析好条件的情况
            if (visited[i] == 1 || (i != 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0)){
            //visited[i-1]是为了顺序访问
            //i!=0 是为了nums[i-1]不溢出
            //nums[i]=nums[i-1]跟subset一样
		// 上面的判断其实并不影响最终结果，目的是为了让dfs能更快
		/*
			上面这一连串判断条件，重点在于要能理解!visited.contains(i-1)
			要理解这个，首先要明白i作为数组内的序号，i是唯一的
			给出一个排好序的数组，[1,2,2]
			第一层递归			第二层递归			第三层递归
			[1]					[1,2]				[1,2,2]
		        序号:[0]				 [0,1]			[0,1,2]
			这种都是OK的，但当第二层递归i扫到的是第二个"2"，情况就不一样了
			[1]					[1,2]				[1,2,2]
                         序号:[0]				[0,2]				[0,2,1]
			所以这边判断的时候!visited.contains(0)就变成了true，不会再继续递归下去，跳出循环
			步主要就是为了去除连续重复存在的，很神奇反正 = =||
	*/
				continue;
			}
            visited[i] = 1;
            list.add(nums[i]);
            subsetshelper(result, list, visited, nums);
            list.remove(list.size() - 1);
            visited[i] = 0;
        }
    }
}

```


####hashmap + DFS 解法
- hashmap保存这个值的下标，如果用过，这次就不加入他了，如果没有才可以加入
- 最后只需要判断result包含过这个list没有，如果没有，才加入

```java
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (length == 0) {
            return result;
        }

        List<Integer> list = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        dfs(nums, result, list, set);

        return result;

    }

    public void dfs(int[] nums, List<List<Integer>> result, List<Integer> list, HashSet<Integer> set) {
        if (list.size() == nums.length && !result.contains(list)) {
            result.add(new ArrayList<Integer>(list));
        }

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(i)) {
                continue;
            }

            list.add(nums[i]);
            set.add(i);

            dfs(nums, result, list, set);

            list.remove(list.size() - 1);
            set.remove(i);
        }
    }
}
```
