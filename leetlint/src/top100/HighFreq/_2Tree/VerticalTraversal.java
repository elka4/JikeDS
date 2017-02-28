package top100.HighFreq._2Tree;
import java.io.*;
import java.util.*;


public class VerticalTraversal {
    static class Node implements Comparable<Node>{
        int value;
        int id;
        int column;
        @Override
        public int compareTo(final Node temp){
            if(column == temp.column) return Integer.compare(id, temp.id);
            else return Integer.compare(column, temp.column);
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
            tree[0].column = 0;
            for(int i = 0; i < n; ++i){
                tree[i].value = in.nextInt();
                tree[i].id = i;
                int leftId = in.nextInt();
                //assign column to child
                if(leftId != -1) tree[leftId].column = tree[i].column - 1;
                int rightId = in.nextInt();
                if(rightId != -1) tree[rightId].column = tree[i].column + 1;

            }
            Arrays.sort(tree);
            for(int i=0; i<n; ++i) System.out.print(tree[i].value +" ");
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
