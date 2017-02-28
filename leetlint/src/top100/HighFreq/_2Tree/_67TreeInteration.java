package top100.HighFreq._2Tree;
import org.junit.Test;

import java.io.*;
import java.util.*;



public class _67TreeInteration {
    class Node{
        int value;
        Node left;
        Node right;
        Node(){
            value = -1;
            left = null;
            right = null;
        }
    }

    class Pair{
        Node node;
        int op;// 0 visit, 1 print
        Pair(Node node, int op){
            this.node = node;
            this.op = op;
        }
    }

    class Traverser{//BFS
        void order(Node root, int order){ // 0 pre; 1 in; 2 post
            Stack<Pair> processing = new Stack<Pair>();
            processing.add(new Pair(root, 0));

            while(processing.isEmpty() == false){
                Pair temp = processing.pop();
                if(temp.node == null) continue;
                if(temp.op == 1){
                    System.out.print(temp.node.value + " ");
                    continue;
                }
                //op == 0
                switch (order){
                    case 0:
                        processing.add(new Pair(temp.node.right, 0));
                        processing.add(new Pair(temp.node.left, 0));
                        processing.add(new Pair(temp.node, 1));
                        break;

                    case 1:
                        processing.add(new Pair(temp.node.right, 0));
                        processing.add(new Pair(temp.node, 1));
                        processing.add(new Pair(temp.node.left, 0));
                        break;

                    case 2:
                        processing.add(new Pair(temp.node, 1));
                        processing.add(new Pair(temp.node.right, 0));
                        processing.add(new Pair(temp.node.left, 0));
                        break;

                }
            }
        }

    }
    public static void main(String[] args) throws FileNotFoundException {

    }

    @Test
    public void test01() throws FileNotFoundException{
        Scanner in = new Scanner(new File("/Users/tzh/IdeaProjects/JikeDS/leetlint/src/top100/HighFreq/_2Tree/input_65"));
        int n = in.nextInt();
        Traverser traverser = new Traverser();
        while (n != -1) {
            //build tree
            Node[] tree = new Node[n];
            for (int i = 0; i < n; ++i) tree[i] = new Node();

            for (int i = 0; i < n; ++i) {
                tree[i].value = in.nextInt();
                int leftId = in.nextInt();
                if(leftId != -1)  tree[i].left = tree[leftId];
                int rightId = in.nextInt();
                if(rightId != -1) tree[i].right = tree[rightId];
            }

            //calculate
            traverser.order(tree[0], 0);
            System.out.println();

            traverser.order(tree[0], 1);
            System.out.println();

            traverser.order(tree[0], 2);
            System.out.println();

            n = in.nextInt();
        }
        in.close();
    }
}
/*
        0
     1    2
    3 4  5
     6 7
 */
