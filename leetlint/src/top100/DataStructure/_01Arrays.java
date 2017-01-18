package top100.DataStructure;

/**
 * Created by tzh on 1/18/17.
 */
public class _01Arrays {
    public static void main(String[] args) {
        int[] numArray = new int[10];
        for (int i = 0; i < 10; ++i) {
            numArray[i] = i;
        }
        for (int i = 0; i < 10; ++i) {
            System.out.print(numArray[i] + " ");
        }
        System.out.println("");
    }
}
