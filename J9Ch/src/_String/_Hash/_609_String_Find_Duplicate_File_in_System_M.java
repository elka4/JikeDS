package _String._Hash;
import java.util.*;
import java.util.stream.*;
import org.junit.Test;

//
//  609. Find Duplicate File in System
//  https://leetcode.com/problems/find-duplicate-file-in-system/description/
//
public class _609_String_Find_Duplicate_File_in_System_M {
//------------------------------------------------------------------------------
//https://leetcode.com/problems/find-duplicate-file-in-system/solution/
//------------------------------------------------------------------------------
//Approach #1 Brute Force [Time Limit Exceeded]
public class Solution1 {
    public List < List < String >> findDuplicate(String[] paths) {
        List < String[] > list = new ArrayList < > ();
        for (String path: paths) {
            String[] values = path.split(" ");
            for (int i = 1; i < values.length; i++) {
                String[] name_cont = values[i].split("\\(");
                name_cont[1] = name_cont[1].replace(")", "");
                list.add(new String[] {
                        values[0] + "/" + name_cont[0], name_cont[1]
                });
            }
        }
        boolean[] visited = new boolean[list.size()];
        List < List < String >> res = new ArrayList < > ();
        for (int i = 0; i < list.size() - 1; i++) {
            if (visited[i])
                continue;
            List < String > l = new ArrayList < > ();
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i)[1].equals(list.get(j)[1])) {
                    l.add(list.get(j)[0]);
                    visited[j] = true;
                }
            }
            if (l.size() > 0) {
                l.add(list.get(i)[0]);
                res.add(l);
            }
        }
        return res;
    }
}




//------------------------------------------------------------------------------

//Approach #2 Using HashMap [Accepted]

    public class Solution2 {
        public List < List < String >> findDuplicate(String[] paths) {
            HashMap < String, List < String >> map = new HashMap < > ();
            for (String path: paths) {
                String[] values = path.split(" ");
                for (int i = 1; i < values.length; i++) {
                    String[] name_cont = values[i].split("\\(");
                    name_cont[1] = name_cont[1].replace(")", "");
                    List < String > list = map.getOrDefault(name_cont[1], new ArrayList < String > ());
                    list.add(values[0] + "/" + name_cont[0]);
                    map.put(name_cont[1], list);
                }
            }
            List < List < String >> res = new ArrayList < > ();
            for (String key: map.keySet()) {
                if (map.get(key).size() > 1)
                    res.add(map.get(key));
            }
            return res;
        }
    }


//------------------------------------------------------------------------------


//    Straight forward solution with a tiny bit of Java8
//    If the creation of the map can also be done using Java8 that would have been cool.

    public static List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for(String path : paths) {
            String[] tokens = path.split(" ");
            for(int i = 1; i < tokens.length; i++) {
                String file = tokens[i].substring(0, tokens[i].indexOf('('));
                String content = tokens[i].substring(tokens[i].indexOf('(') + 1, tokens[i].indexOf(')'));
                map.putIfAbsent(content, new ArrayList<>());
                map.get(content).add(tokens[0] + "/" + file);
            }
        }
        return map.values().stream().filter(e -> e.size() > 1).collect(Collectors.toList());
    }
//------------------------------------------------------------------------------
    //Java Solution, HashMap
    public class Solution4 {
        public List<List<String>> findDuplicate(String[] paths) {
            List<List<String>> result = new ArrayList<List<String>>();
            int n = paths.length;
            if (n == 0) return result;

            Map<String, Set<String>> map = new HashMap<>();
            for (String path : paths) {
                String[] strs = path.split("\\s+");
                for (int i = 1; i < strs.length; i++) {
                    int idx = strs[i].indexOf("(");
                    String content = strs[i].substring(idx);
                    String filename = strs[0] + "/" + strs[i].substring(0, idx);
                    Set<String> filenames = map.getOrDefault(content, new HashSet<String>());
                    filenames.add(filename);
                    map.put(content, filenames);
                }
            }

            for (String key : map.keySet()) {
                if (map.get(key).size() > 1) {
                    result.add(new ArrayList<String>(map.get(key)));
                }
            }

            return result;
        }
    }
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------


//------------------------------------------------------------------------------
}
/*
Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.

A group of duplicate files consists of at least two files that have exactly the same content.

A single directory info string in the input list has the following format:

"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"

It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:

"directory_path/file_name.txt"

Example 1:
Input:
["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
Output:
[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
Note:
No order is required for the final output.
You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].
The number of files given is in the range of [1,20000].
You may assume no files or directories share the same name in the same directory.
You may assume each given directory info represents a unique directory. Directory path and file info are separated by a single blank space.
Follow-up beyond contest:
Imagine you are given a real file system, how will you search files? DFS or BFS?
If the file content is very large (GB level), how will you modify your solution?
If you can only read the file by 1kb each time, how will you modify your solution?
What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?
How to make sure the duplicated files you find are not false positive?
Companies
Dropbox
Related Topics
 String, Hash
 */