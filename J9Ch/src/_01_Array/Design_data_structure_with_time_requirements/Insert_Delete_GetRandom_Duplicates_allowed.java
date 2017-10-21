package _01_Array.Design_data_structure_with_time_requirements;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
/*
LeetCode – Insert Delete GetRandom O(1) – Duplicates allowed (Java)

Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.

insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom(): Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
Java Solution

This problem is similar to Insert Delete GetRandom O(1).
We can use two maps. One tracks the index of the element,
so that we can quickly insert and remove. The other maps
tracks the order of each inserted element, so that we can
randomly access any element in time O(1).
 */
public class Insert_Delete_GetRandom_Duplicates_allowed {
    public class RandomizedCollection {
        HashMap<Integer, HashSet<Integer>> map1;
        HashMap<Integer, Integer> map2;
        Random r;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            map1 = new HashMap<Integer, HashSet<Integer>>();
            map2 = new HashMap<Integer, Integer>();
            r = new Random();
        }

        /** Inserts a value to the collection. Returns true if the
         * collection did not already contain the specified element. */
        public boolean insert(int val) {
            //add to map2
            int size2 = map2.size();
            map2.put(size2+1, val);

            if(map1.containsKey(val)){
                map1.get(val).add(size2+1);
                return false;
            }else{
                HashSet<Integer> set = new HashSet<Integer>();
                set.add(size2+1);
                map1.put(val, set);
                return true;
            }
        }

        /** Removes a value from the collection. Returns true if
         * the collection contained the specified element. */
        public boolean remove(int val) {
            if(map1.containsKey(val)){
                HashSet<Integer> set = map1.get(val);
                int toRemove = set.iterator().next();


                //remove from set of map1
                set.remove(toRemove);

                if(set.size()==0){
                    map1.remove(val);
                }

                if(toRemove == map2.size()){
                    map2.remove(toRemove);
                    return true;
                }

                int size2 = map2.size();
                int key = map2.get(size2);

                HashSet<Integer> setChange = map1.get(key);
                setChange.remove(size2);
                setChange.add(toRemove);



                map2.remove(size2);
                map2.remove(toRemove);

                map2.put(toRemove, key);

                return true;
            }

            return false;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            if(map1.size()==0)
                return -1;

            if(map2.size()==1){
                return map2.get(1);
            }
            // nextInt() returns a random number in [0, n).
            return map2.get(r.nextInt(map2.size())+1);
        }
    }


////////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////////





}
