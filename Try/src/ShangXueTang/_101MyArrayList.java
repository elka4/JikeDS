package ShangXueTang;

import java.util.*;

/**
 * Created by tzh on 1/19/17.
 * implement an arraylist by myself
 */
public class _101MyArrayList {
    private Object[] elementData;

    private int size;

    public _101MyArrayList() {
        this(10);
    }

    public _101MyArrayList(int initialCapacity) {
        if(initialCapacity < 0) {
            try{
                throw new Exception();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        elementData = new Object[initialCapacity];
    }

    public void add(Object obj) {
        //enlarge the array
        ensureCapacity();
        elementData[size] = obj;
        size++;
    }

    public int size() {
        return  size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public Object get(int index){

        return elementData[index];
    }

    private void rangeCheck(int index){
        if(index < 0 || index >= size) {
            try{
                throw new Exception();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void remove(int index) {
        rangeCheck(index);
        int numMoved = size - index - 1;
        if (numMoved > 0){
            System.arraycopy(elementData,index + 1,
                    elementData, index,numMoved);
        }
        elementData[--size] = null;

    }

    public void remove(Object obj) {
        for(int i = 0; i < size; i++) {
            if(get(i).equals(obj)){
                remove(i);
            }
        }
    }

    public Object set(int index, Object obj){
        rangeCheck(index);
        Object oldOjb = elementData[index];
         elementData[index] = obj;
         return oldOjb;

    }

    public void ensureCapacity() {
        if (size == elementData.length){
            Object[] newArray = new Object[size * 2 + 1];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            /*for (int i = 0; i < elementData.length; i++) {
                newArray[i] = elementData[i];
            }*/
            elementData = newArray;
        }

    }
    public void add(int index, Object obj){
        rangeCheck(index);

        //Internal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = obj;
        size++;
    }


    public static  void main(String[] args) {
        //List list = new ArrayList<>();
        _101MyArrayList list = new _101MyArrayList();
        list.add("333");
        list.add("444");
        list.add("5");
        list.add("333");
        list.add("333");
        list.add("333");
        System.out.println(list.size());
        System.out.println(list.get(10));



    }


}
