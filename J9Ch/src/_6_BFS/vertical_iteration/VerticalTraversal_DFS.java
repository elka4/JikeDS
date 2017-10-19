package _6_BFS.vertical_iteration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

@SuppressWarnings("all")
public class VerticalTraversal_DFS {
    static class Node{
        int value;
        Node left;
        Node right;
    }
    static  class DFS{
        HashMap<Integer, LinkedList> rank; //id, list
        DFS(){
            rank = new HashMap<>();
        }
        void traverse(Node ptr, int pos){
            if(ptr == null) return;
            if(rank.containsKey(pos) == false) rank.put(pos,new LinkedList());
            rank.get(pos).add(ptr.value);
            traverse(ptr.left, pos-1);
            traverse(ptr.right, pos+1);
        }
        void display(){
            //find the min left
            int pos = 0;
            while(rank.containsKey(pos) == true){
                pos--;//最后得到的pos是最左的前一个
            }
            for(int i = pos + 1; rank.containsKey(i); ++i){
                rank.get(i).forEach((value) -> System.out.print(" " + value));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws  FileNotFoundException{
        Scanner in = new Scanner(new File(
"/Users/tianhuizhu/Downloads/uber/code/JikeDS/top100/src/_2Tree/intpu_verticalTraversal"));
        //read input process
        int n = in.nextInt();
        while(n != -1){
            //build tree
            Node[] tree = new Node[n];
            for(int i = 0; i < n; ++i) tree[i] = new Node();
            for(int i = 0; i < n; ++i){
                tree[i].value = in.nextInt(); //第一个是数值
                int leftID = in.nextInt();//第二个是编号
                if(leftID != -1) tree[i].left = tree[leftID];
                int rightID = in.nextInt();//第三个是编号
                if(rightID != -1) tree[i].right = tree[rightID];
            }

            DFS dfs = new DFS();
            dfs.traverse(tree[0], 0);
            dfs.display();
            n = in.nextInt();
        }
        in.close();
    }
}

/*
        1
     2     3
         4
2 1 4 3
Process finished with exit code 0

            4
          3   2
         1  5
            7   6
7
4 1 2
3 3 4
2 5 6
1 -1 -1
5 -1 -1
7 -1 -1
6 -1 -1







 */
