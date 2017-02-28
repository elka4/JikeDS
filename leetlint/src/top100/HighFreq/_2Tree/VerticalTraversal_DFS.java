package top100.HighFreq._2Tree;
import java.io.*;
import java.util.*;


public class VerticalTraversal_DFS {
    static class Node{
        int value;
        Node left;
        Node right;
    }
    static  class DFS{
        HashMap<Integer, LinkedList> rank;
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
                pos--;
            }
            for(int i = pos + 1; rank.containsKey(i); ++i){
                rank.get(i).forEach((value) -> System.out.print(" " + value));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws  FileNotFoundException{
        Scanner in = new Scanner(new File("/Users/tzh/IdeaProjects/JikeDS/leetlint/src/top100/HighFreq/_2Tree/intpu_verticalTraversal"));
        //read input process
        int n = in.nextInt();
        while(n != -1){
            //build tree
            Node[] tree = new Node[n];
            for(int i = 0; i < n; ++i) tree[i] = new Node();
            for(int i = 0; i < n; ++i){
                tree[i].value = in.nextInt();
                int leftID = in.nextInt();
                if(leftID != -1) tree[i].left = tree[leftID];
                int rightID = in.nextInt();
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


 */
