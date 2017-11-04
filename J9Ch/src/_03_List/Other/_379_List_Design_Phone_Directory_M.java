package _03_List.Other;
import java.util.*;


//  379. Design Phone Directory

//  https://leetcode.com/problems/design-phone-directory/description/
//
public class _379_List_Design_Phone_Directory_M {
//        Java AC solution using queue and set
    class PhoneDirectory1{

        Set<Integer> used = new HashSet<Integer>();
        Queue<Integer> available = new LinkedList<Integer>();
        int max;
        public PhoneDirectory1(int maxNumbers) {
            max = maxNumbers;
            for (int i = 0; i < maxNumbers; i++) {
                available.offer(i);
            }
        }

        public int get() {
            Integer ret = available.poll();
            if (ret == null) {
                return -1;
            }
            used.add(ret);
            return ret;
        }

        public boolean check(int number) {
            if (number >= max || number < 0) {
                return false;
            }
            return !used.contains(number);
        }

        public void release(int number) {
            if (used.remove(number)) {
                available.offer(number);
            }
        }
    }
/////////////////////////////////////////////////////////////////////////////////////

/*    Java AC solution with Bitset and efficient get() + comments

18
    johnyrufus16
    Reputation:  157
    The idea is to use java's bitset and use smallestFreeIndex/max to keep track of the limit.
    Also, by keeping track of the updated smallestFreeIndex all the time, the run time of get()
    is spared from scanning the entire bitset every time.*/

    public class PhoneDirectory2 {

        BitSet bitset;
        int max; // max limit allowed
        int smallestFreeIndex; // current smallest index of the free bit

        public PhoneDirectory2(int maxNumbers) {
            this.bitset = new BitSet(maxNumbers);
            this.max = maxNumbers;
        }

        public int get() {
            // handle bitset fully allocated
            if(smallestFreeIndex == max) {
                return -1;
            }
            int num = smallestFreeIndex;
            bitset.set(smallestFreeIndex);
            //Only scan for the next free bit, from the previously known smallest free index
            smallestFreeIndex = bitset.nextClearBit(smallestFreeIndex);
            return num;
        }

        public boolean check(int number) {
            return bitset.get(number) == false;
        }

        public void release(int number) {
            //handle release of unallocated ones
            if(bitset.get(number) == false)
                return;
            bitset.clear(number);
            if(number < smallestFreeIndex) {
                smallestFreeIndex = number;
            }
        }
    }
/////////////////////////////////////////////////////////////////////////////////////
/*Java AC solution using LinkedHashSet - clear code and easy to understand

5
    happygirl
    Reputation:  17
    I first used HashSet as the supported data structure - as of now 8/3/2016 10:43AM(PST) the test cases couldn't be passed due to TLE.

    Tried using LinkedHashSet and got AC in 571ms. According to other answers in the thread, bitset is also a good candidate to use as base structure.*/
    public class PhoneDirectory3 {
                Set<Integer> set;
        /** Initialize your data structure here
         @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
        public PhoneDirectory3(int maxNumbers) {
            set = new LinkedHashSet<>();

            for(int i=0; i<maxNumbers; i++){
                set.add(i);
            }
        }

        /** Provide a number which is not assigned to anyone.
         @return - Return an available number. Return -1 if none is available. */
        public int get() {
            Iterator iter = set.iterator();

            if(!set.isEmpty()){
                int val = (int) iter.next();
                set.remove(val);
                return val;
            }
            return -1;
        }

        /** Check if a number is available or not. */
        public boolean check(int number) {
            return set.contains(number);
        }

        /** Recycle or release a number. */
        public void release(int number) {
            set.add(number);
        }

    }

/////////////////////////////////////////////////////////////////////////////////////
/*Java two arrays solution 320 ms, O(1) for each operation.

            2
    yubad2000
    Reputation:  661
    The key idea is to cache and reuse the released numbers.
    Using a boolean array to indicate which numbers are used instead of using Set.
    Another array is served as a "stack" to cache the released numbers.
    The logic of my design is to give out all the numbers during the first round and then "recycle" from the stack afterward.*/

    public class PhoneDirectory4 {
        /** Initialize your data structure here
         @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
        int max;
        int size;
        int cache_idx;
        int[] cache;
        boolean[] used;
        int count;
        public PhoneDirectory4(int maxNumbers) {
            max = maxNumbers;
            size = 0;
            count = 0;
            used = new boolean[max];
            cache = new int[max];
            cache_idx = -1;
        }

        /** Provide a number which is not assigned to anyone.
         @return - Return an available number. Return -1 if none is available. */
        public int get() {
            int ret=-1;
            if ( count >= max ) return ret;
            if (size < max) {
                ret = size++;
            } else {
                ret = cache[cache_idx--];
            }
            used[ret]=true;
            count++;
            return ret;
        }

        /** Check if a number is available or not. */
        public boolean check(int number) {
            return ! used[number];
        }

        /** Recycle or release a number. */
        public void release(int number) {
            if (number < max && ! check(number) ){
                cache[++cache_idx]=number;
                used[number] = false;
                count--;
            }
        }
    }

/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////

}
/*
Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
Example:

// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);
 */

/*

 */