package top100.HighFreq._3Graph;

import java.io.*;
import java.util.*;

class Node
{
    public int id;
    public ArrayList<Node> neighbors;

    public Node(int id){
        this.id = id;
        neighbors = new ArrayList<Node>();
    }

    @Override
    public Node clone(){
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        LinkedList<Node> processing = new LinkedList<Node>();

        map.put(this, new Node(this.id));
        processing.add(this);
        while(processing.isEmpty()==false){
            Node temp = processing.pop();
            Node cloned = map.get(temp);

            for(Node node: temp.neighbors){
                if(map.containsKey(node)==false){
                    map.put(node, new Node(node.id));
                    processing.add(node);
                }
                cloned.neighbors.add(map.get(node));
            }
        }
        return map.get(this);
    }

    public void BFS(){
        HashSet<Node> visited = new HashSet<Node>();

        LinkedList<Node> processing = new LinkedList<Node>();

        visited.add(this);
        processing.add(this);
        while(processing.isEmpty()==false){
            Node temp = processing.pop();

            System.out.print(temp.id+" ");

            for(Node node: temp.neighbors){
                if(visited.add(node)){
                    processing.add(node);
                }
            }
        }
        System.out.println();
    }
}

public class _68CloneGraph {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new File("input.txt"));

        int n = in.nextInt(); // number of node

        while(n!=-1){

            //build tree
            Node[] tree = new Node[n];

            for(int i=0;i<n;++i) tree[i] = new Node(i);

            for(int i=0;i<n;++i){
                int m = in.nextInt();
                while(m--!=0){
                    tree[i].neighbors.add(tree[in.nextInt()]);
                }
            }
            tree[0].clone().BFS();
            n = in.nextInt();
        }
        in.close();
    }
}