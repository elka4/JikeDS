package DP._4DP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by tianhuizhu on 7/5/17.
 */
public class Twitter {
    class Pair implements Comparable<Pair>{
        int pID;
        ListIterator<Integer> itr;
        Pair(int pID, ListIterator<Integer> itr){
            this.pID = pID;
            this.itr = itr;
        }
        public int compareTo(Pair other){
            return other.pID - pID;
        }
    }
    HashMap<Integer, HashSet<Integer>> relationship;
    HashMap<Integer, LinkedList<Integer>> post;
    Twitter(){
        relationship = new HashMap<Integer, HashSet<Integer>>();
        post = new HashMap<Integer, LinkedList<Integer>>();
    }
    void subscribe(int uID, int followID){
        if (uID == followID) return;
        if (relationship.containsKey(uID) == false) relationship.put(uID,new HashSet<Integer>());
        relationship.get(uID).add(followID);
    }
    void unsubscribe(int uID, int followID){
        if (relationship.containsKey(uID) == false) return;
        relationship.get(uID).remove(followID);
    }
    void publish (int uID, int pID){
        if (post.containsKey(uID) == false) post.put(uID, new LinkedList<Integer>());
        post.get(uID).addFirst(pID);
    }
    ArrayList<Integer> get(int uID, int limit){
        ArrayList<Integer> result = new ArrayList<Integer>();
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        if (post.containsKey(uID)){
            ListIterator<Integer> itr = post.get(uID).listIterator();
            pq.add(new Pair(itr.next(), itr));
        }
        for (int following : relationship.get(uID)  ) {
            if (post.containsKey(following)){
                ListIterator<Integer> itr = post.get(following).listIterator();
                pq.add(new Pair(itr.next(), itr));
            }
        }
        while(pq.isEmpty() == false && result.size() < 10){
            Pair temp = pq.poll();
            result.add(temp.pID);
            if (temp.itr.hasNext()){
                temp.pID = temp.itr.next();
                pq.add(temp);
            }
        }
        return result;

    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(
"/Users/tianhuizhu/Downloads/uber/code/JikeDS/top100/src/_4DP/twitter_Input"));
        Twitter twitter = new Twitter();
        int uID = in.nextInt();
        while(uID != -1){
            String op = in.next();
            switch (op){
                case "Publish":
                    twitter.publish(uID, in.nextInt());
                    break;
                case "Subscribe":
                    twitter.subscribe(uID, in.nextInt());
                    break;
                case "Unsubscribe":
                    twitter.unsubscribe(uID, in.nextInt());
                    break;
                case "Get":
                    twitter.get(uID, 10).forEach(pID -> System.out.print(pID + " "));
                    System.out.println();
                    break;
            }
            uID = in.nextInt();
        }
        in.close();


    }

}
